public class Solution {

	public static int countWords(String str) {	
		int start=0;
		int end=str.length();
		int count=1;
		while(start<end){
			if(str.charAt(start)==' ' && str.charAt(start+1)!=' '){ //simple solution looking for space if we found it is considered a word
				count++;
			}
			start++;
			
		}

		return count;
	}

}
