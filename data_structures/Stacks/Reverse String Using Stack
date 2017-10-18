import java.io.IOException;

public class StringReverserThroughStack {
   private String input; 
   private String output;
   public StringReverserThroughStack(String in) {
      input = in;
   }
   public String doRev() {
      int stackSize = input.length(); 
      Stack theStack = new Stack(stackSize); 
      
      for (int i = 0; i < input.length(); i++) {
         char ch = input.charAt(i); 
         theStack.push(ch); 
      }
      output = "";
      while (!theStack.isEmpty()) {
         char ch = theStack.pop(); 
         output = output + ch; 
      }
      return output;
   }
   public static void main(String[] args) throws IOException {
      String input = "Java Source and Support";
      String output;
      StringReverserThroughStack theReverser = 
      new StringReverserThroughStack(input);
      output = theReverser.doRev();
      System.out.println("Reversed: " + output);
   }
   class Stack {
      private int maxSize;
      private char[] stackArray;
      private int top;
      
      public Stack(int max) {
         maxSize = max;
         stackArray = new char[maxSize];
         top = -1;
      }
      public void push(char j) {
         stackArray[++top] = j;
      }
      public char pop() {
         return stackArray[top--];
      }
      public char peek() {
         return stackArray[top];
      }
      public boolean isEmpty() {
         return (top == -1);
      }
   }
}
