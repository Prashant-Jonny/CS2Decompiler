package com.wycody.cs2d.script.inst.base.branch;

import com.wycody.cs2d.script.CS2Script;

/**
 * 
 * @author Walied-Yassen
 * @date Nov 25, 2015
 */
public class CustomJump extends JumpInstruction {

	private int targetJump;

	public CustomJump(CS2Script script, int targetJump) {
		this.script = script;
		this.targetJump = targetJump;
	}

	@Override
	public int getJumpTarget() {
		return targetJump;
	}

	@Override
	public String getGotoString() {
		return "CUST_" + super.getGotoString();
	}

}
