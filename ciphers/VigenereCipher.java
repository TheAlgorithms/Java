/*
Author: Nathan Fei

An implementation of the Vigenère cipher
*/

import java.util.Scanner;

public class VigenereCipher {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Message (letters only): ");
		String message = sc.nextLine().toUpperCase();
		System.out.print("Enter Key: ");
		String key = sc.nextLine().toUpperCase();
		System.out.print("[E]ncode or [d]ecode message?: ");
		char choice = sc.next().toUpperCase().charAt(0);
		if (choice == 'E') System.out.println(encode(message, key));
		else System.out.println(decode(message, key));
	}
	
	private static String encode(String message, String key) {
		StringBuilder sb = new StringBuilder(message.length());
		int keyPlace = 0;
		for (char c : message.toCharArray()) {
			sb.append((char)((c - 'A' + key.charAt(keyPlace) - 'A') % 26 + 'A'));
			keyPlace = (keyPlace + 1) % key.length();
		}
		return sb.toString();
	}
	
	private static String decode(String message, String key) {
		StringBuilder sb = new StringBuilder(message.length());
		int keyPlace = 0;
		for (char c : message.toCharArray()) {
			sb.append((char)((c - key.charAt(keyPlace) + 26) % 26 + 'A'));
			keyPlace = (keyPlace + 1) % key.length();
		}
		return sb.toString();
	}
}
