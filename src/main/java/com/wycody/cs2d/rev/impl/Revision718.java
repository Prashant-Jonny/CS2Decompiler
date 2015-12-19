package com.wycody.cs2d.rev.impl;

import com.wycody.cs2d.rev.RS2Revision;
import com.wycody.cs2d.script.inst.impl.Branch;
import com.wycody.cs2d.script.inst.impl.Math;
import com.wycody.cs2d.script.inst.impl.Push;
import com.wycody.cs2d.script.inst.impl.Store;
import com.wycody.cs2d.script.inst.impl.Text;
import com.wycody.cs2d.script.inst.impl.Unsorted;
import com.wycody.cs2d.script.inst.impl.Var;
import com.wycody.cs2d.script.inst.impl.Widget;

public class Revision718 extends RS2Revision {

	@Override
	public void registerInstructions() {
		registerInstruction(-1, Unsorted.MISSING);
		registerInstruction(753, Unsorted.CALL_SCRIPT);
		registerInstruction(487, Unsorted.RETURN);
		registerInstruction(252, Unsorted.CONCAT_STRS);
		
		
		registerInstruction(878, Push.PUSH_INT);
		registerInstruction(898, Push.PUSH_OBJECT);
		registerInstruction(76, Push.PUSH_LONG);
		registerInstruction(264, Push.LOAD_INT);
		registerInstruction(698, Push.LOAD_OBJ);
		
		
		
		
		registerInstruction(879, Store.STORE_INT);
		registerInstruction(861, Store.STORE_OBJ);
		
		

		registerInstruction(356, Var.PUSH_VARP_BIT);
		registerInstruction(833, Var.PUSH_VARP);
		
		
		
		registerInstruction(409, Branch.INT_EQ);
		registerInstruction(644, Branch.INT_LT);
		registerInstruction(421, Branch.GOTO);

		
		
		registerInstruction(68, Math.DIVIDE);
		registerInstruction(257, Math.MODULO);

		registerInstruction(411, Text.INT_TO_STR);

		registerInstruction(210, Widget.BIND_MOUSEREPEAT_HANDLER);
		registerInstruction(633, Widget.BIND_MOUSELEAVE_HANDLER);
		registerInstruction(790, Widget.PUSH_HIDDEN);
		registerInstruction(600, Widget.SETHIDDEN);

		
	}

	@Override
	public void registerLarges() {
		registerLarge(21);
		registerLarge(59);
		registerLarge(76);
		registerLarge(92);
		registerLarge(103);
		registerLarge(209);
		registerLarge(252);
		registerLarge(264);
		registerLarge(274);
		registerLarge(280);
		registerLarge(302);
		registerLarge(356);
		registerLarge(368);
		registerLarge(393);
		registerLarge(409);
		registerLarge(421);
		registerLarge(438);
		registerLarge(462);
		registerLarge(482);
		registerLarge(510);
		registerLarge(532);
		registerLarge(552);
		registerLarge(587);
		registerLarge(602);
		registerLarge(617);
		registerLarge(627);
		registerLarge(644);
		registerLarge(669);
		registerLarge(698);
		registerLarge(750);
		registerLarge(753);
		registerLarge(766);
		registerLarge(768);
		registerLarge(785);
		registerLarge(811);
		registerLarge(818);
		registerLarge(820);
		registerLarge(833);
		registerLarge(854);
		registerLarge(861);
		registerLarge(878);
		registerLarge(879);
		registerLarge(888);
		registerLarge(889);
		registerLarge(895);
		registerLarge(898);
		registerLarge(925);
		registerLarge(934);
		registerLarge(951);
		registerLarge(978);
		registerLarge(987);

	}


}
