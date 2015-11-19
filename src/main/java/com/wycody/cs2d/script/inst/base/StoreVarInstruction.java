package com.wycody.cs2d.script.inst.base;

import com.wycody.cs2d.script.inst.types.StackType;
import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;

public class StoreVarInstruction extends Instruction {
    private final String storage_name;
    private final StackType pop_type;
    private Object arg;
    
    public StoreVarInstruction(InstructionType type, StackType pop_type, String var_name){
        super(type);
        this.storage_name = var_name;
        this.pop_type = pop_type;
    }

    @Override
    public void process(Context context) {
        arg  = pop(pop_type);
    }

    @Override
    public void print(Context context, ScriptPrinter printer) {
        printer.println(storage_name + "[" + this.integerOperand + "] = " + arg + ";");
    }

    @Override
    public int getPushCount(StackType type) {
        return 0;
    }

    @Override
    public int getPopCount(StackType type) {
        if(type == pop_type)
            return 1;
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%4d:(%d) => %s<%s>",this.address, this.id,"STORE_VAR",this.type);
    }
}
