package com.wycody.cs2d.script.inst;

/**
 * This is used to identify whatever the instruction is, PUSH, CALL, etc..
 * 
 * @author Walied-Yassen
 * @date Nov 6, 2015
 */
public enum InstructionBaseType {

	/**
	 * Instructions that cause the executer to jump multiple steps
	 */
	JUMP(false),

	/**
	 * Instructions that add to a stack
	 */
	PUSH(false),

	/**
	 * Instructions which add more than one attributes to stacks
	 */
	PUSH_MULTI(false),

	/**
	 * Instructions that's execute a code, such as setWidget..., call script
	 * etc..
	 */
	EXECUTE(true),

	/**
	 * IDK Whoever added this one, add the comment :P
	 */
	POP(false),

	/**
	 * Return the values
	 */
	RETURN(true),
	/**
	 * Instructions that jump if a condition is met
	 */
	BRANCH(true),

	/**
	 * It's special not branch, but it's based on branch, it does not have target either
	 */
	SWITCH(true),

	/**
	 * Null type
	 */
	NULL(false), 
	
	/**
	 * Store (anInt0 = 0;)
	 */
	STORE(true), 
	
	/**
	 * Customs things such as InlineConditional
	 */
	CUSTOM(true);

	/**
	 * Is this instruction going to be printed when decompiled?
	 */
	private boolean printable;

	/**
	 * Construct a new {@link InstructionBaseType}
	 * 
	 * @param printable
	 *            is the instruction going to be printed?
	 */
	private InstructionBaseType(boolean printable) {
		this.printable = printable;
	}

	/**
	 * @return the printable
	 */
	public boolean isPrintable() {
		return printable;
	}

}
