package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.thealgorithms.maths.ComplexNumberUtil.ComplexNumber;
import org.junit.jupiter.api.Test;

public class ComplexNumberUtilTest {

    private boolean checkIfEqual(ComplexNumber expected, ComplexNumber actual) {
        final double LIM = 0.0001;
        if (Math.abs(actual.REAL - expected.REAL) > LIM || Math.abs(actual.IMAGINARY - expected.IMAGINARY) > LIM) {
            fail("Expected " + expected + " but got " + actual);
        }

        return true;
    }

    @Test
    public void testComplexNumberConstructor() {
        ComplexNumber c = new ComplexNumber(3.0, 4.0);
        assertEquals(3.0, c.REAL);
        assertEquals(4.0, c.IMAGINARY);
    }

    @Test
    public void testEquals1() {
        ComplexNumber c1 = new ComplexNumber(1, 2);
        ComplexNumber c2 = new ComplexNumber(1, 2);
        ComplexNumber c3 = new ComplexNumber(2, 1);

        assertTrue(checkIfEqual(c1, c2));
        assertNotEquals(c1, c3);
    }

    @Test
    public void testEquals2() {
        ComplexNumber c1 = new ComplexNumber(1, 2);
        assertNotEquals(c1, 2);
    }

    @Test
    public void testToString() {
        ComplexNumber c = new ComplexNumber(3, -2);
        assertEquals("3.0 - 2.0i", c.toString());
    }

    @Test
    public void testAdd() {
        ComplexNumber c1 = new ComplexNumber(1, 1);
        ComplexNumber c2 = new ComplexNumber(2, 3);
        ComplexNumber result = ComplexNumberUtil.add(c1, c2);
        checkIfEqual(new ComplexNumber(3, 4), result);
    }

    @Test
    public void testSubtract() {
        ComplexNumber c1 = new ComplexNumber(5, 5);
        ComplexNumber c2 = new ComplexNumber(3, 2);
        ComplexNumber result = ComplexNumberUtil.subtract(c1, c2);
        checkIfEqual(new ComplexNumber(2, 3), result);
    }

    @Test
    public void testMultiply() {
        ComplexNumber c1 = new ComplexNumber(1, 1);
        ComplexNumber c2 = new ComplexNumber(2, 3);
        ComplexNumber result = ComplexNumberUtil.multiply(c1, c2);
        checkIfEqual(new ComplexNumber(-1, 5), result);
    }

    @Test
    public void testDivide() {
        ComplexNumber c1 = new ComplexNumber(1, 1);
        ComplexNumber c2 = new ComplexNumber(2, 3);
        ComplexNumber result = ComplexNumberUtil.divide(c1, c2);
        checkIfEqual(new ComplexNumber(0.38461538, -0.07692307), result);
    }

    @Test
    public void testDivideByZero() {
        ComplexNumber c1 = new ComplexNumber(1, 1);
        ComplexNumber c2 = new ComplexNumber(0, 0);
        assertThrows(RuntimeException.class, () -> ComplexNumberUtil.divide(c1, c2));
    }

    @Test
    public void testAbs() {
        ComplexNumber c = new ComplexNumber(3, 4);
        double result = ComplexNumberUtil.abs(c);
        assertEquals(5.0, result, 0.001);
    }

    @Test
    public void testExp() {
        ComplexNumber c = new ComplexNumber(1, Math.PI);
        ComplexNumber result = ComplexNumberUtil.exp(c);
        checkIfEqual(new ComplexNumber(-2.718281828459045, 0), result);
    }

    @Test
    public void testLn() {
        ComplexNumber c = new ComplexNumber(5, 5);
        ComplexNumber result = ComplexNumberUtil.ln(c);
        checkIfEqual(new ComplexNumber(Math.log(5 * Math.sqrt(2)), Math.PI / 4), result);
    }

    @Test
    public void testLnOfZero() {
        assertThrows(RuntimeException.class, () -> ComplexNumberUtil.ln(ComplexNumberUtil.ZERO));
    }

