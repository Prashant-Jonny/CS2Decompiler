package com.wycody.cs2d.script.inst.impl;

import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.CallMethodInstruction;
import com.wycody.cs2d.script.inst.base.PushParamInstruction;
import com.wycody.cs2d.script.inst.types.StackType;
import java.util.function.Function;
import java.util.function.Supplier;

public interface Config {
    
    Function<Object,Object> ocFormatter = o -> "getObjectType("+o+")";
    
    /**
     * Gets the name of the specified object (item).
     */
    Supplier<CallMethodInstruction> PUSH_OBJ_NAME = () ->
            new CallMethodInstruction(InstructionType.PUSH_OC_NAME)
                    .setName("getName")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.OBJECT)
                    .setPrefixFormatters(ocFormatter);
    
    /**
     * Gets the ground option at the specified slot of the specified object (item).
     */
    Supplier<CallMethodInstruction> PUSH_OBJ_OP = () ->
            new CallMethodInstruction(InstructionType.PUSH_OC_OP)
                    .setName("getOption")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPushType(StackType.OBJECT)
                    .setPrefixFormatters(ocFormatter);
    
    /**
     * Gets the inventory option at the specified slot of the specified object (item).
     */
    Supplier<CallMethodInstruction> PUSH_OBJ_IOP = () ->
            new CallMethodInstruction(InstructionType.PUSH_OC_IOP)
                    .setName("getInventoryOption")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPushType(StackType.OBJECT)
                    .setPrefixFormatters(ocFormatter);
    
    /**
     * Gets the cost of the specified object (item). This is used to determine alchemy values and store prices.
     */
    Supplier<CallMethodInstruction> PUSH_OBJ_COST = () ->
            new CallMethodInstruction(InstructionType.PUSH_OC_COST)
                    .setName("getCost")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(ocFormatter);
    
    /**
     * Returns whether the specified object (item) can be stacked or not.
     */
    Supplier<CallMethodInstruction> PUSH_OBJ_STACKABLE = () ->
            new CallMethodInstruction(InstructionType.PUSH_OC_STACKABLE)
                    .setName("isStackable")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(ocFormatter);
    
    /**
     * Gets the base object ID for the specified noted object (item).
     */
    Supplier<CallMethodInstruction> PUSH_OBJ_FROMCERT = () ->
            new CallMethodInstruction(InstructionType.PUSH_OC_FROMCERT)
                    .setName("getCertBase")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(ocFormatter);
    
    /**
     * Gets the noted object ID for the specified object (item).
     */
    Supplier<CallMethodInstruction> PUSH_OBJ_TOCERT = () ->
            new CallMethodInstruction(InstructionType.PUSH_OC_TOCERT)
                    .setName("getCert")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(ocFormatter);
    
    /**
     * Gets the worn position (equip ID) for the specified object (item).
     */
    Supplier<CallMethodInstruction> PUSH_OBJ_WEARPOS = () ->
            new CallMethodInstruction(InstructionType.PUSH_OC_WEARPOS)
                    .setName("getWearPos")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(ocFormatter);
    
    /**
     * Gets the second worn position (equip type) for the specified object (item).
     */
    Supplier<CallMethodInstruction> PUSH_OBJ_WEARPOS2 = () ->
            new CallMethodInstruction(InstructionType.PUSH_OC_WEARPOS2)
                    .setName("getWearPos2")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(ocFormatter);
    
    /**
     * Gets the third worn position (equip ID) for the specified object (item).
     */
    Supplier<CallMethodInstruction> PUSH_OBJ_WEARPOS3 = () ->
            new CallMethodInstruction(InstructionType.PUSH_OC_WEARPOS3)
                    .setName("getWearPos3")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(ocFormatter);
    
    /**
     * Gets whether the specified object (item) is members-only.
     */
    Supplier<CallMethodInstruction> PUSH_OBJ_MEMBERS = () ->
            new CallMethodInstruction(InstructionType.PUSH_OC_MEMBERS)
                    .setName("isMembers")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(ocFormatter);
    
    /**
     * Gets the value of the specified object (item) config parameter. Returns either string or int depending on the parameter type
     */
    Supplier<Instruction> PUSH_OBJ_PARAM = () ->
            new PushParamInstruction(InstructionType.PUSH_OC_PARAM, "getObjectType", StackType.INT);

    /**
     * Gets the inventory option cursor at the specified slot for the specified object (item).
     */
    Supplier<CallMethodInstruction> PUSH_OBJ_IOPCUR = () ->
            new CallMethodInstruction(InstructionType.PUSH_OC_IOPCUR)
                    .setName("getInventoryOptionCursor")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(ocFormatter);
    
    
    /**
     * Performs a search for objects (items) containing the specified term, stores the results, and returns the result count. Format: (searchTerm, tradedOnly)
     */
    Supplier<CallMethodInstruction> OBJ_SEARCH = () ->
            new CallMethodInstruction(InstructionType.OBJ_SEARCH)
                    .setFormattedName("objectSearch(%1, %2b)")
                    .setArgumentTypes(StackType.OBJECT, StackType.INT)
                    .setPushType(StackType.INT);
    
    
    /**
     * Returns the next result of the last object search and increments the search result pointer.
     */
    Supplier<CallMethodInstruction> PUSH_OBJ_SEARCH_NEXTRESULT = () ->
            new CallMethodInstruction(InstructionType.PUSH_OBJ_SEARCH_NEXTRESULT)
                    .setName("nextObjectSearchResult")
                    .setPushType(StackType.INT);
    
    
    /**
     * Returns the object search result pointer to the start.
     */
    Supplier<CallMethodInstruction> OBJ_SEARCH_RESET = () ->
            new CallMethodInstruction(InstructionType.OBJ_SEARCH_RESET)
                    .setName("resetObjectSearch");
    
    
    /**
     * Gets the multi stack size for the specified object (item).
     */
    Supplier<CallMethodInstruction> PUSH_OBJ_MULTISTACKSIZE = () ->
            new CallMethodInstruction(InstructionType.PUSH_OC_MULTISTACKSIZE)
                    .setName("getMultiStackSize")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(ocFormatter);
    
    
    /**
     * Performs a search for objects (items) containing the specified term and the specified param value, stores the results, and returns the result count. Format: (searchTerm, tradedOnly, intParam, paramValue)
     */
    Supplier<CallMethodInstruction> OBJ_SEARCH_INTPARAM = () ->
            new CallMethodInstruction(InstructionType.OBJ_SEARCH_INTPARAM)
                    .setFormattedName("objectSearch(%1, %2b, %3, %4)")
                    .setArgumentTypes(StackType.OBJECT, StackType.INT, StackType.INT, StackType.INT)
                    .setPushType(StackType.INT);
    
    
    /**
     * Performs a search for objects (items) containing the specified term and the specified param value, stores the results, and returns the result count. Format: (searchTerm, tradedOnly, strParam, paramValue)
     */
    Supplier<CallMethodInstruction> OBJ_SEARCH_STRPARAM = () ->
            new CallMethodInstruction(InstructionType.OBJ_SEARCH_STRPARAM)
                    .setFormattedName("objectSearch(%1, %2b, %3, %4)")
                    .setArgumentTypes(StackType.OBJECT, StackType.INT, StackType.INT, StackType.OBJECT)
                    .setPushType(StackType.INT);
    
    
    /**
     * Gets whether the specified object (item) has a custom inventory option colour.
     */
    Supplier<CallMethodInstruction> PUSH_OBJ_HASIOPCOL = () ->
            new CallMethodInstruction(InstructionType.PUSH_OC_HASIOPCOL)
                    .setName("hasInventoryOptionColour")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(ocFormatter);
    
    
    /**
     * Gets the custom option colour for the specified object (item).
     */
    Supplier<CallMethodInstruction> PUSH_OBJ_IOPCOL = () ->
            new CallMethodInstruction(InstructionType.PUSH_OC_IOPCOL)
                    .setName("getInventoryOptionColour")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(ocFormatter);
    
    /**
     * Gets the value of the specified npc config parameter. Returns either string or int depending on the parameter type
     */
    Supplier<Instruction> PUSH_NPC_PARAM = () ->
            new PushParamInstruction(InstructionType.PUSH_NPC_PARAM, "getNpcConfig", StackType.INT);
    /**
     * Gets the value of the specified location config parameter. Returns either string or int depending on the parameter type
     */
    Supplier<Instruction> PUSH_LOC_PARAM = () ->
            new PushParamInstruction(InstructionType.PUSH_LOC_PARAM, "getLocationConfig", StackType.INT);
    /**
     * Gets the value of the specified struct parameter. Returns either string or int depending on the parameter type
     */
    Supplier<Instruction> PUSH_STRUCT_PARAM = () ->
            new PushParamInstruction(InstructionType.PUSH_STRUCT_PARAM, "getStruct", StackType.INT);

    /**
     * Gets the idle animation for the specified base (render) type.
     */
    Supplier<CallMethodInstruction> PUSH_BASE_IDLEANIM = () ->
            new CallMethodInstruction(InstructionType.PUSH_BASE_IDLEANIM)
                    .setName("getIdleAnimation")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(x -> "getBaseConfig("+x+")");
    
}
