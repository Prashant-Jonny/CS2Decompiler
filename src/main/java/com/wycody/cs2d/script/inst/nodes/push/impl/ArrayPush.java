package com.wycody.cs2d.script.inst.nodes.push.impl;

import com.wycody.cs2d.script.inst.nodes.PushNode;
import com.wycody.cs2d.script.inst.nodes.push.ArrayNode;
import com.wycody.cs2d.script.inst.types.StackType;

/**
 * 
 * @author Walied-Yassen
 * @date Dec 7, 2015
 */
public class ArrayPush extends PushNode<ArrayNode>{

	public ArrayPush(StackType pushType, ArrayNode value) {
		super(pushType, value);
	}

	@Override
	public String getText() {
		return value.getArray().getName() + "[" + value.getChildIndex() + "]";
	}

}
