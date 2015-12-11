package com.wycody.cs2c;

import java.io.File;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.CharBuffer;

import com.wycody.utils.FileUtils;

public class LexicalAnalyzer {

	private CharBuffer buffer;

	public LexicalAnalyzer(File source) {
		try {
			buffer = CharBuffer.wrap(FileUtils.getCharArray(source));
		} catch (IOException e) {
			e.printStackTrace();
		}
		analyze();
	}

	private void analyze() {
		while (buffer.hasRemaining()) {
			char c = buffer.get();
			if (ignore(c)) {

			} else if (c == '\"') {
				StringBuilder string = new StringBuilder();
				while (true) {
					try {
						char sc = buffer.get();
						if (sc == '\"')
							break;
						string.append(sc);
					} catch (BufferUnderflowException e) {
						throw new CS2CompilerException("Unexpected end of string!");
					}

				}
				System.out.println("Found string: " + string);
			} else if (isNumber(c)) {
				StringBuilder builder = new StringBuilder();
				builder.append(c);
				while (buffer.hasRemaining()) {
					buffer.mark();
					char sc = buffer.get();
					if (isNumber(sc)) {
						builder.append(sc);
					} else {
						buffer.reset();
						break;
					}
				}
				System.out.println("Found number: " + builder.toString());
			} else {

			}
		}
	}

	private boolean ignore(char c) {
		return c == ' ';
	}

	private boolean isNumber(char c) {
		return c >= '0' && c <= '9';
	}

	private boolean isSymbol(char c) {
		return c == '+' || c == '-' || c == '*' || c == '\\' || c == '&' || c == '%';
	}
}
