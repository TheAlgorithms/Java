package com.thealgorithms.maths;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TryJUnit {
    @Test
    public static void TestCeil1 {
        assertEquals(Ceil.ceil(1.6f), 2);
    }

    @Test 
    public static void TestCeil2 {
        assertEquals(Ceil.ceil(0.6f), 1)
    }

    @Test 
    public static void TestCeil3 {
        assertEquals(Ceil.ceil(2.6f), 3)
    }
}

