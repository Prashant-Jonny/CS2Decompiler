package com.wycody.cs2d.script.inst.base;

import org.apache.commons.collections4.list.TreeList;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.Printable;
import com.wycody.cs2d.print.ScriptPrinter;

/**
 * 
 * @author Walied-Yassen
 * @date Nov 26, 2015
 */
public class BlockComment implements Printable {

	private TreeList<String> text;

	public BlockComment() {
		text = new TreeList<String>();
	}

	public BlockComment(Throwable t) {
		this();
		text.add("Error: " + t.getLocalizedMessage());
		StackTraceElement[] element = Thread.currentThread().getStackTrace();
		for (int i = 0; i < (element.length < 4 ? element.length : 4); i++) {
			text.add(element[i].toString());
		}
	}

	public void addLine(String s) {
		text.add(s);
	}

	/**
	 * @return the text
	 */
	public TreeList<String> getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(TreeList<String> text) {
		this.text = text;
	}



	@Override
	public void print(Context context, ScriptPrinter printer) {
		printer.println("/**");
		for (String s : text) {
			printer.println(" * " + s);
		}
		printer.println(" */");
	}



}
