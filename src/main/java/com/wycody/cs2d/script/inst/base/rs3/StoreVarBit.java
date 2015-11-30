package com.wycody.cs2d.script.inst.base.rs3;

import com.jagex.game.runetek5.config.vartype.bit.VarBitType;
import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.StackType;

public class StoreVarBit extends Instruction {//TODO is this being done correct?

	String variable;
	Object value;
	
	public StoreVarBit(int id, int address) {
		super(id, address, InstructionType.STORE_VAR_BIT);
	}

	@Override
	public void process(Context context) {
		VarBitType type = (VarBitType) objectOperand;
		boolean usePlayerMap = integerOperand == 1;
        variable = "varbits[" + type.id + "]";
        value = integerOperand;
        
	}

	@Override
	public void print(Context context, ScriptPrinter printer) {
		printer.println(variable + " = " + value + ";");
	}

	@Override
	public int getPushCount(StackType type) {
		return 0;
	}

	@Override
	public int getPopCount(StackType type) {
		return 0;
	}
}
