/*Explanation: https://www.geeksforgeeks.org/expression-contains-redundant-bracket-not/*/
import java.util.*;
class Solution {

	public static boolean checkRedundantBrackets(String e) {
		//Your code goes here
        Stack<Character> s = new Stack<>();
        boolean ans=false;
        for(int i=0;i<e.length();i++){
            if(e.charAt(i)=='+'||e.charAt(i)=='-'||e.charAt(i)=='*'||e.charAt(i)=='/'||e.charAt(i)=='('){
                s.push(e.charAt(i));
            }else if(e.charAt(i)==')'){
                if(s.peek()=='(')
                    ans=true;
                while(s.peek()=='+'||s.peek()=='-'||s.peek()=='*'||s.peek()=='/'){
                    s.pop();
                }
                s.pop();
            }
        }
        return ans;
	}
}
public class Main
{
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String expression = s.nextLine();
    	System.out.println(Solution.checkRedundantBrackets(expression));
	}
}
