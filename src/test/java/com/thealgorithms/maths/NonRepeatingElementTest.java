package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Syed ali
 */

class NonRepeatingElementTest{
    @Test
    public void testOneAndThree(){
        NonRepeatingElement n = new NonRepeatingElement();
        int a[] = new int{1,3,2,1,3,4,5};
        assertEquals("The two non repeating elements are 1 and 3",n.test(a));
    }
     @Test
    public void testTwoAndNine(){
        NonRepeatingElement n = new NonRepeatingElement();
        int a[] = new int{2,3,9,2,9,4,5};
        assertEquals("The two non repeating elements are 2 and 9",n.test(a));
    }
     @Test
    public void even(){
        NonRepeatingElement n = new NonRepeatingElement();
        int a[] = new int{1,3,2,4,3,4,5};
        assertEquals("enter array with even elements",n.test(a));
    }
}