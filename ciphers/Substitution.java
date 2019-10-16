package ciphers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
* @author HeartBeat1608
*
* This is a Java implementation of the Simple Substitution Cipher.
*
**/

class Substitution {
  static String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  static int Mode = 1;        // 1: Encrypt         -1: Decrypt

  Substitution() {

  }

  // Validate key for Encryption
  public static int checkValidKey(String key) {
    char[] keyList = key.toCharArray();
    char[] LetterList = LETTERS.toCharArray();

    Arrays.sort(keyList);

    return (Arrays.equals(keyList, LetterList) ? 1 : -1);
  }

  // Shuffle the Letters Character Array to generate the key (as a string)
  public static char[] RandomizeArray(char[] array) {
		Random rgen = new Random();  // Random number generator

		for (int i=0; i < array.length; i++) {
		    int randomPosition = rgen.nextInt(array.length);
		    char temp = array[i];
		    array[i] = array[randomPosition];
		    array[randomPosition] = temp;
		}

		return array;
	}

  public static String translateMessage(String key, String msg) {
    String translated = "";
    String charsA = LETTERS;
    String charsB = key;
    char[] msgArray = msg.toCharArray();

    // Swap for Decryption
    if(Mode == -1) {
      String t = charsA;
      charsA = charsB;
      charsB = t;
    }

    for(char symbol : msgArray) {
      if(charsA.contains(Character.toString(Character.toUpperCase(symbol)))) {
        int symIndex = charsA.indexOf(Character.toString(Character.toUpperCase(symbol)));
        if(Character.isUpperCase(symbol)) {
          translated += Character.toUpperCase(charsB.charAt(symIndex));
        } else {
          translated += Character.toLowerCase(charsB.charAt(symIndex));
        }
      } else {
        translated += symbol;
      }
    }

    return translated;
  }

  public static void main(String[] args) {
    BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
    String message, key, translated;

    try {
      // Read Mode of operation
      System.out.print("Enter Mode (1: Encrypt || 2: Decrypt) : ");
      Mode = (Integer.parseInt(bfr.readLine()) == 1 ? 1 : -1);

      // Read the Message
      System.out.print("Enter Message : ");
      message = bfr.readLine();

      // Generate the Encryption Key
      if(Mode == 1) {
        key = new String(RandomizeArray(LETTERS.toCharArray()));
        System.out.println("KEY : " + key);
      }
      else
      {
        // Retrieve the Decyption key from the user and validate it.
        System.out.print("Enter KEY : ");
        key = bfr.readLine().toUpperCase();

        // Break if the Key is invalid
        if(checkValidKey(key) == -1) {
          System.out.println("Key Invalid.. Check your key.");
          return;
        }
      }

      translated = translateMessage(key, message);

      System.out.println("Final Message : ");
      System.out.println(translated);

    } catch(Exception e) {

      System.out.println(e.getMessage());

    }
  }

}
