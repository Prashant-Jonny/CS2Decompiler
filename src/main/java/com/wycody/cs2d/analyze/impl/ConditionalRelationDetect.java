package com.wycody.cs2d.analyze.impl;

import java.util.ArrayList;
import java.util.TreeMap;

import com.wycody.cs2d.analyze.Analyzer;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.flow.impl.BasicBlock;
//import com.wycody.cs2d.script.flow.Old_BasicBlock;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionBaseType;
import com.wycody.cs2d.script.inst.base.branch.ConditionalInstruction;
import com.wycody.cs2d.script.inst.base.branch.Relation;
import com.wycody.cs2d.script.inst.base.branch.RelationType;
import com.wycody.cs2d.script.inst.walker.InstructionWalker;

/**
 * This class is managing to detect relations for conditional instructions,
 * relations like: "&&"
 * 
 * @author Walied-Yassen
 * @date Nov 12, 2015
 */
public class ConditionalRelationDetect extends Analyzer {

	/**
	 * The list of all conditional instructions
	 */
	private TreeMap<Integer, ArrayList<ConditionalInstruction>> instructions;
	private int biggestDepth;
	private ArrayList<Instruction> ignores;

	/**
	 * Construct a new {@link ConditionalRelationDetect}
	 * 
	 * @param script
	 *            the script to analyze
	 */
	public ConditionalRelationDetect(CS2Script script) {
		super(script);
		ignores = new ArrayList<Instruction>();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wycody.cs2d.analyze.Analyzer#initialize()
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wycody.cs2d.analyze.Analyzer#process()
	 */
	@Override
	public void process() {
		for (int depth = biggestDepth; depth >= 0; depth--) {
			ArrayList<ConditionalInstruction> instructions = this.instructions.get(depth);
			if (instructions == null) {
				continue;
			}
			instr:for(ConditionalInstruction instruction : instructions) {
				if(ignores.contains(instruction)) {
					continue instr;
				}
				checkAnd(instruction);
				
			}
		}

	}

	private void checkOr(ConditionalInstruction instruction) {
		/*Old_BasicBlock holder= instruction.getHolder();
		Instruction first = holder.getFirstPrintableInstruction(instruction);
		
		if (first instanceof ConditionalInstruction) {
			ConditionalInstruction firstConditional = (ConditionalInstruction) first;
			if (firstConditional.getJumpTarget() == instruction.getJumpTarget()) {
				instruction.getRelations().add(new Relation(firstConditional, RelationType.OR));
				// Detected || relation
				holder.removeInstruction(firstConditional);
				ignores.add(firstConditional);
				
			}
		}*/
	}

	private void checkAnd(ConditionalInstruction instruction) {
		BasicBlock content = instruction.getTarget();
		if (content.getInstructions().last().getType().getBaseType() == InstructionBaseType.RETURN) {
			return;
		}
		Instruction fp = content.getFirstPrintableInstruction(null);
		if (fp == null) {
			return;
		}
		if(fp instanceof ConditionalInstruction) {
			ConditionalInstruction ci = (ConditionalInstruction) fp;
			if(ci.getHolder().getSuccessors().get(1) != instruction.getHolder().getSuccessors().get(1)) {
				return;
			}
		//	content.removeInstruction(ci);
			instruction.setCustomTarget(ci.getTarget());
			instruction.getRelations().add(new Relation(ci, RelationType.AND));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wycody.cs2d.analyze.Analyzer#finalyze()
	 */
	@Override
	public void finalyze() {
		// TODO Auto-generated method stub

	}
}
