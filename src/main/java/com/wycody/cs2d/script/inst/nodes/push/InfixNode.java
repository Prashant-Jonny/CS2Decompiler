package com.wycody.cs2d.script.inst.nodes.push;

import com.wycody.cs2d.script.CS2Operator;
import com.wycody.cs2d.script.inst.nodes.PushNode;

/**
 * 
 * @author Walied-Yassen
 * @date Dec 7, 2015
 */
public class InfixNode {

	private CS2Operator operator;
	private PushNode<?> left;
	private PushNode<?> right;

	public InfixNode(PushNode<?> left, CS2Operator operator,PushNode<?> right) {
		this.left = left;
		this.operator = operator;
		this.right = right;
	}

	public PushNode<?> getLeft() {
		return left;
	}

	public void setLeft(PushNode<?> left) {
		this.left = left;
	}

	public PushNode<?> getRight() {
		return right;
	}

	public void setRight(PushNode<?> right) {
		this.right = right;
	}

	public CS2Operator getOperator() {
		return operator;
	}

	public void setOperator(CS2Operator operator) {
		this.operator = operator;
	}

}
