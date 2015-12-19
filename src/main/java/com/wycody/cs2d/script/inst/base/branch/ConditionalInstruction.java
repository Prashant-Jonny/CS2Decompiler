package com.wycody.cs2d.script.inst.base.branch;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.flow.impl.BasicBlock;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.StoreInstruction;
import com.wycody.cs2d.script.inst.types.StackType;
import com.wycody.cs2d.utils.CS2Utils;
import com.wycody.utils.DynamicArray;

/**
 * Handles the IF block Instruction
 * 
 * @author Walied-Yassen
 * @date Nov 12, 2015
 */
public class ConditionalInstruction extends BranchInstruction {

	public static final int IF_BLOCK = 0, WHILE_BLOCK = 1, FOR_BLOCK = 2;
	public static final boolean REMOVE_BRACKETS_WHEN_CAN = false;

	private StackType stackType;

	/**
	 * The operator of this instruction, "==", "<", ">" etc..
	 */
	private String operator;

	/**
	 * The left part of the statement
	 */
	private Object left;

	/**
	 * The right part of the statement
	 */
	private Object right;

	/**
	 * Is while loop? is for loop? is normal?
	 */
	private int blockType;

	/**
	 * The relations of the instruction
	 */
	private DynamicArray<Relation> relations;

	private DynamicArray<ConditionalInstruction> elseIfs;

	private BasicBlock elseBlock;

	private BasicBlock customTarget;

	// for things
	private StoreInstruction forVarDeclare;
	private StoreInstruction forVarIncr;

