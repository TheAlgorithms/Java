import java.util.*;
/**
 * Author : Lee Yong Ler
 *
 * A Java Implementation of Vigenere Cipher
 * Vigenere Cipher is a series of interwoven Caesar Ciphers based on the letters of a given Keyword.
 *
 * This Program requires the command line argument to have Encrypt/Decrypt and a 10 digit key(all numbers)
 *
*/
public class VigenereCipher{
	private Scanner sc = new Scanner(System.in);
	private void run(String[] args){
		if(args.length != 2 )
			System.out.println("Please include \"Encrypt\"/\"Decrypt\" and a keyword");
		else if (args[0].equals("Decrypt")){
			decrypt(args[1].toCharArray());
		}
		else if(args[0].equals("Encrypt")){
			encrypt(args[1].toCharArray());
		}
		else
			System.out.println("Please include \"Encrypt\"/\"Decrypt\" and a keyword");
	}
	private void encrypt(char[] key){
		while(sc.hasNextLine()){
			char[] plainText = sc.nextLine().toCharArray();
			for(int i = 0,j = 0 ; i < plainText.length ; i++,j++){
				if(j >= key.length)
					j=0;
				System.out.print(cipher(plainText[i],key[j]));
			}
			System.out.println("");
		}
	}
	private void decrypt(char[] key){
		while(sc.hasNextLine()){
			char[] plainText = sc.nextLine().toCharArray();
			for(int i = 0,j = 0 ; i < plainText.length ; i++,j++){
				if(j >= key.length)
					j=0;
				System.out.print(decipher(plainText[i],key[j]));
			}
			System.out.println("");
		}
	}
	/**
	 *	Letters will cycle within their cases and special characters between 33 and 64 will cycle within 
	 *	all others will not be encrypted
	*/
	private char cipher(char letter, char key){
		if(Character.isWhitespace(letter))
			return letter;
		else if(Character.isUpperCase(letter)){
			return (char)((letter-'A'
						+Character.getNumericValue(key))%26+'A');
		}
		else if(Character.isLowerCase(letter)){
			return (char)((letter-'a'
						+Character.getNumericValue(key))%26+'a');
		}
		else if((int)letter<=64&&(int)letter>=33){
			return (char)((letter-'!'
						+Character.getNumericValue(key))%32+'!');
		}
		return letter;
	}
	private char decipher(char letter , char key){
		if(Character.isWhitespace(letter))
			return letter;
		else if(Character.isUpperCase(letter)){
			int temp = (letter-'A'-Character.getNumericValue(key))%26;
			while(temp < 0 )
				temp +=26;
			return (char)(temp+'A');
		}
		else if(Character.isLowerCase(letter)){
			int temp = (letter-'a'-Character.getNumericValue(key))%26;
			while(temp < 0 )
				temp +=26;
			return (char)(temp+'a');
		}
		else if((int)letter<=64&&(int)letter>=33){
			int temp = (letter-'!'-Character.getNumericValue(key))%32;
			while(temp < 0 )
				temp +=32;
			return (char)(temp+'!');
		}
		return letter;
	}
	public static void main(String[] args){
		VigenereCipher cipher = new VigenereCipher();
		cipher.run(args);
	}
}
