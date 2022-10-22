/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.thealgorithms.datastructures.heaps;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ilya
 */
public class MinPriorityQueueTest {
    
    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    
    /**
     * Main test.
     */
    @Test
    public void testMain() {
        System.setOut(new PrintStream(output));
        
        MinPriorityQueue q = new MinPriorityQueue(8);
        q.insert(5);
        q.insert(2);
        q.insert(4);
        q.insert(1);
        q.insert(7);
        q.insert(6);
        q.insert(3);
        q.insert(8);
        q.print();
        Assertions.assertEquals("1 2 3 5 7 6 4 8 \n", output.toString());
        Assertions.assertEquals(1, q.delete());
        
        q.heapSort();
        
        output.reset();
        q.print();
        Assertions.assertEquals("8 7 6 5 4 3 2 1 \n", output.toString());
        
        Assertions.assertEquals(true, q.isEmpty());
        
        System.setOut(null);
    }
    
}
