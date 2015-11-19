package com.wycody;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.jagex.game.runetek5.config.enumtype.EnumTypeList;
import com.jagex.game.runetek5.config.paramtype.ParamTypeList;
import com.jagex.game.runetek5.config.structtype.StructTypeList;
import com.wycody.cs2d.CS2Decompiler;
import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ConsolePrinter;
import com.wycody.cs2d.rev.Revision;
import com.wycody.cs2d.rev.impl.Revision742;
import com.wycody.cs2d.script.CS2Script;

import net.openrs.cache.Cache;
import net.openrs.cache.FileStore;

public class User {

	// here for now
	public static ParamTypeList paramTypeList;
	public static StructTypeList structTypeList;
	public static EnumTypeList enumTypeList;

	public static void main(String[] args) throws FileNotFoundException {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.INFO);
		int scriptId = 742;
		if (args.length >= 1) {
			try {
				scriptId = Integer.parseInt(args[0]);
			} catch (NumberFormatException nfe) {
			}
		}
		File cachePath = new File("YOUR_CACHE_PATH");
		if (args.length >= 2) {
			cachePath = new File(args[1].replaceFirst("^~", System.getProperty("user.home")));
		}
		Revision revision = new Revision742();
		FileStore s = FileStore.open(cachePath);
		Cache cache = new Cache(s);
		paramTypeList = new ParamTypeList(cache);
		Context context = new Context();
		CS2Decompiler decompiler = new CS2Decompiler(context);

		context.withBlockEditing(true).withDebug(true).withCache(cache).withDecompiler(decompiler).withDisassembler(revision).withInstructionDecoder(revision).withPrinter(new ConsolePrinter());

		CS2Script script = decompiler.decompile(scriptId);// 793
		script.print(context);
	}
}
