package com.wycody.cs2d.analyze.impl;

import java.util.List;
import java.util.TreeMap;

import org.apache.commons.collections4.list.TreeList;

import com.wycody.cs2d.analyze.Analyzer;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.base.ReturnInstruction;
import com.wycody.cs2d.script.inst.types.ReturnType;
import com.wycody.cs2d.script.inst.walker.InstructionWalker;
import com.wycody.cs2d.script.inst.walker.WalkState;
import com.wycody.cs2d.script.inst.walker.WalkerAction;

public class BooleanDetector extends Analyzer {

	private TreeMap<Integer, TreeList<ReturnInstruction>> returnInstructions;
	private int returnBiggestDepth;

	public BooleanDetector(CS2Script script) {
		super(script);
	}

	@Override
	public void initialize() {
		returnInstructions = new TreeMap<Integer, TreeList<ReturnInstruction>>();
		InstructionWalker returnWalker = new InstructionWalker(script.getStartBlock(), InstructionWalker.ALL, new WalkerAction() {

			@Override
			public WalkState visitInstr(int depth, Instruction instruction) {
				if (instruction instanceof ReturnInstruction) {

					if (depth > returnBiggestDepth) {
						returnBiggestDepth = depth;
					}
					if (!returnInstructions.keySet().contains(depth)) {
						returnInstructions.put(depth, new TreeList<ReturnInstruction>());
					}
					returnInstructions.get(depth).add((ReturnInstruction) instruction);
				}
				return WalkState.CONTINUE;
			}
		});
		returnWalker.startWalking();

	}

	@Override
	public void process() {
		boolean retBool = true;
		for (int depth = returnBiggestDepth; depth >= 0; depth--) {
			List<ReturnInstruction> instructions = this.returnInstructions.get(depth);
			if (instructions == null) {
				continue;
			}
	
			b: for (ReturnInstruction retInstr : instructions) {
				Object[] retObjs = retInstr.getReturnObjects();
				if (retObjs != null && retObjs.length == 1) {
					if (retObjs[0] instanceof Integer) {
						int value = (int) retObjs[0];
						if (value == 0 || value == 1) {
							continue b;
						}
					}
				}

				retBool = false;
				break b;

			}

			if (retBool) {
				for (ReturnInstruction retInstr : instructions) {
					retInstr.setReturnType(ReturnType.BOOLEAN);
				}
				script.setType(ReturnType.BOOLEAN);
			}
		}

	}

	@Override
	public void finalyze() {

	}

	public static boolean boolOfInt(int value) {
		return value != 0;
	}
}
