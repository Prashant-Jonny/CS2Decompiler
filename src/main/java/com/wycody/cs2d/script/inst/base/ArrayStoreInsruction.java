package com.wycody.cs2d.script.inst.base;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.StackType;

public class ArrayStoreInsruction extends Instruction {
    
    Object index;
    Object value;

    public ArrayStoreInsruction() {
        super(InstructionType.ARRAY_STORE);
    }

    @Override
    public void process(Context context) {
        value = pop(StackType.INT);
        index = pop(StackType.INT);
    }

    @Override
    public int getPushCount(StackType type) {
        return 0;
    }

    @Override
    public int getPopCount(StackType type) {
        return type == StackType.INT ? 2 : 0;
    }

    @Override
    public void print(Context context, ScriptPrinter printer) {
        printer.println("array"+integerOperand+"[" + index + "] = " + value + ";");
    }
    
}
