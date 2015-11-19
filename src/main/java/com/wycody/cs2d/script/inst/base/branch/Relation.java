package com.wycody.cs2d.script.inst.base.branch;

/**
 * An representer of the relation between two conditional instructions
 * @author Walied-Yassen
 * @date Nov 12, 2015
 */
//FIXME: THIS IS NOT A INSTRUCTION
public class Relation {

	/**
	 * The other instruction which has the relation with this
	 */
	private ConditionalInstruction target;

	/**
	 * The type of the relation between the two instructions
	 */
	private RelationType type;

	/**
	 * Construct a new {@link Relation}
	 * @param target
	 * @param type
	 */
	public Relation(ConditionalInstruction target, RelationType type) {
		this.target = target;
		this.type = type;
	}
	/**
	 * @return the target
	 */
	public ConditionalInstruction getTarget() {
		return target;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	public void setTarget(ConditionalInstruction target) {
		this.target = target;
	}

	/**
	 * @return the type
	 */
	public RelationType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(RelationType type) {
		this.type = type;
	}

}
