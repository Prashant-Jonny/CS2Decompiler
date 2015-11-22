package com.wycody.cs2d.script.inst.impl;

import java.util.function.Function;
import java.util.function.Supplier;

import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.CallMethodInstruction;
import com.wycody.cs2d.script.inst.types.StackType;

public class Quests {
	
	public static final Function<Object, Object> questFormatter = o -> "getQuest(" + o + ")";
    
    public static Supplier<CallMethodInstruction> PUSH_REWARD_POINTS = () -> 
    new CallMethodInstruction(InstructionType.QUESTS_PUSH_REWRD_POINTS)
            .setName("getRewardPoints")
            .setArgumentTypes(StackType.INT)
            .setPushType(StackType.INT)
            .setPrefixFormatters(questFormatter);
}
