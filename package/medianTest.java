package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class medianTest {
    medianTest() {
    }
    @test
    void testMedian() {
        median median = new median();
        Assertions.assertThat(median.median(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})).isEqualTo(5);
    }