package com.thealgorithms.others.cn;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ComputeHammingHandlerTest {

    private final ComputeHammingHandler handler = new ComputeHammingHandler();

    @Test
    public void testHammingDistanceComputation() {
        Object result = handler.handle("1101", "1001");
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    public void identicalStringsShouldReturnZero() {
        Object result = handler.handle("1111", "1111");
        Assertions.assertThat(result).isEqualTo(0);
    }
}
