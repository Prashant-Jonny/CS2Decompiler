package com.wycody.cs2d.script.inst.impl;

import java.util.function.Supplier;

import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.StackType;
import com.wycody.cs2d.script.inst.base.branch.ConditionalInstruction;
import com.wycody.cs2d.script.inst.base.branch.JumpInstruction;
import com.wycody.cs2d.script.inst.swtch.SwitchInstruction;

public class Branch {
	public static Supplier<JumpInstruction> GOTO = () -> new JumpInstruction();

	public static Supplier<ConditionalInstruction> INT_EQ = () -> new ConditionalInstruction("==", InstructionType.INT_EQ, StackType.INT);
	public static Supplier<ConditionalInstruction> INT_NE = () -> new ConditionalInstruction("!=", InstructionType.INT_NE, StackType.INT);
	public static Supplier<ConditionalInstruction> INT_LT = () -> new ConditionalInstruction("<", InstructionType.INT_LT, StackType.INT);
	public static Supplier<ConditionalInstruction> INT_GT = () -> new ConditionalInstruction(">", InstructionType.INT_GT, StackType.INT);
	public static Supplier<ConditionalInstruction> INT_LE = () -> new ConditionalInstruction("<=", InstructionType.INT_LE, StackType.INT);
	public static Supplier<ConditionalInstruction> INT_GE = () -> new ConditionalInstruction(">=", InstructionType.INT_GE, StackType.INT);

	//// LT,GT,LE,GE
	public static Supplier<ConditionalInstruction> LONG_EQ = () -> new ConditionalInstruction("==", InstructionType.LONG_EQ, StackType.LONG);
	public static Supplier<ConditionalInstruction> LONG_NE = () -> new ConditionalInstruction("!=", InstructionType.LONG_NE, StackType.LONG);
	public static Supplier<ConditionalInstruction> LONG_LT = () -> new ConditionalInstruction("<", InstructionType.LONG_LT, StackType.LONG);
	public static Supplier<ConditionalInstruction> LONG_GT = () -> new ConditionalInstruction(">", InstructionType.LONG_GT, StackType.LONG);
	public static Supplier<ConditionalInstruction> LONG_LE = () -> new ConditionalInstruction("<=", InstructionType.LONG_LE, StackType.LONG);
	public static Supplier<ConditionalInstruction> LONG_GE = () -> new ConditionalInstruction(">=", InstructionType.LONG_GE, StackType.LONG);

	public static Supplier<Instruction> SWITCH = () -> new SwitchInstruction(InstructionType.SWITCH);
}
