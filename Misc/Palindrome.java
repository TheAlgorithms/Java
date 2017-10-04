class Palindrome {
	
	// palindrome method, returns true if x is palindrome
	public boolean isPalindrome(String x){ 
		if (x.length() == 0 || x.length() == 1) {
			return true;
		}
		 
		if (x.charAt(0) != x.charAt(s.length() - 1)) {
			return false;
		}
		return isPalindrome(x.substring(1, x.length() - 1));
	}
  
  }
