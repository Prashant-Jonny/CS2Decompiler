package com.wycody.cs2d.script.inst.base;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.StackType;

public class CustomPushInstruction extends Instruction {
	
	private Object[] pushes;
	private StackType[] types;
	
	public CustomPushInstruction(InstructionType type) {
		super(type);
	}

	@Override
	public void process(Context context) {
		for(int i = 0; i < pushes.length; i++) {
			StackType type = types == null ? StackType.INT : types.length == 1 ? types[0] : types[i];
			push(type, pushes[i]);
		}
	}

	@Override
	public void print(Context context, ScriptPrinter printer) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getPushCount(StackType type) {
		return pushes.length;
	}

	@Override
	public int getPopCount(StackType type) {
		return 0;
	}

	/**
	 * @return the pushes
	 */
	public Object[] getPushes() {
		return pushes;
	}

	/**
	 * @param pushes the pushes to set
	 */
	public CustomPushInstruction setPushes(Object... pushes) {
		this.pushes = pushes;
		return this;
	}

	/**
	 * @return the types
	 */
	public StackType[] getTypes() {
		return types;
	}

	/**
	 * @param types the types to set
	 */
	public CustomPushInstruction setTypes(StackType... types) {
		this.types = types;
		return this;
	}



}
