package com.wycody.cs2d.script.inst.nodes.impl;

import com.wycody.cs2d.script.inst.nodes.PushNode;
import com.wycody.cs2d.script.inst.types.StackType;

/**
 * This is treated as integer but in the source code is no, so we need it as
 * push type to determine the field is boolean or is integer
 * 
 * @author Walied-Yassen
 * @date Dec 5, 2015
 */
public class BooleanPush extends PushNode<Boolean> {

	public BooleanPush(Boolean value) {
		super(StackType.INT, value);
	}

}
