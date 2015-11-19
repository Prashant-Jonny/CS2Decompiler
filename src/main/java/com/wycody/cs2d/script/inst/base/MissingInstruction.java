package com.wycody.cs2d.script.inst.base;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.StackType;

public class MissingInstruction extends Instruction {
    public MissingInstruction() {
		super(InstructionType.NULL);
	}

	@Override
	public void process(Context context) {
        System.err.println("Missing instruction: [id=" + getId() +", intOperand: " + getIntegerOperand() + ", objOperand: " + getObjectOperand() + ", scriptId: " + script.getId() + "]");
    }

	@Override
	public void print(Context context, ScriptPrinter printer) {
		printer.println("Missing instruction: [id=" + getId() +", intOperand: " + getIntegerOperand() + ", objOperand: " + getObjectOperand() + "]");
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
        return String.format("%4d:(%d) => %s<%s>",this.address, this.id,"MISSING_INSTRUCTION",this.type);
    }
}
