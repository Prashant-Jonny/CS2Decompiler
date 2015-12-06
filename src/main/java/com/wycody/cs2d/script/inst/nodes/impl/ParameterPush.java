package com.wycody.cs2d.script.inst.nodes.impl;

import com.wycody.cs2d.script.CS2Field;
import com.wycody.cs2d.script.inst.nodes.PushNode;
import com.wycody.cs2d.script.inst.types.StackType;

/**
 * 
 * @author Walied-Yassen
 * @date Dec 5, 2015
 * @param <T>
 */
public class ParameterPush<T> extends PushNode<CS2Field<T>> {

	public ParameterPush(StackType type, CS2Field<T> value) {
		super(type, value);

	}

}
