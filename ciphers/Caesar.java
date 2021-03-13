package ciphers;

import java.util.Scanner;

/**
 * A Java implementation of Caesar Cipher. /It is a type of substitution cipher in which each letter
 * in the plaintext is replaced by a letter some fixed number of positions down the alphabet. /
 *
 * @author FAHRI YARDIMCI
 * @author khalil2535
 */
public class Caesar {
  
  /**
  * number of letters
  */
  public static final int NUM_LATIN_LETTERS = 26;

  /**
   * Encrypt text by shifting every Latin char by add number shift for ASCII Example : A + 1 -> B
   *
   * @param message
   * @param shift
   * @return Encrypted message
   */
  public static String encode(String message, int shift) {
    String encoded = "";

    shift %= NUM_LATIN_LETTERS;

    final int length = message.length();
    for (int i = 0; i < length; i++) {

      //            int current = message.charAt(i); //using char to shift characters because ascii
      // is in-order latin alphabet
      char current = message.charAt(i); // Java law : char + int = char

      if (isLatinLetter(current)) {

        current += shift;
        encoded += (char) (Character.toLowerCase(current) > 'z' ? current - NUM_LATIN_LETTERS : current); 

      } else {
        encoded += current;
      }
    }
    return encoded;
  }

  /**
   * Decrypt message by shifting back every Latin char to previous the ASCII Example : B - 1 -> A
   *
   * @param encryptedMessage
   * @param shift
   * @return message
   */
  public static String decode(String encryptedMessage, int shift) {
    String decoded = "";

    shift %= NUM_LATIN_LETTERS;

    final int length = encryptedMessage.length();
    for (int i = 0; i < length; i++) {
      char current = encryptedMessage.charAt(i);
      if (isLatinLetter(current)) {

        current -= shift;
        decoded += (char) (Character.toLowerCase(current) < 'a' ? current + NUM_LATIN_LETTERS : current); 

      } else {
        decoded += current;
      }
    }
    return decoded;
  }


  /**
   * @param c
   * @return true if character is Latin letter or false for others
   */
  private static boolean isLatinLetter(char c) {
    return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') ;
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Please enter the message (Latin Alphabet)");
    String message = input.nextLine();
    System.out.println(message);
    System.out.println("Please enter the shift number");
    int shift = input.nextInt() % NUM_LATIN_LETTERS;
    System.out.println("(E)ncode or (D)ecode ?");
    char choice = input.next().charAt(0);
    switch (Character.toLowerCase(choice)) {
      case 'e':
        System.out.println(
            "ENCODED MESSAGE IS \n" + encode(message, shift)); // send our function to handle
        break;
      case 'd':
        System.out.println("DECODED MESSAGE IS \n" + decode(message, shift));
        break;
      default:
        System.out.println("invalid input.");
    }
    input.close();
  }
}
