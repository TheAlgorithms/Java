public class PalindromeRecursive {

	public static boolean isPalindrome(String s) {
		// base case
		if (s.length() <= 1) return true;
		
		if (Character.toLowerCase(s.charAt(0)) == Character.toLowerCase(s.charAt(s.length() - 1)))
			return isPalindrome(s.substring(1, s.length() - 1));
		return false;
	}
	
	public static void main(String[] args) {
		String s = "eye";
		System.out.println(isPalindrome(s));		
		s = "redivider";
		System.out.println(isPalindrome(s));
		s = "Redivider";
		System.out.println(isPalindrome(s));		
		s = "ayo";
		System.out.println(isPalindrome(s));
	}

}
