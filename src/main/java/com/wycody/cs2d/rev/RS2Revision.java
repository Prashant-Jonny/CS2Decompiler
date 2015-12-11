package com.wycody.cs2d.rev;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.script.CS2Assembler;
import com.wycody.cs2d.script.CS2Field;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionDecoder;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.swtch.CaseNode;
import com.wycody.cs2d.script.inst.swtch.SwitchBlock;
import com.wycody.io.Buffer;
import com.wycody.utils.DynamicArray;

import net.openrs.io.WrappedByteBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles the revision control methods
 * 
 * @author Walied-Yassen
 * @date Nov 7, 2015
 */
public abstract class RS2Revision implements CS2Assembler, InstructionDecoder {

	private static final Logger logger = LoggerFactory.getLogger(RS2Revision.class.getName());

	/**
	 * The registered instructions by the revision (They get scrambled each
	 * revision)
	 */
	protected Map<Integer, Supplier<? extends Instruction>> registeredInstructions;
	protected Map<Integer, Instruction> emptyInstructions;

	/**
	 * The 32 bit operand instructions
	 */
	protected List<Integer> largeOpcodes;

	/**
	 * Construct a new {@code Revision} class
	 */
	public RS2Revision() {
		registeredInstructions = new HashMap<Integer, Supplier<? extends Instruction>>();
		emptyInstructions = new HashMap<>();
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
	 * @param supplier
	 *            the instruction supplier/factory.
	 */
	public void registerInstruction(int id, Supplier<? extends Instruction> supplier) {
		if (registeredInstructions.containsKey(id)) {
            logger.debug("WARNING: "+id+" was registered multiple times.");
			// throw new Error("You cannot register one id to multiple
			// instructions");
		}
		registeredInstructions.put(id, supplier);
		Instruction instr = supplier.get();
		instr.setId(id);
		emptyInstructions.put(id, instr);
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
	 * 
	 * @param id
	 *            the id of the instruction
	 * @return
	 */
	public boolean isLarge(int id) {
		return largeOpcodes.contains(id);
	}

	/**
	 * Returns if the opcode is known.
	 * 
	 * @param opcode
	 * @return
	 */
	public boolean isKnowOpcode(int opcode) {
		return this.registeredInstructions.containsKey(opcode);
	}

	public int forType(InstructionType type) {
		for (Instruction instr : emptyInstructions.values()) {
			if (instr.getType() == type) {
				return instr.getId();
			}

		}
		return -1;
	}

	@Override
	public CS2Script disassemble(Context context, WrappedByteBuffer buffer) {

		// Construct an empty CS2Script
		CS2Script script = new CS2Script();

		// Read the header offset
		buffer.setPosition(buffer.length() - 2);
		int headerLength = buffer.getUnsignedShort();
		int headerOffset = buffer.length() - 18 - headerLength;
		buffer.setPosition(headerOffset);
		buffer.getInt();// Instruction count (not used)

		// Read the fields lengths and assign them to the script

		script.setIntegerFields(new CS2Field[buffer.getUnsignedShort()]);
		script.setObjectFields(new CS2Field[buffer.getUnsignedShort()]);
		script.setLongFields(new CS2Field[buffer.getUnsignedShort()]);

		// Read the parameters lengths and assign them to the script
		script.setIntegerParameters(new CS2Field[buffer.getUnsignedShort()]);
		script.setObjectParameters(new CS2Field[buffer.getUnsignedShort()]);
		script.setLongParameters(new CS2Field[buffer.getUnsignedShort()]);

		// Read the switch blocks and assign them to the script
		SwitchBlock[] blocks = new SwitchBlock[buffer.getUnsignedByte()];
		for (int blockIndex = 0; blockIndex < blocks.length; blockIndex++) {
			int casesCount = buffer.getUnsignedShort();
			SwitchBlock block = blocks[blockIndex] = new SwitchBlock(casesCount);
			// Loop through cases and store them
			CaseNode previousCase = null;

			for (int caseIndex = 0; caseIndex < casesCount; caseIndex++) {
				CaseNode currentCase = new CaseNode(buffer.getInt(), buffer.getInt());
				if (previousCase != null) {
					previousCase.setNext(currentCase);
				}
				currentCase.setPrevious(previousCase);
				block.addCase(currentCase);
			}

		}
		script.setSwitchBlocks(blocks);
		buffer.setPosition(0);

		buffer.getNullString();
		// Read the instructions and assign them to the script
		int instructionsEnd = buffer.length() - 18 - headerLength;
		int instrAddress = 0;
		ArrayList<Instruction> tempInstructions = new ArrayList<>();
		while (buffer.getPosition() < instructionsEnd) {
			tempInstructions.add(context.getInstructionDecoder().decode(script, context, buffer, buffer.getUnsignedShort(), instrAddress));
			instrAddress++;
		}
		script.setInstructions(tempInstructions.toArray(new Instruction[tempInstructions.size()]));
		script.initializeFields();
		return script;
	}

	@Override
	public byte[] assemble(Context context, CS2Script script) {
		Buffer buffer = new Buffer(512, 0);
		buffer.writeNullableString(null);
		for (int address = 0; address < script.getInstructions().length; address++) {
			Instruction instruction = script.getInstruction(address);
			buffer.writeShort(instruction.getId());
			if (instruction.getType() == InstructionType.PUSH_OBJ) {
				buffer.writeString(instruction.getObjectOperand().toString());
			} else if (instruction.getType() == InstructionType.PUSH_OBJ) {
				buffer.writeLong(instruction.getLongOperand());
			} else {
				if (isLarge(instruction.getId())) {
					buffer.writeInt(instruction.getIntegerOperand());
				} else {
					buffer.writeByte(instruction.getIntegerOperand());
				}
			}
		}

		buffer.writeInt(script.getInstructions().length);
		buffer.writeShort(script.getIntegerFields().length);
		buffer.writeShort(script.getObjectFields().length);
		buffer.writeShort(script.getLongFields().length);
		buffer.writeShort(script.getIntegerParameters().length);
		buffer.writeShort(script.getIntegerParameters().length);
		buffer.writeShort(script.getIntegerParameters().length);
		buffer.writeByte(script.getSwitchBlocks().length);
		int stStart = buffer.getOffset();
		for (int blockIndex = 0; blockIndex < script.getSwitchBlocks().length; blockIndex++) {
			SwitchBlock block = script.getSwitchBlocks()[blockIndex];
			DynamicArray<CaseNode> cases = block.getAllCases();
			buffer.writeShort(cases.size());

			for (int caseIndex = 0; caseIndex < cases.size(); caseIndex++) {
				CaseNode node = cases.get(caseIndex);
				buffer.writeInt(node.getExpression());
				buffer.writeInt(node.getJumpTarget());
			}

		}

		buffer.writeShort(buffer.getOffset() - stStart + 1);
		return buffer.trimAndGet();
	}

	@Override
	public Instruction decode(CS2Script script, Context context, WrappedByteBuffer buffer, int id, int address) {
		int instKey = registeredInstructions.containsKey(id) ? id : -1;
		Instruction instr = registeredInstructions.get(instKey).get();
		instr.setBaseData(id, address);

		if (instr.getType() == InstructionType.PUSH_OBJ) {
			instr.setObjectOperand(buffer.getString().intern());
		} else if (instr.getType() == InstructionType.PUSH_LONG) {
			instr.setLongOperand(buffer.getLong());
		} else {
			instr.setIntegerOperand(isLarge(id) ? buffer.getInt() : buffer.getUnsignedByte());
		}
		instr.setScript(script);
		return instr;
	}
}
