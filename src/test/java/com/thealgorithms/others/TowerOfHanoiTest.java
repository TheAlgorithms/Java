package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TowerOfHanoiTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testShiftWithNullDiscs() {
        TowerOfHanoi.shift(1, null, null, null);
        String expectedOutput = "Move 1 from null to null\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    public void testShiftWithNegativeMove() {
        assertThrows(StackOverflowError.class, () -> { TowerOfHanoi.shift(-1, "A", "B", "C"); });
    }

    @Test
    public void testShiftWithThreeDiscs() {
        TowerOfHanoi.shift(3, "A", "B", "C");
        String expectedOutput = "Move 1 from A to C\n"
            + "Move 2 from A to B\n"
            + "Move 1 from C to B\n"
            + "Move 3 from A to C\n"
            + "Move 1 from B to A\n"
            + "Move 2 from B to C\n"
            + "Move 1 from A to C\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
}
