package com.wycody.cs2d.script.inst.base.branch;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;

/**
 * 
 * @author Walied-Yassen
 * @date Nov 25, 2015
 */
public class NaturalFlowJump extends JumpInstruction {

	private int targetJump;

	public NaturalFlowJump(int targetJump) {
		this.targetJump = targetJump;
	}

	@Override
	public int getJumpTarget() {
		return targetJump;
	}

	@Override
	public void print(Context context, ScriptPrinter printer) {
		if (!context.isDebug()) {
			return;
		}
		super.print(context, printer);
	}

	@Override
	public String getGotoString() {
		return "NAT_" + super.getGotoString();
	}
}
