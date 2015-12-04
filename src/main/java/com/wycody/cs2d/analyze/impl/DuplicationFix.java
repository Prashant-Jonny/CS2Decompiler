package com.wycody.cs2d.analyze.impl;

import java.util.ArrayList;
import java.util.TreeMap;

import com.wycody.cs2d.analyze.Analyzer;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.flow.impl.BasicBlock;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.base.branch.ConditionalInstruction;
import com.wycody.cs2d.script.inst.base.branch.JumpInstruction;
import com.wycody.cs2d.script.inst.walker.InstructionWalker;
import com.wycody.cs2d.script.inst.walker.WalkState;
import com.wycody.cs2d.script.inst.walker.WalkerAction;
import com.wycody.utils.Match;

public class DuplicationFix extends Analyzer {

	private TreeMap<Integer, ArrayList<ConditionalInstruction>> instructions;
	private int biggestDepth;

	public DuplicationFix(CS2Script script) {
		super(script);

	}

	@Override
	public void initialize() {
		instructions = new TreeMap<Integer, ArrayList<ConditionalInstruction>>();
		InstructionWalker walker = new InstructionWalker(script.getStartBlock(), InstructionWalker.ALL, new WalkerAction() {

			@Override
			public WalkState visitInstr(int depth, Instruction instruction) {
				if (instruction instanceof ConditionalInstruction) {
					if (depth > biggestDepth) {
						biggestDepth = depth;
					}
					if (!instructions.containsKey(depth)) {
						instructions.put(depth, new ArrayList<ConditionalInstruction>());
					}
					instructions.get(depth).add((ConditionalInstruction) instruction);
				}
				return WalkState.CONTINUE;
			}
		});
		walker.startWalking();

	}

	@Override
	public void process() {
		for (int depth = biggestDepth; depth >= 0; depth--) {
			ArrayList<ConditionalInstruction> instructions = this.instructions.get(depth);
			if (instructions == null) {
				continue;
			}
			instr: for (ConditionalInstruction instruction : instructions) {
				BasicBlock target = instruction.getTarget();
				BasicBlock holder = instruction.getHolder();

				Instruction next = instruction.getHolder().getFirstJump(0);
				if (next instanceof JumpInstruction) {
					JumpInstruction nextJump = (JumpInstruction) next;
				
					Match<JumpInstruction> match = target.findNearestMatchJump(nextJump);

					if (match != null) {
						JumpInstruction first = match.getFirst();
						first.getHolder().removeInstruction(first);
					}
				}
			}

		}
	}

	@Override
	public void finalyze() {
		// TODO Auto-generated method stub

	}

}
