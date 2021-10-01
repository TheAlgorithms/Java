package DataStructures.Stacks;

import java.util.Stack;

public class InfixToPrefix {
  public static void main(String[] args) throws Exception {
    assert "*+12+34".equals(infix2PreFix("(1+2)*(3+4)"));
    assert "+1/*234".equals(infix2PreFix("1+((2*3)/4)"));
    assert "*1^23".equals(infix2PreFix("1*(2^3)"));
  }

  public static String infix2PreFix(String infixExpression) throws Exception {
    if (!BalancedBrackets.isBalanced(infixExpression)) {
      throw new Exception("invalid expression");
    }
    infixExpression.reverse();
    for(char element : infixExpression.toCharArray()){
        if(element == ')')element='(';
        else if(element == '(')element=')';
    }
    StringBuilder output = new StringBuilder();
    Stack<Character> stack = new Stack<>();
    for (char element : infixExpression.toCharArray()) {
      if (Character.isLetterOrDigit(element)) {
        output.append(element);
      } else if (element == '(') {
        stack.push(element);
      } else if (element == ')') {
        while (!stack.isEmpty() && stack.peek() != '(') {
          output.append(stack.pop());
        }
        stack.pop();
      } else {
        while (!stack.isEmpty() && precedence(element) <= precedence(stack.peek())) {
          output.append(stack.pop());
        }
        stack.push(element);
      }
    }
    while (!stack.isEmpty()) {
      output.append(stack.pop());
    }
    return output.toString();
  }

  private static int precedence(char operator) {
    switch (operator) {
      case '+':
      case '-':
        return 0;
      case '*':
      case '/':
        return 1;
      case '^':
        return 2;
      default:
        return -1;
    }
  }
}
