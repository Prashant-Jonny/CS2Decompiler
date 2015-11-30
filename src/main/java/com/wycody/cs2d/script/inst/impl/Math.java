package com.wycody.cs2d.script.inst.impl;

import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.CallMethodInstruction;
import com.wycody.cs2d.script.inst.types.StackType;
import com.wycody.cs2d.script.inst.base.InfixInstruction;
import java.util.function.Supplier;

public interface Math {
    Supplier<CallMethodInstruction> MAX = () ->
            new CallMethodInstruction(InstructionType.MAX)
                    .setName("max")
                    .setArgumentTypes(StackType.INT,StackType.INT)
                    .setPushType(StackType.INT);
    
    Supplier<CallMethodInstruction> MIN = () ->
            new CallMethodInstruction(InstructionType.MIN)
                    .setName("min")
                    .setArgumentTypes(StackType.INT,StackType.INT)
                    .setPushType(StackType.INT);
    
    Supplier<CallMethodInstruction> TEST_BIT = () ->
            new CallMethodInstruction(InstructionType.TEST_BIT)
                    .setName("testBit")
                    .setArgumentTypes(StackType.INT,StackType.INT)
                    .setPushType(StackType.INT);
    
    Supplier<CallMethodInstruction> SET_BIT = () ->
            new CallMethodInstruction(InstructionType.SET_BIT)
                    .setName("setBit")
                    .setArgumentTypes(StackType.INT,StackType.INT)
                    .setPushType(StackType.INT);
    
    Supplier<CallMethodInstruction> RESET_BIT = () ->
            new CallMethodInstruction(InstructionType.RESET_BIT)
                    .setName("resetBit")
                    .setArgumentTypes(StackType.INT,StackType.INT)
                    .setPushType(StackType.INT);
    
    Supplier<CallMethodInstruction> RANDOM = () ->
            new CallMethodInstruction(InstructionType.RANDOM)
                    .setName("random")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT);
    
    Supplier<CallMethodInstruction> HSL_TO_RGB = () ->
            new CallMethodInstruction(InstructionType.HSL_RGB)
                    .setName("HSLToRGB")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT);
    
    Supplier<CallMethodInstruction> ROOT = () ->
            new CallMethodInstruction(InstructionType.ROOT)
                    .setName("root")
                    .setArgumentTypes(StackType.INT,StackType.INT)
                    .setPushType(StackType.INT);
    
    Supplier<CallMethodInstruction> BITWISE_NOT = () ->
            new CallMethodInstruction(InstructionType.BITWISE_NOT)
                    .setName("~")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT);
    
    Supplier<InfixInstruction> BITWISE_AND = () ->
            new InfixInstruction(InstructionType.BITWISE_AND,false,"&",StackType.INT,StackType.INT,StackType.INT);
    
    Supplier<InfixInstruction> BITWISE_OR = () ->
            new InfixInstruction(InstructionType.BITWISE_OR,false,"|",StackType.INT,StackType.INT,StackType.INT);
    
    Supplier<InfixInstruction> SUBTRACT = () ->
            new InfixInstruction(InstructionType.SUBTRACT,false,"-",StackType.INT,StackType.INT,StackType.INT);

    Supplier<InfixInstruction> SUM = () ->
            new InfixInstruction(InstructionType.ADD,false,"+",StackType.INT,StackType.INT,StackType.INT);
    
    Supplier<InfixInstruction> DIVIDE = () ->
            new InfixInstruction(InstructionType.DIVIDE,false,"/",StackType.INT,StackType.INT,StackType.INT);
    
    Supplier<InfixInstruction> MULTIPLY = () ->
        new InfixInstruction(InstructionType.MULTIPLY,false,"*",StackType.INT,StackType.INT,StackType.INT);
    
    Supplier<InfixInstruction> MODULO = () ->
        new InfixInstruction(InstructionType.MODULO,false,"%",StackType.INT,StackType.INT,StackType.INT);
    
    Supplier<InfixInstruction> POWER = () ->
        new InfixInstruction(InstructionType.POWER,false,"^",StackType.INT,StackType.INT,StackType.INT);

    //FIXME: verify.
    Supplier<CallMethodInstruction> FRAC_MULTIPLY = () ->
            new CallMethodInstruction(InstructionType.FRAC_MULTIPLY)
            .setArgumentTypes(StackType.INT,StackType.INT,StackType.INT)
            .setPushType(StackType.INT)
            .setName("fract_multiply");
    
    // #1 * #3 / #2
}
