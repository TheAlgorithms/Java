package Conversions;

import java.util.*;

public class RomanToInteger {

	private static Map<Character, Integer> map = new HashMap<Character, Integer>() {
		/** */
		private static final long serialVersionUID = 87605733047260530L;

		{
			put('I', 1);
			put('V', 5);
			put('X', 10);
			put('L', 50);
			put('C', 100);
			put('D', 500);
			put('M', 1000);
		}
	};
	private static Map<String, Integer> mapException = new HashMap<String, Integer>() {
		/** */
		private static final long serialVersionUID = 87605733047260530L;

		{
			put("IV", 4);
			put("IX", 9);
			put("XL", 40);
			put("XC", 90);
			put("CD", 400);
			put("CM", 500);
		}
	};
	// Roman Number = Roman Numerals

	/**
	 * This function convert Roman number into Integer
	 *
	 * @param A Roman number string
	 * @return integer
	 */
	public static int romanToInt(String A) {

		A = A.toUpperCase();

		for (int i = 1; i < A.length(); i++) {
			if (map.get(A.charAt(i)) > map.get(A.charAt(i - 1))) {
				if(mapException.containsKey(new String(new char[] { A.charAt(i-1), A.charAt(i) }))) {
					i++;
				} else {
					return -1;
				}
			}
		}

		char prev = ' ';

		int sum = 0;

		int newPrev = 0;
		for (int i = A.length() - 1; i >= 0; i--) {
			char c = A.charAt(i);

			if (prev != ' ') {
				// checking current Number greater then previous or not
				newPrev = map.get(prev) > newPrev ? map.get(prev) : newPrev;
			}

			int currentNum = map.get(c);

			// if current number greater then prev max previous then add
			if (currentNum >= newPrev) {
				sum += currentNum;
			} else {
				// subtract upcoming number until upcoming number not greater then prev max
				sum -= currentNum;
			}

			prev = c;
		}

		return sum;
	}

	public static void main(String[] args) {
    int sum = romanToInt("LXXXVIII");
    if(sum==-1)
    	System.out.println("Invalid Input");
    else
    	System.out.println(sum);
  }
}
