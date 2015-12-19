package com.wycody.cs2d.script.inst.impl;

import java.util.function.Supplier;

import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.MultiPushInstruction;
import com.wycody.cs2d.script.inst.nodes.push.MethodNode;
import com.wycody.cs2d.script.inst.nodes.push.impl.MethodPush;
import com.wycody.cs2d.script.inst.types.StackType;

public interface Input {
    
	Supplier<MultiPushInstruction> PUSH_PRESSED_MOUSE_BUTTONS = () ->
			new MultiPushInstruction(InstructionType.PUSH_MOUSE_BUTTONS_STATE)
					.setTypes(StackType.INT, StackType.INT, StackType.INT)
					.setPushes(MethodPush.create(new StackType[] { StackType.INT }, MethodNode.create("isMouseButton1Pressed", "isMouseButton2Pressed", "isMouseButton3Pressed")));
}
