package com.wycody.cs2d.script.inst.nodes.push;

import com.jagex.game.runetek5.config.vartype.VarType;

/**
 * 
 * @author Walied-Yassen
 * @date Dec 6, 2015
 */
public class VarNode {

	private int varId;
	private VarType type;

	public VarNode(int varId, VarType type) {
		this.varId = varId;
		this.type = type;
	}

	public int getVarId() {
		return varId;
	}

	public void setVarId(int varId) {
		this.varId = varId;
	}

	public VarType getType() {
		return type;
	}

	public void setType(VarType type) {
		this.type = type;
	}

}
