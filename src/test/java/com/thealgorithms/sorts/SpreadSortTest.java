package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

public class SpreadSortTest extends SortingAlgorithmTest {

    protected int getGeneratedArraySize() {
        return 1000;
    }

    @Override
    SortAlgorithm getSortAlgorithm() {
        return new SpreadSort();
    }

    static class ConstructorArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(org.junit.jupiter.api.extension.ExtensionContext context) {
            return Stream.of(Arguments.of(0, 16, 2, IllegalArgumentException.class), Arguments.of(16, 0, 2, IllegalArgumentException.class), Arguments.of(16, 16, 0, IllegalArgumentException.class), Arguments.of(1001, 16, 2, IllegalArgumentException.class),
                Arguments.of(16, 1001, 2, IllegalArgumentException.class), Arguments.of(16, 16, 101, IllegalArgumentException.class));
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ConstructorArgumentsProvider.class)
    void testConstructor(int insertionSortThreshold, int initialBucketCapacity, int minBuckets, Class<Exception> expectedException) {
        Executable executable = () -> new SpreadSort(insertionSortThreshold, initialBucketCapacity, minBuckets);
        assertThrows(expectedException, executable);
    }
}
