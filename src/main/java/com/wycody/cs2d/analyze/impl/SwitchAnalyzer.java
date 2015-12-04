package com.wycody.cs2d.analyze.impl;

import java.util.HashMap;

import org.apache.commons.collections4.list.TreeList;

import com.wycody.cs2d.analyze.Analyzer;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.flow.impl.BasicBlock;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.base.branch.CustomJump;
import com.wycody.cs2d.script.inst.base.branch.JumpInstruction;
import com.wycody.cs2d.script.inst.swtch.CaseNode;
import com.wycody.cs2d.script.inst.swtch.SwitchBlock;
import com.wycody.cs2d.script.inst.swtch.SwitchInstruction;
import com.wycody.cs2d.script.inst.walker.InstructionWalker;
import com.wycody.cs2d.script.inst.walker.WalkState;
import com.wycody.cs2d.script.inst.walker.WalkerAction;
import com.wycody.cs2d.utils.CS2Utils;
import com.wycody.utils.DynamicArray;
import com.wycody.utils.Match;

/**
 * 
 * @author Walied-Yassen
 * @date Nov 25, 2015
 */
public class SwitchAnalyzer extends Analyzer {

	private TreeList<SwitchInstruction> instructions;

	public SwitchAnalyzer(CS2Script script) {
		super(script);
	}

	@Override
	public void initialize() {
		instructions = new TreeList<SwitchInstruction>();

		InstructionWalker walker = new InstructionWalker(script.getStartBlock(), InstructionWalker.ALL, new WalkerAction() {

			@Override
			public WalkState visitInstr(int depth, Instruction instruction) {
				if (instruction instanceof SwitchInstruction) {
					instructions.add((SwitchInstruction) instruction);
				}
				return WalkState.CONTINUE;
			}
		});
		walker.startWalking();
	}

	@Override
	public void process() {
		for (int index = 0; index < instructions.size(); index++) {
			SwitchInstruction instruction = instructions.get(index);
			detectMultiCase(instruction);
			detectDefaultCase(instruction);
			detectDuplicateJumps(instruction);
		}

	}

	private void detectDuplicateJumps(SwitchInstruction instruction) {
		// You cannot merge this with the below one you will break the code..
		// don't

		JumpInstruction[] firstMatch = CS2Utils.findFirstMatchJump(instruction.getTargets());

		if (firstMatch != null) {

			for (JumpInstruction instr : firstMatch) {
				instr.getHolder().removeInstruction(instr);
			}
			instruction.getHolder().addInstruction(new CustomJump(script, firstMatch[0].getJumpTarget()));
		} 
	}

	private void detectDefaultCase(SwitchInstruction instruction) {
		Match<JumpInstruction> defaultMatch = null;
		if(instruction.getBlock().getCases().size() == 1) {
			defaultMatch =  instruction.getHolder().findFirstMatchJump(instruction.getTargets()[0]);
		} else {
			JumpInstruction[] firstMatch = CS2Utils.findFirstMatchJump(instruction.getTargets());
			if(firstMatch == null) {
				return;
			}
			defaultMatch = instruction.getHolder().findNearestMatchJump(firstMatch[0]);
		}

		if (defaultMatch != null) {
			Instruction defaultInstr = instruction.getHolder().getInstructions().last();
			defaultInstr.getHolder().removeInstruction(defaultInstr);
			instruction.setDefaultCase((JumpInstruction) defaultInstr);
			
		}
//		if (defaultMatch == null) {
//			// temporary, maybe this support
//			boolean found = true;
//			for (JumpInstruction instr : firstMatch) {
//				BasicBlock target = instr.getTarget();
//				if(target.getLastReachable().getInstructions().last().getType().getBaseType() != InstructionBaseType.RETURN) {
//					found = false;
//					break;
//				}
//			}
//			if(found) {
//				instruction.setDefaultCase(instruction.getHolder().getInstructions().last());
//				instruction.getHolder().removeInstruction(instruction.getHolder().getInstructions().last());
//			}
//		}
	}

	private void detectMultiCase(SwitchInstruction instruction) {
		SwitchBlock switchBlock = instruction.getBlock();
		DynamicArray<CaseNode> cases = switchBlock.getCases();
		HashMap<BasicBlock, CaseNode> foundCases = new HashMap<BasicBlock, CaseNode>();
		for (int nodeIndex = 0; nodeIndex < cases.size(); nodeIndex++) {
			CaseNode node = cases.get(nodeIndex);
			BasicBlock target = instruction.getTarget(node);
			if (foundCases.containsKey(target)) {
				CaseNode mother = foundCases.get(target);
				mother.addChild(node);
				switchBlock.getCases().remove(nodeIndex, false);
			} else {
				foundCases.put(target, node);
			}
		}
		cases.refresh();

	}

	@Override
	public void finalyze() {
		// TODO Auto-generated method stub

	}

}
