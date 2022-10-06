package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class AbsoluteValueTest {

    @Test
    void testGetAbsValue() {
        Stream
            .generate(() -> ThreadLocalRandom.current().nextInt())
            .limit(1000)
            .forEach(number ->
                assertEquals(
                    Math.abs(number),
                    AbsoluteValue.getAbsValue(number)
                )
            );
    }
}
