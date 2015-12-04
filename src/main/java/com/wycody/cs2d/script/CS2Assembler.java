package com.wycody.cs2d.script;

import com.wycody.cs2d.Context;

import net.openrs.io.WrappedByteBuffer;

public interface CS2Assembler {
	
	public CS2Script disassemble(Context context, WrappedByteBuffer data);

	public byte[] assemble(Context context, CS2Script script);
}
