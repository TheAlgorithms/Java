package com.others;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FindHCFTest {

    @Test
    void of() {
        Assertions.assertEquals(2, FindHCF.Of(new int[]{ 16, 18, 20 }), "Correct Highest Common Factor");
        Assertions.assertEquals(15, FindHCF.Of(new int[]{ 30, 45 }), "Correct Highest Common Factor");
    }

    @Test
    void testOf() {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(3);
        numbers.add(9);
        numbers.add(21);
        Assertions.assertEquals(3, FindHCF.Of(numbers), "Correct HCF");
    }
}