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

import com.wycody.cs2d.rev.Revision;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.CallMethodInstruction;
import com.wycody.cs2d.script.inst.base.EventBindInstruction;
import com.wycody.cs2d.script.inst.base.PushParamInstruction;
import com.wycody.cs2d.script.inst.impl.*;
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
    
    
    public void loadRevision(String revision, Revision target) {
        try{
            String line;
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

                boolean emptyData=false;

                for(int i=0; !emptyData && i < 6; i++)
                    if(i != 5 && parts[i].trim().length() <= 0) {
                        System.out.println("Skipping opcode: " + opcode + " reason: Empty data");
                        emptyData=true;
                    }

                if(emptyData)
                    continue;

                if(opcode >= 0){
                    if(specials.containsKey(opcode_name)){
                        target.registerInstruction(opcode, specials.get(opcode_name));
                    }else if(name_format.startsWith("PARAM:")){
                        final StackType[] stack_in = parseArguments(opcode_in);
                        assert stack_in.length > 1 && stack_in[stack_in.length-1] != null;
                        Supplier<Instruction> ins = () -> new PushParamInstruction(InstructionType.PUSH_STRUCT_PARAM,"",stack_in[0]);
                        target.registerInstruction(opcode,ins);
                    }else if(formats == null || !formats.toUpperCase().startsWith("BIND_EVENT")) {
                        final StackType[] stack_in = parseArguments(opcode_in);
                        final StackType[] stack_out = parseArguments(opcode_out);
                        final Function<Object, Object>[] formatters = parseFormatters(formats);
                        target.registerInstruction(opcode, generateSupplier(name_format,stack_in,stack_out,opcode_name,formatters));
                    } else if(formats.equalsIgnoreCase("BIND_EVENT") || formats.equalsIgnoreCase("BIND_EVENT_ACTIVE")) {
                        target.registerInstruction(opcode, () -> (new EventBindInstruction(InstructionType.COMP_SET_HANDLER,name_format,formats.equalsIgnoreCase("BIND_EVENT_ACTIVE"))));
                    }
                }else{
                    System.out.println("Error: opcode < 0 !?" + opcode);
                }
            }

            
        }catch(Throwable t){
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
                throw new RuntimeException("Invalid formatter type in config.");
        }
    }



    private static final Map<String,Supplier<? extends Instruction>> specials;

    static{
        specials = new HashMap<>();
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


        /*
        191	ARRAY_LOAD_PUSH_INDEX_BEFORE
        809	ARRAY_LOAD_PUSH_INDEX_AFTER
        979	ARRAY_STORE_PUSH_INDEX

        specials.put("ARRAY_LOAD_PUSH_INDEX_BEFORE",);
        specials.put("ARRAY_LOAD_PUSH_INDEX_AFTER",);
        specials.put("ARRAY_STORE_PUSH_INDEX",);
         */
    }
}
