package com.wycody.cs2d.script.inst.walker;

import java.util.ArrayList;

import com.wycody.cs2d.script.flow.impl.BasicBlock;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionBaseType;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.branch.BranchInstruction;
import com.wycody.cs2d.script.inst.base.branch.ConditionalInstruction;
import com.wycody.cs2d.script.inst.base.branch.CustomJump;
import com.wycody.cs2d.script.inst.base.branch.JumpInstruction;
import com.wycody.cs2d.script.inst.base.branch.NaturalFlowJump;
import com.wycody.cs2d.script.inst.swtch.SwitchInstruction;

/**
 * Walk through the opcodes like it's executing, using <strike>depth first
 * search</strike> custom algorithm
 * 
 * @author Walied-Yassen
 * @date Nov 16, 2015
 */
public class InstructionWalker {

	/**
	 * Visit the normal jumps if found
	 */
	public static final int RESOLVE_NOR_JUMP = 1 << 0;
	/**
	 * Visit the normal jumps if found
	 */
	public static final int RESOLVE_CUST_JUMP = 1 << 1;

	/**
	 * Visit the natural jumps if found
	 */
	public static final int RESOLVE_NAT_JUMP = 1 << 2;

	/**
	 * Visit the branch targets if found
	 */
	public static final int RESOLVE_TARGET_BLOCKS = 1 << 3;

	/**
	 * Visit the else conditions if found
	 */
	public static final int RESOLVE_FALSE_BLOCKS = 1 << 4;

	/**
	 * Visit the switch nodes if found
	 */
	public static final int RESOLVE_SWITCH_BLOCKS = 1 << 5;

	/**
	 * Visit the jumps if found
	 */
	public static final int RESOLVE_JUMPS = RESOLVE_NOR_JUMP | RESOLVE_CUST_JUMP | RESOLVE_NAT_JUMP;

	/**
	 * Visit all instructions
	 */
	public static final int ALL = RESOLVE_JUMPS | RESOLVE_TARGET_BLOCKS | RESOLVE_FALSE_BLOCKS | RESOLVE_SWITCH_BLOCKS;

	/**
	 * Visit the original instructions (Not customs nor natural)
	 */
	public static final int ALL_NORMAL = RESOLVE_SWITCH_BLOCKS | RESOLVE_NOR_JUMP | RESOLVE_FALSE_BLOCKS | RESOLVE_TARGET_BLOCKS;

	/**
	 * The visit action
	 */
	private WalkerAction action;

	/**
	 * The start block
	 */
	private BasicBlock startBlock;

	/**
	 * The mode
	 */
	private int mode;

	/**
	 * The current depth of the walker
	 */
	private int depth;

	/**
	 * Stopped walking?
	 */
	private boolean stopped;

	private boolean oneTimeWalk;

	/**
	 * Construct a new {@link InstructionWalker}
	 * 
	 * @param startBlock
	 *            the entry point for the walker
	 * @param action
	 *            the action when we visit an instruction
	 * @param mode
	 *            the walker mode, such as {@link #RESOLVE_JUMPS}
	 */
	public InstructionWalker(BasicBlock startBlock, int mode, WalkerAction action) {
		this.startBlock = startBlock;
		this.mode = mode;
		this.action = action;

	}

	/**
	 * Start the walking algorithm
	 */
	public void startWalking() {
		ArrayList<Integer> walkedList = new ArrayList<Integer>();
		walk(walkedList, startBlock);
	}

	private void walk(ArrayList<Integer> walkedList, BasicBlock startBlock) {
		if (startBlock == null) {
			throw new NullPointerException("The start block is null");
		}
		if (stopped || walkedList.contains(startBlock.getAddress())) {
			return;
		}
		walkedList.add(startBlock.getAddress());
		Instruction prev = null;
		for (Instruction instr : startBlock.getInstructions()) {

			WalkState state = action.visitInstr(depth, instr);
			if (state == WalkState.STOP_WALKING) {
				stopWalking();
				return;
			} else if (state == WalkState.DONT_WALK_BLOCKS) {
				return;
			}
			if (prev != null && prev.getType().getBaseType() == InstructionBaseType.BRANCH) {
				if (instr.getType() == InstructionType.JUMP) {

					JumpInstruction jump = (JumpInstruction) instr;
					if (isMode(RESOLVE_FALSE_BLOCKS)) {

						BasicBlock target = jump.getTarget();
						if (target != null) {
							depth++;

							walk(walkedList, target);

							depth--;

						}

					}

				}

			} else {
				if (instr.getType().getBaseType() == InstructionBaseType.BRANCH) {
					depth++;
					BranchInstruction branch = (BranchInstruction) instr;
					BasicBlock target = branch.getTarget();
					if (instr instanceof ConditionalInstruction) {
						ConditionalInstruction cond = (ConditionalInstruction) instr;
						if (cond.getElse() != null && isMode(RESOLVE_FALSE_BLOCKS)) {
							if (!walkedList.contains(cond.getElse().getAddress())) {
								walk(walkedList, cond.getElse());
							}
						}
					}
					if (target != null && isMode(RESOLVE_TARGET_BLOCKS)) {
						if (!walkedList.contains(target.getAddress())) {
							walk(walkedList, target);
						}
					}
					depth--;
				} else if (instr.getType().getBaseType() == InstructionBaseType.JUMP) {
					JumpInstruction jump = (JumpInstruction) instr;
					BasicBlock target = jump.getTarget();

					if (target != null) {
						if (jump instanceof NaturalFlowJump && !isMode(RESOLVE_NAT_JUMP)) {

							continue;
						}
						if (jump instanceof CustomJump && !isMode(RESOLVE_CUST_JUMP)) {
							continue;
						}
						if (!isMode(RESOLVE_NOR_JUMP)) {
							continue;
						}

						if (!walkedList.contains(target.getAddress())) {
							walk(walkedList, target);
						}
					}
				} else if (instr.getType().getBaseType() == InstructionBaseType.SWITCH) {
					if (isMode(RESOLVE_SWITCH_BLOCKS)) {
						SwitchInstruction instruction = (SwitchInstruction) instr;
						for (BasicBlock tar : instruction.getTargets()) {
							depth++;
							if (!walkedList.contains(tar.getAddress())) {
								walk(walkedList, tar);
							}
							depth--;
						}
					}
				}
			}
			if (instr.isPrintable()) {
				prev = instr;
			}
		}
	}

	/**
	 * @return the action
	 */
	public WalkerAction getaction() {
		return action;
	}

	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(WalkerAction action) {
		this.action = action;
	}

	/**
	 * @return the startBlock
	 */
	public BasicBlock getStartBlock() {
		return startBlock;
	}

	/**
	 * @param startBlock
	 *            the startBlock to set
	 */
	public void setStartBlock(BasicBlock startBlock) {
		this.startBlock = startBlock;
	}

	/**
	 * Check for a mode if enabled
	 * 
	 * @param mode
	 *            the mode to check for
	 * @return mode is enabled
	 */
	public boolean isMode(int mode) {
		return (this.mode & mode) != 0;
	}

	/**
	 * Stop the walking process
	 */
	public void stopWalking() {
		stopped = true;
	}
}
