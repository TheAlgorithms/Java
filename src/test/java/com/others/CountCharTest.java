package com.others;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CountCharTest {

    @Test
    void testCountChar(){
        CountChar counter = new CountChar();
        Assertions.assertEquals(0, counter.countCharacters(""), "Incorrect");
        Assertions.assertEquals(11, counter.countCharacters("coincidence"), "Incorrect");
        Assertions.assertEquals(8, counter.countCharacters("brownish"), "Incorrect");
        Assertions.assertEquals(34, counter.countCharacters("thisisgoingtobeanillegallylongword"), "Incorrect");
    }
}
