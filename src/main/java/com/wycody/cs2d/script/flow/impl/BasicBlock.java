package com.wycody.cs2d.script.flow.impl;

import java.util.TreeMap;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.flow.Block;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionBaseType;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.branch.JumpInstruction;
import com.wycody.cs2d.script.inst.walker.InstructionWalker;
import com.wycody.cs2d.script.inst.walker.WalkState;
import com.wycody.cs2d.script.inst.walker.WalkerAction;
import com.wycody.utils.DynamicArray;
import com.wycody.utils.Match;

/**
 * <a href=https://en.wikipedia.org/wiki/Control_flow_graph#Definition>Click me
 * for more details</a>
 * 
 * @author Walied-Yassen
 * @date Nov 6, 2015
 */
public class BasicBlock extends Block {

	/**
	 * The instructions of the block
	 */
	private DynamicArray<Instruction> instructions;

	/**
	 * Construct a new {@link BasicBlock}
	 * 
	 * @param address
	 *            the address of the block
	 */
	public BasicBlock(int address) {
		super(address);
		this.instructions = new DynamicArray<>(Instruction.class);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wycody.cs2d.script.flow.Block#process(com.wycody.cs2d.Context)
	 */
	@Override
	public void process(Context context) {
		for (Instruction instruction : instructions) {
			instruction.process(context);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wycody.cs2d.script.flow.Block#print(com.wycody.cs2d.Context,
	 * com.wycody.cs2d.print.ScriptPrinter)
	 */
	@Override
	public void print(Context context, ScriptPrinter printer) {
		for (Instruction instruction : instructions) {
			instruction.print(context, context.getPrinter());
		}
	}

	/**
	 * Add an instruction to the current block
	 * 
	 * @param instruction
	 *            the instruction to add
	 */
	public void addInstruction(Instruction instruction) {
		instructions.add(instruction);
		instruction.setHolder(this);

	}

	/**
	 * @return the instructions
	 */
	public DynamicArray<Instruction> getInstructions() {
		return instructions;
	}

	/**
	 * @param instructions
	 *            the instructions to set
	 */
	public void setInstructions(DynamicArray<Instruction> instructions) {
		this.instructions = instructions;
	}

	/**
	 * Gets the block name
	 * 
	 * @return the name
	 */
	public String getName() {
		return "Block(" + getAddress() + ")";
	}

	public void removeInstruction(Instruction last) {
		last.unlink();
		instructions.remove(instructions.indexOf(last));
	}

	public BasicBlock getLastReachable() {
		BasicBlock block = this;
		Instruction cur = null;
		int index = 0;
		while (block != null && (cur = block.getInstructions().get(index++)) != null) {
			if (cur.getType() == InstructionType.JUMP) {
				JumpInstruction jump = (JumpInstruction) cur;
				block = jump.getTarget();
				index = 0;
			}
		}
		return block;
	}

	/**
	 * I know the name is super non accurate but i don't have time to find the
	 * name for the methods atm, however this method does remove the jumps lead
	 * to an block
	 * 
	 * @param target
	 *            the address of the block
	 */
	public void removeWithinRange(int target) {
		BasicBlock block = this;
		Instruction cur = null;
		int index = 0;
		while ((cur = block.getInstructions().get(index++)) != null) {
			if (cur.getType() == InstructionType.JUMP) {
				JumpInstruction jump = (JumpInstruction) cur;
				if (jump.getJumpTarget() == target) {
					jump.getHolder().removeInstruction(jump);

					break;
				} else {
					block = jump.getTarget();
					index = 0;
				}
			}
		}

	}

	public Instruction getFirstPrintableInstruction(Instruction after) {
		for (int address = after == null ? 0 : after.getAddress(); address < instructions.size(); address++) {
			Instruction instr = instructions.get(address);
			if (instr.getType().getBaseType().isPrintable()) {
				return instr;
			}
		}
		return null;
	}

	public Match<JumpInstruction> findNearestMatchJump(BasicBlock target) {
		TreeMap<Integer, JumpInstruction> thisJumps = new TreeMap<Integer, JumpInstruction>();
		Match<JumpInstruction> match = new Match<>();

		InstructionWalker thisWalker = new InstructionWalker(this, InstructionWalker.RESOLVE_JUMPS, new WalkerAction() {
			
			@Override
			public WalkState visitInstr(int depth, Instruction instruction) {
				if (instruction.getType().getBaseType() == InstructionBaseType.JUMP) {
					JumpInstruction jump = (JumpInstruction) instruction;
					thisJumps.put(jump.getJumpTarget(), jump);
				}
				return WalkState.NONE;
			}
		});

		thisWalker.startWalking();

		InstructionWalker targetWalker = new InstructionWalker(target, InstructionWalker.RESOLVE_JUMPS, new WalkerAction() {
			
			@Override
			public WalkState visitInstr(int depth, Instruction instruction) {
				if (instruction.getType().getBaseType() == InstructionBaseType.JUMP) {
					JumpInstruction jump = (JumpInstruction) instruction;
					if (thisJumps.containsKey(jump.getJumpTarget())) {
						match.setFirst(thisJumps.get(jump.getJumpTarget()));
						match.setSecond(jump);
						return WalkState.STOP_WALKING;
					}
				}
				return WalkState.NONE;
			}
		});
		targetWalker.startWalking();
		return match;

	}


}
