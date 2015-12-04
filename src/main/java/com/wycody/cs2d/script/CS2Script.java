package com.wycody.cs2d.script;

import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

import com.google.common.collect.ImmutableMap;
import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.flow.impl.BasicBlock;
import com.wycody.cs2d.script.flow.impl.BasicBlockGenerator;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.base.BlockComment;
import com.wycody.cs2d.script.inst.swtch.SwitchBlock;
import com.wycody.cs2d.script.inst.types.ReturnType;
import com.wycody.cs2d.script.inst.types.StackType;
import com.wycody.cs2d.script.name.FieldNameMapper;
import com.wycody.cs2d.script.name.NameMap;
import com.wycody.utils.DynamicArray;

public class CS2Script {

	/**
	 * The id of the script
	 */
	private int id;

	/**
	 * The storage for every {@link BasicBlock}
	 */
	private Map<Integer, BasicBlock> blocks;

	/**
	 * The integer fields of this script
	 */
	private CS2Field<Integer>[] integerFields;

	/**
	 * The object fields of this script
	 */
	private CS2Field<Object>[] objectFields;

	/**
	 * The long fields of this script
	 */
	private CS2Field<Long>[] longFields;

	/**
	 * The integer parameters of this script
	 */
	private CS2Field<Integer>[] integerParameters;

	/**
	 * The object parameters of this script
	 */
	private CS2Field<Object>[] objectParameters;

	/**
	 * The long parameters of this script
	 */
	private CS2Field<Long>[] longParameters;

	/**
	 * Raw instructions, this should not used at all only at generator :)
	 */
	private Instruction[] instructions;

	/**
	 * The integer stack of this script
	 */
	private Stack<Object> integerStack = new Stack<>();

	/**
	 * The object stack of this script, could be string or vars
	 */
	private Stack<Object> objectStack = new Stack<>();

	/**
	 * The long stack of this script, barley used
	 */
	private Stack<Object> longStack = new Stack<>();

	/**
	 * The return type of the script
	 */
	private ReturnType type;

	/**
	 * The return types of the script null if void
	 */
	private StackType[] returnTypes;

	/**
	 * The switch tables
	 */
	private SwitchBlock[] switchBlocks;

	/**
	 * The name of the script default would be script_xxx
	 */
	private String name;

	/**
	 * The name mapper
	 */
	private FieldNameMapper fieldNameMapper;

	/**
	 * The header comment
	 */
	private BlockComment headerComment;

	/**
	 * The footer comment
	 */
	private BlockComment footerComment;

	/**
	 * The generator that's used in this script
	 */
	private BasicBlockGenerator generator;

	/**
	 * The script refactor map
	 */
	private NameMap nameMap;

	/**
	 * Construct a new {@link CS2Script}
	 */
	public CS2Script() {
		blocks = new TreeMap<>();
		type = ReturnType.VOID;
		fieldNameMapper = new FieldNameMapper();
		BlockComment comment = new BlockComment();
		comment.addLine("Thanks for using our decompiler");
		comment.addLine("Authors: Walied-Yassen, Fear, Sundays911, Pea2nut");
		setFooterComment(comment);
		generator = new BasicBlockGenerator(this);
	}

	/**
	 * @param startAddress
	 *            the first instruction address is defined as startAddress,
	 *            simply it's where the block is located at
	 * @return basic block at address {@code startAddress}
	 */
	public BasicBlock getBlockAt(int startAddress) {
		return getBlockAt(startAddress, true);
	}

	/**
	 * @param startAddress
	 *            the first instruction address is defined as startAddress,
	 *            simply it's where the block is located at
	 * @param create
	 *            Create new block fi not exists
	 * @return basic block at address {@code startAddress}
	 */
	public BasicBlock getBlockAt(int startAddress, boolean create) {
		BasicBlock block = blocks.get(startAddress);
		return block;
	}

