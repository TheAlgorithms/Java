package src.test.java.com.others;

import src.main.java.com.others.Ackermann;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AckermannTest {

  @Test
  public void testAckermann() {
    Ackermann ackTest = new Ackermann();
    assertEquals("Error", 1, ackTest.Ack(0, 0));
    assertEquals("Error", 3, ackTest.Ack(1, 1));
    assertEquals("Error", 7, ackTest.Ack(2, 2));
  }



}
