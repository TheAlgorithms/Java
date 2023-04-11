package com.thealgorithms.others;
import static org.junit.jupiter.api.Assertions.*;
import com.thealgorithms.others.LineSweep;
import org.junit.jupiter.api.Test;
public class LineSweepTest {


    @Test
    void testForOverlap(){
        int[][]arr = {{0,10},{7,20},{15,24}};
        assertTrue(LineSweep.isOverlap(arr,24));
    }

    @Test
    void testForNoOverlap(){
        int[][]arr = {{0,10},{11,20},{21,24}};
        assertFalse(LineSweep.isOverlap(arr,24));
    }

}
