package com.wycody.cs2d;

import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.CS2Disassembler;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.inst.InstructionDecoder;

import net.openrs.cache.Cache;

/**
 * This class is the context for CS2Decompiler, hold various methods and
 * variables such as cache
 * 
 * @author Walied-Yassen
 * @date Nov 6, 2015
 */
public class Context {

	/**
	 * The cache that will be used to get scripts from or any other data
	 */
	private Cache cache;

	/**
	 * This will disassemble the bytecode into {@link CS2Script} structure
	 */
	private CS2Disassembler disassembler;

	/**
	 * This will decode the buffer of the instruction into instruction
	 */
	private InstructionDecoder instructionDecoder;

	/**
	 * The output handler for the script
	 */
	private ScriptPrinter printer;

	/**
	 * Is this debug run?
	 */
	private boolean debug;

	/**
	 * The decompiler
	 */
	private CS2Decompiler decompiler;
	
	/**
	 * Allow the block to be edited after it's generated
	 */
	private boolean blockEditing;
	
	/**
	 * @return the disassembler
	 */
	public CS2Disassembler getDisassembler() {
		return disassembler;
	}

	/**
	 * @param disassembler
	 *            the disassembler to set
	 */
	public void setDisassembler(CS2Disassembler disassembler) {
		this.disassembler = disassembler;
	}

	/**
	 * @param cache
	 *            the cache to set
	 */
	public void setCache(Cache cache) {
		this.cache = cache;
	}

	/**
	 * @return the cache
	 */
	public Cache getCache() {
		return cache;
	}

	/**
	 * @return the instructionDecoder
	 */
	public InstructionDecoder getInstructionDecoder() {
		return instructionDecoder;
	}

	/**
	 * @param instructionDecoder
	 *            the instructionDecoder to set
	 */
	public void setInstructionDecoder(InstructionDecoder instructionDecoder) {
		this.instructionDecoder = instructionDecoder;
	}

	/**
	 * @return the printer
	 */
	public ScriptPrinter getPrinter() {
		return printer;
	}

	/**
	 * @param printer
	 *            the printer to set
	 */
	public void setPrinter(ScriptPrinter printer) {
		this.printer = printer;
	}

	/**
	 * @return the debug
	 */
	public boolean isDebug() {
		return debug;
	}

	/**
	 * @param debug the debug to set
	 */
	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	/**
	 * @return the decompiler
	 */
	public CS2Decompiler getDecompiler() {
		return decompiler;
	}

	/**
	 * @param decompiler the decompiler to set
	 */
	public void setDecompiler(CS2Decompiler decompiler) {
		this.decompiler = decompiler;
	}

	/**
	 * @return the allowBlockEditing
	 */
	public boolean isBlockEditing() {
		return blockEditing;
	}

	/**
	 * @param allowBlockEditing the allowBlockEditing to set
	 */
	public void setBlockEditing(boolean blockEditing) {
		this.blockEditing = blockEditing;
	}

	/**
	 * Building method use it to set the cache to the context
	 * 
	 * @param cache
	 *            the cache to set
	 * @return the current context
	 */
	public Context withCache(Cache cache) {
		this.cache = cache;
		return this;
	}

	/**
	 * Building method use it to set the cache to the context
	 * 
	 * @param disassembler
	 *            the disassembler to set
	 * @return the current context
	 */
	public Context withDisassembler(CS2Disassembler disassembler) {
		this.disassembler = disassembler;
		return this;
	}

	/**
	 * Building method use it to set the instruction decoder to the context
	 * 
	 * @param instructionDecoder
	 *            the instruction decoder to set
	 * @return the current context
	 */
	public Context withInstructionDecoder(InstructionDecoder instructionDecoder) {
		this.instructionDecoder = instructionDecoder;
		return this;
	}

	/**
	 * Building method use it to set the output printer to the context
	 * 
	 * @param printer
	 *            the output printer to set
	 * @return the current context
	 */
	public Context withPrinter(ScriptPrinter printer) {
		this.printer = printer;
		return this;
	}
	/**
	 * Building method use it to set the debug mode
	 * 
	 * @param debug
	 *            is debug run?
	 * @return the current context
	 */
	public Context withDebug(boolean debug) {
		this.debug = debug;
		return this;
	}

	/**
	 * Building method use it to set the decompiler
	 * 
	 * @param decompiler
	 *            the decompiler to set
	 * @return the current context
	 */
	public Context withDecompiler(CS2Decompiler decompiler) {
		this.decompiler = decompiler;
		return this;
	}
	/**
	 * Building method use it to set the block editing mode
	 * 
	 * @param blockEditing
	 *            allow the blocks to be edited after it's generated? (Recommended yes)
	 * @return the current context
	 */
	public Context withBlockEditing(boolean blockEditing) {
		this.blockEditing = blockEditing;
		return this;
	}

}
