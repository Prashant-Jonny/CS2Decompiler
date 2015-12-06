package com.wycody.cs2d.script.inst.nodes.impl;

import com.wycody.cs2d.script.inst.nodes.MethodNode;
import com.wycody.cs2d.script.inst.nodes.PushNode;
import com.wycody.cs2d.script.inst.types.StackType;

public class MethodPush extends PushNode<MethodNode> {

	public MethodPush(StackType type, MethodNode value) {
		super(type, value);
	}

}
