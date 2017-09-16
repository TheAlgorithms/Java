package datastructures.stacks;

import org.junit.Assert;

class StackTest {
    public static void main(String args[]) {
        StackArray myStackArray = new StackArray(4); //Declare a stack of maximum size 4

        myStackArray.push(5);
        myStackArray.push(8);
        myStackArray.push(2);
        myStackArray.push(9);

        Assert.assertFalse(myStackArray.isEmpty());
        Assert.assertEquals(9, myStackArray.peek());
        Assert.assertEquals(9, myStackArray.pop());
        Assert.assertEquals(2, myStackArray.peek());
        Assert.assertEquals(2, myStackArray.pop());
    }
}