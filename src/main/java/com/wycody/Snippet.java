package com.wycody;

public class Snippet {


	public static void main(String[] args) {
		int interfaceId = 1143;
		int componentId = 14;
		System.out.println("Encoded: " + (interfaceId << 16 | componentId));
		int hash = 74907653;
		System.out.println("Decoded: " + (hash >> 16) + ", " + (hash & 0xffff));

	}

}
