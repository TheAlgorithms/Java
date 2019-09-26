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
public class TestGCD_KTHT {
    @Test
    public void testGCD(){
        int x = 0;
        int y = 31;
        int result = GCD.gcd(x, y);
        Assert.assertEquals(32, result);
        
        x = 5;
        y = 0;
        result = GCD.gcd(x, y);
        Assert.assertEquals(5, result);
        
        x = 30;
        y = 24;
        result = GCD.gcd(x, y);
        Assert.assertEquals(6, result);
        
        x = 33;
        y = 36;
        result = GCD.gcd(x, y);
        Assert.assertEquals(3, result);
    }
    
}
