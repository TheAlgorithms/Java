/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conversions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnyBaseToAnyBaseTest {
    AnyBaseToAnyBase anyBaseToAnyBase = new AnyBaseToAnyBase();
    @Test
    public void testDUPath1() {
        String expResult = "2";
        String result = anyBaseToAnyBase.base2base("2", 1, 3);       
        assertEquals(expResult, result);
    }
    @Test
    public void testDUPath2() {
        String expResult = "22";
        String result = anyBaseToAnyBase.base2base("2222", -2147483648, 10);
        assertEquals(expResult, result);
    }
    @Test
    public void testDUPath3() {
        String expResult = "10";
        String result = anyBaseToAnyBase.base2base("3", 1, 3);
        assertEquals(expResult, result);
    }
    @Test
    public void testDUPath4() {
        String expResult = "A";
        String result = anyBaseToAnyBase.base2base("1111111111", 1, 35);
        assertEquals(expResult, result);
    }
}