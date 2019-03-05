package src.test.java.com.dataStructures;


import org.junit.Assert;
import org.junit.Test;
import src.main.java.com.dataStructures.GeneralQueue;
import src.main.java.com.types.Queue;

public class GeneralQueueTest {

    @Test
    public void testGeneralQueue() {

        Queue<Integer> myQueue = new GeneralQueue<>();
        myQueue.add(10);
        myQueue.add(20);
        myQueue.add(30);
        myQueue.add(40);
        myQueue.add(50);


        Object[] myArray =  myQueue.toArray();
        Assert.assertEquals(myArray.length, myQueue.size());

        myQueue.remove(20);
        Assert.assertEquals(myQueue.size(), 4);

        Boolean isEmpty = myQueue.isEmpty();
        Assert.assertEquals(Boolean.valueOf("false"), Boolean.valueOf(isEmpty));

        myQueue.offer(60);
        Assert.assertEquals(5, myQueue.size());

        int polledElement = myQueue.poll();
        Assert.assertEquals(10, polledElement);

        int element = myQueue.element();
        Assert.assertEquals(30, element);

        myQueue.poll();
        int peekedElement = myQueue.peek();
        Assert.assertEquals(40, peekedElement);

    }
}
