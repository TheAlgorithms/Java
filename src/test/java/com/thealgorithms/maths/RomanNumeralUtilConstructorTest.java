package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.lang.reflect.Constructor;
import org.junit.jupiter.api.Test;

// Covers the private constructor for code coverage tools
public class RomanNumeralUtilConstructorTest {
    @SuppressFBWarnings("RFI_SET_ACCESSIBLE")
    @Test
    void shouldInvokePrivateConstructor() throws Exception {
        Constructor<RomanNumeralUtil> constructor = RomanNumeralUtil.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        RomanNumeralUtil instance = constructor.newInstance();

        assertInstanceOf(RomanNumeralUtil.class, instance);
    }
}
