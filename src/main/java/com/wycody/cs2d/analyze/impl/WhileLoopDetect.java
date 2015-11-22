package com.wycody.cs2d.analyze.impl;

import java.util.ArrayList;
import java.util.HashMap;

import com.wycody.cs2d.analyze.Analyzer;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.flow.impl.BasicBlock;
//import com.wycody.cs2d.script.flow.Old_BasicBlock;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.base.branch.ConditionalInstruction;
import com.wycody.cs2d.script.inst.base.branch.JumpInstruction;
import com.wycody.cs2d.script.inst.walker.InstructionWalker;
import com.wycody.cs2d.script.inst.walker.WalkState;
import com.wycody.cs2d.script.inst.walker.WalkerAction;
import java.util.List;

public class WhileLoopDetect extends Analyzer {

	private HashMap<Integer, List<ConditionalInstruction>> instructions;
	private int biggestDepth;

	public WhileLoopDetect(CS2Script script) {
		super(script);
	}

	@Override
	public void initialize() {
		instructions = new HashMap<Integer, List<ConditionalInstruction>>();

		InstructionWalker walker = new InstructionWalker(script.getStartBlock(), InstructionWalker.ALL, new WalkerAction() {

			@Override
			public WalkState visitInstr(int depth, Instruction instruction) {
				if (instruction instanceof ConditionalInstruction) {
					if (depth > biggestDepth) {
						biggestDepth = depth;
					}
					if (!instructions.keySet().contains(depth)) {
						instructions.put(depth, new ArrayList<ConditionalInstruction>());
					}
					instructions.get(depth).add((ConditionalInstruction) instruction);
				}
				return WalkState.NONE;
			}
		});
		walker.startWalking();
	}

	@Override
	public void process() {
		for (int depth = biggestDepth; depth >= 0; depth--) {
			List<ConditionalInstruction> instructions = this.instructions.get(depth);
			if (instructions == null) {
				continue;
			}
			for (ConditionalInstruction instruction : instructions) {
				BasicBlock content = instruction.getTarget();
				JumpInstruction whileJump = content.detectNearestWhileJump(instruction);

				if (whileJump != null) {
					instruction.setWhileLoop(true);
					whileJump.getHolder().removeInstruction(whileJump);
				}
			}
		}
	}

	@Override
	public void finalyze() {

	}

}
