package com.wycody.cs2d.script.inst.impl;

import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.PushInstruction;
import com.wycody.cs2d.script.inst.base.PushInstruction.Type;
import com.wycody.cs2d.script.inst.types.StackType;
import java.util.function.Supplier;

public interface Push {
    Supplier<Instruction> PUSH_OBJECT = () -> new PushInstruction(InstructionType.PUSH_OBJ,Type.OPERAND,StackType.OBJECT);
    Supplier<Instruction> PUSH_LONG = () -> new PushInstruction(InstructionType.PUSH_LONG,Type.OPERAND,StackType.LONG);
    Supplier<Instruction> PUSH_INT = () -> new PushInstruction(InstructionType.PUSH_INT,Type.OPERAND,StackType.INT);
    
    Supplier<Instruction> LOAD_INT = () ->new PushInstruction(InstructionType.LOAD_INT,Type.FIELD,StackType.INT);
    Supplier<Instruction> LOAD_LONG = () ->new PushInstruction(InstructionType.LOAD_LONG,Type.FIELD,StackType.LONG);
    Supplier<Instruction> LOAD_OBJ = () ->new PushInstruction(InstructionType.LOAD_OBJ,Type.FIELD,StackType.OBJECT);
    
    Supplier<Instruction> ARRAY = () -> new PushInstruction(InstructionType.ARRAY_LOAD,Type.ARRAY,StackType.INT);
}
