package com.wycody.cs2d.rev;

import com.jagex.core.constants.SerialEnum;
import java.util.HashMap;
import java.util.Map;

import com.jagex.game.runetek5.config.vartype.VarTypeList;
import com.jagex.game.runetek5.config.vartype.bit.VarBitTypeList;
import com.jagex.game.runetek5.config.vartype.constants.BaseVarType;
import com.jagex.game.runetek5.config.vartype.constants.VarDomainType;
import com.jagex.game.runetek5.config.vartype.general.VarBasicTypeListClient;
import com.jagex.game.runetek5.config.vartype.player.VarPlayerTypeList;
import com.wycody.cs2d.Context;
import com.wycody.cs2d.script.CS2Field;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.impl.Push;
import com.wycody.cs2d.script.inst.swtch.CaseNode;
import com.wycody.cs2d.script.inst.swtch.SwitchBlock;
import java.util.ArrayList;

import net.openrs.cache.Cache;
import net.openrs.io.WrappedByteBuffer;

public abstract class RS3Revision extends RS2Revision {

	public VarBitTypeList varBitTypeList;
	public VarPlayerTypeList varpTypeList;
	public VarBasicTypeListClient varcListClient;
	public VarBasicTypeListClient varclanListClient;
	public VarBasicTypeListClient varClanSettingsListClient;
	public VarBasicTypeListClient varnListClient;
	public VarBasicTypeListClient vargroupListClient;
	public VarBasicTypeListClient varWorldListClient;
	public VarBasicTypeListClient varRegionListClient;
	public VarBasicTypeListClient varObjListClient;
	public VarBasicTypeListClient varExchangeListClient;
	public VarBasicTypeListClient varGlobalListClient;
	public Map<VarDomainType, VarTypeList> varTypeDomain = new HashMap<>();
    
    protected int pushIntId = 109;
    protected int pushLongId = 563;

	public RS3Revision(Cache cache) {
		varcListClient = new VarBasicTypeListClient(cache, VarDomainType.CLIENT);
		varclanListClient = new VarBasicTypeListClient(cache, VarDomainType.CLAN);
		varClanSettingsListClient = new VarBasicTypeListClient(cache, VarDomainType.CLAN_SETTINGS);
		vargroupListClient = new VarBasicTypeListClient(cache, VarDomainType.GROUP);
		varnListClient = new VarBasicTypeListClient(cache, VarDomainType.NPC);
		varRegionListClient = new VarBasicTypeListClient(cache, VarDomainType.REGION);
		varWorldListClient = new VarBasicTypeListClient(cache, VarDomainType.WORLD);
		varObjListClient = new VarBasicTypeListClient(cache, VarDomainType.OBJECT);
		varExchangeListClient = new VarBasicTypeListClient(cache, VarDomainType.EXCHANGE);
		varGlobalListClient = new VarBasicTypeListClient(cache, VarDomainType.GLOBAL);
		varBitTypeList = new VarBitTypeList(cache);
		varpTypeList = new VarPlayerTypeList(cache);
		varTypeDomain.put(varcListClient.type, varcListClient);
		varTypeDomain.put(varnListClient.type, varnListClient);
		varTypeDomain.put(varclanListClient.type, varclanListClient);
		varTypeDomain.put(varClanSettingsListClient.type, varClanSettingsListClient);
		varTypeDomain.put(vargroupListClient.type, vargroupListClient);
		varTypeDomain.put(VarDomainType.PLAYER, varpTypeList);
	}
    
