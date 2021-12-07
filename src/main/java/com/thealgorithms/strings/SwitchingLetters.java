package com.thealgorithms.strings;

import java.util.Scanner;

public class SwitchingLetters {
	
	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);

		System.out.println("Please enter two numbers:");

		int index1 = keyboard.nextInt() - 1;
		int index2 = keyboard.nextInt() - 1;

		keyboard.nextLine();

		System.out.println("Please enter a sentence:");

		String sentence = keyboard.nextLine();
		
		System.out.println("The new sentence:\n" + switchLetters(index1, index2, sentence));
	}

	/* switchLetters method switches letters according to given indexes. */

	public static String switchLetters(int index1, int index2, String sentence) {
		return sentence.substring(0,index1) + sentence.charAt(index2) + sentence.substring(index1 + 1, index2) + sentence.charAt(index1) + sentence.substring(index2 + 1, sentence.length());
	}
}
