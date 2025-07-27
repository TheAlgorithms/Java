package com.thealgorithms.others.cn;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidateLengthHandlerTest {

    private final ValidateLengthHandler handler = new ValidateLengthHandler();

    @Test
    public void validLengthShouldPass() {
    	handler.setNext(new MockHammingHandler("LENGTH_OK"));
        Object result = handler.handle("1010", "0110");
        Assertions.assertThat(result).isEqualTo("LENGTH_OK");
    }

    @Test
    public void mismatchedLengthShouldThrowException() {
        Assertions.assertThatThrownBy(() -> handler.handle("101", "10"))
                  .isInstanceOf(IllegalArgumentException.class)
                  .hasMessageContaining("same length");
    }
}
