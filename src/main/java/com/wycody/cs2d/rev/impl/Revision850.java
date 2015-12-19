package com.wycody.cs2d.rev.impl;

import java.util.ArrayList;
import java.util.HashSet;

import com.jagex.core.constants.SerialEnum;
import com.jagex.game.runetek5.config.vartype.constants.BaseVarType;
import com.jagex.game.runetek5.config.vartype.constants.VarDomainType;
import com.wycody.cs2d.Context;
import com.wycody.cs2d.rev.RS3Revision;
import com.wycody.cs2d.script.CS2Field;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.impl.ActiveWidget;
import com.wycody.cs2d.script.inst.impl.Branch;
import com.wycody.cs2d.script.inst.impl.ClientGeneral;
import com.wycody.cs2d.script.inst.impl.Config;
import com.wycody.cs2d.script.inst.impl.Math;
import com.wycody.cs2d.script.inst.impl.Pop;
import com.wycody.cs2d.script.inst.impl.Push;
import com.wycody.cs2d.script.inst.impl.RS3Var;
import com.wycody.cs2d.script.inst.impl.ScriptEnum;
import com.wycody.cs2d.script.inst.impl.Store;
import com.wycody.cs2d.script.inst.impl.Text;
import com.wycody.cs2d.script.inst.impl.Unsorted;
import com.wycody.cs2d.script.inst.impl.Widget;
import com.wycody.cs2d.script.inst.impl.WidgetContainer;
import com.wycody.cs2d.script.inst.swtch.CaseNode;
import com.wycody.cs2d.script.inst.swtch.SwitchBlock;

import net.openrs.cache.Cache;
import net.openrs.io.WrappedByteBuffer;

public class Revision850 extends RS3Revision {
	
	
	public Revision850 (Cache cache) {
		super(cache);
	}

