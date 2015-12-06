package com.wycody.cs2d.script.inst.nodes.impl;

import com.wycody.cs2d.script.inst.nodes.PushNode;
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

}
