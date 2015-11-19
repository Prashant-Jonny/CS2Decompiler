package com.wycody.cs2d.script.inst.types;

/**
 * Contains the type of the return for CS2Script
 * @author Walied-Yassen
 * @date Nov 9, 2015
 */
public enum ReturnType {
	
	VOID("void", "return;"),
	INTEGER("int", "return {value};"),
	OBJECT("Object", "return {value};"),
	LONG("long", "return {value};"),
	OBJECT_ARRAY("Object[]", "return new Object[] { {value} };"); 
	
	/**
	 * The type of the script
	 */
	private String type;
	
	/**
	 * The return format for the script
	 */
	private String returnFormat;

	/**
	 * Construct a new {@link ReturnType} element
	 * @param type the type of the script
	 * @param returnFormat the return format for the script
	 */
	private ReturnType(String type, String returnFormat) {
		this.type = type;
		this.returnFormat = returnFormat;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the returnFormat
	 */
	public String getReturnFormat() {
		return returnFormat;
	}

	/**
	 * @param returnFormat
	 *            the returnFormat to set
	 */
	public void setReturnFormat(String returnFormat) {
		this.returnFormat = returnFormat;
	}

}
