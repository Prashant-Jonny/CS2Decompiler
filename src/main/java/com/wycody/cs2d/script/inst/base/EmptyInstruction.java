package com.wycody.cs2d.script.inst.base;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.StackType;

public class EmptyInstruction extends Instruction {
    
    public EmptyInstruction(){
        super(InstructionType.NOP);
    }

    @Override
    public void process(Context context) {}

    @Override
    public void print(Context context, ScriptPrinter printer) {}

    @Override
    public int getPushCount(StackType type) {
        return 0;
    }

    @Override
    public int getPopCount(StackType type) {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%4d:(%d) => %s<%s>",this.address, this.id,"NULL_INSTRUCTION",this.type);
    }
}
