package com.wycody.cs2d.script.inst.impl;

import java.util.function.Supplier;

import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.CallMethodInstruction;
import com.wycody.cs2d.script.inst.types.StackType;

/**
 * 
 * @author Walied-Yassen
 * @date Nov 15, 2015
 */
public interface Camera {
	Supplier<CallMethodInstruction> SETZOOM = () ->
            new CallMethodInstruction(InstructionType.CAMERA_SETZOOM)
                    .setName("setCameraZoom")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setReverseArgs(true);

    Supplier<CallMethodInstruction> GET_CAMERA_ROT_X = () ->
            new CallMethodInstruction(InstructionType.CAMERA_PUSH_ROTX)
                    .setName("getCameraRotX")
                    .setPushType(StackType.INT);

    Supplier<CallMethodInstruction> GET_CAMERA_ROT_Y = () ->
            new CallMethodInstruction(InstructionType.CAMERA_PUSH_ROTY)
                    .setName("getCameraRotY")
                    .setPushType(StackType.INT);
    
    Supplier<CallMethodInstruction> CAMERA_IS_ANINT1621_EQUALS_4 = () ->
            new CallMethodInstruction(InstructionType.CAMERA_IS_ANINT1621_EQUALS_4)
                    .setName("isAnInt1621Equals4")
                    .setPushType(StackType.INT);

    Supplier<CallMethodInstruction> UNKNOWN_METHOD = () ->
            new CallMethodInstruction(InstructionType.CAMERA_METHOD3100)
                    .setName("method3100Camera")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setReverseArgs(true);
}
