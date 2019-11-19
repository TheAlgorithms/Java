package com.others;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FindLCMTest {

    @Test
    void of() {
        Assertions.assertEquals(720, FindLCM.Of(new int[]{ 16, 18, 20 }), "Correct LCM");
        Assertions.assertEquals(90, FindLCM.Of(new int[]{ 30, 45 }), "Correct LCM");
    }

    @Test
    void testOf() {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(3);
        numbers.add(9);
        numbers.add(21);
        Assertions.assertEquals(63, FindLCM.Of(numbers), "Correct LCM");
    }
}