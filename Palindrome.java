public class Palidrome {
	
  //helper method
	public String reverseString(String x){
		String output = "";
		for(int i=x.length()-1; i>=0; i--){
			output += x.charAt(i); //addition of chars create String
		}
		return output;
	}
	
	//palidrome method
	public Boolean isPalindrome(String x){
		return (x.equalsIgnoreCase(reverseString(x)));
	}
  
  }
