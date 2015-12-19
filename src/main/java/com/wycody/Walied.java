package com.wycody;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.jagex.game.runetek5.config.enumtype.EnumTypeList;
import com.jagex.game.runetek5.config.paramtype.ParamTypeList;
import com.jagex.game.runetek5.config.structtype.StructTypeList;
import com.thedoge.Update;
import com.thedoge.Update.Revision;
import com.wycody.cs2d.CS2Converter;
import com.wycody.cs2d.CS2Decompiler;
import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ConsolePrinter;
import com.wycody.cs2d.rev.RS2Revision;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.base.MissingInstruction;
import com.wycody.cs2d.script.name.RS2ScriptNameMap;
import com.wycody.cs2d.utils.ConfigParser;
import com.wycody.cs2d.utils.ScriptDumper;

import net.openrs.cache.Cache;
import net.openrs.cache.FileStore;

public class Walied {

	public static final int REVISION_ID = 742;
	public static final String CACHE_PATH = REVISION_ID == 718 ? "C:\\Users\\Cody\\Desktop\\718 cache\\" : "E:\\Misc\\Runescape Private Servers\\Releases\\RuneNova\\Source\\data\\cache\\";

	public static void main(String[] args) throws IOException {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.INFO);
		RS2Revision revision = createRevision(REVISION_ID);
		int scriptId = (1124);
		FileStore s = FileStore.open(new File(CACHE_PATH));
		Cache cache = new Cache(s);
		Main.paramTypeList = new ParamTypeList(cache);
		Main.enumTypeList = new EnumTypeList(cache);
		Main.structTypeList = new StructTypeList(cache);
		Context context = new Context();
		CS2Decompiler decompiler = new CS2Decompiler(context);
		RS2ScriptNameMap map = new RS2ScriptNameMap();
		map.load("data/walied/script_names.txt");
		context.withBlockEditing(true).withScriptNameMap(map).withDebug(false).withCache(cache).withDecompiler(decompiler).withDisassembler(revision).withInstructionDecoder(revision).withRevision(revision).withPrinter(new ConsolePrinter());

		Update.updateFile(Revision._742);
		ConfigParser config = new ConfigParser();
		config.loadRevision("" + REVISION_ID, revision);
		boolean dumpAll = false;
		boolean findMissing = false;
		int searchFor = -1;
//		findEnumUsage(context);
		if (findMissing) {
			try {
				for (Integer id : findMissingInstructions(context)) {
					System.out.println("Missing: " + id);
				}
			} catch (Throwable e) {

			}
		} else if (searchFor != -1) {
			ArrayList<Integer> found = searchForInstruction(context, searchFor);
			if (found.size() == 0) {
				System.out.println("Could not find any match for instruction id: " + searchFor);
			} else {
				System.out.println("Found " + found.size() + " match for instruction id: " + searchFor);

				found.forEach(System.out::println);
			}
		} else {
			if (dumpAll) {
				File outputDir = new File(System.getProperty("user.home") + "/Desktop/742Dump");
				for (scriptId = 0; scriptId < decompiler.getContext().getCache().getFileCount(12); scriptId++) {
					try {
						ScriptDumper.dump(context, scriptId, outputDir, "cs2");
					} catch (Throwable e) {

					}

				}
			} else {
				CS2Script script = decompiler.decompile(scriptId);// 793
				script.print(context);
			}
		}
		// test();
	}

	public static ArrayList<Integer> findEnumUsage(Context context) throws IOException {
		ArrayList<Integer> found = new ArrayList<Integer>();

//		for (int scriptId = 0; scriptId < context.getCache().getFileCount(12); scriptId++) {
//			try {
//				CS2Script script = context.getDecompiler().decompile(scriptId);
//				for (int instrIndex = 0; instrIndex < script.getInstructions().length; instrIndex++) {
//					Instruction instruction = script.getInstruction(instrIndex);
//					if(instruction instanceof PushEnumValueInstruction) {
//						Object id = ((PushEnumValueInstruction) instruction).enumId;
//						if(id instanceof Integer) {
//							if((int)id == 922) {
//								System.out.println(script.getId());
//							}
//						}
//
//					}
//				}
//			} catch(RuntimeException | Error e) {
//				
//			}
//
//		}
		return found;
	}
	public static ArrayList<Integer> findMissingInstructions(Context context) throws IOException {
		ArrayList<Integer> found = new ArrayList<Integer>();

		for (int scriptId = 0; scriptId < context.getCache().getFileCount(12); scriptId++) {
			CS2Script script = context.getDecompiler().disassemble(scriptId);
			for (int instrIndex = 0; instrIndex < script.getInstructions().length; instrIndex++) {
				Instruction instruction = script.getInstruction(instrIndex);
				if (instruction instanceof MissingInstruction) {
					if (!found.contains(instruction.getId()))
						found.add(instruction.getId());
				}
			}
		}
		return found;
	}

	public static ArrayList<Integer> searchForInstruction(Context context, int id) throws IOException {
		ArrayList<Integer> found = new ArrayList<Integer>();
		script_loop: for (int scriptId = 0; scriptId < context.getCache().getFileCount(12); scriptId++) {
			CS2Script script = context.getDecompiler().disassemble(scriptId);
			for (int instrIndex = 0; instrIndex < script.getInstructions().length; instrIndex++) {
				Instruction instruction = script.getInstruction(instrIndex);
				if (instruction.getId() == id) {
					found.add(scriptId);
					continue script_loop;
				}
			}
		}
		return found;
	}

	public static void test() throws IOException {
		RS2Revision rev = createRevision(742);
		Context context742 = new Context().withCache(new Cache(FileStore.open(new File("E:\\Misc\\Runescape Private Servers\\Releases\\RuneNova\\Source\\data\\cache\\")))).withBlockEditing(true).withDisassembler(rev).withInstructionDecoder(rev).withRevision(rev);
		rev = createRevision(718);
		Context context718 = new Context().withPrinter(new ConsolePrinter()).withCache(new Cache(FileStore.open(new File("C:\\Users\\Cody\\Desktop\\718 cache\\")))).withBlockEditing(true).withDisassembler(rev).withInstructionDecoder(rev).withRevision(rev);
		CS2Converter converter = new CS2Converter(context742, context718);
		byte[] data = converter.convert(6128);
		CS2Decompiler decompiler = new CS2Decompiler(context718);
		CS2Script script = decompiler.decompile(decompiler.disassemble(6128, data));
		script.print(context718);
	}

	public static RS2Revision createRevision(int revision) {
		try {
			return (RS2Revision) Class.forName("com.wycody.cs2d.rev.impl.Revision" + revision).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			System.err.println("Could not identify revision for id: " + revision);
			return null;
		}
	}
}
