package com.others;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WWPTest {

    @Test
    void testWWP() {
        WWP text = new WWP();
        Assertions.assertEquals("4", text.solveWWP("I'm doing this for fun, what about you?", 10), "Incorrect Conversion");
        Assertions.assertEquals("2", text.solveWWP("This will be in two rows.", 12), "Incorrect Conversion");
        Assertions.assertEquals("4", text.solveWWP("The brown fox and the grey dog...", 9), "Incorrect Conversion");
    }
}
