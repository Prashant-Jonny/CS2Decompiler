package com.wycody.cs2d.script.inst.nodes.push.impl;

import com.wycody.cs2d.script.CS2Field;
import com.wycody.cs2d.script.inst.types.StackType;

/**
 * 
 * @author Walied-Yassen
 * @date Dec 5, 2015
 */
public class ObjectFieldPush extends FieldPush<Object> {

	public ObjectFieldPush(CS2Field<Object> value) {
		super(StackType.OBJECT, value);
	}

	@Override
	public String getText() {
		return value.getName();
	}

}
