package com.wycody.cs2d.print;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.wycody.cs2d.script.CS2Script;

/**
 * 
 * @author Walied-Yassen
 * @date Nov 22, 2015
 */
public class DirectoryPrinter extends ScriptPrinter {

	private File directory;
	private String format;

	private boolean hasIdent;
	private BufferedWriter writer;

	public DirectoryPrinter(File directory, String format) {
		this.directory = directory;
		this.format = format;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wycody.cache.print.ScriptPrinter#newLine()
	 */
	@Override
	public void newLine() {
		try {
			writer.newLine();
			hasIdent = false;
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wycody.cache.print.ScriptPrinter#print(java.lang.Object)
	 */
	@Override
	public void print(Object object) {

		try {
			if (!hasIdent) {
				writer.write(getIndentString());
				hasIdent = true;
			}
			writer.write(object.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		writer.flush();
		writer.close(); 
		setIndent(0);
		hasIdent = false;
		super.finalize();

	}

	@Override
	public void initializeForScript(CS2Script script) {
		try {
			File file = new File(directory, String.format(format, script.getId()));
			if(file.exists()) {
				file.delete();
			}
			file.createNewFile();
			writer = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void finalizeForScript(CS2Script script) {
		try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
