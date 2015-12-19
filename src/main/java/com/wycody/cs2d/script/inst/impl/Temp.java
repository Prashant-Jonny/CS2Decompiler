package com.wycody.cs2d.script.inst.impl;

import java.util.function.Supplier;

import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.CallMethodInstruction;
import com.wycody.cs2d.script.inst.types.StackType;

public interface Temp {
	   /**
	    * Returns the date of birth of the client player.
		*/
	   Supplier<CallMethodInstruction> PUSH_DATE_OF_BIRTH = () ->
			new CallMethodInstruction(InstructionType.PUSH_DATE_OF_BIRTH)
				.setName("getDateOfBirth")
				.setPushType(StackType.INT);
			   /**
			    * Returns the client player is quick chat.
				*/
			   Supplier<CallMethodInstruction> PUSH_IS_QUICK_CHAT = () ->
				new CallMethodInstruction(InstructionType.PUSH_IS_QUICK_CHAT)
					.setName("isQuickChat")
					.setPushType(StackType.INT);
			    
	            Supplier<CallMethodInstruction> RENDER_WIDTH = () ->
	            new CallMethodInstruction(InstructionType.PUSH_TEXT_RENDER_WIDTH_NORM)
	                    .setFormattedName("getFont(%2).getRenderWidth(%1)")
	                    .setArgumentTypes(StackType.OBJECT, StackType.INT)
	                    .setPushType(StackType.INT);
	            Supplier<CallMethodInstruction> PUSH_IS_CLIENT_FOCUSED = () ->
	            new CallMethodInstruction(InstructionType.PUSH_IS_CLIENT_FOCUSED)
	                    .setName("isClientFocused()")
	                    .setPushType(StackType.INT);
}
