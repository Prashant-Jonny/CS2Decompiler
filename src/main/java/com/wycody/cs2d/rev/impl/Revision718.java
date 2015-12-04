package com.wycody.cs2d.rev.impl;

import java.util.ArrayList;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.rev.Revision;
import com.wycody.cs2d.script.CS2Field;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.impl.Branch;
import com.wycody.cs2d.script.inst.impl.Math;
import com.wycody.cs2d.script.inst.impl.Push;
import com.wycody.cs2d.script.inst.impl.Store;
import com.wycody.cs2d.script.inst.impl.Text;
import com.wycody.cs2d.script.inst.impl.Unsorted;
import com.wycody.cs2d.script.inst.impl.Var;
import com.wycody.cs2d.script.inst.impl.Widget;
import com.wycody.cs2d.script.inst.swtch.CaseNode;
import com.wycody.cs2d.script.inst.swtch.SwitchBlock;

import net.openrs.io.WrappedByteBuffer;

public class Revision718 extends Revision {

	@Override
	public void registerInstructions() {
		registerInstruction(-1, Unsorted.MISSING);
		registerInstruction(753, Unsorted.CALL_SCRIPT);
		registerInstruction(487, Unsorted.RETURN);
		registerInstruction(252, Unsorted.CONCAT_STRS);
		
		
		registerInstruction(878, Push.PUSH_INT);
		registerInstruction(898, Push.PUSH_OBJECT);
		registerInstruction(76, Push.PUSH_LONG);
		registerInstruction(264, Push.LOAD_INT);
		registerInstruction(698, Push.LOAD_OBJ);
		
		
		
		
		registerInstruction(879, Store.STORE_INT);
		registerInstruction(861, Store.STORE_OBJ);
		
		

		registerInstruction(356, Var.PUSH_VARP_BIT);
		registerInstruction(833, Var.PUSH_VARP);
		
		
		
		registerInstruction(409, Branch.INT_EQ);
		registerInstruction(644, Branch.INT_LT);
		registerInstruction(421, Branch.GOTO);

		
		
		registerInstruction(68, Math.DIVIDE);
		registerInstruction(257, Math.MODULO);

		registerInstruction(411, Text.INT_TO_STR);

		registerInstruction(210, Widget.BIND_MOUSE_HOVER_IN_HANDLER);
		registerInstruction(633, Widget.BIND_MOUSE_HOVER_OUT_HANDLER);

		
	}

	@Override
	public void registerLarges() {
		registerLarge(21);
		registerLarge(59);
		registerLarge(76);
		registerLarge(92);
		registerLarge(103);
		registerLarge(209);
		registerLarge(252);
		registerLarge(264);
		registerLarge(274);
		registerLarge(280);
		registerLarge(302);
		registerLarge(356);
		registerLarge(368);
		registerLarge(393);
		registerLarge(409);
		registerLarge(421);
		registerLarge(438);
		registerLarge(462);
		registerLarge(482);
		registerLarge(510);
		registerLarge(532);
		registerLarge(552);
		registerLarge(587);
		registerLarge(602);
		registerLarge(617);
		registerLarge(627);
		registerLarge(644);
		registerLarge(669);
		registerLarge(698);
		registerLarge(750);
		registerLarge(753);
		registerLarge(766);
		registerLarge(768);
		registerLarge(785);
		registerLarge(811);
		registerLarge(818);
		registerLarge(820);
		registerLarge(833);
		registerLarge(854);
		registerLarge(861);
		registerLarge(878);
		registerLarge(879);
		registerLarge(888);
		registerLarge(889);
		registerLarge(895);
		registerLarge(898);
		registerLarge(925);
		registerLarge(934);
		registerLarge(951);
		registerLarge(978);
		registerLarge(987);

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
