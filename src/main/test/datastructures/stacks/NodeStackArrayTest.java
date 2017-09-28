package datastructures.stacks;

import org.junit.Test;

public class NodeStackArrayTest {
    @Test
    public void test() {
        NodeStack<Integer> Stack = new NodeStack<>();

        Stack.push(3);
        Stack.push(4);
        Stack.push(5);
        System.out.println("Testing :");
        Stack.print();            // prints : 5 4 3

        Integer x = Stack.pop();    // x = 5
        Stack.push(1);
        Stack.push(8);
        Integer y = Stack.peek();    // y = 8
        System.out.println("Testing :");
        Stack.print();                // prints : 8 1 4 3

        System.out.println("Testing :");
        System.out.println("x : " + x);
        System.out.println("y : " + y);
    }

}