package com.wycody.cs2d.script.inst.base;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.StackType;

public class PushInstruction extends Instruction {
    
    public static enum Type { OPERAND, FIELD, ARRAY };

    private final Type fieldType;
    private final StackType stackType;
    private final StackType srcType;

    public PushInstruction(InstructionType type, Type fieldType, StackType pushType, StackType srcType){
        super(type);
        this.stackType = pushType;
        this.fieldType = fieldType;
        this.srcType = srcType;
    }

    public PushInstruction(InstructionType type, Type fieldType, StackType pushType){
        this(type, fieldType, pushType, pushType);
    }

    @Override
    public void process(Context context) {
        switch (this.fieldType) {
            case OPERAND:
                this.push(this.stackType,this.getOperand(this.srcType));
                break;
            case FIELD:
                this.push(this.stackType,this.getFields(this.srcType)[this.integerOperand]);
                break;
            case ARRAY:
                this.push(this.stackType, "array"+this.integerOperand+"["+this.pop(this.srcType)+"]");
                break;
        }
    }

    @Override
    public void print(Context context, ScriptPrinter printer) {}

    @Override
    public int getPushCount(StackType type) {
        if(type == stackType)
            return 1;
        return 0;
    }

    @Override
    public int getPopCount(StackType type) {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%4d:(%d) => %s<%s>",this.address, this.id,"PUSH",this.fieldType);
    }
}
