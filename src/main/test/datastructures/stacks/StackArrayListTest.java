package datastructures.stacks;

import org.junit.Assert;
import org.junit.Test;

public class StackArrayListTest {

    @Test
    public void test() {
        StackList myStackList = new StackList();

        myStackList.push(5);
        myStackList.push(8);
        myStackList.push(2);
        myStackList.push(9);

        Assert.assertFalse(myStackList.isEmpty());
        Assert.assertEquals(9, myStackList.peek());
        Assert.assertEquals(9, myStackList.pop());
        Assert.assertEquals(2, myStackList.peek());
        Assert.assertEquals(2, myStackList.pop());
    }
}