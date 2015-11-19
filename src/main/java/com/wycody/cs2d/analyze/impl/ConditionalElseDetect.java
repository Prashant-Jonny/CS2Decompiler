package com.wycody.cs2d.analyze.impl;

import java.util.ArrayList;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wycody.cs2d.analyze.Analyzer;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.flow.impl.BasicBlock;
import com.wycody.cs2d.script.inst.base.branch.ConditionalInstruction;
import com.wycody.cs2d.script.inst.base.branch.JumpInstruction;
import com.wycody.cs2d.script.inst.walker.InstructionWalker;
import com.wycody.cs2d.utils.CS2Utils;

public class ConditionalElseDetect extends Analyzer {

	private static final Logger logger = LoggerFactory.getLogger(ConditionalElseDetect.class.getName());

	private TreeMap<Integer, ArrayList<ConditionalInstruction>> instructions;
	private int biggestDepth;

	public ConditionalElseDetect(CS2Script script) {
		super(script);

	}

	@Override
	public void initialize() {
		instructions = new TreeMap<Integer, ArrayList<ConditionalInstruction>>();
		InstructionWalker walker = new InstructionWalker(script.getStartBlock(), (depth, instr) -> {
			if (instr instanceof ConditionalInstruction) {
				if (depth > biggestDepth) {
					biggestDepth = depth;
				}
				if (!instructions.containsKey(depth)) {
					instructions.put(depth, new ArrayList<ConditionalInstruction>());
				}
				instructions.get(depth).add((ConditionalInstruction) instr);
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
			instr:for(ConditionalInstruction instruction : instructions) {
				BasicBlock block = instruction.getHolder();
				if(block.getSuccessors().size() != 2) {
					continue instr;
				}
				BasicBlock trueBlock = (BasicBlock) block.getSuccessors().get(0);
				BasicBlock falseBlock = (BasicBlock) block.getSuccessors().get(1);
				if(trueBlock.getLastReachable() == falseBlock.getLastReachable()) {
					// Leading to same point
					JumpInstruction[] matches= CS2Utils.getJumpMatches(trueBlock, falseBlock);
					JumpInstruction trueMatchJump = matches[0];
					JumpInstruction falseMatchJump = matches[1];
					//trueMatchJump.getHolder().removeInstruction(trueMatchJump);
					//falseMatchJump.getHolder().removeInstruction(falseMatchJump);
					instruction.setElse(falseBlock);
					
				}
			}
		}

	}

	@Override
	public void finalyze() {

	}

}