    @Test
    public void testPow1() {
        ComplexNumber c1 = new ComplexNumber(0, 0);
        ComplexNumber c2 = new ComplexNumber(0, 0);
        ComplexNumber result = ComplexNumberUtil.pow(c1, c2);
        checkIfEqual(ComplexNumberUtil.ONE, result);
    }

    @Test
    public void testPow2() {
        ComplexNumber c1 = new ComplexNumber(-3, 4);
        ComplexNumber c2 = new ComplexNumber(2, -5);
        ComplexNumber result = ComplexNumberUtil.pow(c1, c2);
        ComplexNumber expected = new ComplexNumber(-1428309.3755404, 738159.21728509);
        checkIfEqual(expected, result);
    }

    @Test
    public void testSqrt() {
        ComplexNumber c = new ComplexNumber(-2, 3);
        ComplexNumber result = ComplexNumberUtil.sqrt(c);
        checkIfEqual(new ComplexNumber(0.8959774, 1.6741492), result);
    }

    @Test
    public void testSin() {
        ComplexNumber c = new ComplexNumber(1, 1);
        ComplexNumber result = ComplexNumberUtil.sin(c);
        ComplexNumber expected = new ComplexNumber(1.2984575814159773, 0.6349639147847361);
        checkIfEqual(expected, result);
    }

    @Test
    public void testCos() {
        ComplexNumber c = new ComplexNumber(1, 1);
        ComplexNumber result = ComplexNumberUtil.cos(c);
        ComplexNumber expected = new ComplexNumber(0.8337300251311491, -0.9888977057628651);
        checkIfEqual(expected, result);
    }

    @Test
    public void testTan() {
        ComplexNumber c = new ComplexNumber(1, 1);
        ComplexNumber result = ComplexNumberUtil.tan(c);
        ComplexNumber expected = new ComplexNumber(0.2717525853195117, 1.0839233273386948);
        checkIfEqual(expected, result);
    }

    @Test
    public void testTanOutOfRange() {
        assertThrows(RuntimeException.class, () -> ComplexNumberUtil.tan(new ComplexNumber(Math.PI * 2.5, 0)));
    }

    @Test
    public void testCot() {
        ComplexNumber c = new ComplexNumber(1, 1);
        ComplexNumber result = ComplexNumberUtil.cot(c);
        ComplexNumber expected = new ComplexNumber(0.21762156185440268, -0.8680141428959249);
        checkIfEqual(expected, result);
    }

    @Test
    public void testCotOutOfRange() {
        assertThrows(RuntimeException.class, () -> ComplexNumberUtil.cot(new ComplexNumber(Math.PI * 3, 0)));
    }

    @Test
    public void testArcsin() {
        ComplexNumber c = new ComplexNumber(0.5, 0);
        ComplexNumber result = ComplexNumberUtil.arcsin(c);
        ComplexNumber expected = new ComplexNumber(0.5235987755982989, 0);
        checkIfEqual(expected, result);
    }

    @Test
    public void testArccos() {
        ComplexNumber c = new ComplexNumber(0.5, 0);
        ComplexNumber result = ComplexNumberUtil.arccos(c);
        ComplexNumber expected = new ComplexNumber(1.0471975511965979, 0);
        checkIfEqual(expected, result);
    }

    @Test
    public void testArctan() {
        ComplexNumber c = new ComplexNumber(1, 1);
        ComplexNumber result = ComplexNumberUtil.arctan(c);
        ComplexNumber expected = new ComplexNumber(1.0172219678978514, 0.40235947810852507);
        checkIfEqual(expected, result);
    }

    @Test
    public void testArctanOfI() {
        assertThrows(RuntimeException.class, () -> ComplexNumberUtil.arctan(ComplexNumberUtil.PLUS_I));
    }

    @Test
    public void testArccot() {
        ComplexNumber c = new ComplexNumber(1, 1);
        ComplexNumber result = ComplexNumberUtil.arccot(c);
        ComplexNumber expected = new ComplexNumber(0.5535743588970452, -0.40235947810852507);
        checkIfEqual(expected, result);
    }

    @Test
    public void testArccotOfI() {
        assertThrows(RuntimeException.class, () -> ComplexNumberUtil.arccot(ComplexNumberUtil.PLUS_I));
    }
}
