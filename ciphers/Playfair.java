package ciphers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Playfair Cipher : https://en.wikipedia.org/wiki/Playfair_cipher
 * 
 * @author Fernando Balandran (@fredz0003)
 * @category Ciphers
 *
 */

public class Playfair {

	public static void main(String[] args) {
		
		String coded;
		
		coded = Playfair.encode("secret message", "keyword");
		Playfair.decode(coded, "keyword");
		
	}
	
	// Variables
	private static String alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
	
	private static String generateTable(String key)
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

		
		
		StringBuilder table = new StringBuilder();
		String currentCharAsString; 
		
		// Copy key chars into the table if they are in the alphabet, ignore duplicates
		for(int i = 0; i < key.length(); i++){
			
			currentCharAsString = String.valueOf(key.charAt(i));
			
			// If letter (Char) is in alphabet && NOT already in the table
			if(alphabet.contains(currentCharAsString.toUpperCase()) && 
					!table.toString().contains(currentCharAsString.toUpperCase()))
			{
				table.append(currentCharAsString.toUpperCase());
			}
		}
		
		// Fill the reset of the table in with remaining alphabet characters
		for(int i = 0; i < alphabet.length(); i++)
		{
			currentCharAsString = String.valueOf(alphabet.charAt(i));
			
			if(!table.toString().contains(currentCharAsString))
			{
				table.append(currentCharAsString);
			}
		}
		
		return table.toString();
	}
	
	private static List<String> chunker(String seq, int size)
	{
		
		List<String> chunks = new ArrayList<String>();
		
		// Remove spaces in seq
		String noSpacesSeq = seq.replaceAll("\\s+", "");
		
		// Break string message into bigrams, if there's one remaining add one random letter at the end
		for(int i = 0; i < noSpacesSeq.length(); i++)
		{
			if(i % 2 != 0)
			{
				chunks.add(noSpacesSeq.substring(i -1, i+1));
			} else if(i == noSpacesSeq.length() -1 && i % 2 == 0) // if last char, and even number 
			{
				// generate a random number from 0-24
				// I've went with random letter, but it is not necessary
				// You could assign your fav letters here without doing random
				Random rand = new Random();
				int randomLetter = rand.nextInt(24);
				
				chunks.add(noSpacesSeq.substring(i) + String.valueOf(alphabet.charAt(randomLetter)));
			}
		}
		
		return chunks;
	}
	
	private static String cleanPlaintext(String dirty)
	{
		StringBuilder clean = new StringBuilder();
		boolean isLastPairSameLetters = false;
		
		// Prepare the plaintext to UpperCase and replace second repeated letters with X's
		for(int i = 0; i < dirty.length() -1; i++)
		{
			
			clean.append(dirty.charAt(i));
			
			if(dirty.charAt(i) == dirty.charAt(i +1))
			{
				if(dirty.length() -2 == i)
				{
					isLastPairSameLetters = true;
				}
				
				clean.append('X');
				i++;
			} 
		}
		
		// Add the last letter, unless the last letter is a repeat
		if(!isLastPairSameLetters)
		{
			clean.append(dirty.charAt(dirty.length() -1));
		}
		
		return clean.toString().toUpperCase();
	}
	
	public static String encode(String plaintext, String key)
	{
		List<String> chunks = new ArrayList<String>();
		String table;
		
		table = generateTable(key);
		plaintext = cleanPlaintext(plaintext);
		chunks = chunker(plaintext, 2);
		StringBuilder ciphertext = new StringBuilder();
		DivMod divmod1 = new DivMod();
		DivMod divmod2 = new DivMod();
		
		for(int i = 0; i < chunks.size(); i++)
		{
			divmod1.divmod(table.indexOf(chunks.get(i).charAt(0)), 5);
			divmod2.divmod(table.indexOf(chunks.get(i).charAt(1)), 5);
			
			if(divmod1.getRow() == divmod2.getRow())
			{
				ciphertext.append(table.charAt(divmod1.getRow()*5 +(divmod1.getCol() +1) % 5));
				ciphertext.append(table.charAt(divmod2.getRow()*5 +(divmod2.getCol() +1) % 5));
			} else if(divmod1.getCol() == divmod2.getCol()){
				
				ciphertext.append(table.charAt(((divmod1.getRow()+1)%5)*5+divmod1.getCol()));
				ciphertext.append(table.charAt(((divmod2.getRow()+1)%5)*5+divmod2.getCol()));
			} else{
				
				ciphertext.append(table.charAt(divmod1.getRow()*5+divmod2.getCol()));
				ciphertext.append(table.charAt(divmod2.getRow()*5+divmod1.getCol()));
			}
			
		}
		
		System.out.println(ciphertext.toString());
		
		return ciphertext.toString();
	}
	
	public static String decode(String ciphertext, String key)
	{
		List<String> chunks = new ArrayList<String>();
		String table;
		
		table = generateTable(key);
		chunks = chunker(ciphertext, 2);
		StringBuilder plaintext = new StringBuilder();
		DivMod divmod1 = new DivMod();
		DivMod divmod2 = new DivMod();
		
		for(int i = 0; i < chunks.size(); i++)
		{
			divmod1.divmod(table.indexOf(chunks.get(i).charAt(0)), 5);
			divmod2.divmod(table.indexOf(chunks.get(i).charAt(1)), 5);
			
			if(divmod1.getRow() == divmod2.getRow())
			{
				plaintext.append(table.charAt(divmod1.getRow()*5 +(divmod1.getCol() -1) % 5));
				plaintext.append(table.charAt(divmod2.getRow()*5 +(divmod2.getCol() -1) % 5));
			} else if(divmod1.getCol() == divmod2.getCol()){
				
				plaintext.append(table.charAt(((divmod1.getRow()-1)%5)*5+divmod1.getCol()));
				plaintext.append(table.charAt(((divmod2.getRow()-1)%5)*5+divmod2.getCol()));
			} else{
				
				plaintext.append(table.charAt(divmod1.getRow()*5+divmod2.getCol()));
				plaintext.append(table.charAt(divmod2.getRow()*5+divmod1.getCol()));
			}
			
		}
		
		System.out.println(plaintext.toString());
		
		return plaintext.toString();
	}

}

// Helper class for easier divmod operations simultaneous
class DivMod {
	
	private int row;
	private int col;
	
	public void divmod(int input1, int input2)
	{
		row = input1 / input2;
		col = input1 % input2;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
}
