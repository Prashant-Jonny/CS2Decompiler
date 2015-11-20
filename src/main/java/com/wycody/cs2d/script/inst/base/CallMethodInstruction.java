package com.wycody.cs2d.script.inst.base;

import java.util.function.Function;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.StackType;
import com.wycody.cs2d.utils.CS2Utils;

public class CallMethodInstruction extends Instruction {
    private String methodName = null;
    private Object arguments[] = null;
    private StackType[] argumentTypes = null;
    private Function<Object, Object>[] prefixFormatter = null;
    private StackType[] returnTypes = null;
    private String nameFormat;
    private boolean reverseArgs = true;
    
    /*
     prefixFormatter is for when the first argument is something special.
     e.g. for widgets.
     => 
     widget(arg1).MethodName(arg2,arg3...)
     */
    @Deprecated
    public CallMethodInstruction(InstructionType type, String methodName, Function<Object, Object>[] prefixFormatter, StackType returnType, StackType... argumentTypes) {
        super(type);
        this.methodName = methodName;
        this.prefixFormatter = prefixFormatter;
        this.returnTypes = new StackType[] { returnType };
        this.argumentTypes = argumentTypes;
        this.arguments = new Object[this.argumentTypes.length];
    }

    public CallMethodInstruction(InstructionType type) {
        super(type);
    }
    
    public CallMethodInstruction() {
        super(InstructionType.METHOD_CALL);
    }
    
    public CallMethodInstruction setReverseArgs(boolean reverseArgs){
        this.reverseArgs = reverseArgs;
        return this;
    }
    
    public CallMethodInstruction setDebugName(String name){
        this.methodName = name;
        return this;
    }
    
    public CallMethodInstruction fixDefaultType(){
        //No push D:
        if(this.returnTypes == null || this.returnTypes.length == 0 || (this.returnTypes.length == 1 && this.returnTypes[0] == null)) {
            this.type = InstructionType.METHOD_CALL;
        } else if (this.returnTypes.length == 1) {
            this.type = InstructionType.METHOD_CALL_WITH_PUSH;
        } else {
            this.type = InstructionType.METHOD_CALL_WITH_MULTIPUSH;
        }
        return this;
    }

    /* Chain functions */
    public CallMethodInstruction setName(String name) {
        this.methodName = name;
        return this;
    }

    public CallMethodInstruction setPrefixFormatters(@SuppressWarnings("unchecked") Function<Object, Object>... formatters) {
        this.prefixFormatter = formatters;
        return this;
    }

    public CallMethodInstruction setPushType(StackType type) {
        this.returnTypes = new StackType[] { type };
        return this;
    }

    public CallMethodInstruction setPushTypes(StackType... pushTypes) {
        if(pushTypes == null || pushTypes.length == 0) {
            this.returnTypes = new StackType[] { null };
        } else if(pushTypes.length == 1) {
            this.setPushType(pushTypes[0]);
        } else {
            this.returnTypes = pushTypes;
        }
        return this;
    }

    public CallMethodInstruction setFormattedName(String format){
        this.nameFormat = format;
        return this;
    }
    
    public CallMethodInstruction setArgumentTypes(StackType... types) {
        this.argumentTypes = types;
        this.arguments = new Object[this.argumentTypes != null  && (this.argumentTypes.length >= 1 && this.argumentTypes[0] != null) ? this.argumentTypes.length : 0];
        return this;
    }

    @Override
    public void process(Context context) {        
        /*
        PLEASE DO NOT ADJUST THIS, THIS MESSED UP A LOT OF STUFF.
        ADJUST THE PRINTING ORDER BY HAND!
        I WILL MAKE AN EXCEPTION FOR ONES WITH NO DISPLAY FORMAT SET!
        */
        if(this.nameFormat == null && reverseArgs) {
            for (int i = 0; argumentTypes != null && i < argumentTypes.length; i++) {
                arguments[argumentTypes.length-i-1] = pop(argumentTypes[argumentTypes.length-1-i]);
            }
        }else{
            for (int i = 0; argumentTypes != null && i < argumentTypes.length; i++) {
                arguments[i] = pop(argumentTypes[i]);
            }
        }

        if (this.returnTypes != null && this.returnTypes.length > 0) {
            if (this.returnTypes.length == 1 && this.returnTypes[0] != null) {
                push(this.returnTypes[0], this.generate());
            } else if (this.returnTypes.length > 1) {
                for (int i=0;i<this.returnTypes.length;i++) {
                    push(this.returnTypes[i], "results["+i+"]");
                }
            }
        }
    }

