package com.jagex.game.runetek5.config.enumtype;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import net.openrs.io.WrappedByteBuffer;

import com.jagex.core.constants.SerialEnum;
import com.jagex.core.stringtools.general.StringTools;
import com.jagex.game.runetek5.config.ConfigType;
import com.jagex.game.runetek5.config.vartype.constants.ScriptVarType;


public class EnumType extends ConfigType {
	
	public void decode (WrappedByteBuffer buffer, int opcode) {
			if (opcode == 1) {
				keyType = ScriptVarType.getByChar(StringTools.getCharacterFromByte(buffer.getByte()));
			} else if (opcode == 2) {
				valueType = ScriptVarType.getByChar(StringTools.getCharacterFromByte(buffer.getByte()));
			} else if (3 == opcode) {
				defaultstr = buffer.getString();
			} else if (4 == opcode) {
				defaultint = buffer.getInt();
			} else if (5 == opcode || 6 == opcode) {
				size = buffer.getUnsignedShort();
				valueMap = new HashMap<Integer, Serializable>(size);
			    for (int i_19_ = 0; i_19_ < size; i_19_++) {
					int key = buffer.getInt();
					Serializable value;
					if (opcode == 5) {
					    value = buffer.getString();
					} else {
					    value = new Integer(buffer.getInt());
					}
					valueMap.put(new Integer(key), value);
			    }
			} else if (opcode == 7 || 8 == opcode) {
			    int max = buffer.getUnsignedShort();
			    size = buffer.getUnsignedShort();
			    valueArray = new Object[max];
			    for (int i_22_ = 0; i_22_ < size; i_22_++) {
			    	int key = buffer.getUnsignedShort();
					if (7 == opcode) {
						valueArray[key] = buffer.getString();
					} else {
						valueArray[key] = new Integer(buffer.getInt());
					}
			    }
			} else if (101 == opcode) {
			    keyType = SerialEnum.forID(ScriptVarType.values(), buffer.getSmartShort());
			} else if (102 == opcode) {
			    valueType = SerialEnum.forID(ScriptVarType.values(), buffer.getSmartShort());
			} else {
				logger.info("Error unrecognised .enum config code: {" + opcode
						+ "}");
			}
	}
	
	HashMap inverse;
	private static final Logger logger = Logger.getLogger(EnumType.class.getName());
	public Map<Integer, Serializable> valueMap;
    public ScriptVarType valueType;
    public String defaultstr = "null";
    public int defaultint;
    public ScriptVarType keyType;
    public Object[] valueArray;
    public int size = 0;

	public boolean containsValue(Object object) {
		if (0 == size) {
			return false;
		} else if (inverse == null) {
			createInverse();
		}

		return inverse.containsKey(object);
	}
	
	public int[] getKeys(Object object) {
		if (0 == size) {
			return null;
		} else if (null == inverse) {
			createInverse();
		}

		return (int[]) inverse.get(object);
	}
	
	void createInverse() {
		Map<Object, List<Integer>> inverse = new HashMap<Object, List<Integer>>();

		if (null != valueArray) {
			for (int index = 0; index < valueArray.length; index++) {
				if (valueArray[index] != null) {
					Object object = valueArray[index];
					List list = inverse.get(object);

					if (null == list) {
						list = new LinkedList();
						inverse.put(object, list);
					}

					list.add(index);
				}
			}
		} else if (null != valueMap) {
			Iterator<Map.Entry<Integer, Serializable>> iterator = valueMap.entrySet().iterator();

			while (iterator.hasNext()) {
				Map.Entry<Integer, Serializable> entry = iterator.next();
				Serializable object = entry.getValue();
				List<Integer> list = inverse.get(object);

				if (list == null) {
					list = new LinkedList();
					inverse.put(object, list);
				}

				list.add(entry.getKey());
			}
		} else {
			throw new IllegalStateException();
		}

		this.inverse = new HashMap();
		Iterator<Map.Entry<Object, List<Integer>>> inverseIterator = inverse.entrySet().iterator();

		while (inverseIterator.hasNext()) {
			Map.Entry<Object, List<Integer>> entry = inverseIterator.next();
			List<Integer> list = entry.getValue();
			int[] ints = new int[list.size()];
			int index = 0;
			Iterator<Integer> iterator = list.iterator();

			while (iterator.hasNext()) {
				Integer integer = iterator.next();
				ints[index++] = integer.intValue();
			}

			if (null == valueArray) {
				Arrays.sort(ints);
			}

			this.inverse.put(entry.getKey(), ints);
		}
	}
    
    public int getValueInt(int key) {
		Object value = getValue(key);
		if (value == null) {
		    return defaultint;
		}
		return ((Integer) value).intValue();
    }
    
    public String getValueString(int key) {
		Object value = getValue(key);
		if (null == value) {
		    return defaultstr;
		}
		return (String) value;
    }
    
    public Object getValue(int key) {
		if (null != valueArray) {
		    if (key < 0 || key >= valueArray.length) {
		    	return null;
		    }
		    return valueArray[key];
		}
		if (null != valueMap) {
		    return valueMap.get(new Integer(key));
		}
		return null;
    }
    
    public int getSize() {
    	return size;
    }

	@Override
	public void postDecode() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return Arrays.toString(valueArray);
	}
}