	/**
	 * Construct a new {@link ConditionalInstruction}
	 * 
	 * @param operator
	 *            the operator of the instruction, ">"...
	 * @param type
	 *            the type of the instruction, INT_EQ...
	 * @param stackType
	 *            the popping action
	 */
	public ConditionalInstruction(String operator, InstructionType type, StackType stackType) {
		super(type);
		this.stackType = stackType;
		this.operator = operator;
		relations = new DynamicArray<Relation>(Relation.class);
		elseIfs = new DynamicArray<>(ConditionalInstruction.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%4d:(%d) => %s<%s> %s \t JUMPIF => %d  Destination: %d", this.address, this.id, "Compare", this.blockType, this.operator, this.integerOperand, this.getJumpTarget());
	}

	@Override
	public int getJumpTarget() {
		if (customTarget != null) {
			return customTarget.getAddress();
		}
		return super.getJumpTarget();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wycody.cs2d.script.inst.Instruction#process(com.wycody.cs2d.Context)
	 */
	@Override
	public void process(Context context) {
		left = this.pop(this.stackType);
		right = this.pop(this.stackType);
	}

	/**
	 * Gets all conditions including the relations as text
	 * 
	 * @return the text of the conditions
	 */
	public String getCondition() {
		StringBuilder builder = new StringBuilder((relations.size() > 0 ? "" : "") + CS2Utils.fixCondition(right, operator, left));
		for (Relation relation : relations) {
			builder.append(" ").append(relation.getType().getSymbol()).append(" ").append(relation.getTarget().getCondition());
		}
		if (relations.size() > 0) {
			builder.append("");
		}
		return builder.toString();
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
		if (blockType == WHILE_BLOCK) {
			printer.println("while (" + getCondition() + ") {");
			printer.tab();
			BasicBlock block = getTarget();
			if (!context.isDebug()) {
				block.print(context, printer);
			} else {
				printer.println(getGotoString());
			}
			printer.untab();
			printer.println("}");

		} else if (blockType == FOR_BLOCK) {
			printer.println("for (" + (forVarDeclare != null ? forVarDeclare.getPrintString(1, true) : ";") + " " + getCondition() + "; " + forVarIncr.getPrintString(0, false) + ") {");
			printer.tab();
			BasicBlock block = getTarget();
			if (!context.isDebug()) {
				block.print(context, printer);
			} else {
				printer.println(getGotoString());
			}
			printer.untab();
			printer.println("}");
		} else {
			BasicBlock block = getTarget();

			boolean needBrackets = needBrackets(block);
			printer.println("if (" + getCondition() + ")" + (needBrackets ? " {" : ""));
			printer.tab();

			if (!context.isDebug()) {
				block.print(context, printer);
			} else {
				printer.println(getGotoString());
			}
			printer.untab();

			for (ConditionalInstruction elseIf : elseIfs) {
				boolean before = needBrackets;
				needBrackets = needBrackets(elseIf.getTarget());
				printer.println((before ? "} " : "") + "else if(" + elseIf.getCondition() + ")" + (needBrackets ? " {" : ""));
				printer.tab();
				elseIf.getTarget().print(context, printer);
				printer.untab();
			}
			boolean before = needBrackets;
			needBrackets = elseBlock != null && needBrackets(elseBlock);
			printer.println((before ? "} " : "") + (elseBlock != null ? "else" + (needBrackets ? " {" : "") : ""));
			if (elseBlock != null) {
				printer.tab();
				if (!context.isDebug()) {
					elseBlock.print(context, printer);
				} else {
					printer.println("GOTO\tblockAt(" + elseBlock.getAddress() + ")");
				}
				printer.untab();
				if (needBrackets)
					printer.println("}");
			}

		}
	}

	private boolean needBrackets(BasicBlock block) {
		if (!REMOVE_BRACKETS_WHEN_CAN)
			return true;
		int count = block.getPrintableInstructions(null).size();
		return count > 1 || count == 0;
	}

	public void printIfBlock(Context context, ScriptPrinter printer) {

	}

	@Override
	public int getPushCount(StackType type) {
		return 0;
	}

	@Override
	public int getPopCount(StackType type) {
		if (type == this.stackType) {
			return 2;
		}
		return 0;
	}

	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * @param operator
	 *            the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * @return the left
	 */
	public Object getLeft() {
		return left;
	}

	/**
	 * @param left
	 *            the left to set
	 */
	public void setLeft(Object left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public Object getRight() {
		return right;
	}

	/**
	 * @param right
	 *            the right to set
	 */
	public void setRight(Object right) {
		this.right = right;
	}

	/**
	 * @return the relations
	 */
	public DynamicArray<Relation> getRelations() {
		return relations;
	}

	/**
	 * @param relations
	 *            the relations to set
	 */
	public void setRelations(DynamicArray<Relation> relations) {
		this.relations = relations;
	}

	public BasicBlock getElse() {
		return elseBlock;
	}

	public void setElse(BasicBlock falseBlock) {
		elseBlock = falseBlock;
	}

	@Override
	public BasicBlock getTarget() {
		if (customTarget != null) {
			return customTarget;
		}
		return super.getTarget();

	}

	public void addElseIf(ConditionalInstruction instruction) {
		for (ConditionalInstruction elseIf : instruction.elseIfs) {
			elseIfs.add(elseIf);
		}
		elseIfs.add(instruction);
	}

	public BasicBlock getCustomTarget() {
		return customTarget;
	}

	public void setCustomTarget(BasicBlock customTarget) {
		this.customTarget = customTarget;
	}

	/**
	 * @return the blockType
	 */
	public int getBlockType() {
		return blockType;
	}

	/**
	 * @param blockType
	 *            the blockType to set
	 */
	public void setBlockType(int blockType) {
		this.blockType = blockType;
	}

	public boolean isWhileLoop() {
		return blockType == WHILE_BLOCK;
	}

	public boolean isForLoop() {
		return blockType == FOR_BLOCK;
	}

	/**
	 * @return the forVarDeclare
	 */
	public StoreInstruction getForVarDeclare() {
		return forVarDeclare;
	}

	/**
	 * @param forVarDeclare
	 *            the forVarDeclare to set
	 */
	public void setForVarDeclare(StoreInstruction forVarDeclare) {
		this.forVarDeclare = forVarDeclare;
	}

	/**
	 * @return the forVarIncr
	 */
	public StoreInstruction getForVarIncr() {
		return forVarIncr;
	}

	/**
	 * @param forVarIncr
	 *            the forVarIncr to set
	 */
	public void setForVarIncr(StoreInstruction forVarIncr) {
		this.forVarIncr = forVarIncr;
	}

	/**
	 * @return this block has else?
	 */
	public boolean hasElse() {
		return elseBlock != null;
	}

	public void addRelation(RelationType type, ConditionalInstruction instruction) {
		relations.add(new Relation(instruction, type));
	}

	public DynamicArray<ConditionalInstruction> getElseIfs() {
		return elseIfs;
	}
}
