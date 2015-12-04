package com.wycody.cs2d.utils;

import java.io.File;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.FilePrinter;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.name.NameMap;

/**
 * 
 * @author Walied-Yassen
 * @date Nov 30, 2015
 */
public class ScriptDumper {

	private static final String DEFAULT_DIRECTORY = "data/lastDump/";
	private static final String DEFAULT_EXT = "cs2";

	public static void dump(Context context, int scriptId) {
		dump(context, scriptId, DEFAULT_DIRECTORY);
	}

	public static void dump(Context context, int scriptId, String directory) {
		dump(context, scriptId, directory, DEFAULT_EXT);
	}

	public static void dump(Context context, int scriptId, String directory, String ext) {
		dump(context, scriptId, directory, ext, true);
	}

	public static void dump(Context context, int scriptId, String directory, String ext, boolean packaged) {
		dump(context, scriptId, directory, ext, packaged, true);
	}

	public static void dump(Context context, int scriptId, String directory, String ext, boolean packaged, boolean named) {
		ScriptPrinter beforePrinter = context.getPrinter();
		try {
			CS2Script script = context.getDecompiler().decompile(scriptId);
			if (script == null) {
				return;
			}
			NameMap map = context.getDecompiler().getNameMapper().getMaps().get(scriptId);
			String name = null;
			if (map != null) {
				if (map.getPackageName().length() > 0) {
					directory = directory + "\\" + map.getPackageName().replace(".", "\\") + "\\";
				}
				name = map.getName() + (ext.length() > 0 ? "." + ext : "");
			} else {
				name = scriptId + (ext.length() > 0 ? "." + ext : "");
			}

			File file = new File(directory, name);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
			context.withPrinter(new FilePrinter(file));
			script.print(context);
			context.getPrinter().finish();
		} catch (Throwable e) {

		}
		context.withPrinter(beforePrinter);
	}
}
