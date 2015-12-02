package com.wycody.cs2d.script.name;

import java.util.HashMap;

import com.wycody.cs2d.script.CS2Field;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.inst.base.StoreInstruction;
import com.wycody.utils.StringUtils;

/**
 * @author Walied-Yassen
 * @date Nov 26, 2015
 */
public class FieldNameMapper {

	private HashMap<String, Integer> counts;

	private HashMap<String, String> methodBasedTemplates;
	private HashMap<String, String> methodNameMatch;

	public FieldNameMapper() {
		counts = new HashMap<String, Integer>();
		methodBasedTemplates = new HashMap<String, String>();
		methodNameMatch = new HashMap<String, String>();
		addTemplates();
	}

	private void addTemplates() {

		methodNameMatch.put("getSystemUpdateTime", "updateTime");
	}

	public void checkStore(StoreInstruction instruction) {
		Object var = instruction.getVariable();
		Object value = instruction.getValue();
		if (value == null || StringUtils.isNumeric(value.toString())) {
			return;
		}

		if (var instanceof CS2Field) {
			CS2Field<?> field = (CS2Field<?>) var;
			String[] parts = null;
			if (value.toString().contains(".")) {
				parts = value.toString().replace(".", "d.t").split("d.t");
			} else {
				parts = new String[] { value.toString() };
			}
			if (parts.length == 1) {
				checkMethodNameMatch(field, parts[0]);
			} else {
				for (String part : parts) {
					// checkSinglePart(instruction.getScript(), field, part);

				}
			}

			// for (int index = 0; index < parts.length; index++) {
			// String part = parts[index];
			// part = part.replace(";", "");
			//
			// part = part.substring(0, part.lastIndexOf('('));
			// parts[index] = part;
			// }
			// String name = "";
			// for (String part : parts) {
			// if (templates.containsKey(part)) {
			// String temp = templates.get(part);
			// if (name.length() < 1) {
			// temp = temp.toLowerCase();
			// }
			// name += temp;
			// }
			// }
			// if (name.trim().length() > 0) {
			// int count = getCount(name);
			// name += count == 0 ? "" : count + 2;
			// field.setName(name);
			// }
		}

	}

	private void checkMethodNameMatch(CS2Field<?> field, String name) {
		
		if(!name.contains("(") || !name.contains(")") || name.length() < 3) {
			return;
		}
		name = name.substring(0, name.indexOf('('));
		
		if (methodNameMatch.containsKey(name)) {
			String res = methodNameMatch.get(name);
		
			if (res.trim().length() > 0) {
				int count = getCount(res);
				res += count == 0 ? "" : count + 2;
				field.setName(res);
			}
	
		}
	}

	private void checkSinglePart(CS2Script script, CS2Field<?> field, String part) {
		// String name = part.substring(0, part.indexOf('('));
		// if(singlesTemplates.containsKey(name)) {
		// String template = singlesTemplates.get(name);
		// String[] args = part.substring(part.indexOf('(')+1,
		// part.lastIndexOf(')')).split(", ");
		// String[] argsName = template.split(", ");
		// for(int i = 0; i < args.length; i++) {
		// String arg = args[i];
		// String argName = argsName[i];
		// if(StringUtils.isNumeric(arg)) {
		// continue;
		// }
		// CS2Field targetField = script.getFieldByName(arg);
		// if(targetField != null) {
		// // targetField.setName(argName);
		// }
		// }
		//
		// }
	}

	private int getCount(String name) {
		int val = 0;
		if (counts.containsKey(name)) {
			val = counts.get(name);
		}
		counts.put(name, val + 1);
		return val;
	}

	/**
	 * @return the counts
	 */
	public HashMap<String, Integer> getCounts() {
		return counts;
	}

	/**
	 * @param counts
	 *            the counts to set
	 */
	public void setCounts(HashMap<String, Integer> counts) {
		this.counts = counts;
	}

}
