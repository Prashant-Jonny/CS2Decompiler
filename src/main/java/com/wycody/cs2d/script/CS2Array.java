package com.wycody.cs2d.script;

import com.jagex.game.runetek5.config.vartype.constants.ScriptVarType;

/**
 * 
 * @author Walied-Yassen
 * @date Dec 7, 2015
 */
public class CS2Array {

	private ScriptVarType type;
	private String name;

	public CS2Array(ScriptVarType type, String name) {
		this.type = type;
		this.name = name;
	}

	public ScriptVarType getType() {
		return type;
	}

	public String getName() {
		return name;
	}

}
