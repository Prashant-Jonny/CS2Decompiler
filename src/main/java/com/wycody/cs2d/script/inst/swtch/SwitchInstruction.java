package com.wycody.cs2d.script.inst.swtch;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.StackType;

/**
 * Handles the switch block instruction
 * @author Walied-Yassen
 * @date Nov 12, 2015
 */
public class SwitchInstruction extends Instruction {
	/**
	 * The switch block of the instruction
	 */
	private SwitchBlock block;
	
	/**
	 * Construct a new {@link SwitchInstruction}
	 * @param type the type of the instruction
	 */
	public SwitchInstruction(InstructionType type) {
		super(type);
	}

	/*
	 * (non-Javadoc)
	 * @see com.wycody.cs2d.script.inst.Instruction#process(com.wycody.cs2d.Context)
	 */
	@Override
	public void process(Context context) {
		block = script.getSwitchBlocks()[integerOperand];
		block.setExpression(script.popInteger(this.address));
		block.setAddress(address);
	}

	/*
	 * (non-Javadoc)
	 * @see com.wycody.cs2d.script.inst.Instruction#print(com.wycody.cs2d.Context, com.wycody.cs2d.print.ScriptPrinter)
	 */
	@Override
	public void print(Context context, ScriptPrinter printer) {
		printer.println("switch (" + block.getExpression() + ") {");
		printer.tab();

		printer.untab();
		printer.println("}");
	}

	@Override
	public int getPushCount(StackType type) {
		return 0;
	}

	@Override
	public int getPopCount(StackType type) {
		return 0;
	}

	/**
	 * @return the block
	 */
	public SwitchBlock getBlock() {
		return block;
	}

	/**
	 * @param block the block to set
	 */
	public void setBlock(SwitchBlock block) {
		this.block = block;
	}

}
