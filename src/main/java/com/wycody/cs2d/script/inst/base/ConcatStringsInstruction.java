package com.wycody.cs2d.script.inst.base;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.StackType;

public class ConcatStringsInstruction extends Instruction {
    private int concatElements;

    public ConcatStringsInstruction() {
        super(InstructionType.MULTISTRING_CONCAT);
    }

    @Override
    public void process(Context context) {
       int length = integerOperand;
       Object[] data = new Object[length];
       for(int index = length; index > 0; index--) {
    	   data[index - 1] = script.popObject(this.address);
       }
		StringBuilder builder = new StringBuilder();
		for (int offset = 0; offset < data.length; offset++) {
			 builder.append(offset == 0 ? "" : " + ").append(data[offset]);
		}
		push(StackType.OBJECT, builder.toString());
    }

    @Override
    public void print(Context context, ScriptPrinter printer) {
        
    }

    @Override
    public void preprocess(Context context){
        concatElements = integerOperand;
    }

    @Override
    public int getPushCount(StackType type) {
        return 1;
    }

    @Override
    public int getPopCount(StackType type) {
        return integerOperand;
    }

    @Override
    public String toString() {
        return String.format("%4d:(%d) => %s<%s>",this.address, this.id,"CONCAT_STRS",this.type);
    }
}
