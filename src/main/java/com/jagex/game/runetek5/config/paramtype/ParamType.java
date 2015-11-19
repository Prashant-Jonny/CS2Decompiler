package com.jagex.game.runetek5.config.paramtype;

import java.util.logging.Logger;

import net.openrs.io.WrappedByteBuffer;

import com.jagex.core.constants.SerialEnum;
import com.jagex.core.stringtools.general.StringTools;
import com.jagex.game.runetek5.config.ConfigType;
import com.jagex.game.runetek5.config.vartype.constants.ScriptVarType;

public class ParamType extends ConfigType {
	private static final Logger logger = Logger.getLogger(ParamType.class.getName());
	public ScriptVarType scriptVarType;
	public String defaultStringValue;
	public int defaultIntValue;
	public char scriptVarTypeChar;
	public boolean autoDisable = true;

	protected void decode(WrappedByteBuffer buffer, int opcode) {
		if (1 == opcode) {
			scriptVarTypeChar = StringTools.getCharacterFromByte(buffer.getByte());
			scriptVarType = ScriptVarType.getByChar(scriptVarTypeChar);
		} else if (2 == opcode)
			defaultIntValue = buffer.getInt();
		else if (opcode == 4)
			autoDisable = false;
		else if (5 == opcode)
			defaultStringValue = buffer.getString();
		else if (opcode == 101) {
			scriptVarType = SerialEnum.forID(ScriptVarType.values(), buffer.getSmartShort());
			if (scriptVarType != null)
				scriptVarTypeChar = scriptVarType.getChar();
		} else {
				logger.info("Error unrecognised .param config code: {" + opcode + "}");
		}
	}

	public boolean isStringVarType() {
		return scriptVarType == ScriptVarType.STRING;
	}

	@Override
	public void postDecode() {
		// TODO Auto-generated method stub
		
	}
}
