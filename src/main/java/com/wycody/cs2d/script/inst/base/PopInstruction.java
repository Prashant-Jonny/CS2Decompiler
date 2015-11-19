package com.wycody.cs2d.script.inst.base;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.StackType;

public class PopInstruction extends Instruction {
    private Object value;
    private StackType type;
    
    public PopInstruction(InstructionType type, StackType stack_type){
        super(type);
        this.type = stack_type;
    }

    @Override
    public void process(Context context) {
        value = pop(type);
    }

    @Override
    public void print(Context context, ScriptPrinter printer) {
        printer.print(value);
    }

    @Override
    public int getPushCount(StackType type) {
        return 0;
    }

    @Override
    public int getPopCount(StackType type) {
        return type == this.type ? 1 : 0;
    }

    @Override
    public String toString() {
        return String.format("%4d:(%d) => %s<%s>",this.address, this.id,"POP",this.type);
    }
}