	@Override
	public void registerInstructions() {
        registerInstruction(-1, Unsorted.MISSING);

        registerInstruction(945, ActiveWidget.SETPOS);
        registerInstruction(932, ActiveWidget.SETSIZE);
        registerInstruction(404, ActiveWidget.SETHIDE);
        registerInstruction(99, ActiveWidget.SETASPECTRATIO);
        registerInstruction(259, ActiveWidget.SETSOLID);
        registerInstruction(922, ActiveWidget.SETSCROLLPOS);
        registerInstruction(64, ActiveWidget.SETCOLOUR);
        registerInstruction(577, ActiveWidget.SETFILLED);
        registerInstruction(457, ActiveWidget.SETALPHA);
        registerInstruction(832, ActiveWidget.SETLINEWEIGHT);
        registerInstruction(488, ActiveWidget.SETGRAPHIC);
        registerInstruction(318, ActiveWidget.SETGRAPHICROTATION);
        registerInstruction(694, ActiveWidget.SETGRAPHICREPEAT);
        registerInstruction(1178, ActiveWidget.SETMODEL);
        registerInstruction(965, ActiveWidget.SETMODELCONSTRAINTS);
        registerInstruction(179, ActiveWidget.SETANIMATION);
        registerInstruction(683, ActiveWidget.SETTEXT);
        registerInstruction(656, ActiveWidget.SETFONT);
        registerInstruction(585, ActiveWidget.SETTEXTALIGN);
        registerInstruction(615, ActiveWidget.SETSHADED);
        registerInstruction(818, ActiveWidget.BIND_MOUSEPRESS_HANDLER);
        registerInstruction(590, ActiveWidget.BIND_MOUSEDRAGPAST_HANDLER);
        registerInstruction(1011, ActiveWidget.BIND_MOUSERELEASE_HANDLER);
        registerInstruction(927, ActiveWidget.BIND_MOUSEOVER_HANDLER);
        registerInstruction(323, ActiveWidget.BIND_MOUSEOUT_HANDLER);
        registerInstruction(764, ActiveWidget.BIND_DESELECT_HANDLER);
        registerInstruction(1117, ActiveWidget.BIND_VARP_HANDLER);
        registerInstruction(197, ActiveWidget.BIND_UPDATE_HANDLER);
        registerInstruction(1179, ActiveWidget.BIND_OPTION_HANDLER);
        registerInstruction(345, ActiveWidget.BIND_DRAG_HANDLER);
        registerInstruction(815, ActiveWidget.BIND_MOUSEDRAG_HANDLER);
        registerInstruction(782, ActiveWidget.BIND_MOUSEMOVE_HANDLER);
        registerInstruction(382, ActiveWidget.BIND_INV_HANDLER);
        registerInstruction(359, ActiveWidget.BIND_STAT_HANDLER);
        registerInstruction(71, ActiveWidget.BIND_SELECT_HANDLER);
        registerInstruction(785, ActiveWidget.BIND_MOUSESCROLL_HANDLER);
        registerInstruction(319, ActiveWidget.BIND_CHAT_HANDLER);
        registerInstruction(1066, ActiveWidget.BIND_KEYPRESS_HANDLER);
        registerInstruction(1083, ActiveWidget.BIND_FRIENDLIST_HANDLER);
        registerInstruction(1050, ActiveWidget.BIND_FRIENDCHAT_HANDLER);
        registerInstruction(601, ActiveWidget.BIND_STATUS_HANDLER);
        registerInstruction(172, ActiveWidget.BIND_ATTACHMENT_HANDLER);
        registerInstruction(714, ActiveWidget.BIND_EXCHANGE_HANDLER);
        registerInstruction(1039, ActiveWidget.BIND_RESIZE_HANDLER);
        registerInstruction(159, ActiveWidget.BIND_VARC_HANDLER);
        registerInstruction(613, ActiveWidget.BIND_VARCSTR_HANDLER);
        registerInstruction(461, ActiveWidget.BIND_USE_HANDLER);
        registerInstruction(760, ActiveWidget.BIND_CLANSETTINGS_HANDLER);
        registerInstruction(296, ActiveWidget.BIND_CLANCHANNEL_HANDLER);
        registerInstruction(650, ActiveWidget.BIND_VARCLAN_HANDLER);
        registerInstruction(939, ActiveWidget.BIND_GROUPCHANNEL_HANDLER);
        registerInstruction(792, ActiveWidget.BIND_VARGROUP_HANDLER);
        registerInstruction(322, ActiveWidget.CLEAR_HANDLERS);
        registerInstruction(1046, Widget.SETFILLED);
        registerInstruction(27,Widget.SETALPHA);
        registerInstruction(580,Widget.SETLINEWEIGHT);
        registerInstruction(73,Widget.SETGRAPHIC);
        registerInstruction(610,  Widget.SETTEXT);
        registerInstruction(1043, Widget.BIND_MOUSEPRESS_HANDLER);
        registerInstruction(985, Widget.BIND_MOUSEDRAGPAST_HANDLER);
        registerInstruction(790, Widget.BIND_MOUSERELEASE_HANDLER);
        registerInstruction(250, Widget.BIND_MOUSEOVER_HANDLER);
        registerInstruction(663, Widget.BIND_MOUSELEAVE_HANDLER);
        registerInstruction(879, Widget.BIND_DESELECT_HANDLER);
        registerInstruction(912, Widget.BIND_VARP_HANDLER);
        registerInstruction(880, Widget.BIND_UPDATE_HANDLER);
        registerInstruction(447, Widget.BIND_OPTION_HANDLER);
        registerInstruction(31, Widget.BIND_DRAG_HANDLER);
        registerInstruction(252, Widget.BIND_MOUSEDRAG_HANDLER);
        registerInstruction(373, Widget.BIND_MOUSEREPEAT_HANDLER);
        registerInstruction(385, Widget.BIND_INV_HANDLER);
        registerInstruction(1122, Widget.BIND_STAT_HANDLER);
        registerInstruction(1086, Widget.BIND_SELECT_HANDLER);
        registerInstruction(314, Widget.BIND_MOUSESCROLL_HANDLER);
        registerInstruction(556, Widget.BIND_CHAT_HANDLER);
        registerInstruction(942, Widget.BIND_KEYPRESS_HANDLER);
        registerInstruction(55, Widget.BIND_FRIENDLIST_HANDLER);
        registerInstruction(293, Widget.BIND_FRIENDCHAT_HANDLER);
        registerInstruction(528, Widget.BIND_STATUS_HANDLER);
        registerInstruction(204, Widget.BIND_ATTACHMENT_HANDLER);
        registerInstruction(210, Widget.BIND_EXCHANGE_HANDLER);
        registerInstruction(681, Widget.BIND_RESIZE_HANDLER);
        registerInstruction(398, Widget.BIND_VARC_HANDLER);
        registerInstruction(300, Widget.BIND_VARCSTR_HANDLER);
        registerInstruction(303, Widget.BIND_USE_HANDLER);
        registerInstruction(310, Widget.BIND_CLANSETTINGS_HANDLER);
        registerInstruction(668, Widget.BIND_CLANCHANNEL_HANDLER);
        registerInstruction(562, Widget.BIND_VARCLAN_HANDLER);
        registerInstruction(888, Widget.BIND_GROUPCHANNEL_HANDLER);
        registerInstruction(419, Widget.BIND_VARGROUP_HANDLER);
        registerInstruction(11, Widget.CLEAR_HANDLERS);
        registerInstruction(278,  Unsorted.RETURN);
        registerInstruction(963, Unsorted.PRINT_TO_CONSOLE);
        registerInstruction(1068, WidgetContainer.SETACTIVE);
        registerInstruction(104, Widget.SETACTIVE);
        registerInstruction(1030, ClientGeneral.PUSH_CLIENT_CYCLE);
        registerInstruction(966, ClientGeneral.PUSH_INV_SLOTOBJ);
        registerInstruction(1088, ClientGeneral.PUSH_INV_SLOTCOUNT);
        registerInstruction(115, ClientGeneral.PUSH_INV_OBJCOUNT);
        registerInstruction(443, ClientGeneral.PUSH_INV_CATCOUNT);
        registerInstruction(735, ClientGeneral.PUSH_INV_CAPACITY);
        registerInstruction(65, ClientGeneral.PUSH_INV_STOCKCOUNT);
        registerInstruction(1175, ClientGeneral.PUSH_STAT_LEVEL);
        registerInstruction(146, ClientGeneral.PUSH_STAT_BASE);
        registerInstruction(276, ClientGeneral.PUSH_STAT_EXPERIENCE);
        registerInstruction(475,  Math.SUM);
        registerInstruction(856,  Math.SUBTRACT);
        registerInstruction(1219, Math.MULTIPLY);
        registerInstruction(987,  Math.DIVIDE);
        registerInstruction(1189, Math.RANDOM);
        registerInstruction(1186, Math.MODULO);
        registerInstruction(313, Math.BITWISE_OR);
        registerInstruction(952,  Math.MIN);
        registerInstruction(241,  Math.MAX);
        registerInstruction(977,  Math.FRAC_MULTIPLY);
        registerInstruction(560,  Text.INT_TO_STR);
        registerInstruction(986,  Text.STRLEN);
        registerInstruction(4, Config.PUSH_WORLDMAP_PARAM);
        registerInstruction(282, Config.PUSH_STRUCT_PARAM);
        registerInstruction(338, Config.PUSH_NPC_PARAM);
        registerInstruction(408, ScriptEnum.PUSH_VALUE_STR);
        registerInstruction(608, ScriptEnum.PUSH_VALUE);
        registerInstruction(285, ScriptEnum.PUSH_CONTAINS_INT);
        registerInstruction(237, ScriptEnum.PUSH_CONTAINS_STR);
        registerInstruction(51, ScriptEnum.PUSH_SIZE);
        registerInstruction(1082, ScriptEnum.PUSH_INTVALKEYS_COUNT);
        registerInstruction(621, ScriptEnum.PUSH_STRVALKEYS_COUNT);
        registerInstruction(789, ScriptEnum.PUSH_INTVALKEY);
        registerInstruction(642, ScriptEnum.PUSH_STRVALKEY);
        

        
	}

