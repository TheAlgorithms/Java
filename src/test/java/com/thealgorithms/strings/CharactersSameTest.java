package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CharactersSameTest {

    @ParameterizedTest
    @CsvSource({"aaa, true", "abc, false", "'1  1  1  1', false", "111, true", "'', true", "'           ', true", "'.       ', false", "'a', true", "'  ', true", "'ab', false", "'11111', true", "'ababab', false", "'       ', true", "'+++', true"})
    void testIsAllCharactersSame(String input, boolean expected) {
        assertEquals(CharactersSame.isAllCharactersSame(input), expected);
    }
}
