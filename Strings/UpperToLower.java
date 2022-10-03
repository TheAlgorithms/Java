import java.util.*;

public class Solution{
  public static void main (String[] args){
  Scanner sc = new Scanner(System.in);
		String str=sc.nextLine();    
    //StringBuffer class is used here
        StringBuffer buf=new StringBuffer(str);    
            
        for(int i = 0; i < str.length(); i++) {    
                
             
            if(Character.isLowerCase(str.charAt(i))) {    
                  
                buf.setCharAt(i, Character.toUpperCase(str.charAt(i)));    
            }    
             
            else if(Character.isUpperCase(str.charAt(i))) {      
                buf.setCharAt(i, Character.toLowerCase(str.charAt(i)));    
            }    
        }
        
       
        System.out.println(buf);
    
