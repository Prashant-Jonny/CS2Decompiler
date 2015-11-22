package com.wycody.cs2d.script.inst.walker;

import java.util.ArrayList;

import com.wycody.cs2d.script.flow.impl.BasicBlock;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionBaseType;
import com.wycody.cs2d.script.inst.base.branch.BranchInstruction;
import com.wycody.cs2d.script.inst.base.branch.ConditionalInstruction;
import com.wycody.cs2d.script.inst.base.branch.JumpInstruction;

/**
 * Walk through the opcodes like it's executing, using <strike>depth first
 * search</strike> custom algorithm
 * 
 * @author Walied-Yassen
 * @date Nov 16, 2015
 */
public class InstructionWalker {

	/**
	 * Visit the jumps if found
	 */
	public static final int RESOLVE_JUMPS = 0x1;

	/**
	 * Visit the branch targets if found
	 */
	public static final int RESOLVE_TARGET_BLOCKS = 0x2;

	/**
	 * Visit the else conditions if found
	 */
	public static final int RESOLVE_ELSE_BLOCKS = 0x4;

	public static final int ALL = RESOLVE_JUMPS | RESOLVE_ELSE_BLOCKS |RESOLVE_TARGET_BLOCKS;

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
		ArrayList<BasicBlock> walkedList = new ArrayList<BasicBlock>();
		walk(walkedList, startBlock);
	}

	private void walk(ArrayList<BasicBlock> walkedList, BasicBlock startBlock) {
		if (startBlock == null || walkedList.contains(startBlock)) {
			return;
		}

		for (Instruction instr : startBlock.getInstructions()) {
			if (stopped) {
				return;
			}

			WalkState state = action.visitInstr(depth, instr);
			if (state == WalkState.STOP_WALKING) {
				stopWalking();
				return;
			} else if(state == WalkState.DONT_WALK_BLOCKS) {
				return;
			}
			if (instr.getType().getBaseType() == InstructionBaseType.BRANCH) {
				depth++;
				BranchInstruction branch = (BranchInstruction) instr;
				BasicBlock target = branch.getTarget();
				if (instr instanceof ConditionalInstruction) {
					ConditionalInstruction cond = (ConditionalInstruction) instr;
					if (cond.elseBlock != null && isMode(RESOLVE_ELSE_BLOCKS)) {
						walk(walkedList, cond.elseBlock);
					}
				}
				if (target != null && isMode(RESOLVE_TARGET_BLOCKS)) {
					walk(walkedList, target);
				}
				depth--;
			} else if (instr.getType().getBaseType() == InstructionBaseType.JUMP) {
				JumpInstruction jump = (JumpInstruction) instr;
				BasicBlock target = jump.getTarget();
				if (target != null && isMode(RESOLVE_JUMPS)) {
					walk(walkedList, target);
				}
			}

		}
		walkedList.add(startBlock);

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
