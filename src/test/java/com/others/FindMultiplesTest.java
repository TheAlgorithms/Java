package com.others;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class FindMultiplesTest {

    @Test
    void of() {
        ArrayList<Integer> multiples = new ArrayList<>(); // multiples of 56 [ 2 x 2 x 2 x 7 ]
        multiples.add(2);
        multiples.add(2);
        multiples.add(2);
        multiples.add(7);

        Assertions.assertIterableEquals(multiples, FindMultiples.Of(56), "Correct multiples");
    }
}