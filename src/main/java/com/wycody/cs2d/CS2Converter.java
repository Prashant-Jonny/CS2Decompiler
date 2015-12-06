package com.wycody.cs2d;

import java.io.IOException;

import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;

import net.openrs.cache.Container;
import net.openrs.io.WrappedByteBuffer;

/**
 * 
 * @author Walied-Yassen
 * @date Dec 4, 2015
 */
public class CS2Converter {

	private Context from;
	private Context to;

	public CS2Converter(Context from, Context to) {
		this.from = from;
		this.to = to;
	}

	/**
	 * Currently only do is changin the id, but willing todo more advanced in
	 * future
	 * 
	 * @param scriptId
	 * @return
	 * @throws IOException
	 */
	public byte[] convert(int scriptId) throws IOException {

		CS2Script script = from.getDisassembler().disassemble(from, WrappedByteBuffer.wrap(Container.decode(from.getCache().getStore().read(12, scriptId)).getData()));
		CS2Script result = new CS2Script();

		result.setId(scriptId);

		Instruction[] resultInstructions = new Instruction[script.getInstructions().length];
		for (int address = 0; address < script.getInstructions().length; address++) {
			Instruction instruction = script.getInstruction(address);
			if (instruction.getType() == InstructionType.NULL) {
				throw new IllegalAccessError("Trying to convert an instruction that in not registered in the revision (Missing) [id=" + instruction.getId() + "]");
			}
			int newId = to.getRevision().forType(instruction.getType());
			if (newId == -1)
				throw new IllegalAccessError("Cannot convert the instruction, (Not defined in the other revision): [new_id:" + newId + ", old_id: " + instruction.getId() + "]");

			instruction.setId(newId);
			resultInstructions[address] = instruction;
		}
		result.setInstructions(resultInstructions);
		return to.getDisassembler().assemble(to, script);
	}

	public Context getFrom() {
		return from;
	}

	public Context getTo() {
		return to;
	}

}