	/**
	 * Remove a block from the script
	 * 
	 * @param target
	 *            the block to remove
	 */
	public void removeBlock(BasicBlock target) {
		blocks.remove(target.getAddress());
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the blocks
	 */
	public Map<Integer, BasicBlock> getBlocks() {
		return blocks;
	}

	/**
	 * @return the blocks
	 */
	public Map<Integer, BasicBlock> getBlocksAndFinalize() {
		if (!(blocks instanceof ImmutableMap)) {
			blocks = ImmutableMap.copyOf(blocks);
		}
		return blocks;
	}

	/**
	 * @param blocks
	 *            the blocks to set
	 */
	public void setBlocks(Map<Integer, BasicBlock> blocks) {
		this.blocks = blocks;
	}

	/**
	 * @return the integerFields
	 */
	public CS2Field<Integer>[] getIntegerFields() {
		return integerFields;
	}

	/**
	 * @param integerFields
	 *            the integerFields to set
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setIntegerFields(CS2Field[] integerFields) {
		this.integerFields = integerFields;
	}

	/**
	 * @return the objectFields
	 */
	public CS2Field<Object>[] getObjectFields() {
		return objectFields;
	}

	/**
	 * @param objectFields
	 *            the objectFields to set
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setObjectFields(CS2Field[] objectFields) {
		this.objectFields = objectFields;
	}

	/**
	 * @return the longFields
	 */

	public CS2Field<Long>[] getLongFields() {
		return longFields;
	}

	/**
	 * @param longFields
	 *            the longFields to set
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setLongFields(CS2Field[] longFields) {
		this.longFields = longFields;
	}

	/**
	 * @return the integerParameters
	 */
	public CS2Field<Integer>[] getIntegerParameters() {
		return integerParameters;
	}

	/**
	 * @param integerParameters
	 *            the integerParameters to set
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setIntegerParameters(CS2Field[] integerParameters) {
		this.integerParameters = integerParameters;
	}

	/**
	 * @return the objectParameters
	 */
	public CS2Field<Object>[] getObjectParameters() {
		return objectParameters;
	}

	/**
	 * @param objectParameters
	 *            the objectParameters to set
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setObjectParameters(CS2Field[] objectParameters) {
		this.objectParameters = objectParameters;
	}

	/**
	 * @return the longParameters
	 */
	public CS2Field<Long>[] getLongParameters() {
		return longParameters;
	}

	/**
	 * @param longParameters
	 *            the longParameters to set
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setLongParameters(CS2Field[] longParameters) {
		this.longParameters = longParameters;
	}

	/**
	 * @return the instructions
	 */
	public Instruction[] getInstructions() {
		return instructions;
	}

	/**
	 * @return the instructions
	 */
	public Instruction getInstruction(int address) {
		return instructions[address];
	}

	/**
	 * @param instructions
	 *            the instructions to set
	 */
	public void setInstructions(Instruction[] instructions) {
		this.instructions = instructions;
	}

	/**
	 * @return the integerStack
	 */
	public Stack<Object> getIntegerStack() {
		return integerStack;
	}

	/**
	 * Push an integer type object to the stack
	 * 
	 * @param value
	 *            the object to push
	 */
	public void pushInteger(Object value) {
		integerStack.push(value);
	}

	/**
	 * Pop integer type object from the stack
	 * 
	 * @return the object
	 */
	public Object popInteger(int address) {
		if (integerStack.size() == 0) {
			throw new Error("Empty stack at script: " + this.id + " at adress: " + address);
		}

		return integerStack.pop();
	}

	/**
	 * @param integerStack
	 *            the integerStack to set
	 */
	public void setIntegerStack(Stack<Object> integerStack) {
		this.integerStack = integerStack;
	}

	/**
	 * @return the objectStack
	 */
	public Stack<Object> getObjectStack() {
		return objectStack;
	}

	/**
	 * Push object type object to the stakc
	 * 
	 * @param object
	 *            the object to push
	 */
	public void pushObject(Object object) {
		objectStack.push(object);
	}

	/**
	 * Pop object type object from the stack
	 * 
	 * @return the object
	 */
	public Object popObject(int address) {
		if (objectStack.size() == 0) {
		
			throw new Error("Empty stack at script: " + this.id + " at adress: " + address);
		}
		Object o = objectStack.pop();
		// if(!(o instanceof CS2Field) && !o.toString().startsWith("\"") &&
		// !o.toString().endsWith("\"")) {
		// return "\"" + o.toString() + "\"";
		// }
		return o;
	}

	/**
	 * @param objectStack
	 *            the objectStack to set
	 */
	public void setObjectStack(Stack<Object> objectStack) {
		this.objectStack = objectStack;
	}

	/**
	 * @return the longStack
	 */
	public Stack<Object> getLongStack() {
		return longStack;
	}

	/**
	 * @param longStack
	 *            the longStack to set
	 */
	public void setLongStack(Stack<Object> longStack) {
		this.longStack = longStack;
	}

	/**
	 * Push long type object to the stack
	 * 
	 * @param value
	 *            the value to push
	 */
	public void pushLong(Object value) {
		longStack.push(value);
	}

	/**
	 * Pop a long type object from the stack
	 * 
	 * @return the object
	 */
	public Object popLong(int address) {
		if (longStack.size() == 0) {
			throw new Error("Empty stack at script: " + this.id + " at adress: " + address);
		}
		return longStack.pop();
	}

	/**
	 * Print the script in the syntax using {@link Context#getPrinter()}
	 * 
	 * HEADER INSTRUCTIONS FOOTER
	 */
	public void print(Context context) {
		ScriptPrinter printer = context.getPrinter();
		printer.initializeForScript(this);

		printHeader(context);
		printBody(context);
		printFooter(context);
		if (footerComment != null) {
			footerComment.print(context, printer);
		}
		printer.finalizeForScript(this);

	}

	/**
	 * Generate the header parameters, iarg etc.. iarg would be
	 * parameters[index] if found
	 * 
	 * @param parameters
	 *            the parameters to base of
	 * @return the string
	 */
	public String generateParameters(Object[] parameters) {
		StringBuilder builder = new StringBuilder();
		int count = 0;
		int total = integerParameters.length + objectParameters.length + longParameters.length;
		for (CS2Field<Integer> field : integerParameters) {
			count++;

			builder.append((parameters == null ? "int " + field.getName() : parameters[count - 1]) + (count > 0 && count < total ? ", " : ""));

		}
		for (CS2Field<Object> field : objectParameters) {
			count++;

			builder.append((parameters == null ? "String " + field.getName() : parameters[count - 1]) + (count > 0 && count < total ? ", " : ""));

		}
		for (CS2Field<Long> field : longParameters) {
			count++;

			builder.append((parameters == null ? "long " + field.getName() : parameters[count - 1]) + (count > 0 && count < total ? ", " : ""));

		}
		return builder.toString();
	}

	/**
	 * Print the header of the script using {@link Context#getPrinter()}
	 * 
	 * @param context
	 *            the context of the decompiler
	 */
	private void printHeader(Context context) {
		ScriptPrinter printer = context.getPrinter();
		printer.print(type.getType() + " " + name + "(");
		printer.print(generateParameters(null));
		printer.println(") {");
		printer.tab();
	}

	/**
	 * Print the body of the script using {@link Context#getPrinter()}
	 * 
	 * @param context
	 *            the context of the decompiler
	 */
	private void printBody(Context context) {
		ScriptPrinter printer = context.getPrinter();
		if (context.isDebug()) {
			BasicBlock block = null;
			while ((block = block == null ? getBlockAt(0) : (BasicBlock) block.getNext()) != null) {

				printer.println(block.getName());
				printer.tab();
				block.print(context, context.getPrinter());
				printer.newLine();
				printer.untab();
			}
		} else {
			getBlockAt(0, false).print(context, context.getPrinter());
		}
	}

	/**
	 * Print the footer of the script using {@link Context#getPrinter()}
	 * 
	 * @param context
	 *            the context of the decompiler
	 */
	private void printFooter(Context context) {
		ScriptPrinter printer = context.getPrinter();
		printer.untab();
		printer.println("}");
	}

	/**
	 * @return the type
	 */
	public ReturnType getType() {
		return type;
	}

	/**
	 * @return the returnTypes
	 */
	public StackType[] getReturnTypes() {
		return returnTypes;
	}

	/**
	 * @param returnTypes
	 *            the returnTypes to set
	 */
	public void setReturnTypes(StackType[] returnTypes) {
		this.returnTypes = returnTypes;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(ReturnType type) {
		this.type = type;
	}

	public void initializeFields() {
		int argAddr = 0;
		for (int fieldAddr = 0; fieldAddr < integerFields.length; fieldAddr++) {
			CS2Field<Integer> field = integerFields[fieldAddr] = new CS2Field<>(fieldAddr, (fieldAddr >= integerParameters.length ? "anInt" + fieldAddr : "arg" + argAddr++));
			if (fieldAddr < integerParameters.length) {
				integerParameters[fieldAddr] = integerFields[fieldAddr];
			}
		}
		for (int fieldAddr = 0; fieldAddr < objectFields.length; fieldAddr++) {
			CS2Field<Object> field = objectFields[fieldAddr] = new CS2Field<>(fieldAddr, (fieldAddr >= objectParameters.length ? "aString" + fieldAddr : "arg" + argAddr++));
			if (fieldAddr < objectParameters.length) {
				objectParameters[fieldAddr] = objectFields[fieldAddr];
			}
		}
		for (int fieldAddr = 0; fieldAddr < longFields.length; fieldAddr++) {
			CS2Field<Long> field = longFields[fieldAddr] = new CS2Field<>(fieldAddr, (fieldAddr >= longParameters.length ? "aLong" + fieldAddr : "arg" + argAddr++));
			if (fieldAddr < longParameters.length) {
				longParameters[fieldAddr] = longFields[fieldAddr];
			}
		}
	}

	/**
	 * Link the blocks to each other using previous, next, current formula
	 */
	public boolean linkBlocks() {
		BasicBlock prev = null;
		BasicBlock current = null;
		for (Integer key : blocks.keySet()) {
			prev = current;
			current = getBlocks().get(key);
			if (prev != null) {
				prev.setNext(current);
				current.setPrevious(prev);
			}

		}
		return true;
	}

	/**
	 * @return the switchBlocks
	 */
	public SwitchBlock[] getSwitchBlocks() {
		return switchBlocks;
	}

	/**
	 * @return the switchBlock
	 */
	public SwitchBlock getSwitchBlock(int id) {
		return switchBlocks[id];
	}

	/**
	 * @param switchBlocks
	 *            the switchBlocks to set
	 */
	public void setSwitchBlocks(SwitchBlock[] switchBlocks) {
		this.switchBlocks = switchBlocks;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		if (nameMap != null) {
			if (!name.equals(nameMap.getName())) {
				name = nameMap.getName();
			}
		}
		return name;
	}

	public String getFullName() {
		String name = getName();
		if (nameMap != null) {
			name = nameMap.getPackageName() + "." + name;
		}
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Makes the block not editable
	 */
	public void finalizeBlocks() {
		blocks = ImmutableMap.copyOf(blocks);
	}

	public Instruction[] getAllInstructions() {
		DynamicArray<Instruction> arr = new DynamicArray<>(Instruction.class);
		for (BasicBlock block : blocks.values()) {
			arr.addAll(block.getInstructions());
		}
		return arr.getData();
	}

	public BasicBlock getStartBlock() {
		return getBlockAt(0);
	}

	/**
	 * @return the mapper
	 */
	public FieldNameMapper getFieldNameMapper() {
		return fieldNameMapper;
	}

	/**
	 * @param mapper
	 *            the mapper to set
	 */
	public void setFieldNameMapper(FieldNameMapper mapper) {
		this.fieldNameMapper = mapper;
	}

	public CS2Field<?> getFieldByName(String arg) {
		for (CS2Field<Integer> field : integerFields) {
			if (field.getName().equalsIgnoreCase(arg)) {
				return field;
			}
		}
		for (CS2Field<Object> field : objectFields) {
			if (field.getName().equalsIgnoreCase(arg)) {
				return field;
			}
		}
		for (CS2Field<Long> field : longFields) {
			if (field.getName().equalsIgnoreCase(arg)) {
				return field;
			}
		}
		return null;
	}

	public void prepareProcess() {
		integerStack.clear();
		objectStack.clear();
		longStack.clear();
	}

	/**
	 * @return the headerComment
	 */
	public BlockComment getHeaderComment() {
		return headerComment;
	}

	/**
	 * @param headerComment
	 *            the headerComment to set
	 */
	public void setHeaderComment(BlockComment headerComment) {
		this.headerComment = headerComment;
	}

	/**
	 * @return the footerComment
	 */
	public BlockComment getFooterComment() {
		return footerComment;
	}

	/**
	 * @param footerComment
	 *            the footerComment to set
	 */
	public void setFooterComment(BlockComment footerComment) {
		this.footerComment = footerComment;
	}

	/**
	 * @return the generator
	 */
	public BasicBlockGenerator getGenerator() {
		return generator;
	}

	/**
	 * @param generator
	 *            the generator to set
	 */
	public void setGenerator(BasicBlockGenerator generator) {
		this.generator = generator;
	}

	/**
	 * The total parameters
	 * 
	 * @return the number
	 */
	public int getTotalArgs() {
		return integerParameters.length + objectParameters.length + longParameters.length;
	}

	public boolean matchParamTypes(StackType[] types) {
		if (types.length != getTotalArgs()) {
			return false;
		}
		int index = 0;
		for (CS2Field<Integer> field : integerParameters) {
			if (types[index++] != StackType.INT) {
				return false;
			}

		}
		for (CS2Field<Object> field : objectParameters) {
			if (types[index++] != StackType.OBJECT) {
				return false;
			}

		}
		for (CS2Field<Long> field : longParameters) {
			if (types[index++] != StackType.LONG) {
				return false;
			}

		}
		return true;
	}

	/**
	 * @return the nameMap
	 */
	public NameMap getNameMap() {
		return nameMap;
	}

	/**
	 * @param nameMap
	 *            the nameMap to set
	 */
	public void setNameMap(NameMap nameMap) {
		this.nameMap = nameMap;
	}

}
