package com.wycody.cs2d.script.inst.impl;

import com.wycody.cs2d.script.inst.base.ArrayCreateInstruction;
import com.wycody.cs2d.script.inst.base.ArrayStoreInsruction;
import java.util.function.Supplier;

public interface Array {
    Supplier<ArrayCreateInstruction> CREATE = ArrayCreateInstruction::new;
    
    Supplier<ArrayStoreInsruction> STORE = ArrayStoreInsruction::new;
}
