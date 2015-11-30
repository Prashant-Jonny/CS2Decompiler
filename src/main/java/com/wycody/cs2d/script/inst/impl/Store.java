package com.wycody.cs2d.script.inst.impl;

import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.StackType;
import com.wycody.cs2d.script.inst.base.StoreInstruction;
import java.util.function.Supplier;

public interface Store {
    Supplier<StoreInstruction> STORE_INT = () -> new StoreInstruction(InstructionType.STORE_INT, StackType.INT, StackType.INT);
    Supplier<StoreInstruction> STORE_OBJ = () -> new StoreInstruction(InstructionType.STORE_OBJ, StackType.OBJECT, StackType.OBJECT);
    Supplier<StoreInstruction> STORE_LONG = () -> new StoreInstruction(InstructionType.STORE_LONG, StackType.LONG, StackType.LONG);
    Supplier<StoreInstruction> ARRAY = () -> new StoreInstruction(InstructionType.ARRAY_STORE, StackType.INT, StackType.INT);
}
