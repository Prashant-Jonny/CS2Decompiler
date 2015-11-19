package com.jagex.game.runetek5.config.vartype.player;

import net.openrs.io.WrappedByteBuffer;

import com.jagex.core.constants.SerialEnum;
import com.jagex.game.runetek5.config.vartype.VarType;

public class VarPlayerType extends VarType {
	
	public int clientCode;
	
	public VarPlayerType() {
		super();
		clientCode = 0;
	}
	
	public VarPlayerType(int id) {
		super(id);
	}
	
	@Override
	protected final void decode_inner (WrappedByteBuffer buffer, int opcode) {
		VarPlayerTypeEncodingKey key = SerialEnum.forID(VarPlayerTypeEncodingKey.values(), opcode);
		if (key == VarPlayerTypeEncodingKey.CLIENTCODE) {
			clientCode = buffer.getUnsignedShort();
		}
	}

}
