package com.wycody.cs2d.script.inst.nodes.push;

import com.wycody.cs2d.script.inst.nodes.PushNode;

/**
 * 
 * @author Walied-Yassen
 * @date Dec 5, 2015
 */
public class MethodNode {

	private String name;
	private PushNode<?>[] parameters;

	public MethodNode(String name, PushNode... parameters) {
		this.name = name;
		this.parameters = parameters;
	}

	public PushNode<?>[] getParameters() {
		return parameters;
	}

	public String getName() {
		return name;
	}

	public static MethodNode[] create(String... names) {
		MethodNode[] nodes = new MethodNode[names.length];
		for (int index = 0; index < names.length; index++) {
			nodes[index] = new MethodNode(names[index]);
		}
		return nodes;
	}

}
