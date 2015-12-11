package com.wycody.cs2d.script.inst.nodes.push.impl;

import com.wycody.cs2d.script.inst.nodes.PushNode;
import com.wycody.cs2d.script.inst.nodes.push.MethodNode;
import com.wycody.cs2d.script.inst.types.StackType;

public class MethodPush extends PushNode<MethodNode> {

	public MethodPush(StackType type, MethodNode value) {
		super(type, value);
	}

	@Override
	public String getText() {
		StringBuilder builder = new StringBuilder();
		builder.append(value.getName()).append("(");
		PushNode<?>[] parameters = value.getParameters();
		for (int i = 0; i < parameters.length; i++) {
			PushNode<?> node = parameters[i];
			builder.append(node.getText());
			if(i != parameters.length -1)
				builder.append(", ");
		}
		builder.append(")");
		return builder.toString();
	}

	public static MethodPush[] create(StackType[] types, MethodNode... nodes) {
		MethodPush[] pushes = new MethodPush[nodes.length];
		StackType type = StackType.INT;
		for (int index = 0; index < nodes.length; index++) {
			if(types.length > index && types[index] != null)
				type = types[index];
			pushes[index] = new MethodPush(type, nodes[index]);
		}
		return pushes;
	}

}
