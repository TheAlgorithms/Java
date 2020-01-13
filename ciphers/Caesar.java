package ciphers;


import java.util.Scanner;

/**
 *
 * A Java implementation of Caesar Cipher. It is a type of substitution cipher
 * in which each letter in the plaintext is replaced by a letter some fixed
 * number of positions in the alphabet. 
 *
 * @author blast314
 */
public class Caesar {

	/**
	 * Shifting every Latin char by add number shift for ASCII values
	 *
	 * @param message
	 * @param shift
	 * @param encode
	 * @return Ciphered message
	 */
	public static StringBuffer cipher(String message, int shift, boolean encode) {
		StringBuffer result = new StringBuffer();
		shift %= 26;
		if (!encode)
			shift *= -1; // reverse the shift for decoding
		for (int i = 0; i < message.length(); i++) {
			if (Character.isUpperCase(message.charAt(i))) {
				char ch = (char) (((int) message.charAt(i) - 65 + shift + 26) % 26 + 65);
				result.append(ch);
			} else {
				char ch = (char) (((int) message.charAt(i) - 97 + shift + 26) % 26 + 97);
				result.append(ch);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Please enter the message (Latin Alphabet): ");
		String message = input.nextLine();
		if((message.equals("")) || (!message.matches("^[a-zA-Z]*$")))
			System.exit(0);
		
		System.out.print("Please enter the shift number: ");
		int shift = input.nextInt() % 26;
		
		System.out.print("(E)ncode or (D)ecode? ");
		Character choice = input.next().toLowerCase().charAt(0);
		if (choice == 'e')
			System.out.println(cipher(message, shift, true));
		if (choice == 'd')
			System.out.println(cipher(message, shift, false));
		
		input.close();
	}

}
