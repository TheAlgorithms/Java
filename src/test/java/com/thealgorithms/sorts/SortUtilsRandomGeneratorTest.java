package com.thealgorithms.sorts;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SortUtilsRandomGeneratorTest {

    @RepeatedTest(1000)
    void generateArray() {
        int size = 1_000;
        Double[] doubles = SortUtilsRandomGenerator.generateArray(size);
        assertThat(doubles).hasSize(size);
        assertThat(doubles).doesNotContainNull();
    }

    @Test
    void generateArrayEmpty() {
        int size = 0;
        Double[] doubles = SortUtilsRandomGenerator.generateArray(size);
        assertThat(doubles).hasSize(size);
    }

    @RepeatedTest(1000)
    void generateDouble() {
        Double randomDouble = SortUtilsRandomGenerator.generateDouble();
        assertThat(randomDouble).isBetween(0.0, 1.0);
        assertThat(randomDouble).isNotEqualTo(1.0);
    }
}
