package com.wycody.cs2d.script.inst.impl;

import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.CallMethodInstruction;
import com.wycody.cs2d.script.inst.types.StackType;
import java.util.function.Supplier;

public interface Login {
    
    Supplier<CallMethodInstruction> PUSH_RESPONSE = () ->
            new CallMethodInstruction(InstructionType.PUSH_LOGIN_RESPONSE)
                    .setName("getLoginResponse")
                    .setPushType(StackType.INT); 
    
    Supplier<CallMethodInstruction> PUSH_HAS_SIGNLE_SIGNON_KEY = () ->
            new CallMethodInstruction(InstructionType.PUSH_HAS_SIGNLE_SIGNON_KEY)
                    .setName("hasSingleSignonKey")
                    .setPushType(StackType.INT); 
    
}
