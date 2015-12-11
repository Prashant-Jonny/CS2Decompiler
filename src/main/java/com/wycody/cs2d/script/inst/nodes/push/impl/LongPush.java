package com.wycody.cs2d.script.inst.nodes.push.impl;

import com.wycody.cs2d.script.inst.types.StackType;

/**
 * 
 * @author Walied-Yassen
 * @date Dec 5, 2015
 */
public class LongPush extends OperandPush<Long> {

	public LongPush(Long value) {
		super(StackType.LONG, value);
	}

	@Override
	public String getText() {
		return String.valueOf(value);
	}

}
