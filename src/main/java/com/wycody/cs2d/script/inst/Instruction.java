package com.wycody.cs2d.script.inst;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.node.Node;
import com.wycody.cs2d.print.Printable;
import com.wycody.cs2d.script.CS2Field;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.flow.impl.BasicBlock;
import com.wycody.cs2d.script.inst.base.BlockComment;
import com.wycody.cs2d.script.inst.nodes.PushNode;
import com.wycody.cs2d.script.inst.types.StackType;

/**
 * Instruction means an "opcode" which is something to struct the script, like
 * PushInteger instruction
 * 
 * @author Walied-Yassen
 * @date Nov 6, 2015
 */
public abstract class Instruction extends Node implements Printable {

	private static final Logger logger = LoggerFactory.getLogger(Instruction.class.getName());

	/**
	 * The id of this instruction
	 */
	protected int id;

	/**
	 * The address of this instruction
	 */
	protected int address;

	/**
	 * The type of this instruction
	 */
	protected InstructionType type;

	/**
	 * The object operand of this instruction
	 */
	protected Object objectOperand;

	/**
	 * The integer operand of this instruction
	 */
	protected Integer integerOperand;

	/**
	 * The long operand of this instruction
	 */
	protected Long longOperand;

	/**
	 * The script which holds this instruction
	 */
	protected CS2Script script;

	/**
	 * The block that's currently hold this instruction
	 */
	private BasicBlock holder;

	/**
	 * Are we going to print the instruction?
	 */
	private boolean printable = true;

	private BlockComment afterComment;
	private BlockComment beforeComment;

	/**
	 * Construct a new {@code Instruction}
	 * 
	 * @param id
	 *            the id of the instruction
	 * @param address
	 *            the address of the instruction
	 * @param type
	 *            the type of the instruction
	 */
	@Deprecated
	public Instruction(int id, int address, InstructionType type) {
		this.id = id;
		this.address = address;
		this.type = type;
	}

	public Instruction(InstructionType type) {
		this(-1, -1, type);
	}

	public void setBaseData(int id, int address) {
		this.id = id;
		this.address = address;
	}


	public abstract void process(Context context);

	public void postprocess(Context context) {

	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the type
	 */
	public InstructionType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(InstructionType type) {
		this.type = type;
	}

	/**
	 * @return the objectOperand
	 */
	public Object getObjectOperand() {
		return objectOperand;
	}

	/**
	 * @param objectOperand
	 *            the objectOperand to set
	 */
	public void setObjectOperand(Object objectOperand) {
		this.objectOperand = objectOperand;
	}

	/**
	 * @return the integerOperand
	 */
	public Integer getIntegerOperand() {
		return integerOperand;
	}

	/**
	 * @param integerOperand
	 *            the integerOperand to set
	 */
	public void setIntegerOperand(Integer integerOperand) {
		this.integerOperand = integerOperand;
	}

	/**
	 * @return the longOperand
	 */
	public Long getLongOperand() {
		return longOperand;
	}

	/**
	 * @param longOperand
	 *            the longOperand to set
	 */
	public void setLongOperand(Long longOperand) {
		this.longOperand = longOperand;
	}

	/**
	 * @return the script
	 */
	public CS2Script getScript() {
		return script;
	}

	/**
	 * @param script
	 *            the script to set
	 */
	public void setScript(CS2Script script) {
		this.script = script;
	}

	public PushNode push(PushNode node) {
		push(node.getPushType(), node);
		return node;
	}
	
	/**
	 * Push value with specified type to it's stack
	 * 
	 * @param type
	 *            the type of the value, Use {@link StackType}
	 * @param value
	 *            the value to push
	 */
	public void push(StackType type, Object value) {
		logger.debug("PUSH => " + type + " // " + this.toString() + " @ " + this.address);
		switch (type) {
		case INT:
			this.script.pushInteger(value);
			break;
		case OBJECT:
			this.script.pushObject(value);
			break;
		case LONG:
			this.script.pushLong(value);
			break;
		}
	}

	/**
	 * Pop an value with specified type from it's stack
	 * 
	 * @param type
	 *            the type of the value to pop, Use {@link StackType}
	 */
	public Object pop(StackType type) {
		logger.debug("POP => " + type + " // " + this.toString() + " @ " + this.address);
		switch (type) {
		case INT:
			return this.script.popInteger(this.address);
		case LONG:
			return this.script.popLong(this.address);
		case OBJECT:
			return this.script.popObject(this.address);
		}
		throw new RuntimeException("Unknown stack type!");
	}

	public Object getOperand(StackType type) {
		switch (type) {
		case INT:
			return this.integerOperand;
		case LONG:
			return this.longOperand;
		case OBJECT:
				return "\"" + this.objectOperand + "\"";
		default:
			throw new Error("Unknown operand type!");
		}
	}

	public CS2Field[] getFields(StackType type) {
		switch (type) {
		case INT:
			return this.script.getIntegerFields();
		case LONG:
			return this.script.getLongFields();
		case OBJECT:
			return this.script.getObjectFields();
		default:
			throw new Error("Unknown operand type!");
		}
	}

	/**
	 * @return the holder
	 */
	public BasicBlock getHolder() {
		return holder;
	}

	/**
	 * @param holder
	 *            the holder to set
	 */
	public void setHolder(BasicBlock holder) {
		this.holder = holder;
	}

	/**
	 * @return the printable
	 */
	public boolean isPrintable() {
		return printable;
	}

	/**
	 * @param printable
	 *            the printable to set
	 */
	public void setPrintable(boolean printable) {
		this.printable = printable;
	}

	/**
	 * Returns the amount of elements pushed on the stack by this method.
	 * Returns -1 in case the amount is unknown.
	 * 
	 * @param type
	 *            the stack type.
	 * @return -1 or N >= 0.
	 */
	public abstract int getPushCount(StackType type);

	/**
	 * Returns the amount of elements popped from the stack by this method. *
	 * Returns -1 in case the amount is unknown.
	 * 
	 * @param type
	 *            the stack type.
	 * @return -1 or N >= 0.
	 */
	public abstract int getPopCount(StackType type);

	public void preprocess(Context context) {
	}

	/**
	 * @return the afterComment
	 */
	public BlockComment getAfterComment() {
		return afterComment;
	}

	/**
	 * @param afterComment
	 *            the afterComment to set
	 */
	public void setAfterComment(BlockComment afterComment) {
		this.afterComment = afterComment;
	}

	/**
	 * @return the beforeComment
	 */
	public BlockComment getBeforeComment() {
		return beforeComment;
	}

	/**
	 * @param beforeComment
	 *            the beforeComment to set
	 */
	public void setBeforeComment(BlockComment beforeComment) {
		this.beforeComment = beforeComment;
	}

}
