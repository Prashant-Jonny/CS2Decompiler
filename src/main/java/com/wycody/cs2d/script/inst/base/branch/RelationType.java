package com.wycody.cs2d.script.inst.base.branch;

/**
 * Contains the type of the relations, between the conditional branches
 * @author Walied-Yassen
 * @date Nov 12, 2015
 */
public enum RelationType {
	/**
	 * The "&&" type
	 */
	AND("&&"),
	
	/**
	 * The "||" type
	 */
	OR("||");

	/**
	 * The symbol of the relation
	 */
	private String symbol;

	/**
	 * Construct a new {@link RelationType}
	 * 
	 * @param symbol
	 *            the symbol of the relation
	 */
	private RelationType(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

}