package com.wycody.cs2d.script.inst.nodes.impl;

import com.wycody.cs2d.script.inst.nodes.PushNode;
import com.wycody.cs2d.script.inst.types.StackType;

/**
 * 
 * @author Walied-Yassen
 * @date Dec 5, 2015
 */
public class OperandPush<T> extends PushNode<T> {

	public OperandPush(StackType pushType, T value) {
		super(pushType, value);
	}

	public OperandPush create(StackType type, Object value) {
		switch (type) {
			case INT:
				return new IntPush((Integer) value);
			case OBJECT:
				return new ObjectPush(value);
			case LONG:
				return new LongPush((Long) value);
			default:
				throw new Error("Undefined operand push type for StackType: " + type.name());
		}
	}
}
