package com.wycody.cs2d.utils;

/**
 * Used fro static vars
 * 
 * @author Walied-Yassen
 * @date Nov 30, 2015
 */
public enum GIComponentType {
	SCROLL_VIEW(0),
	RECTANGLE(3),
	TEXT(4),
	SPRITE(5),
	MODEL(6);

	private int id;

	private GIComponentType(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public static Object forObj(Object o) {
		if(o instanceof Integer) {
			return forInt((int) o);
		}
		return o;
	}
	public static GIComponentType forInt(int id) {
		for (GIComponentType type : values()) {
			if (type.getId() == id) {
				return type;
			}
		}
		return null;
	}
}
