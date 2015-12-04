package com.wycody.cs2d.script.inst.base;

import java.util.function.Function;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.StackType;

public class StoreInstruction extends Instruction {

	private final Function<StoreInstruction, Object> processer;

	private Object variable;
	private Object value;
	private StackType field_type;
	private boolean isArray;

	// increment
	private boolean incr;
	private String incrOperator;
	private Object incrValue;
	private boolean incrBefore;

	public StoreInstruction(InstructionType type, StackType field_type, Function<StoreInstruction, Object> processer) {
		this(type, field_type, processer, false);
	}

	public StoreInstruction(InstructionType type, StackType field_type, Function<StoreInstruction, Object> processer, boolean isArray) {
		super(type);
		this.processer = processer;
		this.field_type = field_type;
		this.isArray = isArray;
	}

	@Override
	public void process(Context context) {
		if (isArray) {
			variable = "array" + integerOperand + "[" + pop(StackType.INT) + "]";
		} else {
			switch (field_type) {
			case INT:
				variable = script.getIntegerFields()[integerOperand];
				break;
			case LONG:
				variable = script.getLongFields()[integerOperand];
				break;
			case OBJECT:
				variable = script.getObjectFields()[integerOperand];
				break;
			}

		}
		value = processer.apply(this);
		if(!isArray) {
			script.getFieldNameMapper().checkStore(this);
		}
	}

	public String getPrintString(int indent, boolean semicon) {
		if (incr) {
			if (incrValue instanceof Integer && ((Integer) incrValue) == 1) {
				if (incrOperator.equalsIgnoreCase("-") || incrOperator.equalsIgnoreCase("+")) {
					String s = "";
					if (incrBefore) {
						s = incrOperator + incrOperator + variable.toString();
					} else {
						s = variable.toString() + incrOperator + incrOperator;
					}
					if (semicon) {
						s += ";";
					}
					return s;
				}
				throw new IllegalStateException("Increment store instruction is under invalid condition!");
			} else {
				return (variable.toString() + " " + incrOperator + "= " + incrValue + (semicon ? ";" : ""));
			}
		} else {
			return ((indent == 1 ? (field_type.getGenericType() + " ") : "") + variable.toString() + " = " + value + (semicon ? ";" : ""));
		}
	}

	@Override
	public void print(Context context, ScriptPrinter printer) {
		printer.println(getPrintString(printer.getIndent(), true));
	}

	@Override
	public int getPushCount(StackType type) {
		return -1;
	}

	@Override
	public int getPopCount(StackType type) {
		return -1;
	}

	@Override
	public String toString() {
		return String.format("%4d:(%d) => %s<%s>", this.address, this.id, "STORE", this.type);
	}

	public void setIncr(boolean b, String operator, Object value) {
		incr = true;
		incrOperator = operator;
		incrValue = value;
	}

	/**
	 * @return the variable
	 */
	public Object getVariable() {
		return variable;
	}

	/**
	 * @param variable
	 *            the variable to set
	 */
	public void setVariable(Object variable) {
		this.variable = variable;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * @return the field_type
	 */
	public StackType getField_type() {
		return field_type;
	}

	/**
	 * @param field_type
	 *            the field_type to set
	 */
	public void setField_type(StackType field_type) {
		this.field_type = field_type;
	}

	/**
	 * @return the isArray
	 */
	public boolean isArray() {
		return isArray;
	}

	/**
	 * @param isArray
	 *            the isArray to set
	 */
	public void setArray(boolean isArray) {
		this.isArray = isArray;
	}

	/**
	 * @return the incr
	 */
	public boolean isIncr() {
		return incr;
	}

	/**
	 * @param incr
	 *            the incr to set
	 */
	public void setIncr(boolean incr) {
		this.incr = incr;
	}

	/**
	 * @return the incrOperator
	 */
	public String getIncrOperator() {
		return incrOperator;
	}

	/**
	 * @param incrOperator
	 *            the incrOperator to set
	 */
	public void setIncrOperator(String incrOperator) {
		this.incrOperator = incrOperator;
	}

	/**
	 * @return the incrValue
	 */
	public Object getIncrValue() {
		return incrValue;
	}

	/**
	 * @param incrValue
	 *            the incrValue to set
	 */
	public void setIncrValue(Object incrValue) {
		this.incrValue = incrValue;
	}

	/**
	 * @return the incrBefore
	 */
	public boolean isIncrBefore() {
		return incrBefore;
	}

	/**
	 * @param incrBefore
	 *            the incrBefore to set
	 */
	public void setIncrBefore(boolean incrBefore) {
		this.incrBefore = incrBefore;
	}

	/**
	 * @return the processer
	 */
	public Function<StoreInstruction, Object> getProcesser() {
		return processer;
	}

}
