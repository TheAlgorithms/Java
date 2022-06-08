package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UpperStringTest{
    @Test
    public void toUpperCase1() {
		//Partition for having space 
        String str1 = "sOfTware TEstinG";
        assertEquals("SOFTWARE TESTING", Upper.toUpperCase(str1));
    }

    @Test
    public void toUpperCase2() {
    	//Partition for not having space
        String str2 = "tHAnG";
        assertEquals("THANG", Upper.toUpperCase(str2));
    }
}
