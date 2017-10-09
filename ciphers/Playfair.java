package ciphers;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Playfair Cipher : https://en.wikipedia.org/wiki/Playfair_cipher
 * 
 * @author Fernando Balandran (@fredz0003)
 * @category Ciphers
 *
 */

public class Playfair {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String key = prompt("Enter an encryption key", sc, 6);
		String text = prompt("Enter the message: ", sc, 1);
		String jti = prompt("Replace J with I?", sc, 1);
		
		boolean changeJtoI = jti.equalsIgnoreCase("y");
		generateTable(key, changeJtoI);
		
		 String enc = encode(prepareText(text, changeJtoI));
		 
	     System.out.printf("%nEncoded message: %n%s%n", enc);
	     System.out.printf("%nDecoded message: %n%s%n", decode(enc));
		
	}
	
	// Variables
	private static String alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
	private static char[][] charTable;
	private static Point[] positions;
	
	private static String prompt(String promptText, Scanner sc, int minLen) {
        String s;
        do {
            System.out.print(promptText);
            s = sc.nextLine().trim();
        } while (s.length() < minLen);
        return s;
    }
	
	private static void generateTable(String key, boolean changeJtoI)
	{
		// Generate a 5x5 table (25 letters)
		// Example: key = keyword
		//		+---+---+---+---+---+
		//		| k | e | y | w | o |
		//		+-------------------+
		//		| r | d | a | b | c |
		//		+-------------------+
		//		| f | g | h | i | l |
		//		+-------------------+
		//		| m | n | p | q | s |
		//		+-------------------+
		//		| t | u | v | x | z |
		//		+---+---+---+---+---+
		
		charTable = new char[5][5];
        positions = new Point[26];
 
        String s = prepareText(key + "ABCDEFGHIJKLMNOPQRSTUVWXYZ", changeJtoI);
 
        int len = s.length();
        for (int i = 0, k = 0; i < len; i++) {
            char c = s.charAt(i);
            if (positions[c - 'A'] == null) {
                charTable[k / 5][k % 5] = c;
                positions[c - 'A'] = new Point(k % 5, k / 5);
                k++;
            }
        }
	}
	
	private static String prepareText(String s, boolean changeJtoI) {
        s = s.toUpperCase().replaceAll("[^A-Z]", "");
        return changeJtoI ? s.replace("J", "I") : s.replace("Q", "");
    }
	
	private static String encode(String s) {
        StringBuilder sb = new StringBuilder(s);
 
        for (int i = 0; i < sb.length(); i += 2) {
 
            if (i == sb.length() - 1)
                sb.append(sb.length() % 2 == 1 ? 'X' : "");
 
            else if (sb.charAt(i) == sb.charAt(i + 1))
                sb.insert(i + 1, 'X');
        }
        
        System.out.println(sb.toString());
        
        return codec(sb, 1);
    }
 
    private static String decode(String s) {
        
    	String plaintext;
    	
    	plaintext = codec(new StringBuilder(s), 4);
    	plaintext = removeX(plaintext);
    	
    	return plaintext;
    }
 
    private static String codec(StringBuilder text, int direction) {
        int len = text.length();
        for (int i = 0; i < len; i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);
 
            int row1 = positions[a - 'A'].y;
            int row2 = positions[b - 'A'].y;
            int col1 = positions[a - 'A'].x;
            int col2 = positions[b - 'A'].x;
 
            if (row1 == row2) {
                col1 = (col1 + direction) % 5;
                col2 = (col2 + direction) % 5;
 
            } else if (col1 == col2) {
                row1 = (row1 + direction) % 5;
                row2 = (row2 + direction) % 5;
 
            } else {
                int tmp = col1;
                col1 = col2;
                col2 = tmp;
            }
 
            text.setCharAt(i, charTable[row1][col1]);
            text.setCharAt(i + 1, charTable[row2][col2]);
        }
        return text.toString();
    }
    
    private static String removeX(String s){
    	
    	StringBuilder stringWithoutX = new StringBuilder();
    	
    	for(int i = 0; i < s.length(); i++)
    	{
    		if(s.charAt(i) == 'X')
    		{
    			// Check if last letter is X
    			if(i == s.length() -1)
    			{
    				break;
    			}
    			
    			// Check if is sandwiched with same letters
    			if(s.charAt(i-1) == s.charAt(i+1))
    			{
    				continue;
    			} else {
    				stringWithoutX.append(s.charAt(i)); // Otherwise append to string
    			}
    		} else {
    			stringWithoutX.append(s.charAt(i));
    		}
    	}
    	
    	return stringWithoutX.toString().toUpperCase();
    }

}