	@Override
	public Instruction decode(CS2Script script, Context context, WrappedByteBuffer buffer, int id, int address) {
        int instKey = registeredInstructions.containsKey(id) ? id : -1;
        Instruction instr = registeredInstructions.get(instKey).get();
        instr.setBaseData(id, address);

        if (InstructionType.PUSH_VAR == instr.getType() || instr.getType() == InstructionType.STORE_VAR) {
            int var_domain_id= buffer.getUnsignedByte();
            int var_type = buffer.getUnsignedShort();
            int operand = buffer.getUnsignedByte();


            System.out.flush();
            System.err.flush();
/*
            System.err.println(" ------ CRITICAL DEBUG INFO ------ ");
            System.err.println("VAR_DOMAIN_ID = " + var_domain_id);
            System.err.println("VAR_TYPE      = " + var_type);
            System.err.println("OPERAND       = " + operand);
*/
            VarDomainType varDomainType = SerialEnum.forID(VarDomainType.values(), var_domain_id);
            instr.setObjectOperand(varTypeDomain.get(varDomainType).list(var_type));
            instr.setIntegerOperand(operand);

/*
            Object obj = varTypeDomain.get(varDomainType).list(var_type);
            System.err.println("VAR_DOMAIN_TYPE = " + varDomainType);
            System.err.println("OBJECT_OP      = " + obj);
            System.err.println("POSITION       = " + address);
            System.err.println(" ------ CRITICAL DEBUG INFO ------ ");
*/
        } else if (instr.getType() == InstructionType.PUSH_OBJ) {
            int idx = buffer.getUnsignedByte();
            BaseVarType baseVarType = SerialEnum.forID(BaseVarType.values(), idx);
            switch (baseVarType) {
                case INTEGER:
                    instr = Push.PUSH_INT.get();
                    instr.setBaseData(pushIntId,address);
                    instr.setIntegerOperand(buffer.getInt());
                    break;
                case STRING:
                    instr.setObjectOperand(buffer.getString().intern());
                    break;
                case LONG:
                    instr = Push.PUSH_LONG.get();
                    instr.setBaseData(pushLongId,address);
                    instr.setLongOperand(buffer.getLong());
                    break;
                default:
                    throw new RuntimeException("ScriptType.decodeOperand(buffer, " + id + ", " + instr + "): Invalid BaseVarType " + baseVarType);
            }
        } else if (InstructionType.PUSH_VAR_BIT == instr.getType() || InstructionType.STORE_VAR_BIT == instr.getType()) {
            int i_3_ = buffer.getUnsignedShort();
            instr.setObjectOperand(varBitTypeList.list(i_3_));
            instr.setIntegerOperand((int) buffer.getUnsignedByte());
        } else {
            if (this.isLarge(id)) {//instr.getType().isLarge()
                instr.setIntegerOperand(buffer.getInt());
            } else {
                instr.setIntegerOperand((int) buffer.getUnsignedByte());
            }
        }

        instr.setScript(script);
        return instr;
	}
    
	@Override
	public CS2Script disassemble(Context context, WrappedByteBuffer buffer) {
		// Construct an empty CS2Script
		CS2Script script = new CS2Script();

		// Read the header offset
		buffer.setPosition(buffer.length() - 2);
		int headerLength = buffer.getUnsignedShort();
		int headerOffset = buffer.length() - 18 - headerLength;
		buffer.setPosition(headerOffset);
		int instructionCount = buffer.getInt();
		
		// Read the fields lengths and assign them to the script
		script.setIntegerFields(new CS2Field[buffer.getUnsignedShort()]);
		script.setObjectFields(new CS2Field[buffer.getUnsignedShort()]);
		script.setLongFields(new CS2Field[buffer.getUnsignedShort()]);

		// Read the parameters lengths and assign them to the script
		script.setIntegerParameters(new CS2Field[buffer.getUnsignedShort()]);
		script.setObjectParameters(new CS2Field[buffer.getUnsignedShort()]);
		script.setLongParameters(new CS2Field[buffer.getUnsignedShort()]);

		// Read the switch blocks and assign them to the script
		SwitchBlock[] blocks = new SwitchBlock[buffer.getUnsignedByte()];
		for (int blockIndex = 0; blockIndex < blocks.length; blockIndex++) {
			int casesCount = buffer.getUnsignedShort();
			SwitchBlock block = blocks[blockIndex] = new SwitchBlock(casesCount);
			// Loop through cases and store them
			CaseNode previousCase = null;

			for (int caseIndex = 0; caseIndex < casesCount; caseIndex++) {
				CaseNode currentCase = new CaseNode(buffer.getInt(), buffer.getInt());
				if (previousCase != null) {
					previousCase.setNext(currentCase);
				}
				currentCase.setPrevious(previousCase);
				block.addCase(currentCase);
			}

		}
        script.setSwitchBlocks(blocks);
		buffer.setPosition(0);

		buffer.getNullString();// Not used at all but appear to be event? or
								// name?
		

		// Read the instructions and assign them to the script
		int instructionsEnd = buffer.length() - 18 - headerLength;
		int instrAddress = 0;
		ArrayList<Instruction> tempInstructions = new ArrayList<>();
		while (buffer.getPosition() < instructionsEnd) {
			tempInstructions.add(context.getInstructionDecoder().decode(script, context, buffer, buffer.getUnsignedShort(), instrAddress));
			instrAddress++;
		}
		script.setInstructions(tempInstructions.toArray(new Instruction[tempInstructions.size()]));
		script.initializeFields();
		return script;
	}

}
