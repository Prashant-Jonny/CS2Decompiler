package com.wycody.cs2d.script.inst.walker;

import com.wycody.cs2d.script.inst.Instruction;

public interface WalkerAction {

	public WalkState visitInstr(int depth, Instruction instruction);
}
