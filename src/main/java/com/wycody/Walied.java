package com.wycody;

import java.io.File;
import java.io.IOException;

import com.jagex.game.runetek5.config.paramtype.ParamTypeList;
import com.wycody.cs2d.CS2Converter;
import com.wycody.cs2d.CS2Decompiler;
import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ConsolePrinter;
import com.wycody.cs2d.rev.Revision;
import com.wycody.cs2d.script.CS2Script;

import net.openrs.cache.Cache;
import net.openrs.cache.FileStore;

public class Walied {

	public static final int REVISION_ID = 718;
	public static final String CACHE_PATH = REVISION_ID == 718 ? "C:\\Users\\Cody\\Desktop\\718 cache\\" : "E:\\Misc\\Runescape Private Servers\\Releases\\RuneNova\\Source\\data\\cache\\";

	public static ParamTypeList paramTypeList;

	public static void main(String[] args) throws IOException {
		Revision revision = createRevision(REVISION_ID);
		int scriptId = 6128;
		FileStore s = FileStore.open(new File(CACHE_PATH));
		Cache cache = new Cache(s);
		paramTypeList = new ParamTypeList(cache);
		Context context = new Context();
		CS2Decompiler decompiler = new CS2Decompiler(context);
		//
		// context.withBlockEditing(true).withDebug(false).withCache(cache).withDecompiler(decompiler).withDisassembler(revision).withInstructionDecoder(revision).withRevision(revision).withPrinter(new
		// ConsolePrinter());
		// CS2Script script = decompiler.decompile(scriptId);
		// script.print(context);
		test();
	}

	public static void test() throws IOException {
		Revision rev = createRevision(742);
		Context context742 = new Context().withCache(new Cache(FileStore.open(new File("E:\\Misc\\Runescape Private Servers\\Releases\\RuneNova\\Source\\data\\cache\\")))).withBlockEditing(true).withDisassembler(rev).withInstructionDecoder(rev).withRevision(rev);
		rev = createRevision(718);
		Context context718 = new Context().withPrinter(new ConsolePrinter()).withCache(new Cache(FileStore.open(new File("C:\\Users\\Cody\\Desktop\\718 cache\\")))).withBlockEditing(true).withDisassembler(rev).withInstructionDecoder(rev).withRevision(rev);
		CS2Converter converter = new CS2Converter(context742, context718);
		byte[] data = converter.convert(6128);
		CS2Decompiler decompiler = new CS2Decompiler(context718);
		CS2Script script = decompiler.decompile(decompiler.disassemble(6128, data));
		script.print(context718);
	}
	public static Revision createRevision(int revision) {
		try {
			return (Revision) Class.forName("com.wycody.cs2d.rev.impl.Revision" + revision).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			System.err.println("Could not identify revision for id: " + revision);
			return null;
		}
	}
}
