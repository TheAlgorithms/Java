/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import Others.GCD;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Admin
 */
public class TestGCD_DataFlow {
    @Test
    public void testGCD_testCase1(){
        int x = 11;
        int y = 33;
        int result = GCD.gcd(x, y);
        Assert.assertEquals(11, result);
    }
    
    @Test
    public void testGCD_testCase2(){
        int x = 24;
        int y = 30;
        int result = GCD.gcd(x, y);
        Assert.assertEquals(6, result);
    }
    
    @Test
    public void testGCD_testCase3(){
        int x = 10;
        int y = 42;
        int result = GCD.gcd(x, y);
        Assert.assertEquals(2, result);
    }
    
    @Test
    public void testGCD_testCase4(){
        int x = 12;
        int y = 5;
        int result = GCD.gcd(x, y);
        Assert.assertEquals(1, result);
    }
    
    @Test
    public void testGCD_testCase5(){
        int x = 73;
        int y = 20;
        int result = GCD.gcd(x, y);
        Assert.assertEquals(1, result);
    }
    
    @Test
    public void testGCD_testCase6(){
        int x = 80;
        int y = 30;
        int result = GCD.gcd(x, y);
        Assert.assertEquals(10, result);
    }   
}
