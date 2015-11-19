package com.wycody.cs2d.script.inst.impl;

import com.wycody.cs2d.script.inst.base.ArrayCreateInstruction;
import java.util.function.Supplier;

public class Array {
    public static Supplier<ArrayCreateInstruction> CREATE = () -> new ArrayCreateInstruction();
	
}
