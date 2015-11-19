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

public class NaturalFlow extends Analyzer {

	private TreeMap<Integer, ArrayList<ConditionalInstruction>> instructions;
	private int biggestDepth;

	public NaturalFlow(CS2Script script) {
		super(script);

	}

	@Override
	public void initialize() {
		instructions = new TreeMap<Integer, ArrayList<ConditionalInstruction>>();
		InstructionWalker walker = new InstructionWalker(script.getStartBlock(), InstructionWalker.RESOLVE_JUMPS | InstructionWalker.RESOLVE_TARGET_BLOCKS | InstructionWalker.RESOLVE_ELSE_BLOCKS, new WalkerAction() {
			
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
				return WalkState.NONE;
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
				BasicBlock block = instruction.getHolder();
				
				Match<JumpInstruction> match = block.findNearestMatchJump(instruction.getTarget());
				if(match == null || match.getFirst() == null || match.getSecond() == null) {
					continue;
				}
				System.out.println(match.getFirst().getGotoString() + ", " + match.getSecond().getGotoString());
			}
			
		}
	}

	@Override
	public void finalyze() {
		// TODO Auto-generated method stub

	}

}
