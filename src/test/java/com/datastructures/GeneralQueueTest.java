package com.datastructures;


import com.types.Queue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GeneralQueueTest {

    @Test
    void testGeneralQueue() {

        Queue<Integer> myQueue = new GeneralQueue<>();
        myQueue.add(10);
        myQueue.add(20);
        myQueue.add(30);
        myQueue.add(40);
        myQueue.add(50);


        Object[] myArray = myQueue.toArray();
        Assertions.assertEquals(myArray.length, myQueue.size());

        myQueue.remove(20);
        Assertions.assertEquals(myQueue.size(), 4);

        Boolean isEmpty = myQueue.isEmpty();
        Assertions.assertEquals(Boolean.valueOf("false"), isEmpty);

        myQueue.offer(60);
        Assertions.assertEquals(5, myQueue.size());

        int polledElement = myQueue.poll();
        Assertions.assertEquals(10, polledElement);

        int element = myQueue.element();
        Assertions.assertEquals(30, element);

        myQueue.poll();
        int peekedElement = myQueue.peek();
        Assertions.assertEquals(40, peekedElement);

    }
}