    private String generate() {
        StringBuilder str = new StringBuilder();
        
        if(this.nameFormat != null){
            Object[] arguments = this.arguments.clone();
            if(this.prefixFormatter != null) {
				for(int i=0; i < arguments.length && i < this.prefixFormatter.length; i++) {
					if(this.prefixFormatter[i] != null) {
						arguments[i] = this.prefixFormatter[i].apply(arguments[i]);
					}
				}
			}
            
            char[] nameChars = this.nameFormat.toCharArray();
            for(int i=0; i < nameChars.length; i++){
                if(nameChars[i] == '%'){
                    int x = 0;
                    while(++i < nameChars.length && Character.isDigit(nameChars[i])) {
						x = (10*x) + Character.digit(nameChars[i], 10);
					}
                    if (i < nameChars.length) {
                        switch (Character.toLowerCase(nameChars[i])) {
                            case 'b':
                                str.append(CS2Utils.getBoolean(arguments[x-1]));
                                break;
                            case 'c':
                                str.append(CS2Utils.getColor(arguments[x-1]));
                                break;
                            case 'w':
                                str.append(CS2Utils.getWidget(arguments[x-1]));
                                break;
                            default:
                                i--;
                                str.append(arguments[x-1]);
                        }                    
                    } else {
                         str.append(arguments[x-1]);
                    }
                } else if(nameChars[i] == '$'){
                    char id = Character.toLowerCase(nameChars[++i]);
                    if(id == 'i'){
                        str.append(this.getIntegerOperand());
                    } else if(id == 'l'){
                        str.append(this.getLongOperand());
                    } else if(id == 'o'){
                        str.append(this.getObjectOperand());
                    } else{
                        str.append("$");
                        i--;
                    }
                } else {
                    str.append(nameChars[i]);
                }
            }
            
            return str.toString();
        }        
        
        if (prefixFormatter == null) {
            str.append(methodName).append('(');
            for (int i = 0; arguments != null && i < arguments.length; i++) {
                if (i > 0) {
                    str.append(',').append(" ").append(arguments[i]);
                } else {
                    str.append(arguments[i]);
                }
            }
            str.append(')');
        } else {
            for (int j = 0; j < prefixFormatter.length; j++) {
                str.append(prefixFormatter[j].apply(arguments[j]));
                str.append('.');
            }
            str.append(methodName).append('(');
            for (int i = prefixFormatter.length; arguments != null && i < arguments.length; i++) {
                if (i > prefixFormatter.length) {
                    str.append(',').append(arguments[i]);
                } else {
                    str.append(arguments[i]);
                }
            }
            str.append(')');
        }
        return str.toString();
    }

    @Override
    public void print(Context context, ScriptPrinter printer) {
        if (this.returnTypes == null || this.returnTypes.length == 0 || (this.returnTypes.length == 1 && this.returnTypes[0] == null)) {
            printer.println(generate() + ";");
        } else if (this.returnTypes.length > 1) {
             printer.println("Object[] results = "+generate() + ";");
        }
    }

    @Override
    public int getPushCount(StackType type) {
        if(returnTypes == null)
            return 0;
        int count = 0;
        for(StackType check : returnTypes)
            if(check == type)
                count++;
        return count;
    }

    @Override
    public int getPopCount(StackType type) {
        if(argumentTypes == null)
            return 0;
        int count = 0;
        for(StackType check : argumentTypes)
            if(check == type)
                count++;
        return count;
    }
    
    /**
     * Gets additional details for debugging purposes.
     * @return Formatted string.
     */
    private String getDetails(){
        StringBuilder s = new StringBuilder();
        if(this.methodName != null && this.methodName.length() > 0) {
			s.append(s).append(this.methodName).append("\t");
		}

        s.append(StackType.format(this.argumentTypes)).append(" -> ").append(StackType.format(this.returnTypes)).append("\t");
        s.append(this.nameFormat).append("\t");
        return s.toString();
    }
    
    @Override
    public String toString() {
        return String.format("%4d:(%d) => %s<%s>\t%s",this.address, this.id,"DYNAMIC",this.type, getDetails());
    }
}
