package com.wycody.cs2d.print;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FilePrinter extends ScriptPrinter {

	private BufferedWriter writer;
	private boolean printIndent;
	
	public FilePrinter(File file) {
		try {
			this.writer = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void newLine() {
		try {
			writer.newLine();
			printIndent = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void print(Object object) {
		try {
			if(printIndent)  {
				printIndent = false;
				writer.write(getIndentString());
			}
			writer.write(object.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	@Override
	public void flush() {
		try {
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void finish() {
		try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
