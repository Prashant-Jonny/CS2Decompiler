package com.wycody.cs2d.script.inst.nodes.push.impl;

import com.wycody.cs2d.script.inst.nodes.PushNode;
import com.wycody.cs2d.script.inst.nodes.push.InfixNode;
import com.wycody.cs2d.script.inst.types.StackType;

public class InfixPush extends PushNode<InfixNode>{

	public InfixPush(StackType pushType, InfixNode value) {
		super(pushType, value);
	}

	@Override
	public String getText() {
		return value.getLeft().getText() + " " + value.getOperator().getSymbol() + " " + value.getRight().getText();
	}

}
