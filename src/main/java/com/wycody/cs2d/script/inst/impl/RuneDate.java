package com.wycody.cs2d.script.inst.impl;

import java.util.function.Supplier;

import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.CallMethodInstruction;
import com.wycody.cs2d.script.inst.types.StackType;

/**
 * 
 * @author Walied-Yassen
 * @date Dec 11, 2015
 */
public interface RuneDate {
	Supplier<CallMethodInstruction> TO_STRING = () -> new CallMethodInstruction(InstructionType.RUNEDATE_FORMAT).setName("RuneDate.format").setArgumentTypes(StackType.INT).setPushType(StackType.OBJECT);
	Supplier<CallMethodInstruction> FROM_TIMESTAMP = () -> new CallMethodInstruction(InstructionType.RUNEDATE_FROM_TIMESTAMP).setName("RuneDate.fromCurrentTimestamp").setPushType(StackType.INT);
}
