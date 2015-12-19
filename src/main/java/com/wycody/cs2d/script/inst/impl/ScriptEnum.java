package com.wycody.cs2d.script.inst.impl;

import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.CallMethodInstruction;
import com.wycody.cs2d.script.inst.base.PushEnumValueInstruction;
import com.wycody.cs2d.script.inst.base.rs3.EnumSpecials;
import com.wycody.cs2d.script.inst.types.StackType;
import java.util.function.Supplier;

public interface ScriptEnum {
    
    /**
     * Gets a string value out of the specified enumeration
     */
    Supplier<CallMethodInstruction> PUSH_VALUE_STR = () ->
            new CallMethodInstruction(InstructionType.PUSH_ENUM_VAL_STR)
                    .setName("enum")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPushType(StackType.OBJECT);
    
    /**
     * Gets a value out of the specified enumeration. Returns either a string or an int, depending on the value type specified.
     * Uses legacy chars to identify variable types (pre-RS3)
     */
    Supplier<PushEnumValueInstruction> LEGACY_PUSH_VALUE = () -> new PushEnumValueInstruction(true,true);
    
    /**
     * Gets a value out of the specified enumeration. Returns either a string or an int, depending on the value type specified.
     * Uses script var type IDs identify variable types (RS3)
     */
    Supplier<PushEnumValueInstruction> PUSH_VALUE = () -> new PushEnumValueInstruction(false,true);
    Supplier<PushEnumValueInstruction> PUSH_VALUE_1 = () -> new PushEnumValueInstruction(false,false);
    Supplier<Instruction> GET_VALUE_KEYS_KEY_BY_INDEX = () -> new EnumSpecials.EnumValueKeyGetterInstruction();
    
    /**
     * Checks whether the enum contains the given int value. Returns 1 if the value exists, 0 otherwise
     */
    Supplier<CallMethodInstruction> PUSH_CONTAINS_INT = () ->
            new CallMethodInstruction(InstructionType.PUSH_ENUM_CONTAINS_INT)
                    .setName("enumContains")
                    .setArgumentTypes(StackType.INT, StackType.INT, StackType.INT)
                    .setPushType(StackType.INT);
    
    /**
     * Checks whether the enum contains the given string value. Returns 1 if the value exists, 0 otherwise
     */
    Supplier<CallMethodInstruction> PUSH_CONTAINS_STR = () ->
            new CallMethodInstruction(InstructionType.PUSH_ENUM_CONTAINS_STR)
                    .setName("enumContains")
                    .setArgumentTypes(StackType.INT, StackType.OBJECT)
                    .setPushType(StackType.INT);
    
    /**
     * Gets the size of the specified enum
     */
    Supplier<CallMethodInstruction> PUSH_SIZE = () ->
            new CallMethodInstruction(InstructionType.PUSH_ENUM_SIZE)
                    .setName("enumSize")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT);
    
    /**
     * Gets the number of keys which link to the given integer value
     */
    Supplier<CallMethodInstruction> PUSH_INTVALKEYS_COUNT = () ->
            new CallMethodInstruction(InstructionType.PUSH_ENUM_INTVALKEYS_COUNT)
                    .setName("enumKeysCount")
                    .setArgumentTypes(StackType.INT, StackType.INT, StackType.INT)
                    .setPushType(StackType.INT);
    
    /**
     * Gets the number of keys which link to the given string value
     */
    Supplier<CallMethodInstruction> PUSH_STRVALKEYS_COUNT = () ->
            new CallMethodInstruction(InstructionType.PUSH_ENUM_STRVALKEYS_COUNT)
                    .setName("enumKeysCount")
                    .setArgumentTypes(StackType.INT, StackType.OBJECT)
                    .setPushType(StackType.INT);
    
    /**
     * Gets the key at the given slot which links to the given int value
     */
    Supplier<CallMethodInstruction> PUSH_INTVALKEY = () ->
            new CallMethodInstruction(InstructionType.PUSH_ENUM_INTVALKEY)
                    .setName("enumKey")
                    .setArgumentTypes(StackType.INT, StackType.INT, StackType.INT, StackType.INT, StackType.INT)
                    .setPushType(StackType.INT);
    
    /**
     * Gets the key at the given slot which links to the given string value
     */
    Supplier<CallMethodInstruction> PUSH_STRVALKEY = () ->
            new CallMethodInstruction(InstructionType.PUSH_ENUM_STRVALKEY)
                    .setName("enumKey")
                    .setArgumentTypes(StackType.INT, StackType.INT, StackType.OBJECT, StackType.INT)
                    .setPushType(StackType.INT);
    
}
