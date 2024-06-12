package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LetterCombinationsOfPhoneNumberTest {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    public void testLetterCombinationsOfPhoneNumber(int[] numbers, List<String> expectedOutput) {
        assertEquals(expectedOutput, LetterCombinationsOfPhoneNumber.getCombinations(numbers));
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                // Test case 1:
                Arguments.of(new int[]{}, List.of("")),

                // Test case 2:
                Arguments.of(new int[]{2}, Arrays.asList("a", "b", "c")),

                // Test case 3:
                Arguments.of(new int[]{2, 3}, Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf")),

                // Test case 4:
                Arguments.of(new int[]{2, 3, 4}, Arrays.asList("adg", "adh", "adi", "aeg", "aeh", "aei", "afg", "afh", "afi",
                        "bdg", "bdh", "bdi", "beg", "beh", "bei", "bfg", "bfh", "bfi",
                        "cdg", "cdh", "cdi", "ceg", "ceh", "cei", "cfg", "cfh", "cfi"))
        );
    }
}
