import java.util.*;
public class RomanToInteger {
		
		/*
		   This function convert Roman number into Integer
		   @param A is Roman number string 
		*/
	    public static int romanToInt(String A) {
	        Map<Character , Integer> map = new HashMap<>();
	        map.put('I' , 1);
	        map.put('V' , 5);
	        map.put('X' , 10);
	        map.put('L' , 50);
	        map.put('C' , 100);
	        map.put('D' , 500);
	        map.put('M' , 1000);
	        
	        char c = A.charAt(A.length()-1);
	        char prev = ' ';
	        
	        int sum =0;
	       
	        int newPrev = 0, currentNum =0;
	        for(int i = A.length() -1;i>=0;i--)
	        {
	            c = A.charAt(i);
	            
	            
	            if(prev != ' ') {
	            	//checking current Number greater then previous or not
	                newPrev = map.get(prev) > newPrev ? map.get(prev) : newPrev ; 
	            }
	               
	             
	             currentNum = map.get(c);  
	               
	             if(currentNum >= newPrev ) //if current number greater then prev max previous then add
	            {
	                    sum += currentNum;
	            } 
	            else {
	            
	                sum -= currentNum;  // subtract upcoming number until upcoming number not greater then prev max
	            }     
	             
	          prev = c;    
	        }
	        
	        return sum;
    }


	public static void main(String[] args) {
	
		
		int sum = romanToInt("MDCCCIV") ;
		System.out.println(sum);
	}

}