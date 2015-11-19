package com.wycody.cs2d.script.inst.base;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jagex.game.runetek5.config.vartype.constants.ScriptVarType;
import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.StackType;
import com.wycody.cs2d.utils.CS2Utils;

public class EventBindInstruction extends Instruction {
    
    private static final Logger logger = LoggerFactory.getLogger(EventBindInstruction.class.getName());
    
    /**
     * The name of the event handler
     */
    private final String handlerName;
    
    /**
     * True if the event is attached to an active component, false otherwise
     */
    private final boolean isActiveComponent;
    
    /**
     * The event call prefix
     */
    private String prefix;
    
    /**
     * The ids of variables to monitor for changes
     */
    private Object[] monitorList;
    
    /**
     * The object which contains the script ID
     */
    private Object scriptId;
    
    /**
     * The script arguments
     */
    private Object[] args;

    public EventBindInstruction(InstructionType type, String handlerName, boolean isActiveComponent) {
        super(type);
        this.handlerName = handlerName;
        this.isActiveComponent = isActiveComponent;
    }

    
    @Override
    public void process(Context context) {
        if (isActiveComponent) {
            prefix = "getActiveWidget()";
        } else {
            prefix = CS2Utils.getWidget(pop(StackType.INT));
        }
        String signature = (String) pop(StackType.OBJECT);     
        
        if (signature.startsWith("\"") && signature.endsWith("\"") && signature.length() >= 2) {
            signature = signature.substring(1, signature.length()-1);//Strip the quote marks off.
        }  
        
        logger.debug("Event argument signature: "+signature);

        if (signature.length() > 0 && signature.charAt(signature.length() - 1) == 'Y') {
            int size = (Integer) pop(StackType.INT);
            if (size > 0) {
                monitorList = new Object[size];
                while (size-- > 0) {
                    monitorList[size] = pop(StackType.INT);
                }
            }
            signature = signature.substring(0, signature.length() - 1);
        }
        args = new Object[signature.length()];
        
        byte[] DBG = signature.getBytes();
        
        for (int i = args.length - 1; i >= 0; i--) {
        	ScriptVarType varType = ScriptVarType.getByChar(signature.charAt(i));
            if (varType == ScriptVarType.STRING) {
                args[i] = pop(StackType.OBJECT);
            } else if (varType == ScriptVarType.INTERFACE) {
                args[i] = CS2Utils.getWidget(pop(StackType.INT));
            } else if (varType == ScriptVarType.BOOLEAN) {
                args[i] = CS2Utils.getBoolean(pop(StackType.INT));
            } else if(varType == ScriptVarType.LONG){
            	args[i] = pop(StackType.LONG);
            } else {
            	args[i] = CS2Utils.fixIntegerField(pop(StackType.INT));
            }
        }
        scriptId = pop(StackType.INT);
    }

    @Override
    public void print(Context context, ScriptPrinter printer) {
        if (scriptId instanceof Integer && ((Integer) scriptId) == -1) {
            printer.println(prefix+".remove"+handlerName+"Event();");
        } else {
            StringBuilder bldr = new StringBuilder();
            bldr.append(prefix).append(".set").append(handlerName).append("Event(script_");
            bldr.append(scriptId).append("(");

          //  bldr.append(scriptId);
            if (args.length > 0) {
            	for(int i = 0; i < args.length; i++) {
            		Object o = args[i];
            		if(i != 0) {
            			bldr.append(", ");
            		}
            		bldr.append(o);
            	}
            	//String s = Arrays.toString(args);
                //bldr.append(", ").append(s.substring(1, s.length()-1));
            }
            bldr.append(")");
            if (monitorList != null && monitorList.length > 0) {
                bldr.append(", ").append(Arrays.toString(monitorList));
            }          
            bldr.append(");");
            printer.println(bldr.toString());
        }
    }

    @Override
    public int getPushCount(StackType type) {
        return -1;
    }

    @Override
    public int getPopCount(StackType type) {
        return -1;
    }

    @Override
    public String toString() {
        return String.format("%4d:(%d) => %s<%s>",this.address, this.id,"EVENT_BIND",this.type);
    }
}