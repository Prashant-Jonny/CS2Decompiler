package com.jagex.game.runetek5.config.vartype.bit;

import net.openrs.io.WrappedByteBuffer;

import com.jagex.core.constants.SerialEnum;
import com.jagex.game.runetek5.config.ConfigType;
import com.jagex.game.runetek5.config.vartype.VarType;
import com.jagex.game.runetek5.config.vartype.constants.VarDomainType;

public class VarBitType extends ConfigType {
	public VarType baseVar;
	public int id;
    public int startBit;
    public int endBit;
    public String debugName;
    VarBitTypeList list;
    public VarDomainType baseVarType;
	public int baseVarKey;
	public int debugNameHash32;
	
    static int[] masklookup = new int[32];
    
    static {
		int mask = 2;
		for (int bit = 0; bit < 32; bit++) {
		    masklookup[bit] = mask - 1;
		    mask += mask;
		}
    }
    
    public VarBitType() {
    	
    }
    
    public VarBitType (int id) {
		this.id = id;
	}
    
    public int getVarbitValue(int baseValue) {
		int mask = masklookup[endBit - startBit];
		return baseValue >> startBit & mask;
    }
    
    public int setVarbitValue(int baseValue, int newValue) throws VarBitOverflowException {
		int mask = masklookup[endBit - startBit];
		if (newValue < 0 || newValue > mask) {
		    throw new VarBitOverflowException(debugName, newValue, mask);
		}
		mask <<= startBit;
		return baseValue & (mask ^ 0xffffffff) | newValue << startBit & mask;
    }
    
    public void decode (WrappedByteBuffer buffer, int opcode) {
    	VarBitTypeEncodingKey key = SerialEnum.forID(VarBitTypeEncodingKey.values(), opcode);
    	switch (key) {
		case BASEVAR:
			int domainID = buffer.getUnsignedByte();
			baseVarType = SerialEnum.forID(VarDomainType.values(), domainID);
			if (baseVarType == null) {
		    	throw new IllegalStateException("Unknown domain ID loading VarType definition: "+domainID);
		    }
			baseVarKey = buffer.getSmartInt();
			break;
    	case BITS:
    		startBit = buffer.getUnsignedByte();
    		endBit = buffer.getUnsignedByte();
    		break;
    	case VARBITNAME_HASH32:
    		debugNameHash32 = buffer.getInt();
    		break;
    	case DEBUGNAME:
    		debugName = buffer.getCheckedString();
    		break;
    	default:
    		break;
    	}
    }
    
    @Override
    public String toString () {
		return id + "{baseVar=" + baseVarType + ", start=" + startBit + ", end=" + endBit + "}";
    }

	@Override
	public void postDecode() {
		// TODO Auto-generated method stub
		
	} 
}
