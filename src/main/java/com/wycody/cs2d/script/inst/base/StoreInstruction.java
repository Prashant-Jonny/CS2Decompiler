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
	public Object value;
	private StackType field_type;
    private boolean isArray;

	// temporary
	private boolean oe;
	private Object oeo, oev;
    
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
		value = processer.apply(this);
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
