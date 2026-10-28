package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StandardScoreTest {

    @Test
    void test1() {
        Assertions.assertEquals(0.4, StandardScore.zScore(2, 0, 5));
    }

    @Test
    void test2() {
        Assertions.assertEquals(0.0, StandardScore.zScore(1, 1, 1));
    }

    @Test
    void test3() {
        Assertions.assertEquals(1.0, StandardScore.zScore(2.5, 1.8, 0.7));
    }

    @Test
    void test4() {
        Assertions.assertEquals(1.4047619047619049, StandardScore.zScore(8.9, 3, 4.2));
    }
}
