package com.wycody.cs2d.script.inst.impl;

import java.util.function.Supplier;

import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.GetVarInstruction;
import com.wycody.cs2d.script.inst.base.GroupVarInstruction;
import com.wycody.cs2d.script.inst.base.StoreVarInstruction;
import com.wycody.cs2d.script.inst.types.StackType;

public interface Var {
    Supplier<GetVarInstruction> PUSH_VAR_CLAN = () ->
            new GetVarInstruction(InstructionType.PUSH_VARCLAN, StackType.INT, "varclan");
    Supplier<GetVarInstruction> PUSH_VARBIT_CLAN = () ->
            new GetVarInstruction(InstructionType.PUSH_VARCLAN_BIT, StackType.INT, "varbitclan");
    Supplier<GetVarInstruction> PUSH_VAR_CLAN_LONG = () ->
            new GetVarInstruction(InstructionType.PUSH_VARCLAN_LONG, StackType.LONG, "varclan_long");
    Supplier<GetVarInstruction> PUSH_VARCLANSETTING = () ->
            new GetVarInstruction(InstructionType.PUSH_VARCLANSETTING, StackType.INT, "varclansetting");
    Supplier<GetVarInstruction> PUSH_VARCLANSETTING_BIT = () ->
            new GetVarInstruction(InstructionType.PUSH_VARCLANSETTING_BIT, StackType.INT, "varclanbitsetting_bit");
    Supplier<GetVarInstruction> PUSH_VARCLANSETTING_LONG = () ->
            new GetVarInstruction(InstructionType.PUSH_VARCLANSETTING_LONG, StackType.LONG, "varclansetting_long");
    Supplier<GetVarInstruction> PUSH_VARCLANSETTING_STR = () ->
            new GetVarInstruction(InstructionType.PUSH_VARCLANSETTING_STR, StackType.OBJECT, "varclansetting_str");
    Supplier<GetVarInstruction> PUSH_VAR_CLAN_STRING = () ->
            new GetVarInstruction(InstructionType.PUSH_VARCLAN, StackType.OBJECT, "varclan");
    Supplier<GetVarInstruction> PUSH_VARC = () ->
            new GetVarInstruction(InstructionType.PUSH_VARC, StackType.INT, "varc_int");
    Supplier<GetVarInstruction> PUSH_VARC_STR = () ->
            new GetVarInstruction(InstructionType.PUSH_VARCSTR, StackType.OBJECT, "varc_str");
    Supplier<GetVarInstruction> PUSH_VARP = () ->
            new GetVarInstruction(InstructionType.PUSH_VARP, StackType.INT, "varp");
    Supplier<GetVarInstruction> PUSH_VARP_BIT = () ->
            new GetVarInstruction(InstructionType.PUSH_VARPBIT, StackType.INT, "varpbit");
    
    // Store
    
    Supplier<StoreVarInstruction> STORE_VARC = () ->
            new StoreVarInstruction(InstructionType.STORE_VARC, StackType.INT, "varc_int");
    Supplier<StoreVarInstruction> STORE_VARC_STR = () ->
            new StoreVarInstruction(InstructionType.STORE_VARC_STR, StackType.OBJECT, "varc_str");
    Supplier<StoreVarInstruction> STORE_VARP = () ->
            new StoreVarInstruction(InstructionType.STORE_VARP, StackType.INT, "varp");
    Supplier<StoreVarInstruction> STORE_VARP_BIT = () ->
            new StoreVarInstruction(InstructionType.STORE_VARPBIT, StackType.INT, "varpbit");

    // RS3
    Supplier<GroupVarInstruction> GROUP_GET_MULTI_VAR = () -> new GroupVarInstruction("GroupChannel.getPlayerByIndex");
}
