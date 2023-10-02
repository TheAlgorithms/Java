package com.thealgorithms.stacks;


// Infix to Postfix
import java.util.*;

public class InfixPostfix{
    public static int getPrecedence(char ch){
        if(ch == '^'){
            return 3;
        }
        else if(ch == '*'){
            return 2;
        }
        else if(ch == '/'){
            return 2;
        }
        else if(ch == '+'){
            return 1;
        }
        else if(ch == '-'){
            return 1;
        }
        return 0;
    }

    public static String infixToPostfix(String exp) {
        Stack<Character> s = new Stack<>();
        StringBuilder ans = new StringBuilder();
        s.push('(');

        for(int i = 0; i<exp.length();i++){
            char ch = exp.charAt(i);

            if(Character.isAlphabetic(ch) || Character.isDigit(ch)){
                ans.append(ch);
            }

            else if(ch=='('){
                s.push(ch);
            }

            else if(ch == ')'){
                while(!s.isEmpty() && s.peek()!='('){
                    ans.append(s.peek());
                    s.pop();
                }
                s.pop();
            }

            else{
                int x = getPrecedence(ch);
                    while(!s.isEmpty()){
                        if(getPrecedence(s.peek())>=x){
                        ans.append(s.peek());
                        s.pop();
                        }
                        else{
                            break;
                        }
                    }
                    s.push(ch);
            }
        }

        if(!s.isEmpty()){
            while(s.peek()!='('){
            ans.append(s.peek());
            s.pop();
          }
        }
        return ans.toString();
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        System.out.println(infixToPostfix(str));

        sc.close();
    }
}
