package com.wycody.cs2d.analyze.impl;

import java.util.ArrayList;
import java.util.TreeMap;

import com.wycody.cs2d.analyze.Analyzer;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.flow.impl.BasicBlock;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.base.StoreInstruction;
import com.wycody.cs2d.script.inst.base.branch.ConditionalInstruction;
import com.wycody.cs2d.script.inst.base.branch.InlineConditional;
import com.wycody.cs2d.script.inst.walker.InstructionWalker;
import com.wycody.cs2d.script.inst.walker.WalkState;
import com.wycody.cs2d.script.inst.walker.WalkerAction;
import com.wycody.utils.DynamicArray;

/**
 * 
 * @author Walied-Yassen
 * @date Nov 26, 2015
 */
public class InlineConditionalDetect extends Analyzer {

	public static final int MAX_ALLOWED = 2;

	private TreeMap<Integer, ArrayList<ConditionalInstruction>> instructions;
	private int biggestDepth;

	public InlineConditionalDetect(CS2Script script) {
		super(script);

	}

	@Override
	public void initialize() {
		instructions = new TreeMap<Integer, ArrayList<ConditionalInstruction>>();
		InstructionWalker walker = new InstructionWalker(script.getStartBlock(), InstructionWalker.RESOLVE_JUMPS | InstructionWalker.RESOLVE_SWITCH_BLOCKS | InstructionWalker.RESOLVE_TARGET_BLOCKS | InstructionWalker.RESOLVE_FALSE_BLOCKS, new WalkerAction() {

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
				if (!instruction.hasElse()) {
					continue instr;
				}
				BasicBlock holder = instruction.getHolder();
				BasicBlock targetBlock = instruction.getTarget();
				BasicBlock elseBlock = instruction.getElse();

				DynamicArray<Instruction> targetPrintables = targetBlock.getPrintableInstructions(null);
				DynamicArray<Instruction> elsePrintables = elseBlock.getPrintableInstructions(null);

				if (targetPrintables.size() != elsePrintables.size() || targetPrintables.size() > MAX_ALLOWED) {
					System.err.println(targetPrintables.size() + ", " + elsePrintables.size() +", " + instruction.getCondition());
					continue instr;
				}
				boolean apply = true;
				c: for (int index = 0; index < targetPrintables.size(); index++) {
					Instruction targetPrintable = targetPrintables.get(index);
					Instruction elsePrintable = elsePrintables.get(index);
					if (targetPrintable.getType() != elsePrintable.getType() || !canBe(elsePrintable)) {
						apply = false;
						break c;
					}

				}
				if (apply) {
					c: for (int index = 0; index < targetPrintables.size(); index++) {
						Instruction targetPrintable = targetPrintables.get(index);
						Instruction elsePrintable = elsePrintables.get(index);
						InlineConditional cond = new InlineConditional(instruction, targetPrintable, elsePrintable);
						holder.addAfter(instruction,  cond);
					}
					holder.removeInstruction(instruction);

				}

			}

		}
	}

	private boolean canBe(Instruction t) {
		if (t instanceof StoreInstruction) {
			return true;
		}
		return false;
	}

	@Override
	public void finalyze() {
		// TODO Auto-generated method stub

	}

}
