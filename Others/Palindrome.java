class Palindrome {
	
	public String reverseString(String x){ //*helper method
		String output = "";
		for(int i=x.length()-1; i>=0; i--){
			output += x.charAt(i); //addition of chars create String
		}
		return output;
	}
	
	
	public Boolean isPalindrome(String x){ //*palindrome method, returns true if palindrome
		return (x.equalsIgnoreCase(reverseString(x)));
	}
  
  }
