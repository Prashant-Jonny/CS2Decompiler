package com.wycody.cs2d.script.inst.base.branch;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionBaseType;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.ReturnInstruction;
import com.wycody.cs2d.script.inst.base.StoreInstruction;
import com.wycody.cs2d.script.inst.types.ReturnType;
import com.wycody.cs2d.script.inst.types.StackType;

/**
 * 
 * @author Walied-Yassen
 * @date Nov 26, 2015
 */
public class InlineConditional extends Instruction {

	// eh. for more safe just use it for condition getting
	private ConditionalInstruction real;

	private Instruction trueInstr;
	private Instruction falseInstr;

	public InlineConditional(ConditionalInstruction real, Instruction trueInstr, Instruction falseInstr) {
		super(InstructionType.INLINE_CONDITIONAL);
		this.real = real;
		this.trueInstr = trueInstr;
		this.falseInstr = falseInstr;
	}

	@Override
	public void process(Context context) {

	}

	@Override
	public void print(Context context, ScriptPrinter printer) {
		if (trueInstr.getType().getBaseType() == InstructionBaseType.RETURN) {
			ReturnInstruction trueReturn = (ReturnInstruction) trueInstr;
			ReturnInstruction falseReturn = (ReturnInstruction) falseInstr;

			if(trueReturn.getReturnType() == ReturnType.BOOLEAN) {
				Object trueVal = trueReturn.getReturnObjects()[0];
				String cond = real.getCondition();
				
				printer.println("return " + cond + ";");
			} else {
				printer.println("return " + real.getCondition() + " ? " + trueReturn.getReturnObjectsText() + " : " + falseReturn.getReturnObjectsText() + ";");
				
			}
		} else if (trueInstr.getType().getBaseType() == InstructionBaseType.STORE) {
			StoreInstruction trueStore = (StoreInstruction) trueInstr;
			StoreInstruction falseStore = (StoreInstruction) falseInstr;
			printer.println(trueStore.getVariable().toString() + " = " + real.getCondition() + " ? (" + trueStore.getValue() + ") : (" + falseStore.getValue() + ")");
		}
	}

	@Override
	public int getPushCount(StackType type) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPopCount(StackType type) {
		// TODO Auto-generated method stub
		return 0;
	}

}
