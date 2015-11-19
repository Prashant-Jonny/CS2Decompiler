package com.wycody.cs2d.analyze.impl;

import com.wycody.cs2d.analyze.Analyzer;
import com.wycody.cs2d.script.CS2Script;
//import com.wycody.cs2d.script.flow.Old_BasicBlock;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.base.StoreInstruction;
import com.wycody.utils.DynamicArray;
/**
 * 
 * @author Walied-Yassen
 * @date Nov 15, 2015
 */
public class MathOEDetect extends Analyzer{

	private DynamicArray<StoreInstruction> instructions;

	public MathOEDetect(CS2Script script) {
		super(script);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize() {
		instructions = new DynamicArray<>(StoreInstruction.class);
		/*for (Old_BasicBlock block : script.getBlocks().values()) {
			for (Instruction instruction : block.getInstructions()) {
				if (instruction instanceof StoreInstruction) {
					instructions.add((StoreInstruction) instruction);
				}
			}
		}*/
	}

	@Override
	public void process() {
		for(StoreInstruction instruction : instructions) {
			// Temporary while we store the stacks as instructions
			String value = instruction.value.toString();
			if(!value.startsWith("(")) {
				continue;
			}
			value = value.substring(1, value.length() - 1);
			String[] parts = value.split(" ", 3);
			if(parts[0].equalsIgnoreCase(instruction.getVariable().toString())) {
				// DETECTEd
				String o = parts[1];
				Object v = parts[2];
				instruction.setOE(true, o, v);
			}
		}
	}

	@Override
	public void finalyze() {
		// TODO Auto-generated method stub
		
	}

}
