package com.thealgorithms.maths;

import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Albina Gimaletdinova on 25/07/2023
 */
public class FibonacciJavaStreamsTest {
    private static final String EXCEPTION_MESSAGE = "Input index cannot be null or negative!";

    @Test
    public void testWithNegativeIndexShouldThrowException() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> FibonacciJavaStreams.calculate(new BigDecimal(-1)));
        Assertions.assertEquals(EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    public void testCheckTheFirst4SequenceElements() {
        checkElement(BigDecimal.ZERO, BigDecimal.ZERO);
        checkElement(BigDecimal.ONE, BigDecimal.ONE);
        checkElement(BigDecimal.TWO, BigDecimal.ONE);
        checkElement(new BigDecimal(3), BigDecimal.TWO);
    }

    @Test
    public void testCheck10thSequenceElement() {
        checkElement(BigDecimal.TEN, new BigDecimal(55));
    }

    @Test
    public void testCheck20thSequenceElement() {
        checkElement(new BigDecimal(20), new BigDecimal(6765));
    }

    @Test
    public void testCheck30thSequenceElement() {
        checkElement(new BigDecimal(30), new BigDecimal(832040));
    }

    @Test
    public void testCheck40thSequenceElement() {
        checkElement(new BigDecimal(40), new BigDecimal(102334155));
    }

    @Test
    public void testCheck50thSequenceElement() {
        checkElement(new BigDecimal(50), new BigDecimal(12586269025L));
    }

    @Test
    public void testCheck100thSequenceElement() {
        checkElement(new BigDecimal(100), new BigDecimal("354224848179261915075"));
    }

    @Test
    public void testCheck200thSequenceElement() {
        checkElement(new BigDecimal(200), new BigDecimal("280571172992510140037611932413038677189525"));
    }

    private static void checkElement(BigDecimal index, BigDecimal expected) {
        // when
        Optional<BigDecimal> result = FibonacciJavaStreams.calculate(index);

        // then
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(result.get(), expected);
    }
}
