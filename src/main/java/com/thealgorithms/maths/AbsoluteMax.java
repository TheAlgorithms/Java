package com.thealgorithms.maths;

import java.util.Arrays;
import static org.junit.Assert.*;  
import com.javatpoint.logic.*;  
import org.junit.Test;  

public class AbsoluteMax {  
 
     @Test  
    public void testFindMax(){  
        assertEquals(4,Calculation.findMax(new int[]{1,3,4,2}));  
        assertEquals(-1,Calculation.findMax(new int[]{-12,-1,-3,-4,-2}));  
    }  
}  
