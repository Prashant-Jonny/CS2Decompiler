package com.wycody.cs2d.script.inst.base.branch;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.flow.impl.BasicBlock;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.StackType;
import com.wycody.utils.DynamicArray;

/**
 * Handles the IF block Instruction
 * 
 * @author Walied-Yassen
 * @date Nov 12, 2015
 */
public class ConditionalInstruction extends BranchInstruction {

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
	 * Is while loop?
	 */
	private boolean whileLoop;

	/**
	 * The relations of the instruction
	 */
	private DynamicArray<Relation> relations;

	public BasicBlock elseBlock;
	private StackType stackTpe;
	private BasicBlock customTarget;

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
		this.stackTpe = stackType;
		this.operator = operator;
		relations = new DynamicArray<Relation>(Relation.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%4d:(%d) => %s<%s> %s \t JUMPIF => %d  Destination: %d", this.address, this.id, "Compare", this.type, this.operator, this.integerOperand, this.getJumpTarget());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wycody.cs2d.script.inst.Instruction#process(com.wycody.cs2d.Context)
	 */
	@Override
	public void process(Context context) {
		left = this.pop(this.stackTpe);
		right = this.pop(this.stackTpe);
	}

	/**
	 * Gets all conditions including the relations as text
	 * 
	 * @return the text of the conditions
	 */
	public String getCondition() {
		StringBuilder builder = new StringBuilder((relations.size() > 0 ? "(" : "") + right + " " + operator + " " + left);
		for (Relation relation : relations) {
			builder.append(" ").append(relation.getType().getSymbol()).append(" ").append(relation.getTarget().getCondition());
		}
		if (relations.size() > 0) {
			builder.append(")");
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
		printer.println("if (" + getCondition() + " ) {");
		printer.tab();

		BasicBlock block = getTarget();
			
		block.print(context, printer);
		printer.untab();
		printer.println("}" + (elseBlock != null ? " else {" : ""));
		if (elseBlock != null) {
			printer.tab();
			elseBlock.print(context, printer);
			printer.untab();
			printer.println("}");
		}
	}

	@Override
	public int getPushCount(StackType type) {
		return 0;
	}

	@Override
	public int getPopCount(StackType type) {
		if (type == this.stackTpe) {
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
	 * @return the whileLoop
	 */
	public boolean isWhileLoop() {
		return whileLoop;
	}

	/**
	 * @param whileLoop
	 *            the whileLoop to set
	 */
	public void setWhileLoop(boolean whileLoop) {
		this.whileLoop = whileLoop;
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

	public void setCustomTarget(BasicBlock target) {
		customTarget = target;
	}

}
