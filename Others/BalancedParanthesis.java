import java.util.*; 
  
public class BalancedParanthesis { 
  
    // function to check if paranthesis are balanced 
    static boolean isBalanced(String expression) 
    { 
        // Using ArrayDeque 
        Deque<Character> stack = new ArrayDeque<Character>(); 
  
       
        for (int i = 0; i < expr.length(); i++) { 
            char x = expression.charAt(i); 
  
            if (x == '(' || x == '[' || x == '{') { 
                // Push the element in the stack 
                stack.push(x); 
                continue; 
            } 
  
            // if current current character is not opening bracket, then it must be closing. 
            // stack cannot be empty at this point. 
            if (stack.isEmpty()) 
                return false; 
  
            switch (x) { 
            case ')': 
                stack.pop(); 
                if (x == '{' || x == '[') 
                    return false; 
                break; 
  
            case '}': 
                stack.pop(); 
                if (x == '(' || x == '[') 
                    return false; 
                break; 
  
            case ']': 
                stack.pop(); 
                if (x == '(' || x == '{') 
                    return false; 
                break; 
            } 
        } 
  
        // Check Empty Stack 
        return (stack.isEmpty()); 
    } 
  
    public static void main(String[] args) 
    { 
        String expr = "([{}])"; 
        if (isBalanced(expr)) 
            System.out.println("Paranthesis are Balanced "); 
        else
            System.out.println("Paranthesis are Not Balanced "); 
    } 
} 