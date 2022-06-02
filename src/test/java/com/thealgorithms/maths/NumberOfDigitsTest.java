import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NumberOfDigitsTest {
  @Test
  public void testNumberOfDigits() {
    assertEquals(1, NumberOfDigits.numberOfDigits(987654321));
  }

  @Test
  public void testNumberOfDigitsFast() {
    assertEquals(3, NumberOfDigits. numberOfDigitsFast(98765432));
  }

  @Test
  public void testNumberOfDigitsFaster() {
    assertEquals(4, NumberOfDigits. numberOfDigitsFaster(7765321));
  }

  @Test
  public void testNumberOfDigitsFaster() {
    assertEquals(0, NumberOfDigits. numberOfDigitsRecursion(88654321));
  }
}