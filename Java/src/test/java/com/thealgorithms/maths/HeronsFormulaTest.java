package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HeronsFormulaTest {

    @Test
    void test1() {
        Assertions.assertEquals(HeronsFormula.herons(3, 4, 5), 6.0);
    }

    @Test
    void test2() {
        Assertions.assertEquals(HeronsFormula.herons(24, 30, 18), 216.0);
    }

    @Test
    void test3() {
        Assertions.assertEquals(HeronsFormula.herons(1, 1, 1), 0.4330127018922193);
    }

    @Test
    void test4() {
        Assertions.assertEquals(HeronsFormula.herons(4, 5, 8), 8.181534085976786);
    }

    @Test
    public void testCalculateAreaWithInvalidInput() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> { HeronsFormula.herons(1, 2, 3); });
        Assertions.assertThrows(IllegalArgumentException.class, () -> { HeronsFormula.herons(2, 1, 3); });
        Assertions.assertThrows(IllegalArgumentException.class, () -> { HeronsFormula.herons(3, 2, 1); });
        Assertions.assertThrows(IllegalArgumentException.class, () -> { HeronsFormula.herons(1, 3, 2); });

        Assertions.assertThrows(IllegalArgumentException.class, () -> { HeronsFormula.herons(1, 1, 0); });
        Assertions.assertThrows(IllegalArgumentException.class, () -> { HeronsFormula.herons(1, 0, 1); });
        Assertions.assertThrows(IllegalArgumentException.class, () -> { HeronsFormula.herons(0, 1, 1); });
    }
}
