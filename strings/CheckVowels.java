package strings;

public class vowelCounter{
	
	public static void main(String[] args) {
	    
		assert countVowel("aeiou AEIOU")==(10);
		assert countVowel("This is a vowel counter")==countVowel("i i a oe oue");
	    

	  }
	
/**
 * Count how many vowels contained in one strings
 * 	
 * @param s the strings to be counted
 * @return the numbers of appearance of vowels inside one strings
 */
	public static int countVowel (String s) {
		
		 char[] letters = s.toCharArray();
		 int vowelCounted = 0 ;
		 
		 for (char i : letters) { 
			 switch (i) { 
			 	case 'a':
			 	case 'A':
				case 'e': 
				case 'E':
				case 'i':
				case 'I':
				case 'o':
				case 'O':
				case 'u':
				case 'U':
					 vowelCounted++; break;
				default: 
				}
			 }
		 return vowelCounted;
	}
}
