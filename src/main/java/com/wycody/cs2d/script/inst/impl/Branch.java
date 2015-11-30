package com.wycody.cs2d.script.inst.impl;

import java.util.function.Supplier;

import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.StackType;
import com.wycody.cs2d.script.inst.base.branch.ConditionalInstruction;
import com.wycody.cs2d.script.inst.base.branch.JumpInstruction;
import com.wycody.cs2d.script.inst.swtch.SwitchInstruction;

public interface Branch {
	Supplier<JumpInstruction> GOTO = () -> new JumpInstruction();

	Supplier<ConditionalInstruction> INT_EQ = () -> new ConditionalInstruction("==", InstructionType.INT_EQ, StackType.INT);
	Supplier<ConditionalInstruction> INT_NE = () -> new ConditionalInstruction("!=", InstructionType.INT_NE, StackType.INT);
	Supplier<ConditionalInstruction> INT_LT = () -> new ConditionalInstruction("<", InstructionType.INT_LT, StackType.INT);
	Supplier<ConditionalInstruction> INT_GT = () -> new ConditionalInstruction(">", InstructionType.INT_GT, StackType.INT);
	Supplier<ConditionalInstruction> INT_LE = () -> new ConditionalInstruction("<=", InstructionType.INT_LE, StackType.INT);
	Supplier<ConditionalInstruction> INT_GE = () -> new ConditionalInstruction(">=", InstructionType.INT_GE, StackType.INT);

	//// LT,GT,LE,GE
	Supplier<ConditionalInstruction> LONG_EQ = () -> new ConditionalInstruction("==", InstructionType.LONG_EQ, StackType.LONG);
	Supplier<ConditionalInstruction> LONG_NE = () -> new ConditionalInstruction("!=", InstructionType.LONG_NE, StackType.LONG);
	Supplier<ConditionalInstruction> LONG_LT = () -> new ConditionalInstruction("<", InstructionType.LONG_LT, StackType.LONG);
	Supplier<ConditionalInstruction> LONG_GT = () -> new ConditionalInstruction(">", InstructionType.LONG_GT, StackType.LONG);
	Supplier<ConditionalInstruction> LONG_LE = () -> new ConditionalInstruction("<=", InstructionType.LONG_LE, StackType.LONG);
	Supplier<ConditionalInstruction> LONG_GE = () -> new ConditionalInstruction(">=", InstructionType.LONG_GE, StackType.LONG);

	Supplier<Instruction> SWITCH = () -> new SwitchInstruction(InstructionType.SWITCH);
}
