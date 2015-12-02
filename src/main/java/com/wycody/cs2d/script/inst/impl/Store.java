package com.wycody.cs2d.script.inst.impl;

import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.StackType;
import com.wycody.cs2d.script.inst.base.StoreInstruction;
import java.util.function.Supplier;

public class Store {
    public static Supplier<StoreInstruction> STORE_INT = () -> new StoreInstruction(InstructionType.STORE_INT, StackType.INT, i -> i.getScript().popInteger(i.getAddress()));
    public static Supplier<StoreInstruction> STORE_OBJ = () -> new StoreInstruction(InstructionType.STORE_OBJ, StackType.OBJECT, i -> i.getScript().popObject(i.getAddress()));
    public static Supplier<StoreInstruction> STORE_LONG = () -> new StoreInstruction(InstructionType.STORE_LONG, StackType.LONG, i -> i.getScript().popLong(i.getAddress()));
    
    public static Supplier<StoreInstruction> ARRAY = () -> new StoreInstruction(InstructionType.ARRAY_STORE, StackType.INT, i -> i.getScript().popInteger(i.getAddress()));
}
