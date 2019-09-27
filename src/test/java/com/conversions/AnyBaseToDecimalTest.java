package com.conversions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AnyBaseToDecimalTest {

    @Test
    void testAnyBaseToDecimal() {
        AnyBaseToDecimal anyBaseToDecimal = new AnyBaseToDecimal();
        Assertions.assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal("2", 2));
        Assertions.assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal("3", 2));
        Assertions.assertEquals("3", anyBaseToDecimal.convertToDecimal("11", 2));
        Assertions.assertEquals("4", anyBaseToDecimal.convertToDecimal("100", 2));
        Assertions.assertEquals("5", anyBaseToDecimal.convertToDecimal("101", 2));
        Assertions.assertEquals("10", anyBaseToDecimal.convertToDecimal("1010", 2));
        Assertions.assertEquals("1024", anyBaseToDecimal.convertToDecimal("10000000000", 2));

        Assertions.assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal("8", 8));
        Assertions.assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal("9", 8));
        Assertions.assertEquals("7", anyBaseToDecimal.convertToDecimal("7", 8));
        Assertions.assertEquals("8", anyBaseToDecimal.convertToDecimal("10", 8));
        Assertions.assertEquals("9", anyBaseToDecimal.convertToDecimal("11", 8));
        Assertions.assertEquals("10", anyBaseToDecimal.convertToDecimal("12", 8));
        Assertions.assertEquals("1024", anyBaseToDecimal.convertToDecimal("2000", 8));

        Assertions.assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal("A", 10));
        Assertions.assertEquals("10", anyBaseToDecimal.convertToDecimal("10", 10));
        Assertions.assertEquals("1024", anyBaseToDecimal.convertToDecimal("1024", 10));

        Assertions.assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal("G", 16));
        Assertions.assertEquals("16", anyBaseToDecimal.convertToDecimal("10", 16));
        Assertions.assertEquals("17", anyBaseToDecimal.convertToDecimal("11", 16));
        Assertions.assertEquals("100", anyBaseToDecimal.convertToDecimal("64", 16));
        Assertions.assertEquals("225", anyBaseToDecimal.convertToDecimal("E1", 16));
        Assertions.assertEquals("1024", anyBaseToDecimal.convertToDecimal("400", 16));

    }

    @Test
    void testConvertToDecimal2InpNum1(){
        AnyBaseToDecimal anyBaseToDecimal = new AnyBaseToDecimal();
        Assertions.assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal2("2", 2));
    }

    @Test
    void testConvertToDecimal2Base1() {
        AnyBaseToDecimal anyBaseToDecimal = new AnyBaseToDecimal();
        Assertions.assertEquals("7", anyBaseToDecimal.convertToDecimal2("7", 8));
    }

    @Test
    void testConvertToDecimal2Base2() {
        AnyBaseToDecimal anyBaseToDecimal = new AnyBaseToDecimal();
        Assertions.assertEquals("12", anyBaseToDecimal.convertToDecimal2("C", 16));
    }

    @Test
    void testConvertToDecimal2Base3() {
        AnyBaseToDecimal anyBaseToDecimal = new AnyBaseToDecimal();
        Assertions.assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal2("C", 10));
    }

    @Test
    void testConvertToDecimal2Base4() {
        AnyBaseToDecimal anyBaseToDecimal = new AnyBaseToDecimal();
        Assertions.assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal2("7", 7));
    }

    @Test
    void testConvertToDecimal2Len() {
        AnyBaseToDecimal anyBaseToDecimal = new AnyBaseToDecimal();
        Assertions.assertEquals("0", anyBaseToDecimal.convertToDecimal2("", 7));
    }

    @Test
    void testConvertToDecimal2Num1() {
        AnyBaseToDecimal anyBaseToDecimal = new AnyBaseToDecimal();
        Assertions.assertEquals("16", anyBaseToDecimal.convertToDecimal2("10", 16));
    }

    @Test
    void testConvertToDecimal2Num2() {
        AnyBaseToDecimal anyBaseToDecimal = new AnyBaseToDecimal();
        Assertions.assertEquals("225", anyBaseToDecimal.convertToDecimal2("E1", 16));
    }

    @Test
    void testConvertToDecimal2Pow1() {
        AnyBaseToDecimal anyBaseToDecimal = new AnyBaseToDecimal();
        Assertions.assertEquals("7", anyBaseToDecimal.convertToDecimal2("7", 8));
    }

    @Test
    void testConvertToDecimal2Pow2() {
        AnyBaseToDecimal anyBaseToDecimal = new AnyBaseToDecimal();
        Assertions.assertEquals("10", anyBaseToDecimal.convertToDecimal2("A", 16));
    }

    @Test
    void testConvertToDecimal2ValOfChar1() {
        AnyBaseToDecimal anyBaseToDecimal = new AnyBaseToDecimal();
        Assertions.assertEquals("7", anyBaseToDecimal.convertToDecimal2("7", 8));
    }

    @Test
    void testConvertToDecimal2ValOfChar2() {
        AnyBaseToDecimal anyBaseToDecimal = new AnyBaseToDecimal();
        Assertions.assertEquals("10", anyBaseToDecimal.convertToDecimal2("A", 16));
    }

    @Test
    void testConvertToDecimal2C1() {
        AnyBaseToDecimal anyBaseToDecimal = new AnyBaseToDecimal();
        Assertions.assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal2("G", 16));
    }

    @Test
    void testConvertToDecimal2C2() {
        AnyBaseToDecimal anyBaseToDecimal = new AnyBaseToDecimal();
        Assertions.assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal2("2", 2));
    }
}
