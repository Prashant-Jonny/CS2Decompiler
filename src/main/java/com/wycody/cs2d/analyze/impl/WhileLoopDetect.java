package com.wycody.cs2d.analyze.impl;

import com.wycody.cs2d.analyze.Analyzer;
import com.wycody.cs2d.script.CS2Script;
//import com.wycody.cs2d.script.flow.Old_BasicBlock;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.base.branch.ConditionalInstruction;
import com.wycody.cs2d.script.inst.base.branch.JumpInstruction;
import com.wycody.utils.DynamicArray;

public class WhileLoopDetect extends Analyzer {

	private DynamicArray<ConditionalInstruction> instructions;

	public WhileLoopDetect(CS2Script script) {
		super(script);
	}

	@Override
	public void initialize() {
//		instructions = new DynamicArray<>(ConditionalInstruction.class);
//		for (Old_BasicBlock block : script.getBlocks().values()) {
//			for (Instruction instruction : block.getInstructions()) {
//				if (instruction instanceof ConditionalInstruction) {
//					instructions.add((ConditionalInstruction) instruction);
//				}
//			}
//		}
	}

	@Override
	public void process() {
//		for (int index = 0; index < instructions.size(); index++) {
//			ConditionalInstruction instruction = instructions.get(index);
//
//			Old_BasicBlock content = instruction.getTarget();
//			Instruction last = content.tryReachLast();
//
//			if (last instanceof JumpInstruction) {
//				JumpInstruction lastJump = (JumpInstruction) last;
//
//				if (lastJump.getJumpTarget() <= instruction.getAddress()) {
//					// TODO Better way to determine the jump is for while
//
//					// We got a while loop.
//					instruction.setWhileLoop(true);
//					last.getHolder().removeInstruction(lastJump);
//				}
//			}
//		}
	}

	@Override
	public void finalyze() {

	}

}
