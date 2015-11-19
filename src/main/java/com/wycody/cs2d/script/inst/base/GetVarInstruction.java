package com.wycody.cs2d.script.inst.base;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.StackType;


// TODO - this could be replaced by using the CallMethodInstruction
//
//  .setFormattedName(storage_name + "[$]")
//  .setDebugName("DEBUG_NAME")
public class GetVarInstruction extends Instruction {
    private String storage_name;
    private StackType push_type;
    private Object arg;
    
    public GetVarInstruction(InstructionType type, StackType push_type, String var_name){
        super(type);
        this.storage_name = var_name;
        this.push_type = push_type;
    }

    @Override
    public void process(Context context) {
        push(push_type, storage_name + "[" + this.integerOperand + "]");
    }

    @Override
    public void print(Context context, ScriptPrinter printer) {}

    @Override
    public int getPushCount(StackType type) {
        if(type == push_type)
            return 1;
        return 0;
    }

    @Override
    public int getPopCount(StackType type) {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%4d:(%d) => %s<%s>(OP=%d,C=%s)",this.address, this.id,"GET_VAR",this.type,this.integerOperand,storage_name);
    }


}
