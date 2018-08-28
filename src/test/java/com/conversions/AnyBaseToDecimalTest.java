package src.test.java.com.conversions;

import org.junit.Assert;
import org.junit.Test;
import src.main.java.com.conversions.AnyBaseToDecimal;

public class AnyBaseToDecimalTest {

  @Test
  public void testAnyBaseToDecimal() {
    AnyBaseToDecimal anyBaseToDecimal = new AnyBaseToDecimal();
    Assert.assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal("2", 2));
    Assert.assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal("3", 2));
    Assert.assertEquals("3", anyBaseToDecimal.convertToDecimal("11", 2));
    Assert.assertEquals("4", anyBaseToDecimal.convertToDecimal("100", 2));
    Assert.assertEquals("5", anyBaseToDecimal.convertToDecimal("101", 2));
    Assert.assertEquals("10", anyBaseToDecimal.convertToDecimal("1010", 2));
    Assert.assertEquals("1024", anyBaseToDecimal.convertToDecimal("10000000000", 2));

    Assert.assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal("8", 8));
    Assert.assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal("9", 8));
    Assert.assertEquals("7", anyBaseToDecimal.convertToDecimal("7", 8));
    Assert.assertEquals("8", anyBaseToDecimal.convertToDecimal("10", 8));
    Assert.assertEquals("9", anyBaseToDecimal.convertToDecimal("11", 8));
    Assert.assertEquals("10", anyBaseToDecimal.convertToDecimal("12", 8));
    Assert.assertEquals("1024", anyBaseToDecimal.convertToDecimal("2000", 8));

    Assert.assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal("A", 10));
    Assert.assertEquals("10", anyBaseToDecimal.convertToDecimal("10", 10));
    Assert.assertEquals("1024", anyBaseToDecimal.convertToDecimal("1024", 10));

    Assert.assertEquals("Invalid Number", anyBaseToDecimal.convertToDecimal("G", 16));
    Assert.assertEquals("16", anyBaseToDecimal.convertToDecimal("10", 16));
    Assert.assertEquals("17", anyBaseToDecimal.convertToDecimal("11", 16));
    Assert.assertEquals("100", anyBaseToDecimal.convertToDecimal("64", 16));
    Assert.assertEquals("225", anyBaseToDecimal.convertToDecimal("E1", 16));
    Assert.assertEquals("1024", anyBaseToDecimal.convertToDecimal("400", 16));
  }
}
