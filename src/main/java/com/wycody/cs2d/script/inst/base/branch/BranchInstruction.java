package com.wycody.cs2d.script.inst.base.branch;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.script.flow.impl.BasicBlock;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;

/**
 * The base for every branch instructions, switches, if blocks, whiles, for
 * loops
 * 
 * @author Walied-Yassen
 * @date Nov 12, 2015
 */
public abstract class BranchInstruction extends Instruction {

	/**
	 * Construct a new {@link BranchInstruction}
	 * 
	 * @param type
	 *            the type of the branch, could be switch, while etc..
	 */
	public BranchInstruction(InstructionType type) {
		super(type);
	}


	@Override
	public void process(Context context) {

	}

	/**
	 * Get the target block, The block that's located at
	 * {@link #getJumpTarget()}
	 * 
	 * @return the block
	 */
	public BasicBlock getTarget() {
		return script.getBlockAt(getJumpTarget(), false);
	}

	/**
	 * Where the target block is located at
	 * 
	 * @return the address of the block
	 */
	public int getJumpTarget() {
		return address + integerOperand + 1;
	}

	/**
	 * Gets the "GOTO someBlock" strng
	 * 
	 * @return the string
	 */
	public String getGotoString() {
		return "GOTO\t blockAt(" + getJumpTarget() + ")";
	}
}
