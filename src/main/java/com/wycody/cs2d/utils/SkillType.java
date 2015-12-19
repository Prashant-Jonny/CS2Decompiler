package com.wycody.cs2d.utils;

/**
 * Used fro static vars
 * 
 * @author Walied-Yassen
 * @date Nov 30, 2015
 */
public enum SkillType {
	ATTACK(0),
	DEFENCE(1),
	STRENGTH(2),
	HITPOINTS(3),
	RANGE(4),
	PRAYER(5),
	MAGIC(6),
	COOKING(7),
	WOODCUTTING(8),
	FLETCHING(9),
	FISHING(10),
	FIREMAKING(11),
	CRAFTING(12),
	SMITHING(13),
	MINING(14),
	HERBLORE(15),
	AGILITY(16),
	THIEVING(17),
	SLAYER(18),
	FARMING(19),
	RUNECRAFTING(20),
	HUNTER(21),
	CONSTRUCTION(22),
	SUMMONING(23),
	DUNGEONEERING(24),
	DIVINATION(25);
	
	private int id;

	private SkillType(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public static Object forObj(Object o) {
		if(o instanceof Integer) {
			return forInt((int) o);
		}
		return o;
	}
	public static SkillType forInt(int id) {
		for (SkillType type : values()) {
			if (type.getId() == id) {
				return type;
			}
		}
		return null;
	}
}
