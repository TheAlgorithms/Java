package com.search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JumpSearchTest {

    @Test
    void testJumpSearch() {
        JumpSearch jumpSearch = new JumpSearch();

        Integer arr[] = {11, 15, 16, 29, 36, 40, 42, 52};
        int x = 36;
        int index = jumpSearch.findIndex(arr, x);
        Assertions.assertEquals(4, index, "Incorrect index");

        Integer arrTwo[] = {-210, -190, -180, -160, -130, -120, -100};
        x = -120;
        index = jumpSearch.findIndex(arrTwo, x);
        Assertions.assertEquals(5, index, "Incorrect index");

        String arrString[] = {"101", "122", "136", "165", "225", "251", "291"};
        String stringX = "122";
        index = jumpSearch.findIndex(arrString, stringX);
        Assertions.assertEquals(1, index, "Incorrect index");
    }
}
