import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class StalinSortTest {
    
    @ParameterizedTest
    @MethodSource("provideArraysForStalinSort")
    void testStalinSort(Integer[] input, Integer[] expected) {
        StalinSort stalinSort = new StalinSort();
        Integer[] sortedArray = stalinSort.sort(input);
        assertArrayEquals(expected, sortedArray);
    }

    private static Stream<Arguments> provideArraysForStalinSort() {
        return Stream.of(arguments(new Integer[] {4}, new Integer[] {4}),
            arguments(new Integer[] {4, 23, 6, 78, 1, 54, 231, 9, 12}, new Integer[] {4, 23, 78, 231}),
            arguments(new Integer[] {5, 5, 5, 5, 5}, new Integer[] {5, 5, 5, 5, 5}),
            arguments(new Integer[] {1, 2, 3, 4, 5}, new Integer[] {1, 2, 3, 4, 5}),
            arguments(new Integer[] {5, 4, 3, 2, 1}, new Integer[] {5}),
            arguments(new String[] {"c", "a", "e", "b", "d"}, new String[] {"c", "e"}),
            arguments(new Integer[] {-1, -2, -3, 4, 5}, new Integer[] {-1, 4, 5}),
            arguments(new Integer[] {0, -1, -2, 1, 2}, new Integer[] {0, 1, 2}),
            arguments(new Integer[] {3, -1, 0, -5, 2}, new Integer[] {3}),
            arguments(new Integer[] {10, 10, 10, 10}, new Integer[] {10, 10, 10, 10}),
            arguments(new Integer[] {1, 2, 2, 3, 3, 3, 4}, new Integer[] {1, 2, 2, 3, 3, 3, 4}),
            arguments(new Integer[] {Integer.MAX_VALUE, Integer.MIN_VALUE, 0}, new Integer[] {Integer.MAX_VALUE}),
            arguments(new Character[] {'d', 'a', 'c', 'b'}, new Character[] {'d'}),
            arguments(new Integer[] {100, 200, 150, 300, 250, 400, 350, 450, 500}, new Integer[] {100, 200, 300, 400, 500}));
    }
}
