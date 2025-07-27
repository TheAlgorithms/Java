package com.thealgorithms.others.cn;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidateBinaryHandlerTest {

    private final ValidateBinaryHandler handler = new ValidateBinaryHandler();

    @Test
    public void validBinaryStringsShouldPass() {
    	handler.setNext(new MockHammingHandler("PASSED"));
        Object result = handler.handle("1010", "0110");
        Assertions.assertThat(result).isEqualTo("PASSED");
    }

    @Test
    public void invalidBinaryShouldThrowException() {
        Assertions.assertThatThrownBy(() -> handler.handle("10A0", "0110"))
                  .isInstanceOf(IllegalArgumentException.class)
                  .hasMessageContaining("binary string");
    }
}
