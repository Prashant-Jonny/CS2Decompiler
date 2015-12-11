package com.wycody.cs2d.script.inst.nodes.push;

import com.wycody.cs2d.script.CS2Array;

/**
 * 
 * @author Walied-Yassen
 * @date Dec 7, 2015
 */
public class ArrayNode {

	private CS2Array array;
	private int childIndex;

	public ArrayNode(CS2Array array, int childId) {
		this.array = array;
		this.childIndex = childId;
	}

	public CS2Array getArray() {
		return array;
	}

	public void setArray(CS2Array array) {
		this.array = array;
	}

	public int getChildIndex() {
		return childIndex;
	}

	public void setChildIndex(int childId) {
		this.childIndex = childId;
	}

}
