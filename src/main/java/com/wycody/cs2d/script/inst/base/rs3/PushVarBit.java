package com.wycody.cs2d.script.inst.base.rs3;

import com.jagex.game.runetek5.config.vartype.VarType;
import com.jagex.game.runetek5.config.vartype.bit.VarBitType;
import com.jagex.game.runetek5.config.vartype.constants.BaseVarType;
import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.StackType;

public class PushVarBit extends Instruction {

	public PushVarBit(int id, int address) {
		super(id, address, InstructionType.PUSH_VAR_BIT);
	}

	@Override
	public void process(Context context) {
		VarBitType varbitType = (VarBitType) objectOperand;
		push(StackType.INT, "%varbit" + varbitType.id);   
		
		//with basevar
		//push(StackType.INT, "var" + varbitType.baseVarType.name().toLowerCase() + "s[" + varbitType.baseVarKey + "].varbits[" + varbitType.id + "]"); 
		
	}

	@Override
	public void print(Context context, ScriptPrinter printer) {
		
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