package com.wycody.cs2d.script.inst.base.branch;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.flow.impl.BasicBlock;
//import com.wycody.cs2d.script.flow.BasicBlock;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.StackType;

/**
 * Handles the jump instruction
 * 
 * @author Walied-Yassen
 * @date Nov 12, 2015
 */
public class JumpInstruction extends BranchInstruction {

	public JumpInstruction() {
		super(InstructionType.JUMP);
	}

	@Override
	public void process(Context context) {
		
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
    public String toString() {
		return String.format("%4d:(%d) => %s<%s>                Destination: %d", this.address, this.id, "   JUMP", this.type, this.getJumpTarget());
    }
    
    
	@Override
	public void print(Context context, ScriptPrinter printer) {
		if (context.isDebug() || getTarget() == null) {
			
			printer.println(getGotoString());
		} else {
			BasicBlock block = getTarget();
			block.print(context, context.getPrinter());
		}
	}

	@Override
	public int getPushCount(StackType type) {
		return 0;
	}

	@Override
	public int getPopCount(StackType type) {
		return 0;
	}

}
