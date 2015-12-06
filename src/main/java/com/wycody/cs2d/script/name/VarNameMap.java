package com.wycody.cs2d.script.name;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;

import com.wycody.cs2d.script.inst.base.StoreInstruction;

public abstract class VarNameMap {

	private String symbol;
	private String type;

	private TreeMap<Integer, String> names;

	public VarNameMap(String type, String symbol) {
		this.type = type;
		this.symbol = symbol;
		names = new TreeMap<>();
	}

	public abstract void registerNames();

	public void registerName(int id, String name) {
		names.put(id, name);
	}

	public void dumpMap(File file) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		for (Integer key : names.keySet()) {
			writer.write(symbol + names.get(key) + " = " + type + "[" + key + "];");
			writer.newLine();
		}
		writer.flush();
		writer.close();
	}

	public void checkStore(StoreInstruction instruction) {

	}
	public TreeMap<Integer, String> getNames() {
		return names;
	}

}
