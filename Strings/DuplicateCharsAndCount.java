package Strings;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class determines and prints duplicate characters in the input string
 *
 * */
public class DuplicateCharsAndCount {
	
	
	/**
	 * Prints duplicate characters and it's count in the input string
	 * @param inputString
	 */
	public static void printDuplicateCharsAndCount(String inputString) {
		// char count initialized to 0
        int charCount = 0;
 
        // Temporary list of characters to store already printed characters
        ArrayList<Character> charList = new ArrayList<>();
        int i = 0;
 
        for (; i < inputString.length(); i++) {
 
            char ch = inputString.charAt(i);
 
            // count the number of occurrences of the char ch in the inputString
            for (int j = 0; j < inputString.length(); j++) {
                if (Character.toLowerCase(inputString.charAt(j)) != Character.toLowerCase(ch)) {
                    continue;
                }
                charCount++;
 
            }
 
            // check if character ch is already printed
            if (!charList.contains(Character.toLowerCase(ch))) {
                // check if count is more than 1 i.e. duplicate and char should not be space
                if (charCount > 1 && ch != ' ') {
 
                    System.out.println("Char: " + ch + ", Count: " + charCount + " times.");
                    charList.add(Character.toLowerCase(ch));
                }
            }
 
            // Re-initialize charCount to 0 for next character
            charCount = 0;
        }
        
        // check if the counter i is equal to the input string length and if char list is empty i.e. no duplicate found
        if(i == inputString.length() && charList.isEmpty()) {
        	System.out.println("No duplicate characters found in the input string.");
        }
	}

	public static void main(String[] args) {
		String inputString = "";
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the string: ");
        inputString = sc.nextLine();
        
        printDuplicateCharsAndCount(inputString);
	}

}
