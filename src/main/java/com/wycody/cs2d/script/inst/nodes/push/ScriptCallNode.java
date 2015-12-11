package com.wycody.cs2d.script.inst.nodes.push;

import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.inst.nodes.PushNode;

/**
 * 
 * @author Walied-Yassen
 * @date Dec 5, 2015
 */
public class ScriptCallNode {

	private CS2Script calledScript;
	private PushNode<?>[] arguments;

	public ScriptCallNode(CS2Script calledScript, PushNode... arguments) {
		this.calledScript = calledScript;
		this.arguments = arguments;
	}

	public CS2Script getCalledScript() {
		return calledScript;
	}

	public PushNode<?>[] getArguments() {
		return arguments;
	}

}
