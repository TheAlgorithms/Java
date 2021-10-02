package DynamicProgramming;

/**
 * Given a text and wildcard pattern implement a wildcard pattern matching
 * algorithm that finds if wildcard is matched with text. The matching should
 * cover the entire text 
 * ?-> matches single characters 
 * *-> match the sequence of characters
 **/

/**
 * For Time and Space Complexity. Let N be length of src and M be length of pat
 **/

public class RegexMatching {

	// Method 1: Using Recursion
	// Time Complexity=0(2^(N+M)) Space Complexity=Recursion Extra Space
	static boolean RegexRecursion(String src, String pat) {
		if (src.length() == 0 && pat.length() == 0) {
			return true;
		}
		if (src.length() != 0 && pat.length() == 0) {
			return false;
		}
		if (src.length() == 0 && pat.length() != 0) {
			for (int i = 0; i < pat.length(); i++) {
				if (pat.charAt(i) != '*') {
					return false;
				}
			}
			return true;
		}
		char chs = src.charAt(0);
		char chp = pat.charAt(0);

		String ros = src.substring(1);
		String rop = pat.substring(1);

		boolean ans;
		if (chs == chp || chp == '?') {
			ans = RegexRecursion(ros, rop);
		} else if (chp == '*') {
			boolean blank = RegexRecursion(src, rop);
			boolean multiple = RegexRecursion(ros, pat);
			ans = blank || multiple;
		} else {
			ans = false;
		}
		return ans;
	}

	// Method 2: Using Recursion and breaking string using virtual index
	// Time Complexity=0(2^(N+M)) Space Complexity=Recursion Extra Space
	static boolean RegexRecursion(String src, String pat, int svidx, int pvidx) {
		if (src.length() == svidx && pat.length() == pvidx) {
			return true;
		}
		if (src.length() != svidx && pat.length() == pvidx) {
			return false;
		}
		if (src.length() == svidx && pat.length() != pvidx) {
			for (int i = pvidx; i < pat.length(); i++) {
				if (pat.charAt(i) != '*') {
					return false;
				}
			}
			return true;
		}
		char chs = src.charAt(svidx);
		char chp = pat.charAt(pvidx);

		boolean ans;
		if (chs == chp || chp == '?') {
			ans = RegexRecursion(src, pat, svidx + 1, pvidx + 1);
		} else if (chp == '*') {
			boolean blank = RegexRecursion(src, pat, svidx, pvidx + 1);
			boolean multiple = RegexRecursion(src, pat, svidx + 1, pvidx);
			ans = blank || multiple;
		} else {
			ans = false;
		}
		return ans;
	}

	// Method 3: Top-Down DP
	// Time Complexity=0(N*M) Space Complexity=0(N*M)+Recursion Extra Space
	static boolean RegexRecursion(String src, String pat, int svidx, int pvidx, int[][] strg) {
		if (src.length() == svidx && pat.length() == pvidx) {
			return true;
		}
		if (src.length() != svidx && pat.length() == pvidx) {
			return false;
		}
		if (src.length() == svidx && pat.length() != pvidx) {
			for (int i = pvidx; i < pat.length(); i++) {
				if (pat.charAt(i) != '*') {
					return false;
				}
			}
			return true;
		}
		if (strg[svidx][pvidx] != 0) {
			return strg[svidx][pvidx] == 1 ? false : true;
		}
		char chs = src.charAt(svidx);
		char chp = pat.charAt(pvidx);

		boolean ans;
		if (chs == chp || chp == '?') {
			ans = RegexRecursion(src, pat, svidx + 1, pvidx + 1, strg);
		} else if (chp == '*') {
			boolean blank = RegexRecursion(src, pat, svidx, pvidx + 1, strg);
			boolean multiple = RegexRecursion(src, pat, svidx + 1, pvidx, strg);
			ans = blank || multiple;
		} else {
			ans = false;
		}
		strg[svidx][pvidx] = ans == false ? 1 : 2;
		return ans;
	}

	// Method 4: Bottom-Up DP
	// Time Complexity=0(N*M) Space Complexity=0(N*M)
	static boolean regexBU(String src, String pat) {

		boolean strg[][] = new boolean[src.length() + 1][pat.length() + 1];
		strg[src.length()][pat.length()] = true;
		for (int row = src.length(); row >= 0; row--) {
			for (int col = pat.length() - 1; col >= 0; col--) {
				if (row == src.length()) {
					if (pat.charAt(col) == '*') {
						strg[row][col] = strg[row][col + 1];
					} else {
						strg[row][col] = false;
					}
				} else {
					char chs = src.charAt(row);
					char chp = pat.charAt(col);

					boolean ans;
					if (chs == chp || chp == '?') {
						ans = strg[row + 1][col + 1];
					} else if (chp == '*') {
						boolean blank = strg[row][col + 1];
						boolean multiple = strg[row + 1][col];
						ans = blank || multiple;
					} else {
						ans = false;
					}
					strg[row][col] = ans;
				}
			}
		}
		return strg[0][0];
	}

	public static void main(String[] args) {

		String src = "aa";
		String pat = "*";
		System.out.println("Method 1: "+RegexRecursion(src, pat));
		System.out.println("Method 2: "+RegexRecursion(src, pat, 0, 0));
		System.out.println("Method 3: "+RegexRecursion(src, pat, 0, 0, new int[src.length()][pat.length()]));
		System.out.println("Method 4: "+regexBU(src, pat));

	}

}
