package com.others;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class MaximizeDistancetoClosestPersonTest {
    @Test
    void testMaxmizeDistancetoClosestPerson1() {
        int[] input = new int[] {1,0,1,0,1};
        Assertions.assertEquals(1,MaximizeDistancetoClosestPerson.maxDistToClosest(input));
    }

    @Test
    void testMaxmizeDistancetoClosestPerson3() {
        int[] input = new int[] {0,0,0,0,1,0,1};
        Assertions.assertEquals(4,MaximizeDistancetoClosestPerson.maxDistToClosest(input));
    }

    @Test
    void testMaxmizeDistancetoClosestPerson4() {
        int[] input = new int[] {1,0,0,0};
        Assertions.assertEquals(3,MaximizeDistancetoClosestPerson.maxDistToClosest(input));
    }

    @Test
    void testMaxmizeDistancetoClosestPerson5() {
        int[] input = new int[] {1,1,1,0};
        Assertions.assertEquals(1,MaximizeDistancetoClosestPerson.maxDistToClosest(input));
    }
}