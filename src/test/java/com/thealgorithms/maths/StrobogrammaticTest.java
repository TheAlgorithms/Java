package com.thealgorithms.maths;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class StrobogrammaticNumberTest {

    @Test
    void testIsStrobogrammatic() {
        assertThat(StrobogrammaticNumber.isStrobogrammatic("69")).isTrue();
        assertThat(StrobogrammaticNumber.isStrobogrammatic("88")).isTrue();
        assertThat(StrobogrammaticNumber.isStrobogrammatic("818")).isTrue();
        assertThat(StrobogrammaticNumber.isStrobogrammatic("101")).isTrue();
        assertThat(StrobogrammaticNumber.isStrobogrammatic("609")).isFalse();
        assertThat(StrobogrammaticNumber.isStrobogrammatic("120")).isFalse();
    }
}
