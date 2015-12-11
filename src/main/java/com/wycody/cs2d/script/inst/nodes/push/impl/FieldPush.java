package com.wycody.cs2d.script.inst.nodes.push.impl;

import com.wycody.cs2d.script.CS2Field;
import com.wycody.cs2d.script.inst.nodes.PushNode;
import com.wycody.cs2d.script.inst.types.StackType;

/**
 * 
 * @author Walied-Yassen
 * @date Dec 5, 2015
 * @param <T>
 */
public abstract class FieldPush<T> extends PushNode<CS2Field<T>> {

	public FieldPush(StackType type, CS2Field<T> value) {
		super(type, value);

	}

	public static FieldPush create(StackType type, CS2Field field) {
		switch (type) {
			case INT:
				return new IntFieldPush(field);
			case OBJECT:
				return new ObjectFieldPush(field);
			case LONG:
				return new LongFieldPush(field);
			default:
				throw new Error("Undefined field push type for StackType: " + type.name());
		}
	}

}
