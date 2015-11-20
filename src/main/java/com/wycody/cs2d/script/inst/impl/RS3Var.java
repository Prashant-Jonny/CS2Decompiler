package com.wycody.cs2d.script.inst.impl;

import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.impl.var.rs3.PushVar;
import com.wycody.cs2d.script.inst.impl.var.rs3.PushVarBit;
import com.wycody.cs2d.script.inst.impl.var.rs3.StoreVar;
import com.wycody.cs2d.script.inst.impl.var.rs3.StoreVarBit;
import java.util.function.Supplier;

public interface RS3Var {
    public static final Supplier<Instruction> RS3_STORE_VARBIT = () -> new StoreVarBit(-1,-1);
    public static final Supplier<Instruction> RS3_STORE_VAR = () -> new StoreVar(-1,-1);
    public static final Supplier<Instruction> RS3_PUSH_VARBIT = () -> new PushVarBit(-1,-1);
    public static final Supplier<Instruction> RS3_PUSH_VAR = () -> new PushVar(-1,-1);    
}
