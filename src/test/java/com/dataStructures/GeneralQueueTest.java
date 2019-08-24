package com.dataStructures;

import com.types.Queue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneralQueueTest {

    @Test
    public void testGeneralQueue() {

        Queue<Integer> myQueue = new GeneralQueue<>();
        myQueue.add(10);
        myQueue.add(20);
        myQueue.add(30);
        myQueue.add(40);
        myQueue.add(50);


        Object[] myArray = myQueue.toArray();
        assertEquals(myArray.length, myQueue.size());

        myQueue.remove(20);
        assertEquals(myQueue.size(), 4);

        Boolean isEmpty = myQueue.isEmpty();
        assertEquals(Boolean.valueOf("false"), Boolean.valueOf(isEmpty));

        myQueue.offer(60);
        assertEquals(5, myQueue.size());

        int polledElement = myQueue.poll();
        assertEquals(10, polledElement);

        int element = myQueue.element();
        assertEquals(30, element);

        myQueue.poll();
        int peekedElement = myQueue.peek();
        assertEquals(40, peekedElement);
    }
}
