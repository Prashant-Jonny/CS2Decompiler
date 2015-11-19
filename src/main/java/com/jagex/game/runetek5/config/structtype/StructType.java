package com.jagex.game.runetek5.config.structtype;

import java.util.HashMap;
import java.util.logging.Logger;

import com.jagex.game.runetek5.config.ConfigType;

import net.openrs.io.WrappedByteBuffer;


public class StructType extends ConfigType {
	
	private static final Logger logger = Logger.getLogger(StructType.class.getName());
	public HashMap<Integer, Object> config;
	
	public StructType() {
		config = new HashMap<Integer, Object>();
	}
	
	public String getParam(int key, String defaultStr) {
		if (config == null) {
			return defaultStr;
		}
		Object value = config.get((long) key);
		if (value == null) {
			return defaultStr;
		}
		return (String) value;
	}
	
	public int getParam(int key, int defaultInt) {
		if (config == null) {
			return defaultInt;
		}
		Object value = config.get((long) key);
		if (value == null) {
			return defaultInt;
		}
		return (int) value;
	}
	
	public void decode(WrappedByteBuffer buffer, int opcode) {
		 if (opcode == 249) {
				int length = buffer.getUnsignedByte();
				for (int index = 0; index < length; index++) {
					boolean stringInstance = (buffer.getUnsignedByte()) == 1;
					int key = buffer.getMedium();
					Object val;
					if (stringInstance) {
						val = buffer.getString();
					} else {
						val = buffer.getInt();
					}
					config.put(key, val);
				}
			} else
			logger.info("Error unrecognised .struct config code: {" + opcode + "}");
	}

	@Override
	public void postDecode() {
		// TODO Auto-generated method stub
		
	}
}
