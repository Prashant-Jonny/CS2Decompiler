package com.wycody.cs2d.script.inst.impl;

import java.util.function.Supplier;

import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.CustomPushInstruction;
import com.wycody.cs2d.script.inst.types.StackType;

public class Input {

	public static Supplier<CustomPushInstruction> PUSH_PRESSED_MOUSE_BUTTONS = () -> new CustomPushInstruction(InstructionType.PUSH_MOUSE_BUTTONS_STATE).setTypes(StackType.INT, StackType.INT, StackType.INT).setPushes("isMouseButton1()", "isMouseButton2()", "isMouseButton3()");
}
