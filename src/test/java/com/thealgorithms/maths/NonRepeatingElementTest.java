package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Syed ali
 */

class NonRepeatingElementTest{
    @Test
    public void test(){
        NonRepeatingElement n = new NonRepeatingElement();
        int a[] = new int{1,3,2,1,3,4,5};
        assertEquals("The two non repeating elements are 1 and 3",n.test(a));
    }
}