package com.wycody.cs2d.script;

import net.openrs.io.WrappedByteBuffer;

import com.wycody.cs2d.Context;

public interface CS2Disassembler {
	
	public CS2Script disassemble(Context context, WrappedByteBuffer data);
}
