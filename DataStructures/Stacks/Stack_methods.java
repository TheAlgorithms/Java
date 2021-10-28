import java.util.Stack;
import java.util.stack;
public class stack_demo {
    public static void main(String [] args){

        Stack s=new Stack();
        s.add("Microsoft"); //Method of List
        s.addElement("Apple"); //Method of Vector
        s.push("Google");  //Method of Stack 
        s.push("Amazon");

        System.out.println(s);  //[Microsoft, Apple, Google, Amazon]
        //Methods of Stack:
        s.pop();  //Last In First Out
        System.out.println(s);    //[Microsoft, Apple, Google]

        System.out.println(s.peek());    //[Microsoft, Apple, Google]
    
        System.out.println(s.search("Microsoft")); 
      
        System.out.println(s.empty());    //false

    }
}
