package com.wycody.cs2d.script.flow.impl;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionBaseType;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.ReturnInstruction;
import com.wycody.cs2d.script.inst.base.branch.BranchInstruction;
import com.wycody.cs2d.script.inst.base.branch.JumpInstruction;
import com.wycody.cs2d.script.inst.base.branch.NaturalFlowJump;

/**
 * 
 * @author Walied-Yassen
 * @date Nov 15, 2015
 */
public class BasicBlockGenerator {

	private static final Logger logger = LoggerFactory.getLogger(BasicBlockGenerator.class.getName());

	/**
	 * The target script to generate for
	 */
	private CS2Script script;

	/**
	 * The generated blocks map. (StartAddress, Block)
	 */
	private Map<Integer, BasicBlock> generatedBlocks;

	/**
	 * The last generated block
	 */
	private BasicBlock currentBlock;

	/**
	 * The custom address for blocks this will always be negative
	 */
	private int customAddress;
	
	/**
	 * Construct a new {@link BasicBlockGenerator}
	 * 
	 * @param script
	 *            the script to generator for
	 */
	public BasicBlockGenerator(CS2Script script) {
		this.script = script;
		generatedBlocks = new TreeMap<Integer, BasicBlock>();
		customAddress = -1;
	}

	/**
	 * Start the generation process
	 */
	public void start() {
		Set<Integer> createAt = new HashSet<>();
		Instruction[] raw = script.getInstructions();

		generateBasicBlock(0);

		for (int address = 0; address < raw.length; address++) {
			Instruction instruction = raw[address];
			Instruction prevInstruction = address > 0 ? raw[address - 1] : null;
			Instruction nextInstruction = address < raw.length - 1 ? raw[address + 1] : null;
			currentBlock.addInstruction(instruction);
			if (instruction.getType().getBaseType() == InstructionBaseType.BRANCH) {
				if (!createAt.contains(1 + instruction.getAddress() + instruction.getIntegerOperand())) {
					createAt.add(1 + instruction.getAddress() + instruction.getIntegerOperand());
				}
			} else if (instruction.getType() == InstructionType.JUMP) {
				if (!createAt.contains(1 + instruction.getAddress() + instruction.getIntegerOperand())) {
					createAt.add(1 + instruction.getAddress() + instruction.getIntegerOperand());
				}
				generateBasicBlock(instruction.getAddress() + 1);
			} else if (instruction.getType() == InstructionType.RETURN) {
				if (!createAt.contains(instruction.getAddress() + 1)) {
					generateBasicBlock(instruction.getAddress() + 1);
				}
			}
			if (nextInstruction != null) {
				if (createAt.contains(nextInstruction.getAddress())) {

					createAt.remove(nextInstruction.getAddress());
					generateBasicBlock(nextInstruction.getAddress());
				}
			}
		}

		script.setBlocks(generatedBlocks);
		script.linkBlocks();
		calculate();
	}

	private void calculate() {
		for (Instruction instr : script.getAllInstructions()) {
			if (instr instanceof BranchInstruction) {
				BranchInstruction branch = (BranchInstruction) instr;
				BasicBlock target = generatedBlocks.get(branch.getJumpTarget());
				if (target == null) {
					continue;
				}
				target.getPredecessors().add(branch.getHolder());
				branch.getHolder().getSuccessors().add(target);
			}
		}
	}

	/**
	 * Generate a new basic block and attach it to the script
	 * 
	 * @param address
	 *            the address of the block
	 * @param instructions
	 *            the content of the block
	 */
	private void generateBasicBlock(int address) {
		if (currentBlock != null) {
			Instruction last = currentBlock.getInstructions().last();
			if (currentBlock.getAddress() == 222 && last != null) {
				
			}
			if (!(last instanceof JumpInstruction) && !(last instanceof ReturnInstruction)) {
				NaturalFlowJump natJump = new NaturalFlowJump(address);
				natJump.setScript(script);
				currentBlock.addInstruction(natJump);

			}
		}
		currentBlock = new BasicBlock(address);
		currentBlock.setHolder(script);
		generatedBlocks.put(address, currentBlock);

	}

	public BasicBlock generateCustomBlock() {
		BasicBlock block = new  BasicBlock(customAddress--);
		block.setHolder(script);
		generatedBlocks.put(block.getAddress(), block);
		return block;
	}
	/**
	 * @return the script
	 */
	public CS2Script getScript() {
		return script;
	}

	/**
	 * @param script
	 *            the script to set
	 */
	public void setScript(CS2Script script) {
		this.script = script;
	}

	/**
	 * @return the generatedBlocks
	 */
	public Map<Integer, BasicBlock> getGeneratedBlocks() {
		return generatedBlocks;
	}

	/**
	 * @param generatedBlocks
	 *            the generatedBlocks to set
	 */
	public void setGeneratedBlocks(Map<Integer, BasicBlock> generatedBlocks) {
		this.generatedBlocks = generatedBlocks;
	}

	/**
	 * @return the currentBlock
	 */
	public BasicBlock getCurrentBlock() {
		return currentBlock;
	}

	/**
	 * @param currentBlock
	 *            the currentBlock to set
	 */
	public void setCurrentBlock(BasicBlock currentBlock) {
		this.currentBlock = currentBlock;
	}

}
