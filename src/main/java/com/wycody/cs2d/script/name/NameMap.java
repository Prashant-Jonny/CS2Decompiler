package com.wycody.cs2d.script.name;

import com.wycody.cs2d.script.inst.types.StackType;
import com.wycody.utils.DynamicArray;

/**
 * 
 * @author Walied-Yassen
 * @date Nov 28, 2015
 */
public class NameMap {

	/**
	 * The script package path
	 */
	private String packageName;
	
	/**
	 * The script name
	 */
	private String name;
	
	/**
	 * The script arg(s) types example: {@link StackType#OBJECT}
	 */
	private DynamicArray<StackType> argsType;

	/**
	 * The script arg(s) names example: "text"
	 */
	private DynamicArray<String> argsName;

	/**
	 * Check for the parameters when finding
	 */
	private boolean checkParameters;

	/**
	 * Construct a new {@link NameMap} without the need for the builder methods
	 */
	public NameMap(String packageName, String name) {
		this.packageName = packageName;
		this.name = name;
		this.argsType = new DynamicArray<>(StackType.class);
		this.argsName = new DynamicArray<>(String.class);
		checkParameters = true;
	}


	/**
	 * @return the argsType
	 */
	public DynamicArray<StackType> getArgsType() {
		return argsType;
	}

	/**
	 * @param argsType
	 *            the argsType to set
	 */
	public void setArgsType(DynamicArray<StackType> argsType) {
		this.argsType = argsType;
	}

	/**
	 * @return the argsName
	 */
	public DynamicArray<String> getArgsName() {
		return argsName;
	}

	/**
	 * @param argsName
	 *            the argsName to set
	 */
	public void setArgsName(DynamicArray<String> argsName) {
		this.argsName = argsName;
	}

	public NameMap addType(StackType type, String... parts) {
		for (String name : parts) {
			argsType.add(type);
			argsName.add(name);
		}
		return this;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}


	/**
	 * @param packageName the packageName to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public boolean isCheckParameters() {
		return checkParameters;
	}

	public void setCheckParameters(boolean checkParameters) {
		this.checkParameters = checkParameters;
	}

}
