package com.dataStructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QueuesInsertTest {
    @Test
    public void testInsertQueues(){
        PriorityQueue myQueue = new PriorityQueue(4);
        myQueue.insert(10);
        Assertions.assertEquals(myQueue.toString().trim(), "10 0 0 0");
        myQueue.insert(2);
        myQueue.insert(5);
        myQueue.insert(3);
        Assertions.assertEquals(myQueue.toString().trim(), "2 3 5 10");
        try{
            myQueue.insert(2);
        }
        catch (Exception e){
            Assertions.assertEquals(e.getMessage(), "Queue is full");
        }
    }
}
