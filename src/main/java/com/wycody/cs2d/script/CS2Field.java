package com.wycody.cs2d.script;

/**
 * Represent a field in a CS2Script
 * 
 * @author Walied-Yassen
 * @date Nov 7, 2015
 * @param <T>
 *            the type of the field a.k.a Integer, Boolean, String, Object
 *            whatever
 */
public class CS2Field<T> {

	/**
	 * The address of the field, More like index
	 */
	private int address;

	/**
	 * The name of the field
	 */
	private String name;

	/**
	 * Construct a new {@link CS2Field}
	 * 
	 * @param address
	 *            the address of the field
	 * @param name
	 *            the name of the field
	 */
	public CS2Field(int address, String name) {
		this.address = address;
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public int getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(int address) {
		this.address = address;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
        
        @Override
        public String toString() {
            return this.getName();
        }
}
