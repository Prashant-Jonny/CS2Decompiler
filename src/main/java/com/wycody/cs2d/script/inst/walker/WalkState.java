package com.wycody.cs2d.script.inst.walker;

/**
 * 
 * @author Walied-Yassen
 * @date Nov 19, 2015
 */
public enum WalkState {

	/**
	 * Continue walking normally
	 */
	CONTINUE,

	/**
	 * Stop walking (Stop the walker)
	 */
	STOP_WALKING,

	/**
	 * Stop walking for the blocks that are related to this, Such as JumpTarget,
	 */
	DONT_WALK_BLOCKS,

}
