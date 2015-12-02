package com.wycody.cs2d;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Function;

import com.wycody.cs2d.analyze.Analyzer;
import com.wycody.cs2d.analyze.AnalyzerManager;
import com.wycody.cs2d.analyze.impl.ConditionalElseDetect;
import com.wycody.cs2d.analyze.impl.ConditionalRelationDetect;
import com.wycody.cs2d.analyze.impl.DuplicationFix;
import com.wycody.cs2d.analyze.impl.ForLoopDetect;
import com.wycody.cs2d.analyze.impl.IncrDecrDetect;
import com.wycody.cs2d.analyze.impl.SwitchAnalyzer;
import com.wycody.cs2d.analyze.impl.WhileLoopDetect;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.name.ScriptNameMapper;

import net.openrs.cache.Container;
import net.openrs.io.WrappedByteBuffer;

/**
 * Decompilation is a form of reverse engineering, its aim to convert compiled
 * file into source file, for many reasons such as understanding how an script
 * works
 * 
 * @author Walied-Yassen
 * @date Nov 6, 2015
 */
public class CS2Decompiler {

	/**
	 * The list of the analyzers that should run after generator
	 */
	public static final ArrayList<Class<? extends Analyzer>> ANALYZER_GROUP = new ArrayList<Class<? extends Analyzer>>();

	static {
		ANALYZER_GROUP.add(WhileLoopDetect.class);
		ANALYZER_GROUP.add(ConditionalElseDetect.class);
		ANALYZER_GROUP.add(SwitchAnalyzer.class);
		ANALYZER_GROUP.add(IncrDecrDetect.class);
		ANALYZER_GROUP.add(DuplicationFix.class);
		ANALYZER_GROUP.add(ConditionalRelationDetect.class);
		ANALYZER_GROUP.add(ForLoopDetect.class);
		// ANALYZER_GROUP.add(InlineConditionalDetect.class); this works fine but tbh i dont prefer it :D

	}

	/**
	 * The context of the decompiler
	 */
	private Context context;

	private SortedMap<Integer, CS2Script> decompiledScripts;

	private ScriptNameMapper nameMapper;
	
	/**
	 * Construct a new {@link CS2Decompiler}
	 * 
	 * @param context
	 */
	public CS2Decompiler(Context context) {
		this.context = context;
		this.decompiledScripts = new TreeMap<Integer, CS2Script>();
		this.nameMapper = new ScriptNameMapper();
	}

	/**
	 * Take the script id and disassemble it then pass it to the decompiler
	 * 
	 * @param scriptId
	 *            the script id you want to decompile
	 * @return the decompiled script
	 */
	public CS2Script decompile(int scriptId) {
		if (decompiledScripts.containsKey(scriptId)) {
			return decompiledScripts.get(scriptId);
		}
		return decompile(disassemble(scriptId));
	}

	/**
	 * Take the script id and disassemble it then pass it to the decompiler
	 * 
	 * @param scriptId
	 *            the script id you want to decompile
	 * @param file
	 *            the file the script should be loaded from (raw)
	 * @return the decompiled script
	 */
	public CS2Script decompile(int scriptId, File file) {
		if (decompiledScripts.containsKey(scriptId)) {
			return decompiledScripts.get(scriptId);
		}
		return decompile(disassemble(scriptId, file));
	}

	/**
	 * Decompile the script with analyzing
	 * 
	 * @param script
	 *            the script to decompile
	 * @return the decompiled script
	 */
	private CS2Script decompile(CS2Script script) {
		nameMapper.check(script);
		script.getGenerator().start();
		if (!context.isBlockEditing()) {
			script.finalizeBlocks();
		}
		script.getBlocks().forEach((k, v) -> {
			v.process(context);
		});

		new AnalyzerManager(script, ANALYZER_GROUP).run();
		decompiledScripts.put(script.getId(), script);
		return script;
	}

	/**
	 * This method will disassemble the byte-code of script with id
	 * {@code scriptId} into {@link CS2Script} structure
	 * 
	 * @param scriptId
	 * @return the disassembled code, CS2Script
	 */
	public CS2Script disassemble(int scriptId, WrappedByteBuffer data) {
		CS2Script script = context.getDisassembler().disassemble(context, data);
		script.setId(scriptId);
		script.setName("script_" + scriptId);
		return script;

	}

	public static Function<Integer, WrappedByteBuffer> scriptLoader = null;

	public CS2Script disassemble(int scriptId) {
		WrappedByteBuffer data = null;
		try {
			if (scriptLoader == null) {
				data = WrappedByteBuffer.wrap(Container.decode(context.getCache().getStore().read(12, scriptId)).getData());
			} else {
				data = scriptLoader.apply(scriptId);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return disassemble(scriptId, data);
	}

	/**
	 * This method will disassemble the byte-code of script with id
	 * {@code scriptId} into {@link CS2Script} structure
	 * 
	 * @param scriptId
	 * @param file
	 * @return the disassembled code, CS2Script
	 */
	public CS2Script disassemble(int scriptId, File file) {
		WrappedByteBuffer data = null;
		try {
			FileInputStream in = new FileInputStream(file);
			byte[] buffer = new byte[in.available()];
			in.read(buffer);
			data = WrappedByteBuffer.wrap(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return disassemble(scriptId, data);
	}

	/**
	 * @return the context
	 */
	public Context getContext() {
		return context;
	}

	/**
	 * @param context
	 *            the context to set
	 */
	public void setContext(Context context) {
		this.context = context;
	}

	/**
	 * @return the nameMapper
	 */
	public ScriptNameMapper getNameMapper() {
		return nameMapper;
	}

	/**
	 * @param nameMapper the nameMapper to set
	 */
	public void setNameMapper(ScriptNameMapper nameMapper) {
		this.nameMapper = nameMapper;
	}

}
