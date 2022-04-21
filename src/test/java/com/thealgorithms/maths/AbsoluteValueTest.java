package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbsoluteValueTest {

    @Test
    void testGetAbsValue() {
        Stream.generate(() -> ThreadLocalRandom.current().nextInt())
                .limit(1000)
                .forEach(number -> assertEquals(Math.abs(number), AbsoluteValue.getAbsValue(number)));
    }
}
