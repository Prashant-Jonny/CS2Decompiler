package com.wycody.utils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileUtils {

	public static char[] getCharArray(File file) throws IOException {
		StringBuilder builder = new StringBuilder();
		Scanner scanner = new Scanner(file);
		while(scanner.hasNextLine()) {
			builder.append(scanner.nextLine() + "\n");
		}
		scanner.close();
		return builder.toString().toCharArray();
	}
}
