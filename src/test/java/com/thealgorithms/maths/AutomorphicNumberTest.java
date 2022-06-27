package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AutomorphicNumberTest{

    @Test
    void testAutomorphicNumber(){
        assertThat(AutomorphicNumber.isAutomorphic(625)).isTrue();
        assertThat(AutomorphicNumber.isAutomorphic(144)).isFalse();
        assertThat(AutomorphicNumber.isAutomorphic(9376)).isTrue();
        assertThat(AutomorphicNumber.isAutomorphic(169)).isFalse();
    }
}
