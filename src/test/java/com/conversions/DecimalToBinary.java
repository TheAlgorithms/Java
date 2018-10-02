package src.test.java.com.conversions;

import org.junit.Assert;
import org.junit.Test;
import src.main.java.com.conversions.DecimalToBinary;

public class DecimalToBinary {

    @Test
    public void testConventionalConversion() {
        DecimalToBinary decimalToBinary = new DecimalToBinary();
        Assert.assertEquals("", decimalToBinary.conventionalConversion(0));
        Assert.assertEquals("1", decimalToBinary.conventionalConversion(1));
        Assert.assertEquals("10", decimalToBinary.conventionalConversion(2));
        Assert.assertEquals("11", decimalToBinary.conventionalConversion(3));
        Assert.assertEquals("1111", decimalToBinary.conventionalConversion(15));
        Assert.assertEquals("1000000000", decimalToBinary.conventionalConversion(512));
        Assert.assertEquals("1111111111", decimalToBinary.conventionalConversion(1023));
        Assert.assertEquals("10000000000", decimalToBinary.conventionalConversion(1024));
    }

    @Test
    public void testBitwiseConversion(){
        DecimalToBinary decimalToBinary = new DecimalToBinary();
        Assert.assertEquals("", decimalToBinary.bitwiseConversion(0));
        Assert.assertEquals("1", decimalToBinary.bitwiseConversion(1));
        Assert.assertEquals("10", decimalToBinary.bitwiseConversion(2));
        Assert.assertEquals("11", decimalToBinary.bitwiseConversion(3));
        Assert.assertEquals("1111", decimalToBinary.bitwiseConversion(15));
        Assert.assertEquals("1000000000", decimalToBinary.bitwiseConversion(512));
        Assert.assertEquals("1111111111", decimalToBinary.bitwiseConversion(1023));
        Assert.assertEquals("10000000000", decimalToBinary.bitwiseConversion(1024));
    }
}
