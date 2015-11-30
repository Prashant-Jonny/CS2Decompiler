package com.wycody.cs2d.script.inst.base;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.StackType;

public class StoreInstruction extends Instruction {
	private final StackType valueType;
	private Object variable;
	public Object value;
	private StackType field_type;
    private boolean isArray;

	// temporary
	private boolean oe;
	private Object oeo, oev;
    
    public StoreInstruction(InstructionType type, StackType field_type, StackType valueType) {
        this(type, field_type, valueType, false);
    }
    
	public StoreInstruction(InstructionType type, StackType field_type, StackType valueType, boolean isArray) {
		super(type);
		this.valueType = valueType;
		this.field_type = field_type;
        this.isArray = isArray;
	}

	@Override
	public void process(Context context) {
        if (isArray) {
            variable = "array"+integerOperand+"["+pop(StackType.INT)+"]";
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
		value = this.pop(valueType);
	}

	@Override
	public void print(Context context, ScriptPrinter printer) {
		if (oe) {
			printer.println(variable.toString() + " " + oeo +  "= " + oev + ";");
		} else {
			printer.println((printer.getIndent() == 1 ? (field_type.getGenericType() + " ") : "") + variable.toString() + " = " + value + ";");
		}
	}

	@Override
	public int getPushCount(StackType type) {
		return 0;
	}

	@Override
	public int getPopCount(StackType type) {
		return (type.equals(valueType) ? 1 : 0) + (this.isArray && type.equals(StackType.INT) ? 1 : 0);
	}

	@Override
	public String toString() {
		return String.format("%4d:(%d) => %s<%s>", this.address, this.id, "STORE", this.type);
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

	public void setOE(boolean b, String o, Object v) {
		oe = b;
		oeo = o;
		oev = v;
	}
}
