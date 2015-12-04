package com.wycody;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.jagex.game.runetek5.config.paramtype.ParamTypeList;
import com.wycody.cs2d.CS2Decompiler;
import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ConsolePrinter;
import com.wycody.cs2d.rev.Revision;
import com.wycody.cs2d.rev.impl.Revision718;
import com.wycody.cs2d.rev.impl.Revision742;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.utils.ScriptDumper;

import net.openrs.cache.Cache;
import net.openrs.cache.FileStore;

public class Walied {

	public static final int REVISION_ID = 718;
	public static final String CACHE_PATH = "C:\\Users\\Cody\\Desktop\\718 cache\\";

	public static ParamTypeList paramTypeList;

	public static void main(String[] args) throws IOException {
		Revision revision = createRevision(REVISION_ID);
		int scriptId = 338;
		FileStore s = FileStore.open(new File(CACHE_PATH));
		Cache cache = new Cache(s);
		paramTypeList = new ParamTypeList(cache);
		Context context = new Context();
		CS2Decompiler decompiler = new CS2Decompiler(context);

		context.withBlockEditing(true).withDebug(false).withCache(cache).withDecompiler(decompiler).withDisassembler(revision).withInstructionDecoder(revision).withPrinter(new ConsolePrinter());
		CS2Script script = decompiler.decompile(scriptId);
		script.print(context);

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
