package com.wycody.cs2d.rev;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wycody.cs2d.script.CS2Disassembler;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionDecoder;
import java.util.function.Supplier;

/**
 * Handles the revision control methods
 * 
 * @author Walied-Yassen
 * @date Nov 7, 2015
 */
public abstract class Revision implements CS2Disassembler, InstructionDecoder {

	/**
	 * The registered instructions by the revision (They get scrambled each
	 * revision)
	 */
	protected Map<Integer, Supplier<? extends Instruction>> registeredInstructions;

	/**
	 * The 32 bit operand instructions
	 */
	protected List<Integer> largeOpcodes;

	/**
	 * Construct a new {@code Revision} class
	 */
	public Revision() {
		registeredInstructions = new HashMap<Integer, Supplier<? extends Instruction>>();
		largeOpcodes = new ArrayList<Integer>();
		registerInstructions();
		registerLarges();
	}

	/**
	 * Register the instructions for this reivison
	 */
	public abstract void registerInstructions();

	/**
	 * Register the large instruction for this revision
	 */
	public abstract void registerLarges();

	/**
	 * @return the registeredInstructions
	 */
	public Map<Integer, Supplier<? extends Instruction>> getRegisteredInstructions() {
		return registeredInstructions;
	}

	/**
	 * @param registeredInstructions
	 *            the registeredInstructions to set
	 */
	public void setRegisteredInstructions(Map<Integer, Supplier<? extends Instruction>> registeredInstructions) {
		this.registeredInstructions = registeredInstructions;
	}

	/**
	 * Register the {@code id} at the instruction map so we can decode it later
	 * 
	 * @param id
	 *            the id of the instruction
	 * @param instruction
	 *            the instruction supplier/factory.
	 */
	public void registerInstruction(int id, Supplier<? extends Instruction> instruction) {
		if (registeredInstructions.containsKey(id)) {
//			throw new Error("You cannot register one id to multiple instructions");
		}
		registeredInstructions.put(id, instruction);
	}

	/**
	 * Flag the instruction as 32 bit operand opcode
	 * 
	 * @param id
	 *            the id of the instruction
	 */
	public void registerLarge(int id) {
		largeOpcodes.add(id);
	}

	
	/**
	 * Return the instruction is 32 bit integer operand
	 * @param id the id of the instruction
	 * @return 
	 */
	public boolean isLarge(int id) {
		return largeOpcodes.contains(id);
	}
    
    /**
     * Returns if the opcode is known.
     * @param opcode
     * @return
     */
    public boolean isKnowOpcode(int opcode) {
        return this.registeredInstructions.containsKey(opcode);
    }
}
