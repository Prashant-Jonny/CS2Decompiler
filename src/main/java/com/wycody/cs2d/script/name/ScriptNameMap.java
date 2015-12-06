package com.wycody.cs2d.script.name;

import java.io.File;
import java.util.TreeMap;

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
	private TreeMap<Integer, NameMap[]> maps;

	/**
	 * Construct a new {@link ScriptNameMap} object.
	 */
	public ScriptNameMap() {
		maps = new TreeMap<Integer, NameMap[]>();

	}

	public abstract void registerMaps();

	public void registerMap(int id, NameMap... maps) {
		this.maps.put(id, maps);
	}

	/**
	 * Load the maps from path
	 */
	public void load(String path) {
		@SuppressWarnings("unused")
		File file = new File(path);
		// TODO
	}

	public NameMap findMatch(CS2Script script) {
		NameMap[] maps = this.maps.get(script.getId());
		if (maps == null) {
			return null;
		}
		for (NameMap map : maps) {
			StackType[] types = map.getArgsType().getData();
			if (script.getTotalArgs() != types.length) {
				continue;
			}
			if (script.matchParamTypes(types)) {
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
	public TreeMap<Integer, NameMap[]> getMaps() {
		return maps;
	}

	/**
	 * @param maps
	 *            the maps to set
	 */
	public void setMaps(TreeMap<Integer, NameMap[]> maps) {
		this.maps = maps;
	}



}
