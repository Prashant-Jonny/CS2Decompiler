package com.wycody.cs2d.analyze.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections4.list.TreeList;

import com.wycody.cs2d.analyze.Analyzer;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.flow.impl.BasicBlock;
//import com.wycody.cs2d.script.flow.Old_BasicBlock;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.base.StoreInstruction;
import com.wycody.cs2d.script.inst.base.branch.ConditionalInstruction;
import com.wycody.cs2d.script.inst.walker.InstructionWalker;
import com.wycody.cs2d.script.inst.walker.WalkState;
import com.wycody.cs2d.script.inst.walker.WalkerAction;

public class ForLoopDetect extends Analyzer {

	private HashMap<Integer, TreeList<StoreInstruction>> storeForLoops;
	private HashMap<Integer, TreeList<ConditionalInstruction>> normalForLoops;

	private int storeBiggestDepth;
	private int normalBiggestDepth;

	public ForLoopDetect(CS2Script script) {
		super(script);
	}

	@Override
	public void initialize() {
		storeForLoops = new HashMap<Integer, TreeList<StoreInstruction>>();

		InstructionWalker walker = new InstructionWalker(script.getStartBlock(), InstructionWalker.ALL, new WalkerAction() {

			@Override
			public WalkState visitInstr(int depth, Instruction instruction) {
				if (instruction instanceof StoreInstruction) {
					if (depth > storeBiggestDepth) {
						storeBiggestDepth = depth;
					}
					if (!storeForLoops.keySet().contains(depth)) {
						storeForLoops.put(depth, new TreeList<StoreInstruction>());
					}
					storeForLoops.get(depth).add((StoreInstruction) instruction);
				}
				return WalkState.CONTINUE;
			}
		});
		walker.startWalking();
		normalForLoops = new HashMap<Integer, TreeList<ConditionalInstruction>>();
		InstructionWalker normalWalker = new InstructionWalker(script.getStartBlock(), InstructionWalker.ALL, new WalkerAction() {

			@Override
			public WalkState visitInstr(int depth, Instruction instruction) {
				if (instruction instanceof ConditionalInstruction) {
					ConditionalInstruction instr = (ConditionalInstruction) instruction;
					if (!instr.isWhileLoop()) {
						return WalkState.CONTINUE;
					}
					if (depth > normalBiggestDepth) {
						normalBiggestDepth = depth;
					}
					if (!normalForLoops.keySet().contains(depth)) {
						normalForLoops.put(depth, new TreeList<ConditionalInstruction>());
					}
					normalForLoops.get(depth).add((ConditionalInstruction) instruction);
				}
				return WalkState.CONTINUE;
			}
		});
		normalWalker.startWalking();
	}

	@Override
	public void process() {
		checkStoreLoops();
		checkNormalLoops();// eh temporary ill merge them later into 1 thing and
							// add checks
	}

	private void checkNormalLoops() {
		for (int depth = normalBiggestDepth; depth >= 0; depth--) {
			List<ConditionalInstruction> instructions = this.normalForLoops.get(depth);
			if (instructions == null) {
				continue;
			}
			b: for (ConditionalInstruction instruction : instructions) {
				if (!instruction.isWhileLoop()) {
					return;
				}

				BasicBlock lastReach = instruction.getTarget().getLastReachable();
				Instruction first = instruction.getTarget().getFirstPrintableInstruction(null);
				Instruction last = lastReach.getInstructions().last();
				StoreInstruction storeIncr = null;
				if (first != last && first instanceof StoreInstruction && ((StoreInstruction) first).isIncr()) {
					storeIncr = (StoreInstruction) first;
					storeIncr.setIncrBefore(true);
				} else if (last instanceof StoreInstruction && ((StoreInstruction) last).isIncr()) {
					storeIncr = (StoreInstruction) last;
				}
				if (storeIncr != null) {
					instruction.setBlockType(ConditionalInstruction.FOR_BLOCK);
					instruction.setForVarIncr(storeIncr);

					storeIncr.getHolder().removeInstruction(storeIncr);
				}

			}
		}
	}

	private void checkStoreLoops() {
		for (int depth = storeBiggestDepth; depth >= 0; depth--) {
			List<StoreInstruction> instructions = this.storeForLoops.get(depth);
			if (instructions == null) {
				continue;
			}
			b: for (StoreInstruction instruction : instructions) {

				if (instruction.isIncr()) {
					continue b;
				}

				Instruction firstPrintable = instruction.getHolder().getFirstPrintableInstruction(instruction, StoreInstruction.class);
				if (firstPrintable instanceof ConditionalInstruction) {
					ConditionalInstruction cond = (ConditionalInstruction) firstPrintable;

					if (!cond.isWhileLoop()) {

						continue b;
					}

					BasicBlock lastReach = cond.getTarget().getLastReachable();
					Instruction first = cond.getTarget().getFirstPrintableInstruction(null);
					Instruction last = lastReach.getInstructions().last();
					StoreInstruction storeIncr = null;
					if (first != last && first instanceof StoreInstruction && ((StoreInstruction) first).isIncr()) {
						storeIncr = (StoreInstruction) first;
						storeIncr.setIncrBefore(true);
					} else if (last instanceof StoreInstruction && ((StoreInstruction) last).isIncr()) {
						storeIncr = (StoreInstruction) last;
					}
					if (storeIncr != null && instruction.getVariable() == storeIncr.getVariable()) {
						// DynamicArray<Instruction> inner =
						// instruction.getHolder().getBetween(instruction,
						// cond);
						// for(Instruction n : inner) {
						/// n.getHolder().removeInstruction(n);
						// cond.getTarget().addFirst(n);
						//
						// }
						cond.setBlockType(ConditionalInstruction.FOR_BLOCK);
						cond.setForVarDeclare(instruction);
						cond.setForVarIncr(storeIncr);

						storeIncr.getHolder().removeInstruction(storeIncr);
						instruction.getHolder().removeInstruction(instruction);
					}

				}
			}
		}
	}

	@Override
	public void finalyze() {

	}

}
