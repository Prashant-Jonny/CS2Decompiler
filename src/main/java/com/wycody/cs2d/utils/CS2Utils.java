package com.wycody.cs2d.utils;

import java.util.HashMap;
import java.util.Stack;

import org.apache.commons.collections4.list.TreeList;

import com.wycody.cs2d.script.CS2Field;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.flow.impl.BasicBlock;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.base.branch.JumpInstruction;
import com.wycody.cs2d.script.inst.walker.InstructionWalker;
import com.wycody.cs2d.script.inst.walker.WalkState;
import com.wycody.cs2d.script.inst.walker.WalkerAction;
import com.wycody.utils.ArrayUtils;

/**
 * 
 * @author Walied-Yassen
 * @date Nov 13, 2015
 */
public class CS2Utils {


	/**
	 * Get formatted boolean value based on integer
	 * 
	 * @param value
	 *            the uid field
	 * @return the formatted boolean
	 */
	public static String getBoolean(Object value) {
		value = fixIntegerField(value);// incase it was integer
		String result = "";
		if (value instanceof Number) {
			long val = ((Number) value).longValue();
			if (val == 0) {
				result = "false";
			} else if (val == 1) {
				result = "true";
			} else {
				result += val;
			}
		} else {
			result += value;
		}
		return result;
	}

	/**
	 * Try parse the color format of value
	 * 
	 * @param value
	 *            the value
	 * @return the formatted color
	 */
	public static String getColor(Object value) {
		value = fixIntegerField(value);
		String s = "";
		if (value instanceof Number) {
			long val = ((Number) value).longValue();
			int a = (int) ((val >> 24) & 0xFF), r = (int) ((val >> 16) & 0xFF), g = (int) ((val >> 8) & 0xFF), b = (int) ((val) & 0xFF);
			if (a != 0) {
				s += "rgba(" + a + "," + r + "," + g + "," + b + ")";
			} else {
				s += "rgb(" + r + "," + g + "," + b + ")";
			}
			return s;
		} else {
			return "rgba(" + value + ")";
		}
	}

	/**
	 * Try and fix the integer field if it was broken in the push andpop
	 * 
	 * @param val
	 *            the pop to check
	 * @return the fixed value
	 */
	public static Object fixIntegerField(Object val) {
		if (val instanceof Integer) {
			switch ((Integer) val) {
			case -2147483647:
				return "getMouseX()";
			case -2147483646:
				return "getMouseY()";
			case -2147483644:
				return "getCallerOption()";
			case -2147483643:
				return "getCallerSlot()";
			case -2147483641:
				return "getTargetSlot()";
			case -2147483640:
				return "getKeyCode()";
			case -2147483639:
				return "getKeyChar()";
            case -2147483645:
				return "caller";
			case -2147483642:
				return "target";
			}
			return val;
		} else if (val instanceof CS2Field) {

			return ((CS2Field) val).getName();
		}
		return val;
	}

	public static Object fixStringField(Object val) {
		if (val instanceof CS2Field) {
			return ((CS2Field) val).getName();
		}
		return val;
	}

	/**
	 * Get the call parameters of an script
	 * 
	 * @param target
	 *            the script to get for
	 * @param integerStack
	 *            the integer stack to pop from
	 * @param objectStack
	 *            the object stack to pop from
	 * @param longStack
	 *            the long stack to pop form
	 * @return the parameters as {@link Object} array
	 */
	public static Object[] getParameters(CS2Script target, CS2Script script) {
		int totalInts = target.getIntegerParameters() == null ? 0 : target.getIntegerParameters().length;
		int totalStrings = target.getObjectParameters() == null ? 0 : target.getObjectParameters().length;
		int totalLongs = target.getLongParameters() == null ? 0 : target.getLongParameters().length;
		Object[] ints = new Object[totalInts];
		Object[] objs = new Object[totalStrings];
		Object[] longs = new Object[totalLongs];
		
		for (int index = 0; index < totalInts; index++) {
			ints[index] = script.popInteger(0);
		}
		for (int index = 0; index < totalStrings; index++) {
			objs[index] = script.popObject(0);
		}
		for (int index = 0; index < totalLongs; index++) {
			longs[index] = script.popLong(0);
		}
		ArrayUtils.flip(ints);
		ArrayUtils.flip(objs);
		ArrayUtils.flip(longs);
		return ArrayUtils.merge(ints, objs, longs);
	}

	public static Object fixStringPOP(Object pop) {
		if (pop instanceof CS2Field) {
			return fixStringField(pop);
		}
		return "\"" + pop + "\"";

	}

