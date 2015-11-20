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
import java.util.logging.Level;
import java.util.logging.Logger;

import com.wycody.cs2d.rev.Revision;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.CallMethodInstruction;
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
            if(!dstDir.exists()) {
				dstDir.mkdir();
			}
            
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
            String line = null;
            BufferedReader r = new BufferedReader(new FileReader(new File("config/"+revision+".tsv")));
            line = r.readLine(); // Skip header of tsv export.
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
                
                
                if(opcode >= 0){
                    final StackType[] stack_in = parseArguments(opcode_in);
                    final StackType[] stack_out = parseArguments(opcode_out);
                    final Function<Object, Object>[] formatters = parseFormatters(formats);
                    
                    //CallMethodInstruction cm = new CallMethodInstruction(InstructionType.METHOD_CALL);
                    
                    
                    target.registerInstruction(opcode, () -> {
                        return 
                                (new CallMethodInstruction(InstructionType.METHOD_CALL))
                                .setName(null).setFormattedName(name_format)
                                .setArgumentTypes(stack_in)
                                .setPushTypes(stack_out)
                                .setDebugName(opcode_name)
                                .setPrefixFormatters(formatters)
                                .fixDefaultType();
                    });
                    
                    /*
                    cm.setName(null)
                            .setFormattedName(name_format)
                            .setArgumentTypes(parseArguments(opcode_in))
                            .setPushTypes(parseArguments(opcode_out))
                            .setPrefixFormatters(parseFormatters(formats))
                            .set
                    */
                }
            }
            //target.registerInstruction(id, getSupplier());
            
            
        }catch(Throwable t){
            throw new RuntimeException(t);
        }
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
        formats = formats.trim();
        if(formats == null || formats.length() == 0) {
			return new Function[0];
		}
        
        Function<Object,Object>[] formatters = new Function[formats.length()];
        
        for(int i=0; i < formats.length(); i++){
            char c = formats.charAt(i);
            formatters[i] = getFormatter(c);
        }
        return formatters;
    }
    
    
    private Function<Object,Object> getFormatter(char c){
        if(c == 'R' || c == ' ') {
			return (x -> x);
		}
        if(c == 'C') {
			return x -> CS2Utils.getWidget(x);
		}
        if(c == 'B') {
			return x -> CS2Utils.getBoolean(x);
		}
        if(c == 'c') {
			return x -> CS2Utils.getColor(x);
		}
        throw new RuntimeException("Invalid formatter type in config.");
    }
    

    
    
}
