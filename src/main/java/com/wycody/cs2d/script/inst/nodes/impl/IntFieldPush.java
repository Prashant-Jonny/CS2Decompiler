package com.wycody.cs2d.script.inst.nodes.impl;

import com.wycody.cs2d.script.CS2Field;
import com.wycody.cs2d.script.inst.types.StackType;

/**
 * Maybe merge all into ParameterPush based on stack type?
 * @author Walied-Yassen
 * @date Dec 5, 2015
 */
public class IntFieldPush extends ParameterPush<Integer> {

	public IntFieldPush(CS2Field<Integer> value) {
		super(StackType.INT, value);

	}

}
