package com.wycody.cs2d.script.name;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

import org.apache.commons.collections4.list.TreeList;

import com.wycody.cs2d.script.CS2Field;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.inst.types.StackType;

/**
 * 
 * @author Walied-Yassen
 * @date Nov 28, 2015
 */
public abstract class ScriptNameMap {

	/**
	 * The registered maps
	 */
	protected TreeMap<Integer, TreeList<NameMap>> maps;

	/**
	 * Construct a new {@link ScriptNameMap} object.
	 */
	public ScriptNameMap() {
		maps = new TreeMap<Integer, TreeList<NameMap>>();

	}

	public abstract void registerMaps();

	public void registerMap(int id, NameMap... maps) {
		if (!this.maps.containsKey(id)) {
			this.maps.put(id, new TreeList<NameMap>());

		}
		TreeList<NameMap> list = this.maps.get(id);
		for (NameMap map : maps)
			list.add(map);

	}

	/**
	 * Load the maps from file path
	 * 
	 * @throws IOException
	 *             If any input/output errors occured will throw IOException
	 */
	public void load(String path) throws IOException {
		File file = new File(path);
		BufferedReader reader = new BufferedReader(new FileReader(file));

		String line = null;
		while ((line = reader.readLine()) != null) {
			if (line.startsWith("#"))
				continue;
			String[] mainSplit = line.split(" = ");
			if (mainSplit.length != 2) {
				reader.close();
				throw new RuntimeException("Expected one equal but inputed more than one at: " + line + " (" + path + ")");
			}
			String refactored = mainSplit[1];
			int id = 0;
			try {
				id = Integer.parseInt(mainSplit[0]);
			} catch (Exception e) {
				e.printStackTrace();
				reader.close();
				throw new RuntimeException("Could not parse id at " + line + " (" + path + ")");
			}
			String scriptName = refactored;
			StackType[] argsType = new StackType[0];
			String[] argsName = new String[0];
			boolean checkParams = true;
			if (refactored.contains("(") && refactored.contains(")")) {
				scriptName = refactored.substring(0, refactored.indexOf("("));
				String argsRaw = refactored.substring(refactored.indexOf("(") + 1, refactored.indexOf(")"));
				String[] splitedArgs = argsRaw.split(", ");
				if (splitedArgs.length == 1 && splitedArgs[0].equalsIgnoreCase(""))
					splitedArgs = new String[0];
				argsType = new StackType[splitedArgs.length];
				argsName = new String[splitedArgs.length];

				for (int i = 0; i < splitedArgs.length; i++) {
					String argRaw = splitedArgs[i];
					String[] splitedArg = argRaw.split(" ");
					if (splitedArg.length != 2) {
						reader.close();
						throw new RuntimeException("Expected parameter format as follow (type name) but inputed: " + argRaw + ", at: " + line + " (" + path + ")");

					}
					String argTypeName = splitedArg[0];
					String argName = splitedArg[1];
					StackType type = StackType.forType(argTypeName);
					if (type == null) {
						reader.close();
						throw new RuntimeException("Could not identify StackType for type: " + type + ", at: " + line + " (" + path + ")");
					}
					argsType[i] = type;
					argsName[i] = argName;
				}
			} else {
				checkParams = false;
			}

			if (scriptName != null) {
				String pkg = "";
				if (scriptName.contains(".")) {
					pkg = scriptName.substring(0, scriptName.lastIndexOf("."));

					scriptName = scriptName.substring(scriptName.lastIndexOf(".") + 1, scriptName.length());
				}
				NameMap map = new NameMap(pkg, scriptName);
				for (int i = 0; i < argsType.length; i++) {
					map.addType(argsType[i], argsName[i]);
				}

				map.setCheckParameters(checkParams);
				registerMap(id, map);
			}

		}

		reader.close();
	}

	public NameMap findMatch(CS2Script script) {
		TreeList<NameMap> maps = this.maps.get(script.getId());
		if (maps == null) {
			return null;
		}
		for (NameMap map : maps) {
			if (map.isCheckParameters()) {
				StackType[] types = map.getArgsType().getData();
				if (script.getTotalArgs() != types.length) {
					continue;
				}
				if (script.matchParamTypes(types)) {
					return map;
				}
			} else {
				return map;
			}

		}
		return null;
	}

	/**
	 * Try map the script
	 * 
	 * @param script
	 *            the script to try on
	 */
	public void check(CS2Script script) {
		NameMap map = findMatch(script);
		if (map != null) {
			String[] names = map.getArgsName().getData();
			int index = 0;
			for (CS2Field<Integer> field : script.getIntegerParameters()) {
				field.setName(names[index++]);
			}
			for (CS2Field<Object> field : script.getObjectParameters()) {
				field.setName(names[index++]);
			}
			for (CS2Field<Long> field : script.getLongParameters()) {
				field.setName(names[index++]);
			}
			script.setNameMap(map);
			script.setName(map.getName());
		}
	}

	/**
	 * @return the maps
	 */
	public TreeMap<Integer, TreeList<NameMap>> getMaps() {
		return maps;
	}

	/**
	 * @param maps
	 *            the maps to set
	 */
	public void setMaps(TreeMap<Integer, TreeList<NameMap>> maps) {
		this.maps = maps;
	}

}
