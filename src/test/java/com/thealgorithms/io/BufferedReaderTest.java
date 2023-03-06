package com.thealgorithms.io;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BufferedReaderTest {
  @Test
  public void testPeeks() throws IOException {
    String text = "Hello!\nWorld!";
    int len = text.length();
    byte[] bytes = text.getBytes();

    ByteArrayInputStream input = new ByteArrayInputStream(bytes);
    BufferedReader reader = new BufferedReader(input);

    // read the first letter
    assertEquals(reader.read(), 'H');
    len--;
    assertEquals(reader.available(), len);

    // position: H[e]llo!\nWorld!
    // reader.read() will be == 'e'
    assertEquals(reader.peek(1), 'l');
    assertEquals(reader.peek(2), 'l'); // second l
    assertEquals(reader.peek(3), 'o');
  }

  @Test
  public void testMixes() throws IOException {
    String text = "Hello!\nWorld!";
    int len = text.length();
    byte[] bytes = text.getBytes();

    ByteArrayInputStream input = new ByteArrayInputStream(bytes);
    BufferedReader reader = new BufferedReader(input);

    // read the first letter
    assertEquals(reader.read(), 'H'); // first letter
    len--;

    assertEquals(reader.peek(1), 'l'); // third later (second letter after 'H')
    assertEquals(reader.read(), 'e'); // second letter
    len--;
    assertEquals(reader.available(), len);

    // position: H[e]llo!\nWorld!
    assertEquals(reader.peek(2), 'o'); // second l
    assertEquals(reader.peek(3), '!');
    assertEquals(reader.peek(4), '\n');

    assertEquals(reader.read(), 'l'); // third letter
    assertEquals(reader.peek(1), 'o'); // fourth letter

    for (int i = 0; i < 6; i++)
      reader.read();
    try {
      System.out.println((char) reader.peek(4));
    } catch (Exception ignored) {
      System.out.println("[cached intentional error]");
      // intentional, for testing purpose
    }
  }
}