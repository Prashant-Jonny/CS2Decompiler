package com.wycody.cs2d.rev.impl;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.inst.impl.Unsorted;
import net.openrs.cache.Cache;

public class Revision861 extends Revision850 {


	public Revision861(Cache cache) {
		super(cache);
	}

	@Override
	public void registerInstructions() {
        registerInstruction(-1, Unsorted.MISSING);
	}

	@Override
	public void registerLarges() {
		//NOT redundant - DO NOT FUCKING REMOVE
        registerLarge(656);
        registerLarge(1075);
        registerLarge(378);
        registerLarge(636);
        registerLarge(57);
        registerLarge(758);
        registerLarge(17);
        registerLarge(72);
        registerLarge(14);
        registerLarge(1217);
        registerLarge(931);
        registerLarge(965);
        registerLarge(842);
        registerLarge(54);
        registerLarge(964);
        registerLarge(137);
        registerLarge(1134);
        registerLarge(764);
        registerLarge(865);
        registerLarge(66);
        registerLarge(295);
        registerLarge(792);
        registerLarge(56);
        registerLarge(67);
        registerLarge(159);
        registerLarge(519);
        registerLarge(590);
        registerLarge(710);
        registerLarge(557);
        registerLarge(909);
        registerLarge(634);
        registerLarge(534);
        registerLarge(716);
        registerLarge(1125);
        registerLarge(763);
        registerLarge(828);
        registerLarge(841);
        registerLarge(334);
	}

	@Override
	public byte[] assemble(Context context, CS2Script script) {
		// TODO Auto-generated method stub
		return null;
	}

}
