package com.wycody.cs2d.script.inst.impl;

import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.CallMethodInstruction;
import com.wycody.cs2d.script.inst.base.CallScriptInstruction;
import com.wycody.cs2d.script.inst.base.ConcatStringsInstruction;
import com.wycody.cs2d.script.inst.base.EmptyInstruction;
import com.wycody.cs2d.script.inst.base.MissingInstruction;
import com.wycody.cs2d.script.inst.base.ReturnInstruction;
import com.wycody.cs2d.script.inst.types.StackType;
import java.util.function.Supplier;

/**
 * Contains instructions which have not yet been assigned
 * to another category file.
 * 
 * @author Stefan
 */
public interface Unsorted {
	
    Supplier<CallMethodInstruction> PRINT_TO_CONSOLE = () ->
            new CallMethodInstruction(InstructionType.CONSOLE_PRINT).setName("println")
                    .setArgumentTypes(StackType.OBJECT).fixDefaultType();
    
    Supplier<CallMethodInstruction> IS_WINDOWS = () ->
            new CallMethodInstruction(InstructionType.IS_WINDOWS).setName("isOperatingSystem(WINDOWS)")
                    .setPushType(StackType.INT).setArgumentTypes(StackType.NONE).fixDefaultType();
    
    Supplier<CallMethodInstruction> TWITCH_LOGOUT = () ->
            new CallMethodInstruction(InstructionType.TWITCH_LOGOUT).setName("twitch_logout")
                    .setPushType(StackType.INT).setArgumentTypes(StackType.NONE).fixDefaultType();
    
    Supplier<MissingInstruction> MISSING = MissingInstruction::new;
    Supplier<EmptyInstruction> NOP = EmptyInstruction::new;
    Supplier<Instruction> RETURN = ReturnInstruction::new;
    Supplier<Instruction> CALL_SCRIPT = CallScriptInstruction::new;
    Supplier<Instruction> CONCAT_STRS = ConcatStringsInstruction::new;
    
    // TODO: InstructionType once the correct name is known.
    Supplier<CallMethodInstruction> GET_CURRENT_TOOLKIT_PREFERENCE = () ->
            new CallMethodInstruction(InstructionType.MIN)
                    .setName("getUnknownGamePreference")
                    .setPushType(StackType.INT);
    
    Supplier<CallMethodInstruction> PUSH_DOB = () ->
            new CallMethodInstruction(InstructionType.PUSH_DOB).setName("getActivePlayer().getDateOfBirth")
                    .setPushType(StackType.INT).setArgumentTypes(StackType.NONE).fixDefaultType();
    
    Supplier<CallMethodInstruction> PUSH_CHAT_RELATED_BOOLEAN = () ->
            new CallMethodInstruction(InstructionType.PUSH_CHAT_RELATED_BOOLEAN).setName("getActivePlayer().getSomeChatRelatedBoolean")
                    .setPushType(StackType.INT).setArgumentTypes(StackType.NONE).fixDefaultType();
}
