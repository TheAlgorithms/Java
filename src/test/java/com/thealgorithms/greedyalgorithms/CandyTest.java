package com.thealgorithms.greedyalgorithms;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class CandyTest {

    @Test
    public void testCandyWithExampleCase() {
        int arr[] = { 1, 0, 2 };
        // Converting to List<Integer>
        List<Integer> ratings = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) ratings.add(arr[i]);
        assertEquals(5, Candy.candy(ratings));
    }

    @Test
    public void testCandyWithEmptyRatings() {
        List<Integer> ratings = new ArrayList<>();
        assertEquals(0, Candy.candy(ratings));
    }

    @Test
    public void testCandyWithSingleRating() {
        int arr[] = {1};
        // Converting to List<Integer>
        List<Integer> ratings = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) ratings.add(arr[i]);
        assertEquals(1, Candy.candy(ratings));
    }
}
