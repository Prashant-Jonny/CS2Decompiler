package com.wycody.cs2d.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.wycody.cs2d.rev.RS2Revision;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.CallMethodInstruction;
import com.wycody.cs2d.script.inst.base.EventBindInstruction;
import com.wycody.cs2d.script.inst.base.PushParamInstruction;
import com.wycody.cs2d.script.inst.impl.*;
import com.wycody.cs2d.script.inst.impl.Math;
import com.wycody.cs2d.script.inst.types.StackType;

/**
 *
 * Parses tsv files.
 */
public class ConfigParser {
    
    static {
        
    }
    
    private final String urlBase;
    
    private final Map<String,Integer> revisionGids;
    
    public ConfigParser () {
        this(null, null);
    }
    
    public ConfigParser (String urlBase, Map<String,Integer> revisionGids) {
        this.urlBase = urlBase;
        this.revisionGids = revisionGids;
    }
    
    private int getGID(String revision) {
        if (revisionGids == null) {
            throw new IllegalStateException("No revision GIDs declared - cannot get revision GID.");
        }
        Integer key = revisionGids.get(revision);
        if(key == null) {
			throw new IllegalArgumentException("Invalid/Unknown revision: " + revision);
		}
        return key;
    }
    
    
    public void download(String revision) {
        if (urlBase == null) {
            throw new IllegalStateException("No url declared - cannot download revision file.");
        }
        String url = urlBase+getGID(revision);
        System.out.println("Fetching from: " + url);
        URL uri;
        try {
            uri = new URL(url);
            InputStream is = uri.openConnection().getInputStream();
            
            File dstDir = new File("config");
            assert dstDir.exists() || dstDir.mkdir();
            FileOutputStream fos = new FileOutputStream("config/"+revision+".tsv");
            int b;
            while((b=is.read()) != -1) {
				fos.write((byte)b);
			}        
        } catch (Exception ex) {
            Logger.getLogger(ConfigParser.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }
    
    
    public void loadRevision(String revision, RS2Revision target) {
        String line="";
        try{

            BufferedReader r = new BufferedReader(new FileReader(new File("config/"+revision+".tsv")));
            r.readLine(); // Skip header of tsv export.
            while((line = r.readLine()) != null){
                if(line.startsWith("#")) {
					continue;
				}
                
                String[] parts = line.split("\t");
                
                if(parts.length <= 5) {
					continue;
				}
                
                final int opcode = (parts[0] != null && parts[0].length() > 0 ) ? Integer.parseInt(parts[0]) : -1;
                final String opcode_name  = parts[1];
                final String opcode_in    = parts[2];
                final String opcode_out   = parts[3];
                final String name_format  = parts[4];
                final String formats = parts.length > 5 ? parts[5] : "";
                //6 => comment


                if(opcode_name.equalsIgnoreCase("ARRAY_LOAD_PUSH_INDEX_BEFORE"))
                    continue;
                if(opcode_name.equalsIgnoreCase("ARRAY_LOAD_PUSH_INDEX_AFTER"))
                    continue;
                if(opcode_name.equalsIgnoreCase("ARRAY_STORE_PUSH_INDEX"))
                    continue;

                boolean emptyData=false;

                for(int i=0; !emptyData && i < 6; i++)
                    if(i != 5 && parts[i].trim().length() <= 0) {
//                        System.out.println("Skipping opcode: " + opcode + " reason: Empty data");
                        emptyData=true;
                    }

                if(emptyData)
                    continue;

                if(opcode >= 0){
                    if(!opcode_name.equalsIgnoreCase("NO_EXIST")) {
                        if (specials.containsKey(opcode_name)) {
                            target.registerInstruction(opcode, specials.get(opcode_name));
                        } else if (name_format.startsWith("PARAM:")) {
                            final StackType[] stack_in = parseArguments(opcode_in);
                            assert stack_in.length > 1 && stack_in[stack_in.length - 1] != null;
                            Supplier<Instruction> ins = () -> new PushParamInstruction(InstructionType.PUSH_STRUCT_PARAM, "", stack_in[0]);
                            target.registerInstruction(opcode, ins);
                        } else if (formats.equalsIgnoreCase("BIND_EVENT") || formats.equalsIgnoreCase("BIND_EVENT_ACTIVE")) {
                            target.registerInstruction(opcode, () -> (new EventBindInstruction(InstructionType.COMP_SET_HANDLER, name_format, formats.equalsIgnoreCase("BIND_EVENT_ACTIVE"))));
                        } else {
                            final StackType[] stack_in = parseArguments(opcode_in);
                            final StackType[] stack_out = parseArguments(opcode_out);
                            final Function<Object, Object>[] formatters = parseFormatters(formats);
                            target.registerInstruction(opcode, generateSupplier(name_format, stack_in, stack_out, opcode_name, formatters));
                        }
                    }
                }else{
                    System.out.println("Error: opcode < 0 !?" + opcode);
                }
            }

            
        }catch(Throwable t){
            System.out.println(line);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new RuntimeException(t);
        }
    }

    private Supplier<? extends Instruction> generateSupplier(String name_format, StackType[] stack_in,StackType[] stack_out, String debugName, Function<Object,Object>[] formatters) {
        //noinspection unchecked
        return () ->
                (new CallMethodInstruction(InstructionType.METHOD_CALL))
                        .setName(null)
                        .setFormattedName(name_format)
                        .setArgumentTypes(stack_in)
                        .setPushTypes(stack_out)
                        .setDebugName(debugName)
                        .setPrefixFormatters(formatters)
                .fixDefaultType();
    }

    
    private StackType identifyStackType(char c){
        if(c == 'I' || c == 'i') {
			return StackType.INT;
		}
        if(c == 'L' || c == 'l') {
			return StackType.LONG;
		}
        if(c == 'O' || c == 'o') {
			return StackType.OBJECT;
		}
        throw new RuntimeException("Invalid stack type in config. ("+c+")");
    }
    
    private StackType[] parseArguments(String opcode_in) {
        opcode_in = opcode_in.trim();
        if(opcode_in.equals("V") || opcode_in.equals("v")) {
			return new StackType[0];
		}
        
        StackType[] types = new StackType[opcode_in.length()];
        for(int i=0; i < opcode_in.length(); i++){
            char c = opcode_in.charAt(i);
            types[i] = identifyStackType(c);
        }
        
        return types;
    }

    private Function<Object, Object>[] parseFormatters(String formats) {
        Function[] formatters;
        if(formats == null || formats.trim().length() == 0 || formats.equals("R") || formats.equals("-") || formats.equals("_") || formats.equals(" ")) {
            formatters =  new Function[0];
		}else {
            formats = formats.trim();
            formatters = new Function[formats.length()];
            for (int i = 0; i < formats.length(); i++) {
                char c = formats.charAt(i);
                formatters[i] = getFormatter(c);
            }
        }
        //noinspection unchecked
        return (Function<Object, Object>[]) formatters;
    }
    
    
    private Function<Object,Object> getFormatter(char c){
        switch(c) {
            //Base case. nothing
            case 'R':
            case ' ':
            case '_':
            case '-':
                return (x->x);

            // Capital letters
            case 'C':
                return CS2Utils::getWidget;
            case 'B':
                return CS2Utils::getBoolean;


            //Lowercase letters
            case 'c':
                return CS2Utils::getColor;
            case 's':
                return CS2Utils::getSkill;

            //Special characters
            case '$':
                return CS2Utils::getSkillDataType;

            default:
                throw new RuntimeException("Invalid formatter type in config. ("+c+")");
        }
    }



    private static final Map<String,Supplier<? extends Instruction>> specials;

    static{
        specials = new HashMap<>();
        specials.put("GET_NPC_PARAM_BY_DEFAULT_VAL", Config.PUSH_STRUCT_PARAM);
        specials.put("GET_WORLDMAP_PARAM", Config.PUSH_WORLDMAP_PARAM);
        specials.put("CC_PUSH_PARAM", ActiveWidget.PUSH_PARAM);
        specials.put("GET_OBJ_PARAM_BY_DEFAULT_VAL", Config.PUSH_OBJ_PARAM);
        specials.put("NEW_ARRAY", Array.CREATE);
        specials.put("ARRAY_LOAD", Push.ARRAY);
        specials.put("ARRAY_STORE", Store.ARRAY);
        specials.put("RETI", Unsorted.RETURN);
        specials.put("PUSH_INT", Push.PUSH_INT);
        specials.put("PUSH_LONG", Push.PUSH_LONG);
        specials.put("PUSH_OBJ", Push.PUSH_OBJECT);
        specials.put("GOTO", Branch.GOTO);
        specials.put("INT_NE", Branch.INT_NE);
        specials.put("INT_EQ", Branch.INT_EQ);
        specials.put("INT_LT", Branch.INT_LT);
        specials.put("INT_GT", Branch.INT_GT);
        specials.put("INT_LE", Branch.INT_LE);
        specials.put("INT_GE", Branch.INT_GE);
        specials.put("LONG_NE", Branch.LONG_NE);
        specials.put("LONG_EQ", Branch.LONG_EQ);
        specials.put("LONG_LT", Branch.LONG_LT);
        specials.put("LONG_GT", Branch.LONG_GT);
        specials.put("LONG_LE", Branch.LONG_LE);
        specials.put("LONG_GE", Branch.LONG_GE);
        specials.put("POP_INT", Pop.POP_INT);
        specials.put("POP_LONG", Pop.POP_LONG);
        specials.put("POP_OBJ", Pop.POP_OBJ);
        specials.put("SWITCH", Branch.SWITCH);
        specials.put("LOAD_INT", Push.LOAD_INT);
        specials.put("LOAD_LONG", Push.LOAD_LONG);
        specials.put("LOAD_OBJ", Push.LOAD_OBJ);
        specials.put("STORE_INT", Store.STORE_INT);
        specials.put("STORE_LONG", Store.STORE_LONG);
        specials.put("STORE_OBJ", Store.STORE_OBJ);
        specials.put("MERGE_STRS", Unsorted.CONCAT_STRS);
        specials.put("CALL_SCRIPT", Unsorted.CALL_SCRIPT);
        specials.put("PUSH_VAR", RS3Var.RS3_PUSH_VAR);
        specials.put("STORE_VAR", RS3Var.RS3_STORE_VAR);
        specials.put("PUSH_VAR_BIT", RS3Var.RS3_PUSH_VARBIT);
        specials.put("STORE_VAR_BIT", RS3Var.RS3_STORE_VARBIT);
        specials.put("GROUP_GET_PARM_OR_BIT", Var.GROUP_GET_MULTI_VAR);


        specials.put("ADD", Math.SUM);
        specials.put("ROOT", Math.ROOT);
        specials.put("POW", Math.POWER);
        specials.put("MOD", Math.MODULO);
        specials.put("MAX", Math.MAX);
        specials.put("MIN", Math.MIN);
        specials.put("OR", Math.BITWISE_OR);
        specials.put("FRAC_MUL", Math.FRAC_MULTIPLY);
        specials.put("SUBTRACT", Math.SUBTRACT);
        specials.put("MULTIPLY", Math.MULTIPLY);
        specials.put("DIVIDE", Math.DIVIDE);
        specials.put("RANDOM", Math.RANDOM);
        specials.put("RANDOM_PLUS_ONE", Math.RANDOM); //TODO fix


        /*
        registerInstruction(408, ScriptEnum.PUSH_VALUE_STR);
        registerInstruction(285, ScriptEnum.PUSH_CONTAINS_INT);
        registerInstruction(237, ScriptEnum.PUSH_CONTAINS_STR);
        registerInstruction(51, ScriptEnum.PUSH_SIZE);
        registerInstruction(1082, ScriptEnum.PUSH_INTVALKEYS_COUNT);
        registerInstruction(621, ScriptEnum.PUSH_STRVALKEYS_COUNT);
        registerInstruction(789, ScriptEnum.PUSH_INTVALKEY);
        registerInstruction(642, ScriptEnum.PUSH_STRVALKEY);
         */
        specials.put("GET_ENUM_VALUE", ScriptEnum.PUSH_VALUE_1);




        /*

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
        registerInstruction(663, Widget.BIND_MOUSE_HOVER_OUT_HANDLER);
        registerInstruction(879, Widget.BIND_DESELECT_HANDLER);
        registerInstruction(912, Widget.BIND_VARP_HANDLER);
        registerInstruction(880, Widget.BIND_UPDATE_HANDLER);
        registerInstruction(447, Widget.BIND_OPTION_HANDLER);
        registerInstruction(31, Widget.BIND_DRAG_HANDLER);
        registerInstruction(252, Widget.BIND_MOUSEDRAG_HANDLER);
        registerInstruction(373, Widget.BIND_MOUSE_HOVER_IN_HANDLER);
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
         */

        /* Older revisions */
        specials.put("PUSH_VARP",Var.PUSH_VARP);
        specials.put("STORE_VARP",Var.STORE_VARP);
        specials.put("PUSH_VARP_BIT",Var.PUSH_VARP_BIT);
        specials.put("STORE_VARP_BIT",Var.STORE_VARP_BIT);
        specials.put("PUSH_VARC",Var.PUSH_VARC);
        specials.put("STORE_VARC",Var.STORE_VARC);



        /* Newer stuff */
        specials.put("PUSH_WORLDMAP_PARAM",Config.PUSH_WORLDMAP_PARAM); //TODO: check spreadsheet neme.
        specials.put("GET_STRUCT_VALUE",Config.PUSH_STRUCT_PARAM);
        specials.put("GET_NPC_PARAM_BY_DEFAULT_VAL",Config.PUSH_NPC_PARAM);
        specials.put("PUSH_VALUE_STR",ScriptEnum.PUSH_VALUE_STR); //TODO: check spreadsheet neme.
        specials.put("PUSH_VALUE",ScriptEnum.PUSH_VALUE); //TODO: check spreadsheet neme.
        specials.put("PUSH_CONTAINS_INT",ScriptEnum.PUSH_CONTAINS_INT); //TODO: check spreadsheet neme.
        specials.put("PUSH_CONTAINS_STR",ScriptEnum.PUSH_CONTAINS_STR); //TODO: check spreadsheet neme.
        specials.put("PUSH_SIZE",ScriptEnum.PUSH_SIZE); //TODO: check spreadsheet neme.
        specials.put("PUSH_INTVALKEYS_COUNT",ScriptEnum.PUSH_INTVALKEYS_COUNT); //TODO: check spreadsheet neme.
        specials.put("PUSH_STRVALKEYS_COUNT",ScriptEnum.PUSH_STRVALKEYS_COUNT); //TODO: check spreadsheet neme.
        specials.put("PUSH_INTVALKEY",ScriptEnum.PUSH_INTVALKEY); //TODO: check spreadsheet neme.
        specials.put("PUSH_STRVALKEY",ScriptEnum.PUSH_STRVALKEY); //TODO: check spreadsheet neme.


        specials.put("GET_VALUE_KEYS_KEY_BY_INDEX", ScriptEnum.GET_VALUE_KEYS_KEY_BY_INDEX);
    }
}
