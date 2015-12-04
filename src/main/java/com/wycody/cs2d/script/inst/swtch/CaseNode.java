package com.wycody.cs2d.script.inst.swtch;

import org.apache.commons.collections4.list.TreeList;

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
	 * The child(s) of the case, (Multiply case)
	 */
	private TreeList<CaseNode> childs;

	/**
	 * Construct a new {@link CaseNode}
	 * 
	 * @param expression
	 *            the expression of the node
	 * @param jumpTarget
	 *            where to go if it success
	 */
	public CaseNode(int expression, int jumpTarget) {
		this.expression = expression;
		this.jumpTarget = jumpTarget;
		childs = new TreeList<CaseNode>();
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

	/**
	 * Add an child node to this node
	 * @param node the child to add
	 */
	public void addChild(CaseNode node) {
		childs.add(node);
	}

	/**
	 * Remove an child from this node
	 * @param node the child to remove
	 */
	public void removeChild(CaseNode node) {
		childs.remove(childs.indexOf(node));
	}

	public TreeList<CaseNode> getChilds() {
		return childs;
	}

	public void setChilds(TreeList<CaseNode> childs) {
		this.childs = childs;
	}

	


}