	public static JumpInstruction[] findFirstMatchJump(BasicBlock... blocks) {

		if (blocks.length == 0) {
			return null;
		}
		// Create the storage
		HashMap<BasicBlock, TreeList<JumpInstruction>> blocksInstrs = new HashMap<BasicBlock, TreeList<JumpInstruction>>();

		// Populate the storage
		for (BasicBlock block : blocks) {
			TreeList<JumpInstruction> instrs = new TreeList<JumpInstruction>();
			InstructionWalker walker = new InstructionWalker(block, InstructionWalker.RESOLVE_JUMPS | InstructionWalker.RESOLVE_SWITCH_BLOCKS  | InstructionWalker.RESOLVE_TARGET_BLOCKS | InstructionWalker.RESOLVE_FALSE_BLOCKS, new WalkerAction() {

				@Override
				public WalkState visitInstr(int depth, Instruction instruction) {

					if (instruction instanceof JumpInstruction) {
						instrs.add((JumpInstruction) instruction);
					}

					return WalkState.CONTINUE;
				}
			});
			walker.startWalking();
			blocksInstrs.put(block, instrs);
		}

		// Start finding the instructions
		// At first we start with the first block and check for match jumps in
		// the others
		TreeList<JumpInstruction> findList = blocksInstrs.get(blocks[0]);
		JumpInstruction[] founds = new JumpInstruction[blocks.length];
		boolean found = false;
		find_instr_loop: for (JumpInstruction findInstr : findList) {

			founds[0] = findInstr;
			// We loop through the other blocks so we maybe find match
			// instruction
			found = true;
			other_block_loop: for (int index = 1; index < blocksInstrs.size(); index++) {
				TreeList<JumpInstruction> list = blocksInstrs.get(blocks[index]);
				for (JumpInstruction instr : list) {
					if (index == 16 && blocks[index].getAddress() > 300) {
						System.out.println(instr.getGotoString() + ", " + instr.getJumpTarget() + ", " + findInstr.getJumpTarget());
					}
					if (instr.getJumpTarget() == findInstr.getJumpTarget()) {
						founds[index] = instr;
						continue other_block_loop;
					}

				}
				found = false;
			}
			if (found) {
				break find_instr_loop;
			}

		}

		if (found) {
			for (int index = 0; index < founds.length; index++) {
				if (founds[index] == null) {
					throw new Error("The found jump is null! How that could happen? (" + index + "/" + (founds.length-1) + ")");
				}
			}
			return founds;
		} else {

			return null;
		}

	}

	public static String fixCondition(Object right, String operator, Object left) {
		String s = "";
		if (right.toString().contains("is") && operator.equals("==") && left instanceof Number) {
			if (((Number) left).longValue() == 0) {
				s += "!";
			}
			s += right;
		} else {
			s = right + " " + operator + " " + left;

		}
		return s;
	}

	/**
	 * Return the format of widget instructions
	 * 
	 * @param uid
	 *            the uid of the widget
	 * @return the formatted text
	 */
	public static String getWidget(Object uid) {
		return getWidget(null, uid);
	}

	public static String getWidget(CS2Script script, Object uid) {
		uid = fixIntegerField(uid);
		String s = "getWidget(";
		if (uid instanceof Integer) {
			int i = (Integer) uid;
			if (i == -1) {
				s += "-1";
			} else if (i == -2147483645) {
				s += "caller";
			} else if (i == -2147483642) {
				s += "target";
			} else {
				s += (i >> 16) + ", " + (i & 0xffff);
			}
		} else {
			s += uid;
		}
		s += ")";
		return s;
	}

	//FIXME: HSL colors?
	public static String getSkillDataType(Object value) {
		if (value instanceof Number) {
			int check = ((Number) value).intValue();
			if(check==0)
				return "EXPERIENCE";
			else if(check==1)
				return "LEVEL";
			else
				return "UNKNOWN_SKILL_TYPE_" + value;
		}
		return value != null ? value.toString() : "null";
	}

	public static Object getSkill(Object o) {
		if(o instanceof Number){
			int id = ((Number) o).intValue();
			if(id >= 0 && id < skills.length)
				return skills[id];
			return "UNKNOWN_SKILL("+o+")";
		}
		return o;
	}



	private final static String[] skills = new String[]{
		"ATTACK","STRENGTH","RANGED","MAGIC","DEFENCE",
		"CONSTITUTION","PRAYER","AGILITY","HERBLORE","THIEVING",
		"CRAFTING","RUNECRAFTING","MINING","SMITHING","FISHING",
		"COOKING","FIREMAKING","WOODCUTTING","FLETCHING","SLAYER",
		"FARMING","CONSTRUCTION","HUNTER","SUMMONING","DUNGEONEERING",
		"DIVINATION","INVENTION","UNKNOWN_1","UNKNOWN_2","UNKNOWN_3",
	};
}
