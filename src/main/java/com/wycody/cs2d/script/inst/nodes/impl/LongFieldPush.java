package com.wycody.cs2d.script.inst.nodes.impl;

import com.wycody.cs2d.script.CS2Field;
import com.wycody.cs2d.script.inst.types.StackType;

/**
 * 
 * @author Walied-Yassen
 * @date Dec 5, 2015
 */
public class LongFieldPush extends ParameterPush<Long> {

	public LongFieldPush(CS2Field<Long> value) {
		super(StackType.LONG, value);
	}

}
