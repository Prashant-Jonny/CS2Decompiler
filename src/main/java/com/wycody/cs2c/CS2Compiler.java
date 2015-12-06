package com.wycody.cs2c;

import java.io.File;

/**
 * It's not to use good
 * 
 * @author Walied-Yassen
 * @date Dec 5, 2015
 */
public class CS2Compiler {

	public static void main(String[] args) {
		File file = new File("source.cs2");
		LexicalAnalyzer analyzer = new LexicalAnalyzer(file);

	}
}
