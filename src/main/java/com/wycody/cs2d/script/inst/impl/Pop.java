package com.wycody.cs2d.script.inst.impl;

import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.PopInstruction;
import com.wycody.cs2d.script.inst.types.StackType;
import java.util.function.Supplier;

public interface Pop {
    Supplier<PopInstruction> POP_INT = () -> new PopInstruction(InstructionType.POP_INT, StackType.INT);
    Supplier<PopInstruction> POP_OBJ = () -> new PopInstruction(InstructionType.POP_OBJ, StackType.OBJECT);
    Supplier<PopInstruction> POP_LONG = () -> new PopInstruction(InstructionType.POP_LONG, StackType.LONG);
}
