package com.wycody.cs2d.script.flow;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.node.Node;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.utils.DynamicArray;

/**
 * This will be the base of each block, BasicBlock, IfBlock, SwitchBlock, IfElse
 * Block, WhileBlock
 * 
 * @author Walied-Yassen
 * @date Nov 15, 2015
 */
public abstract class Block extends Node {

	/**
	 * The address of the block
	 */
	private int address;

	/**
	 * Successor is what this block jump to, this is a list of the jumps this
	 * block will do
	 */
	private DynamicArray<Block> successors;

	/**
	 * Predecessor is what blocks that jumped to this block
	 */
	private DynamicArray<Block> predecessors;

	/**
	 * The mother of this block
	 */
	private CS2Script holder;

	/**
	 * Construct a new {@link Block}
	 * 
	 * @param address
	 */
	public Block(int address) {
		this.address = address;
		this.predecessors = new DynamicArray<Block>(Block.class);
		this.successors = new DynamicArray<Block>(Block.class);

	}

	/**
	 * 
	 */
	public abstract void preprocess(Context context);
	
	/**
	 * Process the script
	 */
	public abstract void process(Context context);

	/**
	 * 
	 * @param context
	 */
	public abstract void postprocess(Context context);
	
	/**
	 * Print the script
	 */
	public abstract void print(Context context, ScriptPrinter printer);

	/**
	 * @return the address
	 */
	public int getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(int address) {
		this.address = address;
	}

	/**
	 * @return the successors
	 */
	public DynamicArray<Block> getSuccessors() {
		return successors;
	}

	/**
	 * @param successors
	 *            the successors to set
	 */
	public void setSuccessors(DynamicArray<Block> successors) {
		this.successors = successors;
	}

	/**
	 * @return the predecessors
	 */
	public DynamicArray<Block> getPredecessors() {
		return predecessors;
	}

	/**
	 * @param predecessors
	 *            the predecessors to set
	 */
	public void setPredecessors(DynamicArray<Block> predecessors) {
		this.predecessors = predecessors;
	}

	/**
	 * @return the holder
	 */
	public CS2Script getHolder() {
		return holder;
	}

	/**
	 * @param holder
	 *            the holder to set
	 */
	public void setHolder(CS2Script holder) {
		this.holder = holder;
	}

}
