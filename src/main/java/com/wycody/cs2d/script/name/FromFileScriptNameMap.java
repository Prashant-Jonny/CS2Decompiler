package com.wycody.cs2d.script.name;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.commons.collections4.list.TreeList;

public class FromFileScriptNameMap extends ScriptNameMap {

	public FromFileScriptNameMap(String path){
		maps = new TreeMap<Integer, TreeList<NameMap>>();
		try {
			load(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void registerMaps() {

	}

}
