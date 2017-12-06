import java.util.*;

/**
 * This program will check if the parentheses are balanced or
 * not for an input string, i.e. to determine whether the pairs
 * and the orders of “{“,”}”,”(“,”)”,”[“,”]” are correct in
 * input string or not.
 * 
 * @author Nishita Aggarwal
 *
 */

public class BalancedParentheses {


    /**
	 * isBalanced Method
	 * 
	 * @param s The string expression
	 * @return boolean value indicating that whether the 
   	 * parentheses are balanced or not
	 */

   static boolean isBalanced(String s) {
      Stack<Character> stack=new Stack<>();
      int i=0,len=s.length();
      for(;i<len;i++)
      {
        if(s.charAt(i)=='{'||s.charAt(i)=='['||s.charAt(i)=='(')
          stack.push(s.charAt(i));
        else if(!stack.isEmpty())
        {
          if(s.charAt(i)==')' && stack.peek()=='(')
            stack.pop();
          else if(s.charAt(i)=='}' && stack.peek()=='{')
            stack.pop();
          else if(s.charAt(i)==']' && stack.peek()=='[')
            stack.pop();
          else
            return false;    
        }
        else
        	return false;
      }
     return stack.isEmpty();
    }
    

    /**
	 * The main method
	 * 
	 * @param args Command line arguments
	 */

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        Boolean result = isBalanced(s);
        System.out.println(result);
        in.close();
    }

}