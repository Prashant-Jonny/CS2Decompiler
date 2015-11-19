package com.wycody.cs2d.script.inst;

import net.openrs.io.WrappedByteBuffer;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.script.CS2Script;

public interface InstructionDecoder {

	public Instruction decode(CS2Script script, Context context, WrappedByteBuffer buffer, int id, int address);
}