	@Override
	public void registerLarges() {
		//NOT redundant - DO NOT FUCKING REMOVE
        registerLarge(109);
        registerLarge(964);
        registerLarge(537);
        registerLarge(403);
        registerLarge(687);
        registerLarge(226);
        registerLarge(375);
        registerLarge(1064);
        registerLarge(569);
        registerLarge(281);
        registerLarge(835);
        registerLarge(206);
        registerLarge(131);
        registerLarge(17);
        registerLarge(165);
        registerLarge(244);
        registerLarge(641);
        registerLarge(26);
        registerLarge(444);
        registerLarge(702);
        registerLarge(48);
        registerLarge(933);
        registerLarge(342);
        registerLarge(563);
        registerLarge(84);
        registerLarge(483);
        registerLarge(685);
        registerLarge(0);
        registerLarge(1131);
        registerLarge(324);
        registerLarge(544);
        registerLarge(445);
        registerLarge(129);
        registerLarge(191);
        registerLarge(809);
        registerLarge(979);
        registerLarge(166);
        registerLarge(865);
	}
	
    //Temp.
    private HashSet<Integer> unids = new HashSet<>();
    /*
    opcode: 17
Unknown opcode: 342
    */
	@Override
	public Instruction decode(CS2Script script, Context context, WrappedByteBuffer buffer, int id, int address) {
        if(!this.isKnowOpcode(id) && !unids.contains(id)){
            unids.add(id);
            if(print_unknowns)
                System.err.println("Unknown opcode: " + id);
        }
        
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
                    instr.setBaseData(109,address);
                    instr.setIntegerOperand(buffer.getInt());
                    break;
                case STRING:
                    instr.setObjectOperand(buffer.getString().intern());
                    break;
                case LONG:
                    instr = Push.PUSH_LONG.get();
                    instr.setBaseData(563,address);
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
        if(debug_instructions)
            System.out.println(id + "\t" + instr);
        return instr;
	}

    public static boolean debug_instructions = false, print_unknowns = false;

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

	@Override
	public byte[] assemble(Context context, CS2Script script) {
		// TODO Auto-generated method stub
		return null;
	}

}
