package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PythagoreanTripleTest {
    @Test
    public void Testtruevalue(){
         //partition to test true value
        assertEquals(true, PythagoreanTriple.isPythagTriple(3,4,5));
    }
    @Test
    public void Testfalsevalue(){
        //partition to test false value
        assertEquals(false, PythagoreanTriple.isPythagTriple(3,4,6));
    }
    @Test
    public void TestminusvalueforA(){
        //partition to test minus value for parameter A
        assertEquals(false, PythagoreanTriple.isPythagTriple(-3,4,5));
    }
    @Test
    public void TestminusvalueforB(){
        //partition to test minus value for parameter B
        assertEquals(false, PythagoreanTriple.isPythagTriple(3,-4,5));   
    }
    @Test
    public void TestminusvalueforC(){
        //partition to test minus value for parameter C
        assertEquals(false, PythagoreanTriple.isPythagTriple(3,4,-5));        
    }
    @Test
    public void Testminusvalue(){
        //partition to test minus value for all parameters
        assertEquals(false, PythagoreanTriple.isPythagTriple(-3,-4,-5));
    }
}
