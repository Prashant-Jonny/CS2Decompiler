package com.wycody.cs2d.script.flow.impl;

import java.util.TreeMap;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.flow.Block;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionBaseType;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.branch.ConditionalInstruction;
import com.wycody.cs2d.script.inst.base.branch.JumpInstruction;
import com.wycody.cs2d.script.inst.swtch.SwitchBlock;
import com.wycody.cs2d.script.inst.swtch.SwitchInstruction;
import com.wycody.cs2d.script.inst.walker.InstructionWalker;
import com.wycody.cs2d.script.inst.walker.WalkState;
import com.wycody.cs2d.script.inst.walker.WalkerAction;
import com.wycody.utils.DynamicArray;
import com.wycody.utils.IllegalOperationException;
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

	public BasicBlock(int address, DynamicArray<Instruction> instructions) {
		super(address);
		this.instructions = instructions;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wycody.cs2d.script.flow.Block#preprocess(com.wycody.cs2d.Context)
	 */
	@Override
	public void preprocess(Context context) {
		for (Instruction instruction : instructions) {
			try {
				instruction.preprocess(context);
			} catch (RuntimeException ex) {
				throw new RuntimeException("Error preprocessing instruction " + instruction, ex);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wycody.cs2d.script.flow.Block#process(com.wycody.cs2d.Context)
	 */
	@Override
	public void process(Context context) {

		for (Instruction instruction : instructions) {
			try {
				instruction.process(context);
			} catch (RuntimeException ex) {
				throw new RuntimeException("Error processing instruction " + instruction, ex);
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wycody.cs2d.script.flow.Block#postprocess(com.wycody.cs2d.Context)
	 */
	@Override
	public void postprocess(Context context) {
		for (Instruction instruction : instructions) {
			try {
				instruction.postprocess(context);
			} catch (RuntimeException ex) {
				throw new RuntimeException("Error postprocessing instruction " + instruction, ex);
			}
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
		for (int index = 0; index < instructions.size(); index++) {

			Instruction instr = instructions.get(index);

			if (instr.getBeforeComment() != null) {
				instr.getBeforeComment().print(context, printer);
			}
			instr.print(context, printer);
			if (instr.getAfterComment() != null) {
				instr.getAfterComment().print(context, printer);
			}

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
		int index = instructions.indexOf(last);
		if (index != -1) {
			instructions.remove(index);
		} else {
			// throw new IndexOutOfBoundsException("The object could not be
			// found in the array!");
		}
		last.unlink();
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

	public DynamicArray<Instruction> getPrintableInstructions(Instruction after) {
		DynamicArray<Instruction> instrs = new DynamicArray<>(Instruction.class);
		InstructionWalker walker = new InstructionWalker(this, InstructionWalker.RESOLVE_JUMPS | InstructionWalker.RESOLVE_FALSE_BLOCKS, new WalkerAction() {

			@Override
			public WalkState visitInstr(int depth, Instruction instruction) {
				if (instruction.getHolder() == BasicBlock.this) {
					if (after != null && after.getAddress() >= instruction.getAddress()) {
						return WalkState.CONTINUE;
					}
				}
				if (!instruction.isPrintable()) {
					return WalkState.CONTINUE;
				}
				if (instruction.getType().getBaseType().isPrintable()) {
					instrs.add(instruction);
				}
				return WalkState.CONTINUE;
			}
		});
		walker.startWalking();
		return instrs;
	}

	public Instruction getFirstPrintableInstruction(Instruction after) {
		return getFirstPrintableInstruction(after, null);
	}

	public Instruction getFirstPrintableInstruction(Instruction after, Class<?> ignores) {
		return getFirstPrintableInstruction(after, InstructionWalker.RESOLVE_JUMPS | InstructionWalker.RESOLVE_FALSE_BLOCKS, ignores);
	}

	public Instruction getFirstPrintableInstruction(Instruction after, int mode, Class<?> ignores) {
		final Instruction[] instr = new Instruction[1];
		InstructionWalker walker = new InstructionWalker(this, mode, new WalkerAction() {

			@Override
			public WalkState visitInstr(int depth, Instruction instruction) {
				if (instruction.getHolder() == BasicBlock.this) {
					if (after != null && instruction.getHolder().getInstructions().indexOf(after) >= instruction.getHolder().getInstructions().indexOf(instruction)) {
						return WalkState.CONTINUE;
					}
				}

				if (instruction.getType().getBaseType().isPrintable()) {
					if (ignores != null) {
						if (ignores == instruction.getClass()) {
							return WalkState.CONTINUE;
						}
					}
					instr[0] = instruction;
					return WalkState.STOP_WALKING;
				}
				return WalkState.CONTINUE;
			}
		});
		walker.startWalking();
		return instr[0];
	}

	public DynamicArray<Instruction> getBetween(Instruction start, Instruction end) {
		if (start == null) {
			throw new NullPointerException("The start node is null");
		}
		if (end == null) {
			throw new NullPointerException("The end node is null");
		}

		DynamicArray<Instruction> between = new DynamicArray<Instruction>(Instruction.class);
		InstructionWalker walker = new InstructionWalker(this, InstructionWalker.RESOLVE_JUMPS | InstructionWalker.RESOLVE_FALSE_BLOCKS, new WalkerAction() {

			@Override
			public WalkState visitInstr(int depth, Instruction instruction) {
				if (start != null && start.getAddress() < instruction.getAddress()) {
					if (end != null && end.getAddress() > instruction.getAddress()) {

						between.add(instruction);
					}
				}
				return WalkState.CONTINUE;
			}
		});
		walker.startWalking();
		return between;
	}

	public Match<JumpInstruction> findElseJump(BasicBlock target) {
		if (this == target) {
			try {
				throw new IllegalOperationException("You cannot search for matches in same block!");
			} catch (IllegalOperationException e) {
				e.printStackTrace();
			}
		}
		TreeMap<Integer, JumpInstruction> thisJumps = new TreeMap<Integer, JumpInstruction>();
		Match<JumpInstruction> match = new Match<>();

		InstructionWalker thisWalker = new InstructionWalker(this, InstructionWalker.RESOLVE_SWITCH_BLOCKS | InstructionWalker.RESOLVE_JUMPS | InstructionWalker.RESOLVE_FALSE_BLOCKS, new WalkerAction() {

			@Override
			public WalkState visitInstr(int depth, Instruction instruction) {
				if (instruction.getType().getBaseType() == InstructionBaseType.JUMP) {
					JumpInstruction jump = (JumpInstruction) instruction;
					thisJumps.put(jump.getJumpTarget(), jump);
				}
				return WalkState.CONTINUE;
			}
		});

		thisWalker.startWalking();

		InstructionWalker targetWalker = new InstructionWalker(target, InstructionWalker.RESOLVE_SWITCH_BLOCKS | InstructionWalker.RESOLVE_JUMPS | InstructionWalker.RESOLVE_FALSE_BLOCKS, new WalkerAction() {

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
				return WalkState.CONTINUE;
			}
		});
		targetWalker.startWalking();
		if (match.getFirst() != null && match.getSecond() != null && match.getFirst() != match.getSecond()) {
			return match;
		}
		return null;
	}

	public Match<JumpInstruction> findFirstMatchJump(BasicBlock target) {
		TreeMap<Integer, JumpInstruction> thisJumps = new TreeMap<Integer, JumpInstruction>();
		Match<JumpInstruction> match = new Match<>();

		InstructionWalker thisWalker = new InstructionWalker(this, InstructionWalker.RESOLVE_JUMPS | InstructionWalker.RESOLVE_FALSE_BLOCKS, new WalkerAction() {

			@Override
			public WalkState visitInstr(int depth, Instruction instruction) {
				if (instruction.getType().getBaseType() == InstructionBaseType.JUMP) {
					JumpInstruction jump = (JumpInstruction) instruction;
					thisJumps.put(jump.getJumpTarget(), jump);
				}
				return WalkState.CONTINUE;
			}
		});

		thisWalker.startWalking();

		InstructionWalker targetWalker = new InstructionWalker(target, InstructionWalker.RESOLVE_JUMPS | InstructionWalker.RESOLVE_FALSE_BLOCKS, new WalkerAction() {

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
				return WalkState.CONTINUE;
			}
		});
		targetWalker.startWalking();
		if (match.getFirst() != null && match.getSecond() != null) {
			return match;
		}
		return null;
	}

	public Match<JumpInstruction> findDuplicationJump(JumpInstruction target) {
		Match<JumpInstruction> match = new Match<>();
		InstructionWalker thisWalker = new InstructionWalker(this, InstructionWalker.RESOLVE_JUMPS | InstructionWalker.RESOLVE_FALSE_BLOCKS, new WalkerAction() {

			@Override
			public WalkState visitInstr(int depth, Instruction instruction) {
				if (instruction.getType().getBaseType() == InstructionBaseType.JUMP) {
					JumpInstruction jump = (JumpInstruction) instruction;
					if (jump.getJumpTarget() == target.getJumpTarget()) {
						match.setFirst(jump);

						match.setSecond(target);

						return WalkState.STOP_WALKING;
					}
				}
				return WalkState.CONTINUE;
			}
		});

		thisWalker.startWalking();
		if (match.getFirst() != null && match.getSecond() != null) {
			return match;
		}
		return null;

	}

	public JumpInstruction detectNearestWhileJump(ConditionalInstruction cond) {
		// Instruction instr = instructions.last();
		//
		// while ((instr instanceof JumpInstruction)) {
		// JumpInstruction jumpInstr = (JumpInstruction) instr;
		//
		// BasicBlock target = jumpInstr.getTarget();
		//
		// if(target == null) {
		// if((jumpInstr.getAddress() > jumpInstr.getJumpTarget()) &&
		// (jumpInstr.getJumpTarget() == cond.getTarget().getAddress())) {
		// return jumpInstr;
		// }
		// System.err.println(jumpInstr.getJumpTarget() + ", " +
		// cond.getTarget().getAddress() + ", (" + cond.getCondition() + ")");
		// //throw new Error("Undetected while loop probably!");
		//
		// }
		//
		// Instruction last = target.getInstructions().last();
		// if (last instanceof JumpInstruction) {
		// instr = last;
		// } else {
		// instr = null;//jumpInstr.getJumpTarget() >=
		// cond.getHolder().getAddress() && jumpInstr.getJumpTarget() <=
		// cond.getAddress()-2
		// }
		// }
		final JumpInstruction[] jumps = new JumpInstruction[1];// temporary
																// patch for
																// final shit in
																// enclosing
																// types
		InstructionWalker walker = new InstructionWalker(this, InstructionWalker.ALL, new WalkerAction() {

			@Override
			public WalkState visitInstr(int depth, Instruction instruction) {
				if (instruction instanceof JumpInstruction) {
					JumpInstruction jump = (JumpInstruction) instruction;
					BasicBlock target = jump.getTarget();
					if (target == null || target == cond.getHolder()) {
						if (jump.getJumpTarget() < jump.getAddress()) {
							if (jump.getJumpTarget() >= cond.getHolder().getAddress()) {
								if (jump.getJumpTarget() <= cond.getAddress() - 2) {

									jumps[0] = jump;
									return WalkState.STOP_WALKING;
								}
							}
						}

						return WalkState.DONT_WALK_BLOCKS;

					}
				}
				return WalkState.CONTINUE;
			}
		});
		walker.startWalking();
		return jumps[0];
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("----------- Start of " + getName() + " -----------\n");
		for (Instruction instruction : instructions) {
			sb.append("[id=" + instruction.getId() + ", address=" + instruction.getAddress() + "]\n");
		}
		return sb.toString();

	}

	public void addAfter(Instruction after, Instruction instr) {
		instructions.addAfter(after, instr);
		instr.setHolder(this);
	}

	public void addFirst(Instruction n) {
		n.setHolder(this);
		instructions.addFirst(n);
	}

	public Instruction getFirstJump(int mode) {
		final JumpInstruction[] jumps = new JumpInstruction[1];
		InstructionWalker walker = new InstructionWalker(this, mode, new WalkerAction() {

			@Override
			public WalkState visitInstr(int depth, Instruction instruction) {
				if (instruction instanceof JumpInstruction) {
					jumps[0] = (JumpInstruction) instruction;
					return WalkState.STOP_WALKING;
				}
				return WalkState.CONTINUE;
			}
		});
		walker.startWalking();
		return jumps[0];
	}

	public Instruction getLastReachableInstruction() {
		final Instruction[] jumps = new Instruction[1];
		InstructionWalker walker = new InstructionWalker(this, InstructionWalker.RESOLVE_FALSE_BLOCKS | InstructionWalker.RESOLVE_NOR_JUMP | InstructionWalker.RESOLVE_CUST_JUMP, new WalkerAction() {

			@Override
			public WalkState visitInstr(int depth, Instruction instruction) {
				jumps[0] = instruction;
				return WalkState.CONTINUE;
			}
		});
		walker.startWalking();
		return jumps[0];
	}

	public void replaceInstruction(Instruction instruction, Instruction with) {
		int index = instructions.indexOf(instruction);
		if (index == -1)
			throw new Error("Could not find the instruction to replace with.");
		instructions.add(index, with);
	}

	// after index = 2, before index = 5
	// index = 3
	// index = 1
	public DynamicArray<Instruction> cut(SwitchInstruction after, Instruction before) {
		DynamicArray<Instruction> instructions = new DynamicArray<>(Instruction.class);

		for (Instruction instruction : this.instructions) {
			if (after != null && after.getAddress() >= instruction.getAddress())
				continue;
			if (before != null && before.getAddress() <= instruction.getAddress())
				continue;
			removeInstruction(instruction);
			instructions.add(instruction);

		}
		return instructions;
	}

//	public static Object ofArray(int id, DynamicArray<Instruction> defaultBlock) {
//		//BasicBlock block = new BasicBlock(SwitchBlock.generateDefaultBlockId());
//
//	}

}
