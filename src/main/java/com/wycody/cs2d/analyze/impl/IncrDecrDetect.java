package com.wycody.cs2d.analyze.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.wycody.cs2d.analyze.Analyzer;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.base.StoreInstruction;
import com.wycody.cs2d.script.inst.walker.InstructionWalker;
import com.wycody.cs2d.script.inst.walker.WalkState;
import com.wycody.cs2d.script.inst.walker.WalkerAction;

/**
 * 
 * @author Walied-Yassen
 * @date Nov 15, 2015
 */
public class IncrDecrDetect extends Analyzer {
	private HashMap<Integer, List<StoreInstruction>> instructions;
	private int biggestDepth;

	public IncrDecrDetect(CS2Script script) {
		super(script);
	}

	@Override
	public void initialize() {
		instructions = new HashMap<Integer, List<StoreInstruction>>();
		InstructionWalker walker = new InstructionWalker(script.getStartBlock(), InstructionWalker.ALL, new WalkerAction() {

			@Override
			public WalkState visitInstr(int depth, Instruction instruction) {
				if (instruction instanceof StoreInstruction) {
					if (depth > biggestDepth) {
						biggestDepth = depth;
					}
					if (!instructions.keySet().contains(depth)) {
						instructions.put(depth, new ArrayList<StoreInstruction>());
					}
					instructions.get(depth).add((StoreInstruction) instruction);
				}
				return WalkState.CONTINUE;
			}
		});
		walker.startWalking();
	}

	@Override
	public void process() {
		for (int depth = biggestDepth; depth >= 0; depth--) {
			List<StoreInstruction> instructions = this.instructions.get(depth);
			if (instructions == null) {
				continue;
			}
			for (StoreInstruction instruction : instructions) {
	
				// Temporary while we store the stacks as instructions
				String value = instruction.getValue().toString();
				if (value.startsWith("(")) {
					value = value.substring(1, value.length() - 1);
				}
		
				String[] parts = value.split(" ", 3);
				if (parts[0].equalsIgnoreCase(instruction.getVariable().toString())) {
					// DETECTEd
					String incrVar = parts[1];
					Object incrValue = parts[2];
					try {
						incrValue = Integer.parseInt((String) incrValue);
					} catch(NumberFormatException e) {
						
					}
	
					instruction.setIncr(true, incrVar, incrValue);
				}
			}
		}
	}

	@Override
	public void finalyze() {
		// TODO Auto-generated method stub

	}

}
