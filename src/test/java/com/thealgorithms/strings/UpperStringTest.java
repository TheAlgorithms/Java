package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UpperStringTest{
    
	 @Test
	    public void toUpperCase1() {
			//Partition: not have numbers
	        String str1 = "Hello World";
	        assertEquals("HELLO WORLD", Upper.toUpperCase(str1));
	    }

	    @Test
	    public void toUpperCase2() {
	    	//Partition: have numbers
	        String str2 = "tHAnG vIppro 123";
	        assertEquals("THANG VIPPRO 123", Upper.toUpperCase(str2));
	    }
}
