/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btvn_;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tuananh
 */
public class base2base_Test {

    @Test
    public void test1() {
        String expResult = "2";
        String result = BTVN_.base2base("2", -2147483647, 6);       
        assertEquals(expResult, result);
    }
    @Test
    public void test2() {
        String expResult = "2";
        String result = BTVN_.base2base("2", -2147483648, 10);
        assertEquals(expResult, result);
    }
    @Test
    public void test3() {
        String expResult = "2";
        String result = BTVN_.base2base("2", 2147483647, 10);
        assertEquals(expResult, result);
    }
    @Test
    public void test4() {
        String expResult = "";
        String result = BTVN_.base2base("", 2, 5);
        assertEquals(expResult, result);
    }
    @Test
    public void test5() {
        String expResult = "";
        String result = BTVN_.base2base("", -2147483648, 2147483647);
        assertEquals(expResult, result);
    }
    @Test
    public void test6() {
        String expResult = "2";
        String result = BTVN_.base2base("6", -2147483648, 2147483647);
        assertEquals(expResult, result);
    }
}
