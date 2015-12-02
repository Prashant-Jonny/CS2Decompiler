package com.wycody.cs2d.script.inst.swtch;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.flow.impl.BasicBlock;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionBaseType;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.branch.JumpInstruction;
import com.wycody.cs2d.script.inst.types.StackType;

/**
 * Handles the switch block instruction
 * 
 * @author Walied-Yassen
 * @date Nov 12, 2015
 */
public class SwitchInstruction extends Instruction {
	/**
	 * The switch block of the instruction
	 */
	private SwitchBlock block;

	/**
	 * The default case jump instruction
	 */
	private Instruction defaultInstruction;

	private BasicBlock defaultBlock;

	/**
	 * Construct a new {@link SwitchInstruction}
	 * 
	 * @param type
	 *            the type of the instruction
	 */
	public SwitchInstruction(InstructionType type) {
		super(type);
		defaultBlock = new BasicBlock(-1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wycody.cs2d.script.inst.Instruction#process(com.wycody.cs2d.Context)
	 */
	@Override
	public void process(Context context) {
		block = script.getSwitchBlocks()[integerOperand];
		block.setExpression(script.popInteger(this.address));
		block.setAddress(address);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wycody.cs2d.script.inst.Instruction#print(com.wycody.cs2d.Context,
	 * com.wycody.cs2d.print.ScriptPrinter)
	 */
	@Override
	public void print(Context context, ScriptPrinter printer) {
		printer.println("switch (" + block.getExpression() + ") {");
		printer.tab();
		for (CaseNode node : block.getCases()) {
			printer.println("case " + node.getExpression() + ":");
			for (CaseNode child : node.getChilds()) {
				printer.println("case " + child.getExpression() + ":");
			}
			printNode(context, printer, getTarget(node));
		}
		if (defaultInstruction != null && defaultBlock.getInstructions().size() > 0) {
			printer.println("default:");
			printNode(context, printer, defaultBlock);

		}
		printer.untab();
		printer.println("}");
	}

	private void printNode(Context context, ScriptPrinter printer, BasicBlock block) {
		printer.tab();
		// block.print(context, printer);
		if (!context.isDebug()) {
			block.print(context, printer);
		} else {
			printer.println("GOTO\tblockAt(" + block.getAddress() + ")");
		}
		BasicBlock reach = block.getLastReachable();
		if (reach != null) {
			if (reach.getInstructions().last() == null || reach.getInstructions().last().getType().getBaseType() != InstructionBaseType.RETURN) {
				printer.println("break;");
			}
		}

		printer.untab();
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
	 * @param block
	 *            the block to set
	 */
	public void setBlock(SwitchBlock block) {
		this.block = block;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Switch SWITCH_BLOCK[").append(integerOperand);
		sb.append(")\n");

		SwitchBlock block = script.getSwitchBlock(integerOperand);
		for (CaseNode node : block.getCases()) {
			sb.append("\t\t").append(node.getExpression()).append(" -> ").append(node.getJumpTarget() + 1 + this.address).append("\n");
		}
		return sb.toString();
	}

	public BasicBlock getTarget(CaseNode node) {
		return script.getBlockAt(getJumpTarget(node));
	}

	private int getJumpTarget(CaseNode node) {
		return 1 + address + node.getJumpTarget();
	}

	public BasicBlock[] getTargets() {
		BasicBlock[] targets = new BasicBlock[block.getCases().size() + (defaultInstruction != null && defaultInstruction instanceof JumpInstruction ? 1 : 0)];
		for (int caseIndex = 0; caseIndex < block.getCases().size(); caseIndex++) {
			targets[caseIndex] = getTarget(block.getCase(caseIndex));
		}
		if (defaultInstruction != null && defaultInstruction instanceof JumpInstruction) {
			targets[targets.length - 1] = defaultBlock;
		}
		return targets;
	}

	public void setDefaultCase(JumpInstruction defaultInstruction) {
		if(defaultBlock == null) {
			defaultBlock = script.getGenerator().generateCustomBlock();
		}
		this.defaultInstruction = defaultInstruction;
		defaultBlock.getInstructions().clear();
		defaultBlock.addInstruction(defaultInstruction);

	}

	public Instruction getDefaultInstruction() {
		return defaultInstruction;
	}

}
