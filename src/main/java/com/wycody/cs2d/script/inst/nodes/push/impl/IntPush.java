package com.wycody.cs2d.script.inst.nodes.push.impl;

import com.wycody.cs2d.script.inst.types.StackType;

/**
 * Maybe merge all into SimplePush with StackType?
 * 
 * @author Walied-Yassen
 * @date Dec 5, 2015
 */
public class IntPush extends OperandPush<Integer> {

	public IntPush(Integer value) {
		super(StackType.INT, value);
	}

	@Override
	public String getText() {
		return String.valueOf(value);
	}

}
