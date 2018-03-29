class Palindrome {
	
	private String reverseString(String x){ //*helper method
		String output = "";
		for(int i=x.length()-1; i>=0; i--){
			output += x.charAt(i); //addition of chars create String
		}
		return output;
	}
	
	
	public Boolean FirstWay(String x){ //*palindrome method, returns true if palindrome
		return (x.equalsIgnoreCase(reverseString(x)));
	}
  	
  	public boolean SecondWay(String x)
  	{
  		if (x.length() == 0 || x.length() == 1)
  			return true;

  		if (x.charAt(0) != x.charAt(x.length() - 1))
  			return false;

  		return SecondWay(x.substring(1 , x.length() - 1));
  	}
  }
