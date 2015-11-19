package com.wycody.cs2d.script.inst.impl;

import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.PopInstruction;
import com.wycody.cs2d.script.inst.types.StackType;
import java.util.function.Supplier;

public class Pop {
    public static final Supplier<PopInstruction> POP_INT = () -> new PopInstruction(InstructionType.POP_INT, StackType.INT);
    public static final Supplier<PopInstruction> POP_OBJ = () -> new PopInstruction(InstructionType.POP_OBJ, StackType.OBJECT);
    public static final Supplier<PopInstruction> POP_LONG = () -> new PopInstruction(InstructionType.POP_LONG, StackType.LONG);
}
