package com.wycody.cs2d.analyze.impl;

import java.util.ArrayList;
import java.util.TreeMap;

import org.apache.commons.collections4.list.TreeList;

import com.wycody.cs2d.analyze.Analyzer;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.flow.impl.BasicBlock;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.base.branch.ConditionalInstruction;
import com.wycody.cs2d.script.inst.base.branch.Relation;
import com.wycody.cs2d.script.inst.base.branch.RelationType;
import com.wycody.cs2d.script.inst.walker.InstructionWalker;
import com.wycody.cs2d.script.inst.walker.WalkState;
import com.wycody.cs2d.script.inst.walker.WalkerAction;

public class ConditionalRelationDetect extends Analyzer {

	private TreeMap<Integer, TreeList<ConditionalInstruction>> instructions;
	private int biggestDepth;

	private TreeList<BasicBlock> affectedBlocks;

	private ArrayList<ConditionalInstruction> orIgnores;

	public ConditionalRelationDetect(CS2Script script) {
		super(script);
		affectedBlocks = new TreeList<BasicBlock>();
		orIgnores = new ArrayList<>();
	}

	@Override
	public void initialize() {
		retriveInstructions(script.getStartBlock(), InstructionWalker.ALL);

	}

	private void retriveInstructions(BasicBlock startBlock, int mode) {
		instructions = new TreeMap<Integer, TreeList<ConditionalInstruction>>();
		InstructionWalker walker = new InstructionWalker(startBlock, mode, new WalkerAction() {

			@Override
			public WalkState visitInstr(int depth, Instruction instruction) {
				if (instruction instanceof ConditionalInstruction) {
					if (depth > biggestDepth) {
						biggestDepth = depth;
					}
					if (!instructions.containsKey(depth)) {
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
			TreeList<ConditionalInstruction> instructions = this.instructions.get(depth);
			if (instructions == null) {
				continue;
			}
			b:for(int i = instructions.size()-1; i >= 0; i--) {
				ConditionalInstruction instruction = instructions.get(i);
				if (instruction.getElse() != null) {
					continue b;
				}

				detectAnd(instruction);

			}
		}
		for (int depth = biggestDepth; depth >= 0; depth--) {
			TreeList<ConditionalInstruction> instructions = this.instructions.get(depth);
			if (instructions == null) {
				continue;
			}
			b:for(int i = instructions.size()-1; i >= 0; i--) {
				ConditionalInstruction instruction = instructions.get(i);
				if (instruction.getElse() != null) {
					continue b;
				}
				detectOr(instruction);
			}

		}

	}

	private boolean detectAnd(ConditionalInstruction instruction) {
		BasicBlock target = instruction.getTarget();
		Instruction first = target.getFirstPrintableInstruction(null);
		if (first instanceof ConditionalInstruction) {

			ConditionalInstruction cond = (ConditionalInstruction) first;

			if (target.getFirstPrintableInstruction(cond) == null) {

				instruction.getRelations().add(new Relation(cond, RelationType.AND));
				cond.getHolder().removeInstruction(cond);
				instruction.setCustomTarget(cond.getTarget());
				affectedBlocks.add(instruction.getHolder());

				return true;
			}
		}
		return false;
	}

	private boolean detectOr(ConditionalInstruction instruction) {
		BasicBlock holder = instruction.getHolder();

		Instruction first = holder.getFirstPrintableInstruction(instruction, 0, null);
		if (first instanceof ConditionalInstruction) {
			ConditionalInstruction condFirst = (ConditionalInstruction) first;
			if (condFirst.getTarget() == instruction.getTarget()) {
				//	orIgnores.add(condFirst);
				
				condFirst.getHolder().removeInstruction(condFirst);
				instruction.addRelation(RelationType.OR, condFirst);
				int index = instruction.getHolder().getSuccessors().indexOf(condFirst.getTarget());
				if (index != -1)
					instruction.getHolder().getSuccessors().remove(index);
				return true;
			}
		}
		return false;
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
