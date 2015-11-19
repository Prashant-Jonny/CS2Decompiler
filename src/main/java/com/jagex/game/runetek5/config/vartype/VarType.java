package com.jagex.game.runetek5.config.vartype;

import net.openrs.io.WrappedByteBuffer;

import com.jagex.core.constants.SerialEnum;
import com.jagex.game.runetek5.config.ConfigType;
import com.jagex.game.runetek5.config.vartype.constants.ScriptVarType;
import com.jagex.game.runetek5.config.vartype.constants.VarDomainType;
import com.jagex.game.runetek5.config.vartype.constants.VarLifetime;
import com.jagex.game.runetek5.config.vartype.constants.VarTransmitLevel;


public class VarType extends ConfigType {
	public VarLifetime lifeTime = VarLifetime.TEMPORARY;
	public VarTransmitLevel transmitLevel = VarTransmitLevel.NEVER;
	private int id;
	public ScriptVarType dataType;
	public String debugName;
	public boolean isTemp = true;
	private VarDomainType domain;
	public int debugNameHash32;

	public VarType () {
	}
	
	public VarType (int id) {
		this.id = id;
	}
	
	
	protected void decode (WrappedByteBuffer buffer, int opcode) {
		VarTypeEncodingKey key = SerialEnum.forID(VarTypeEncodingKey.values(), opcode);
		if (key == null) {
			decode_inner(buffer, opcode);
		} else {
			switch (key) {
			case DEBUGNAME:
				debugName = buffer.getCheckedString();
				break;
	    	case VARNAME_HASH32:
	    		debugNameHash32 = buffer.getInt();
	    		break;
			case LIFETIME:
				lifeTime = SerialEnum.forID(VarLifetime.values(), buffer.getUnsignedByte());
				break;
			case TRANSMITLEVEL:
				transmitLevel = SerialEnum.forID(VarTransmitLevel.values(), buffer.getUnsignedByte());
			case TYPE:
				dataType = SerialEnum.forID(ScriptVarType.values(), buffer.getUnsignedByte());
				break;
			case NOT_TEMPORARY:
				isTemp = false;
				break;
			default:
				throw new IllegalStateException(key + "");	
			}
		}
		
	}
	
	protected void decode_inner (WrappedByteBuffer buffer, int opcode) {
		
	}
    
    @Override
    public String toString () {
		return new StringBuilder().append(id).toString();
    }
    
    public int getId() {
    	return id;
    }


	@Override
	public void postDecode() {
		// TODO Auto-generated method stub
		
	} 
	
	public VarDomainType getDomain() {
		return domain;
	}
	public void setDomainType(VarDomainType type) {
		this.domain = type;
	}
}
