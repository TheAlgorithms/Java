package com.others;

import static org.junit.Assert.*;
import org.junit.Test;

public class AbecedarianTest {
     @Test
     public void test(){
     Abecedarian abecedarian=new Abecedarian();
     assertEquals(true,abecedarian.isAbecedarian("Almost"));
     assertEquals(true,abecedarian.isAbecedarian("first"));
     assertEquals(true,abecedarian.isAbecedarian("FortY"));

     assertEquals(false,abecedarian.isAbecedarian("Plan"));
     assertEquals(false,abecedarian.isAbecedarian("Laptop"));
     assertEquals(false,abecedarian.isAbecedarian("Sixty"));


     }
}
