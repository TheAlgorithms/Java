package com.search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InterpolationSearchTest {

    @Test
    void testInterpolationSearch() {
        InterpolationSearch interpolationSearch = new InterpolationSearch();

        Integer arr[] = {10, 12, 13, 16, 18, 19, 21};
        int x = 18;
        int index = interpolationSearch.findIndex(arr, x);
        Assertions.assertEquals(4, index, "Incorrect index");

        Integer arrTwo[] = {-210, -190, -180, -160, -130, -120, -100};
        x = -190;
        index = interpolationSearch.findIndex(arrTwo, x);
        Assertions.assertEquals(1, index, "Incorrect index");

        String arrString[] = {"10", "12", "13", "16", "22", "25", "29"};
        String stringX = "13";
        index = interpolationSearch.findIndex(arrString, stringX);
        Assertions.assertEquals(2, index, "Incorrect index");
    }
}
