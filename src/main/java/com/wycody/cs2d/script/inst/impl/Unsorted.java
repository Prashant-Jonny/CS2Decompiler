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
public class Unsorted {
	
    public static final Supplier<CallMethodInstruction> PRINT_TO_CONSOLE = () ->
    new CallMethodInstruction(InstructionType.CONSOLE_PRINT).setName("println").setArgumentTypes(StackType.OBJECT).fixDefaultType();
    
    public static final Supplier<CallMethodInstruction> IS_WINDOWS = () ->
            new CallMethodInstruction(InstructionType.IS_WINDOWS).setName("isOperatingSystem(WINDOWS)")
                    .setPushType(StackType.INT).setArgumentTypes(StackType.NONE).fixDefaultType();
    
    public static final Supplier<CallMethodInstruction> TWITCH_LOGOUT = () ->
            new CallMethodInstruction(InstructionType.TWITCH_LOGOUT).setName("twitch_logout")
                    .setPushType(StackType.INT).setArgumentTypes(StackType.NONE).fixDefaultType();
    
    public static final Supplier<MissingInstruction> MISSING = () -> new MissingInstruction();
    public static final Supplier<EmptyInstruction> NOP = () -> new EmptyInstruction();
    public static final Supplier<Instruction> RETURN = () -> new ReturnInstruction();
    public static final Supplier<Instruction> CALL_SCRIPT = () -> new CallScriptInstruction();
    public static final Supplier<Instruction> CONCAT_STRS = () -> new ConcatStringsInstruction();
    
    // TODO: InstructionType once the correct name is known.
    public static final Supplier<CallMethodInstruction> GET_CURRENT_TOOLKIT_PREFERENCE = () ->
            new CallMethodInstruction(InstructionType.MIN)
                    .setName("getUnknownGamePreference")
                    .setPushType(StackType.INT);
}
