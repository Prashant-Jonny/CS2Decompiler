package com.wycody.cs2d.analyze.impl;

import java.util.List;
import java.util.TreeMap;

import org.apache.commons.collections4.list.TreeList;

import com.wycody.cs2d.analyze.Analyzer;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.flow.impl.BasicBlock;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.base.branch.ConditionalInstruction;
import com.wycody.cs2d.script.inst.base.branch.CustomJump;
import com.wycody.cs2d.script.inst.base.branch.JumpInstruction;
import com.wycody.cs2d.script.inst.walker.InstructionWalker;
import com.wycody.cs2d.script.inst.walker.WalkState;
import com.wycody.cs2d.script.inst.walker.WalkerAction;
import com.wycody.utils.Match;

public class ConditionalElseDetect extends Analyzer {

	private TreeMap<Integer, TreeList<ConditionalInstruction>> instructions;
	private int biggestDepth;

	public ConditionalElseDetect(CS2Script script) {
		super(script);

	}

	@Override
	public void initialize() {
		instructions = new TreeMap<Integer, TreeList<ConditionalInstruction>>();
		InstructionWalker walker = new InstructionWalker(script.getStartBlock(), InstructionWalker.ALL, new WalkerAction() {

			@Override
			public WalkState visitInstr(int depth, Instruction instruction) {
				if (instruction instanceof ConditionalInstruction) {
					
					if (depth > biggestDepth) {
						biggestDepth = depth;
					}
					if (!instructions.keySet().contains(depth)) {
						instructions.put(depth, new TreeList<ConditionalInstruction>());
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
			List<ConditionalInstruction> instructions = this.instructions.get(depth);
			if (instructions == null) {
				continue;
			}
			b: for (ConditionalInstruction instruction : instructions) {
				if (instruction.getHolder().getSuccessors().size() != 2) {
					continue b;
				}
				BasicBlock trueBlock = (BasicBlock) instruction.getHolder().getSuccessors().get(0);
				if (!(instruction.getHolder().getInstructions().last() instanceof JumpInstruction)) {
					continue b;
				}
				BasicBlock falseBlock = (BasicBlock) instruction.getHolder().getSuccessors().get(1);
		
				Match<JumpInstruction> match = trueBlock.findElseJump(falseBlock);
		
				if (match != null) {
					// Else block
					JumpInstruction first = match.getFirst();

					JumpInstruction second = match.getSecond();
					first.getHolder().removeInstruction(first);
					second.getHolder().removeInstruction(second);
					//
					instruction.getHolder().removeInstruction(instruction.getHolder().getInstructions().last());
					instruction.getHolder().addInstruction(new CustomJump(script, first.getJumpTarget()));

					instruction.setElse(falseBlock);
					int index = instruction.getHolder().getSuccessors().indexOf(falseBlock);
					if(index == -1) {
						throw new Error(index + ", " + instruction.getHolder().getSuccessors().size());
					}
						
					instruction.getHolder().getSuccessors().remove(index);
				}
			}
		}
		for (int depth = biggestDepth; depth >= 0; depth--) {
			List<ConditionalInstruction> instructions = this.instructions.get(depth);
			if (instructions == null) {
				continue;
			}
			for (ConditionalInstruction instruction : instructions) {
				BasicBlock elseBlock = instruction.getElse();
				if (elseBlock != null) {
					Instruction first = elseBlock.getFirstPrintableInstruction(null);
					if (first != null && first instanceof ConditionalInstruction) {

						ConditionalInstruction condFirst = (ConditionalInstruction) first;

						instruction.setElse(condFirst.getElse());
						condFirst.setElse(null);
						instruction.addElseIf(condFirst);

					}
				}
			}

		}
	}

	@Override
	public void finalyze() {

	}

}
