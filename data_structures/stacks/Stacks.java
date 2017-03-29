package data_structures.stacks;

//Example
public class Stacks {
    public static void main(String args[]) {
        Stack myStack = new Stack(4); //Declare a stack of maximum size 4
        //Populate the stack
        myStack.push(5);
        myStack.push(8);
        myStack.push(2);
        myStack.push(9);

        System.out.println("*********************Stack Array Implementation*********************");
        System.out.println(myStack.isEmpty()); //will print false
        System.out.println(myStack.isFull()); //will print true
        System.out.println(myStack.peek()); //will print 9
        System.out.println(myStack.pop()); //will print 9
        System.out.println(myStack.peek()); // will print 2

        Stack2 myStack2 = new Stack2(); //Declare a stack of maximum size 4
        //Populate the stack
        myStack2.push(5);
        myStack2.push(8);
        myStack2.push(2);
        myStack2.push(9);

        System.out.println("*********************Stack List Implementation*********************");
        System.out.println(myStack2.isEmpty()); //will print false
        System.out.println(myStack2.peek()); //will print 9
        System.out.println(myStack2.pop()); //will print 9
        System.out.println(myStack2.peek()); // will print 2
        System.out.println(myStack2.pop()); //will print 2
    }
}
