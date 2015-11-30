package com.wycody.cs2d.script.inst.impl;

import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.CallMethodInstruction;
import com.wycody.cs2d.script.inst.types.StackType;
import java.util.function.Supplier;

public interface Player {
    Supplier<CallMethodInstruction> SET_WORN_OBJECT = () ->
        new CallMethodInstruction(InstructionType.PLAYER_SETWORNOBJ)
                .setName("getActivePlayer().setWornObject").
                setArgumentTypes(StackType.INT,StackType.INT);
        
    Supplier<CallMethodInstruction> SET_IDK = () ->
            new CallMethodInstruction(InstructionType.PLAYER_SETIDK)
                    .setName("getActivePlayer().setIdentikit").
                    setArgumentTypes(StackType.INT,StackType.INT);
    
    Supplier<CallMethodInstruction> SET_COLOUR = () ->
            new CallMethodInstruction(InstructionType.PLAYER_SETCOL)
                    .setName("getActivePlayer().setColour").
                    setArgumentTypes(StackType.INT,StackType.INT);
    
    Supplier<CallMethodInstruction> SET_GENDER = () ->
            new CallMethodInstruction(InstructionType.PLAYER_SETGENDER)
            .setName("getActivePlayer().setGender").setArgumentTypes(StackType.INT);
}
