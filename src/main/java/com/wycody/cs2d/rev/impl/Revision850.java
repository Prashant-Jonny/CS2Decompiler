package com.wycody.cs2d.rev.impl;

import java.util.ArrayList;

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
import com.wycody.cs2d.script.inst.impl.Pop;
import com.wycody.cs2d.script.inst.impl.Push;
import com.wycody.cs2d.script.inst.impl.RS3Var;
import com.wycody.cs2d.script.inst.impl.Store;
import com.wycody.cs2d.script.inst.impl.Text;
import com.wycody.cs2d.script.inst.impl.Math;
import com.wycody.cs2d.script.inst.impl.ScriptEnum;
import com.wycody.cs2d.script.inst.impl.Unsorted;
import com.wycody.cs2d.script.inst.impl.Widget;
import com.wycody.cs2d.script.inst.impl.WidgetContainer;
import com.wycody.cs2d.script.inst.swtch.CaseNode;
import com.wycody.cs2d.script.inst.swtch.SwitchBlock;

import java.util.HashSet;

import net.openrs.cache.Cache;
import net.openrs.io.WrappedByteBuffer;

public class Revision850 extends RS3Revision {
	
	
	public Revision850 (Cache cache) {
		super(cache);
	}

	@Override
	public void registerInstructions() {
        registerInstruction(-1, Unsorted.MISSING);

        // Push,Pop,Store
        registerInstruction(109,  Push.PUSH_INT);
        registerInstruction(964,  RS3Var.RS3_PUSH_VAR);
        registerInstruction(537,  RS3Var.RS3_STORE_VAR);
        registerInstruction(403,  RS3Var.RS3_PUSH_VARBIT);
        registerInstruction(687,  RS3Var.RS3_STORE_VARBIT);
        registerInstruction(226,  Push.PUSH_OBJECT);

        //Branching
        registerInstruction(375,  Branch.GOTO);
        registerInstruction(1064, Branch.INT_EQ);
        registerInstruction(569,  Branch.INT_NE);
        registerInstruction(281,  Branch.INT_LT);
        registerInstruction(835,  Branch.INT_GT);
        registerInstruction(206,  Branch.INT_LE);
        registerInstruction(131,  Branch.INT_GE);

        //Var
        registerInstruction(17,   Push.LOAD_INT);
        registerInstruction(165,  Store.STORE_INT);
        registerInstruction(244,  Push.LOAD_OBJ);
        registerInstruction(641,  Store.STORE_OBJ);
        registerInstruction(26,   Text.CONCAT);
        registerInstruction(286,  Pop.POP_INT);
        registerInstruction(122,  Pop.POP_OBJ);

        // TODO: 444, CALL_SCRIPT...SWITCH
        
        
        registerInstruction(342,  Branch.SWITCH);
        registerInstruction(563,  Push.PUSH_LONG);
        registerInstruction(84,   Pop.POP_LONG);
        registerInstruction(483,  Push.LOAD_LONG);
        registerInstruction(685,  Store.STORE_LONG);
        registerInstruction(0,    Branch.LONG_NE);
        registerInstruction(1131, Branch.LONG_EQ);
        
        
        registerInstruction(324, Branch.LONG_LT);
        registerInstruction(544, Branch.LONG_GT);
        registerInstruction(445, Branch.LONG_LE);
        registerInstruction(129, Branch.LONG_GE);
        
        /*
            3x ?
            TRUE_INT    166
            FALSE_INT   685
            CREATE_COMPONENT_CHILD
            1x ?
        */
       // registerInstruction(1123, WidgetContainer.CREATE);
       // registerInstruction(887, WidgetContainer.DELETE);
       // registerInstruction(936, WidgetContainer.CLEAR);
        
        /*
            11x ?
            SET_CURRENT_COMP_POSITION
            SET_CURRENT_COMP_SIZE
        */
       // registerInstruction(404, Widget.SHOW_SELECTED_COMPONENT);
        //Active component - position
        registerInstruction(945, ActiveWidget.SETPOS);
        registerInstruction(932, ActiveWidget.SETSIZE);
        registerInstruction(404, ActiveWidget.SETHIDE);
        registerInstruction(99, ActiveWidget.SETASPECTRATIO);
        registerInstruction(259, ActiveWidget.SETSOLID);
        
        //Active component - attribute setters
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
        //4x unknown
        registerInstruction(683, ActiveWidget.SETTEXT);
        registerInstruction(656, ActiveWidget.SETFONT);
        registerInstruction(585, ActiveWidget.SETTEXTALIGN);
        registerInstruction(615, ActiveWidget.SETSHADED);
        
        /*    SET_CUR_INTER_ASPECT_RATIO
            2x ?
            SET_CURRENT_COMP_COLOR
            SET_CURRENT_COMP_FILLED
            SET_CURRENT_COMP_ALPHA
            1x ?
            SET_CURRENT_COMP_SPRITE
            SET_CURRENT_COMP_SPRITE_ROTATION
            SET_CURRENT_COMP_SPRITE_REPEAT
            7x ?
            SET_CURRENT_COMP_TEXT
            SET_CURRENT_COMP_FONT
            SET_CURRENT_COMP_TEXT_ALLIGNMENT
            SET_CURRENT_COMP_SHADED
            34x ?
            SET_CURRENT_COMP_OPTION
            SET_CURRENT_COMP_DEFAULT_SLOT
            SET_CURRENT_COMP_CONTENT_TYPE
            2x ?
            SET_CURRENT_COMP_APPLY_TEXT
            SET_CURRENT_COMP_TARGET_OP
            2x ?
            SET_CURRENT_COMP_USE_ON_CURSOR(639)
            SET_CURRENT_COMP_OPTION_CURSOR(213)
            SET_CURRENT_COMP_DEFAULT_OPTION(294)
            SET_CURRENT_COMP_OP_CURSOR(568)
            9x ?*/
        
        
        //Active component - event binding
        registerInstruction(818, ActiveWidget.BIND_MOUSEPRESS_HANDLER);
        registerInstruction(590, ActiveWidget.BIND_MOUSEDRAGPAST_HANDLER);
        registerInstruction(1011, ActiveWidget.BIND_MOUSERELEASE_HANDLER);
        registerInstruction(927, ActiveWidget.BIND_MOUSEOVER_HANDLER);
        registerInstruction(323, ActiveWidget.BIND_MOUSEOUT_HANDLER);
        //1x unknown
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
        //4x unused
        registerInstruction(1083, ActiveWidget.BIND_FRIENDLIST_HANDLER);
        registerInstruction(1050, ActiveWidget.BIND_FRIENDCHAT_HANDLER);
        registerInstruction(601, ActiveWidget.BIND_STATUS_HANDLER);
        registerInstruction(172, ActiveWidget.BIND_ATTACHMENT_HANDLER);
        //1x unknown
        registerInstruction(714, ActiveWidget.BIND_EXCHANGE_HANDLER);
        //1x unknown
        registerInstruction(1039, ActiveWidget.BIND_RESIZE_HANDLER);
        registerInstruction(159, ActiveWidget.BIND_VARC_HANDLER);
        registerInstruction(613, ActiveWidget.BIND_VARCSTR_HANDLER);
        registerInstruction(461, ActiveWidget.BIND_USE_HANDLER);
        registerInstruction(760, ActiveWidget.BIND_CLANSETTINGS_HANDLER);
        registerInstruction(296, ActiveWidget.BIND_CLANCHANNEL_HANDLER);
        registerInstruction(650, ActiveWidget.BIND_VARCLAN_HANDLER);
        registerInstruction(939, ActiveWidget.BIND_GROUPCHANNEL_HANDLER);
        registerInstruction(792, ActiveWidget.BIND_VARGROUP_HANDLER);
        //1x unknown
        registerInstruction(322, ActiveWidget.CLEAR_HANDLERS);
           /* 2x ?
            SET_COMP_POSITION(6)
            SET_COMP_SIZE(126)
            TOGGLE_SHOW_INTERFACE_COMPONENT(579)
            SET_COMP_ASPECT_RATIO(284)
            SET_COMP_SOLID(376)
            SET_COMP_SCROLL_POSITION(174)
            SET_COMP_COLOR(409)
        */
        

        
        registerInstruction(1046, Widget.SETFILLED);
        registerInstruction(27,Widget.SETALPHA);
        registerInstruction(580,Widget.SETLINEWEIGHT);
        registerInstruction(73,Widget.SETGRAPHIC);
        
        /* 
            9x ?
            */
        registerInstruction(610,  Widget.SETTEXT);
/*            8x?
            SET_COMP_SCROLL
            1x ?
            SET_COMP_ZOOM
            10x ?
            SET_MEDIA_NPC_HEAD
            1x ?
            SET_MEDIA_NPC
            12x ?
            SET_COMP_OPTION
            9x ?  
        */
//        registerInstruction(2,    ComponentSetDefaultOption.class);
        
        /*
            10x ?
            
            */
        //Component - event binding
        registerInstruction(1043, Widget.BIND_MOUSEPRESS_HANDLER);
        registerInstruction(985, Widget.BIND_MOUSEDRAGPAST_HANDLER);
        registerInstruction(790, Widget.BIND_MOUSERELEASE_HANDLER);
        registerInstruction(250, Widget.BIND_MOUSEOVER_HANDLER);
        registerInstruction(663, Widget.BIND_MOUSEOUT_HANDLER);
        //SET_UNKNOWN1_EVENT_HANDLER(90)
        registerInstruction(879, Widget.BIND_DESELECT_HANDLER);
        registerInstruction(912, Widget.BIND_VARP_HANDLER);
        registerInstruction(880, Widget.BIND_UPDATE_HANDLER);
        registerInstruction(447, Widget.BIND_OPTION_HANDLER);
        registerInstruction(31, Widget.BIND_DRAG_HANDLER);
        registerInstruction(252, Widget.BIND_MOUSEDRAG_HANDLER);
        registerInstruction(373, Widget.BIND_MOUSEHOVER_HANDLER);
        registerInstruction(385, Widget.BIND_INV_HANDLER);
        registerInstruction(1122, Widget.BIND_STAT_HANDLER);
        registerInstruction(1086, Widget.BIND_SELECT_HANDLER);
        registerInstruction(314, Widget.BIND_MOUSESCROLL_HANDLER);
        registerInstruction(556, Widget.BIND_CHAT_HANDLER);
        registerInstruction(942, Widget.BIND_KEYPRESS_HANDLER);
        //SET_UNUSED1_EVENT_HANDLER(857)
        //SET_UNUSED2_EVENT_HANDLER(1080)
        //SET_UNUSED3_EVENT_HANDLER(485)
        //SET_UNUSED4_EVENT_HANDLER(622)
        registerInstruction(55, Widget.BIND_FRIENDLIST_HANDLER);
        registerInstruction(293, Widget.BIND_FRIENDCHAT_HANDLER);
        registerInstruction(528, Widget.BIND_STATUS_HANDLER);
        registerInstruction(204, Widget.BIND_ATTACHMENT_HANDLER);
        //SET_UNKNOWN3_EVENT_HANDLER(684)
        registerInstruction(210, Widget.BIND_EXCHANGE_HANDLER);
        //SET_UNKNOWN4_EVENT_HANDLER(596)
        registerInstruction(681, Widget.BIND_RESIZE_HANDLER);
        registerInstruction(398, Widget.BIND_VARC_HANDLER);
        registerInstruction(300, Widget.BIND_VARCSTR_HANDLER);
        registerInstruction(303, Widget.BIND_USE_HANDLER);
        registerInstruction(310, Widget.BIND_CLANSETTINGS_HANDLER);
        registerInstruction(668, Widget.BIND_CLANCHANNEL_HANDLER);
        registerInstruction(562, Widget.BIND_VARCLAN_HANDLER);
        registerInstruction(888, Widget.BIND_GROUPCHANNEL_HANDLER);
        registerInstruction(419, Widget.BIND_VARGROUP_HANDLER);
        //SET_UNKNOWN6_EVENT_HANDLER(60)
        registerInstruction(11, Widget.CLEAR_HANDLERS);
   
 
        /*6x ?
        RECOLOR_COMP_NPC
        3x ?
        SEND_GAME_MESSAGE
        */
        

        
        
        
        
        registerInstruction(278,  Unsorted.RETURN);
        
        /*
                76x ?
        SET_WINDOW_MODE 624
        7x ?
        */
        registerInstruction(963, Unsorted.PRINT_TO_CONSOLE);
        /*
        5x
        CALL_JS_3(58),
        146x ? 
        */
        
        registerInstruction(1068, WidgetContainer.SETACTIVE);
        registerInstruction(104, Widget.SETACTIVE);
        /*
        SET_SELECTED_INTERFACE
        GET_CURRENT_COMP_RENDER_X
        GET_CURRENT_COMP_RENDER_Y
        1x ?
        GET_CURRENT_COMP_RENDER_HEIGHT
        1x ?
        GET_CURRENT_COMP_DEFAULT_PARENT_HASH
        14x ?
        GET_CURRENT_COMP_SPRITE_ID
        14x ?
        GET_COMP_RENDER_X
        GET_COMP_RENDER_Y
        GET_COMP_RENDER_WIDTH
        GET_COMP_RENDER_HEIGHT
        GET_IS_COMP_HIDDEN
        GET_COMP_DEFAULT_PARENT_HASH
        GET_COMP_PARENT_HASH
        1x ?
        GET_COMP_SCROLL_X
        GET_COMP_SCROLL_Y
        2x ?
        GET_COMP_SCROLL_HEIGHT
        16x ?
        GET_FREE_COMP_INDEX
        7x ?
        */
        registerInstruction(1030, ClientGeneral.PUSH_CLIENT_CYCLE);
        registerInstruction(966, ClientGeneral.PUSH_INV_SLOTOBJ);
        registerInstruction(1088, ClientGeneral.PUSH_INV_SLOTCOUNT);
        registerInstruction(115, ClientGeneral.PUSH_INV_OBJCOUNT);
        registerInstruction(443, ClientGeneral.PUSH_INV_CATCOUNT);
        registerInstruction(735, ClientGeneral.PUSH_INV_CAPACITY);
        registerInstruction(65, ClientGeneral.PUSH_INV_STOCKCOUNT);
        
        //didnt check these 3 for accuracy
        registerInstruction(1175, ClientGeneral.PUSH_STAT_LEVEL);
        registerInstruction(146, ClientGeneral.PUSH_STAT_BASE);
        registerInstruction(276, ClientGeneral.PUSH_STAT_EXPERIENCE);
        /*
        15x ?
        ARE_MEM_ITEMS_ALLOWED
        11x ?
        PLAYER_IS_MALE
        19x ?
        GET_ENUM_VALUE
        2x ?
        GET_ENUM_SIZE
        16x ?
        IS_VARCLAN_ENABLED
        IS_EXCHANGE_SELL_OFFER
        2x ?
        GET_EXCHANGE_OFFER_AMOUNT
        GET_EXCHANGE_PROCESSED_COINS
        5x ?
        ADD(475),*/
        

        

        // PUSH GET_EXCHANGE_COMPLETED_COINS[popInt()]
        // PUSH (GRAND_EXCHANGE_STATUS[popInt()] == 0)
        // PUSH (GRAND_EXCHANGE_STATUS[popInt()] == 2)
        // PUSH (GRAND_EXCHANGE_STATUS[popInt()] == 1)
        // PUSH (GRAND_EXCHANGE_STATUS[popInt()] == 5)
        registerInstruction(475,  Math.SUM);
        registerInstruction(856,  Math.SUBTRACT);
        registerInstruction(1219, Math.MULTIPLY);

        //TODO: help!? what is this
        //registerInstruction(987,  Math.DIVIDE_REVERSED);
        registerInstruction(987,  Math.DIVIDE);
        registerInstruction(1189, Math.RANDOM);
        
        /*
        RANDOM_PLUS_ONE(644),
        LINEAR_INTERPOLATE(476),
        WOT(108),
        SET_BIT(132),
        CLEAR_BIT(1161),
        TEST_BIT(287),*/

        registerInstruction(1186, Math.MODULO);
        
        
        /*POW(236),
        ROOT(1143),
        AND(81),
        OR(313),
        MIN(952),
        */

        registerInstruction(952,  Math.MIN);
        registerInstruction(241,  Math.MAX);
        registerInstruction(977,  Math.FRAC_MULTIPLY);
        //241
        //TODO: 977, 241 
        
        /*
        FRAC_MUL(977),
        6x ?
        GET_COLOR_HTML
        4x ?
        */
        registerInstruction(560,  Text.INT_TO_STR);

        /*
        COMPARE_STRINGS
        GET_LINE_COUNT
        9x ?
        */
        registerInstruction(986,  Text.STRLEN);
        
        /*
        7x ?
        GET_STRING_WIDTH
        3x ?
        GET_OBJ_NAME
        1x ?
        GET_OBJ_INV_OP
        2x ?
        GET_OBJ_CATEGORY
        9x ?
        IS_MEMBERS_ITEM
        GET_OBJ_PARAM_BY_DEFAULT_VAL
        GET_OBJ_CURSOR_OP
        4x ?
        GET_OBJ_NAME_COLOR
        GET_NPC_PARAM_BY_DEFAULT_VAL
        1x ?*/
        
        registerInstruction(282, Config.PUSH_STRUCT_PARAM);
        /*
        55x ?
        GET_MAP_SEGMENT_COORDS
        16x ?
        LOAD_INT_ZERO
        21x ?
        GET_ACC_CREATION_STATUS
        */
        registerInstruction(1006, Unsorted.GET_CURRENT_TOOLKIT_PREFERENCE);
        /*
        75x ?
        GET_ROOT_INTERFACE
        126 ?
        */
        
        registerInstruction(1115, Unsorted.NOP);
        /*
            31x ?
        */
        registerInstruction(28,   Unsorted.TWITCH_LOGOUT);
        /*
            32x ?
        */
        registerInstruction(831,  Unsorted.IS_WINDOWS);
        /*
            22x ?
        */
        registerInstruction(408, ScriptEnum.PUSH_VALUE_STR);
        registerInstruction(608, ScriptEnum.PUSH_VALUE);
        registerInstruction(285, ScriptEnum.PUSH_CONTAINS_INT);
        registerInstruction(237, ScriptEnum.PUSH_CONTAINS_STR);
        registerInstruction(51, ScriptEnum.PUSH_SIZE);
        registerInstruction(1082, ScriptEnum.PUSH_INTVALKEYS_COUNT);
        registerInstruction(621, ScriptEnum.PUSH_STRVALKEYS_COUNT);
        registerInstruction(789, ScriptEnum.PUSH_INTVALKEY);
        registerInstruction(642, ScriptEnum.PUSH_STRVALKEY);
        
        
        registerInstruction(444, Unsorted.CALL_SCRIPT);

        
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
        }else{
//            System.err.println();
            //System.err.println("opcode: "+id);
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

}
