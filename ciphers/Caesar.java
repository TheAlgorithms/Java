package ciphers;

import java.util.Scanner;

/**
 * A Java implementation of Caesar Cipher. It is a type of substitution cipher
 * in which each letter in the plaintext is replaced by a letter some fixed
 * number of positions down the alphabet.
 * 
 * @author FAHRI YARDIMCI
 * @author khalil2535
 */
public class Caesar {

	/**
	 * Encrypt a message by shifting every letter in the alphabet to the next
	 * 
	 * PS: just English letters will encrypt
	 * 
	 * @param message
	 *            the message to encrypt (English letters)
	 * @param shift
	 *            the number of units for shifting
	 * @return encoded the encrypted message
	 */
	public static String encrypt(String message, int shift) {
		String encoded = "";
		int messageLength = message.length();
		for (int i = 0; i < messageLength; i++) {
			char currentLetter = message.charAt(i);
			// Capital letter
			if (currentLetter >= 65 && currentLetter <= 90) {
				currentLetter = (char) (currentLetter + shift); // shift the letter
				while (currentLetter > 90) // shift back if big (90 = Z)
					currentLetter = (char) (currentLetter - 26);// 26 the number of English letters
			}
			// Small letter
			else if (currentLetter >= 97 && currentLetter <= 122) {
				currentLetter = (char) (currentLetter + shift);// shift the letter
				while (currentLetter > 122) // shift back if big (90 = z)
					currentLetter = (char) (currentLetter - 26);// 26 the number of English letters
			}
			encoded += currentLetter;
		}
		return encoded;
	}

	/**
	 * Decrypt message by shifting back every letter in the alphabet to the previous
	 * 
	 * PS:just English letters will decrypt
	 * 
	 * @param decryptedMessage
	 *            the encrypted message to decrypt
	 * @param shift
	 *            the number of units to decrypt
	 * @return decoded the plain message as String
	 */
	public static String decrypt(String decryptedMessage, int shift) {
		String decoded = "";
		int messageLength = decryptedMessage.length();
		for (int i = 0; i < messageLength; i++) {
			char currentLetter = decryptedMessage.charAt(i);
			// Capital letter
			if (currentLetter >= 65 && currentLetter <= 90) {
				currentLetter -= shift;// shift back
				while (currentLetter < 65)// shift the letter if big (65 = A)
					currentLetter += 26;// 26 is the number of English letters
			}
			// Small letter
			else if (currentLetter >= 97 && currentLetter <= 122) {
				currentLetter -= shift;// shift back
				while (currentLetter < 97)// shift the letter if big (97 = a)
					currentLetter += 26;// 26 is the number of English letters
			}
			decoded += currentLetter;
		}
		return decoded;
	}

	public static void main(String[] args) {
		System.out.println("** Caesar Encryption method **");

		Scanner input = new Scanner(System.in);
		System.out.println("Please enter e to encrypt or d to decrypt ");
		char operation = input.nextLine().charAt(0);
		do {
			System.out.println("Please enter the message (just Latin Alphabet will affect)");
			String message = input.nextLine();
			System.out.println("Please enter the key (shifting number)");
			int shift = input.nextInt();
			switch (operation) {
			case 'e':
			case 'E':
				System.out.println(encrypt(message, shift));
				break;
			case 'd':
			case 'D':
				System.out.println(decrypt(message, shift));
				break;
			default:
				input = null;
				break;
			}
			System.out.println("more messages ?");
			System.out.println("Enter e to encrypt or d to decrypt or any other letter to exit ");
			operation = input.nextLine().charAt(0);
		} while (operation == 'e' || operation == 'd' || operation == 'E' || operation == 'D');

		System.out.println("** Exit **");
	}

}