package com.wycody.cs2d.script.inst.nodes.push.impl;

import com.wycody.cs2d.script.CS2Field;
import com.wycody.cs2d.script.inst.types.StackType;

/**
 * Maybe merge all into FieldPush based on stack type?
 * @author Walied-Yassen
 * @date Dec 5, 2015
 */
public class IntFieldPush extends FieldPush<Integer> {

	public IntFieldPush(CS2Field<Integer> value) {
		super(StackType.INT, value);

	}

	@Override
	public String getText() {
		return value.getName();
	}

}
