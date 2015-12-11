package com.wycody.cs2d.script.inst.nodes.push.impl;

import com.wycody.cs2d.script.inst.nodes.PushNode;
import com.wycody.cs2d.script.inst.nodes.push.ScriptCallNode;
import com.wycody.cs2d.script.inst.types.StackType;

/**
 * 
 * @author Walied-Yassen
 * @date Dec 5, 2015
 */
public class ScriptCallPush extends PushNode<ScriptCallNode> {

	public ScriptCallPush(StackType pushType, ScriptCallNode value) {
		super(pushType, value);
	}

	@Override
	public String getText() {
		StringBuilder builder = new StringBuilder();
		builder.append("~").append(value.getCalledScript().getName()).append("(");
		PushNode<?>[] arguments = value.getArguments();
		for (int i = 0; i < arguments.length; i++) {
			PushNode<?> arg = arguments[i];
			builder.append(arg.getText());
			if (i != arguments.length - 1)
				builder.append(", ");

		}
		return builder.toString();

	}

}
