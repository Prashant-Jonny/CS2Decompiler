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
public class ScriptNameMapper {

	/**
	 * The registered maps
	 */
	private TreeMap<Integer, NameMap> maps;

	/**
	 * Construct a new {@link ScriptNameMapper} object.
	 */
	public ScriptNameMapper() {
		maps = new TreeMap<Integer, NameMap>();
		maps.put(1619, new NameMap("content.puzzle", "sliding_puzzle_set_slot").addType(StackType.INT, "objectId", "row", "column"));
		
		maps.put(5350, new NameMap("content.loyalty_store", "loy_refresh_tab").addType(StackType.INT, "currentTab", "containerWidget"));
		maps.put(5350, new NameMap("content.loyalty_store", "loy_refresh_tab").addType(StackType.INT, "currentTab", "containerWidget"));
		
		maps.put(5355, new NameMap("content.loyalty_store.dialog", "loy_show_dialog").addType(StackType.INT, "productStructId", "type"));
		maps.put(5356, new NameMap("content.loyalty_store.dialog", "loy_dialog_set_costume_color").addType(StackType.INT, "slot", "widget", "productStructId"));
		
		maps.put(5998, new NameMap("content.loyalty_store.sorting", "loy_sort_by_name").addType(StackType.INT, "unkownInt", "start", "end", "structId"));
		maps.put(5999, new NameMap("content.loyalty_store.sorting", "loy_sort_by_price").addType(StackType.INT, "unknownInt", "start", "end", "structId"));
		maps.put(4801, new NameMap("content.loyalty_store.sorting", "loy_set_sorting_type").addType(StackType.INT, "type"));
		

	}

	/**
	 * Load the maps from path
	 */
	public void load(String path) {
		@SuppressWarnings("unused")
		File file = new File(path);
		// TODO
	}

	/**
	 * Try map the script
	 * 
	 * @param script
	 *            the script to try on
	 */
	public void check(CS2Script script) {
		NameMap map = maps.get(script.getId());
		if (map == null) {
			return;
		}
		StackType[] types = map.getArgsType().getData();
		String[] names = map.getArgsName().getData();
		if (script.getTotalArgs() != types.length) {
			return;
		}
		if (script.matchParamTypes(types)) {
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
	public TreeMap<Integer, NameMap> getMaps() {
		return maps;
	}

	/**
	 * @param maps
	 *            the maps to set
	 */
	public void setMaps(TreeMap<Integer, NameMap> maps) {
		this.maps = maps;
	}

	public String getName(Object scriptId) {
		if (!maps.containsKey(scriptId)) {
			return "script_" + scriptId;
		}
		return maps.get(scriptId).getName();

	}
}
