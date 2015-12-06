package com.wycody.cs2d.script.inst.nodes;

/**
 * 
 * @author Walied-Yassen
 * @date Dec 5, 2015
 */
public class MethodNode {

	private PushNode<?>[] parameters;

	public MethodNode(PushNode... parameters) {
		this.parameters = parameters;
	}

	public PushNode<?>[] getParameters() {
		return parameters;
	}

}
