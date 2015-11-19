package com.wycody.cs2d.script.inst.swtch;

import com.wycody.cs2d.node.Node;

public class CaseNode extends Node {

	/**
	 * The expression of the case ex: "{@code case EXPRESSION:}"
	 */
	private int expression;

	/**
	 * The jump target if the case success
	 */
	private int jumpTarget;

	/**
	 * Construct a new {@link CaseNode}
	 * @param expression the expression of the node
	 * @param jumpTarget where to go if it success
	 */
	public CaseNode(int expression, int jumpTarget) {
		this.expression = expression;
		this.jumpTarget = jumpTarget;
	}

	/**
	 * @return the expression
	 */
	public int getExpression() {
		return expression;
	}

	/**
	 * @param expression
	 *            the expression to set
	 */
	public void setExpression(int expression) {
		this.expression = expression;
	}

	/**
	 * @return the jumpTarget
	 */
	public int getJumpTarget() {
		return jumpTarget;
	}

	/**
	 * @param jumpTarget
	 *            the jumpTarget to set
	 */
	public void setJumpTarget(int jumpTarget) {
		this.jumpTarget = jumpTarget;
	}

}
