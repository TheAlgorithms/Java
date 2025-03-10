package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StandardScoreTest {

    @Test
    void test1() {
        Assertions.assertEquals(StandardScore.zScore(2, 0, 5), 0.4);
    }

    @Test
    void test2() {
        Assertions.assertEquals(StandardScore.zScore(1, 1, 1), 0.0);
    }

    @Test
    void test3() {
        Assertions.assertEquals(StandardScore.zScore(2.5, 1.8, 0.7), 1.0);
    }

    @Test
    void test4() {
        Assertions.assertEquals(StandardScore.zScore(8.9, 3, 4.2), 1.4047619047619049);
    }
}
