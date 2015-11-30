package com.wycody.cs2d.utils;

import java.util.Stack;

import com.wycody.cs2d.script.CS2Field;
import com.wycody.cs2d.script.CS2Script;

/**
 * 
 * @author Walied-Yassen
 * @date Nov 13, 2015
 */
public class CS2Utils {

	/**
	 * Return the format of widget instructions
	 * 
	 * @param uid
	 *            the uid of the widget
	 * @return the formatted text
	 */
	public static String getWidget(Object uid) {
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

	/**
	 * Try parse the color format of value
	 * 
	 * @param value
	 *            the value
	 * @return the formatted color
	 */
	public static String getColor(Object value) {
		//FIXME: how about constants like BLACK,RED,YELLOW,BLUE...
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
			}
			return val;
		} else if (val instanceof CS2Field) {
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
	public static Object[] getParameters(CS2Script target, Stack<Object> integerStack, Stack<Object> objectStack, Stack<Object> longStack) {
		int totalInts = target.getIntegerParameters() == null ? 0 : target.getIntegerParameters().length;
		int totalStrings = target.getObjectParameters() == null ? 0 : target.getObjectParameters().length;
		int totalLongs = target.getLongParameters() == null ? 0 : target.getLongParameters().length;

		Object[] params = new Object[totalInts + totalStrings + totalLongs];
		int paramIndex = 0;
		for (int index = 0; index < totalInts; index++) {
			params[paramIndex++] = integerStack.pop();
		}
		for (int index = 0; index < totalStrings; index++) {
			params[paramIndex++] = objectStack.pop();
		}
		for (int index = 0; index < totalLongs; index++) {
			params[paramIndex++] = longStack.pop();
		}
		return params;
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
