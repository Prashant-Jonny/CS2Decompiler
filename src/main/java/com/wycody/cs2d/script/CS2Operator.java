package com.wycody.cs2d.script;

/**
 * @author Walied-Yassen
 * @date Dec 7, 2015
 */
public enum CS2Operator {

	// Arithmetic
	SUM("+"),
	SUB("-"),
	MULTI("*"),
	DIVIDE("/"),
	MODULUS("%"),
	
	// Binary
	BITWISE_AND("&"),
	BITWISE_OR("|"),
	BITWISE_XOR("^"),
	BITWISE_COMPLIMENT("~"),
	SHIFT_LEFT("<<"),
	SHIFT_RIGHT(">>"),
	
	// Logical
	LOGICAL_AND("&&"),
	LOGICAL_OR("||"),
	
	// Relational
	RELATIONAL_EQUAL("=="),
	RELATIONAL_NOT_EQUAL("!="),
	RELATIONAL_GREATER_THAN(">"),
	RELATIONAL_LESS_THAN("<"),
	RELATIONAL_GREATER_THAN_OR_EQUAL(">="),
	RELATIONAL_LESS_THAN_OR_EQUAL("<="),
	;
	
	private String symbol;
	
	private CS2Operator(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}

}
