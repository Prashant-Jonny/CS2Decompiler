package com.wycody.cs2d.script.inst.impl;

import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.CallMethodInstruction;
import com.wycody.cs2d.script.inst.types.StackType;
import java.util.function.Supplier;

public class WindowMode {
    
    /**
     * Sets the dimensions for the full-screen window mode. Returns 1 if set successfully, 0 otherwise.
     */
    public static Supplier<CallMethodInstruction> FS_SETDIMENSIONS = () ->
            new CallMethodInstruction(InstructionType.SET_FS_SIZE)
                    .setName("setFullScreenSize")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPushType(StackType.INT);
    
    /**
     * Pushes the current window mode onto the stack. 1=small, 2=resizable, 3=fullscreen
     */
    public static Supplier<CallMethodInstruction> PUSHMODE = () ->
            new CallMethodInstruction(InstructionType.PUSH_WINDOW_MODE)
                    .setName("getWindowMode")
                    .setPushType(StackType.INT);
    
}
