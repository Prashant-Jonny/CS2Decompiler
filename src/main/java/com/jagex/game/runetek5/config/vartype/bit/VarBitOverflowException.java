package com.jagex.game.runetek5.config.vartype.bit;

public class VarBitOverflowException extends Exception {

	private static final long serialVersionUID = -1726359456250936398L;

	VarBitOverflowException(String debugname, int value, int max) {
    	super("Varbit oveflow on varbit \'" + debugname + "\' Value " + value + " is outside the range 0-" + max);
    }
}
