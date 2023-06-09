package com.thealgorithms.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import org.junit.jupiter.api.Test;

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

        for (int i = 0; i < 6; i++) reader.read();
        try {
            System.out.println((char) reader.peek(4));
        } catch (Exception ignored) {
            System.out.println("[cached intentional error]");
            // intentional, for testing purpose
        }
    }

    @Test
    public void testBlockPractical() throws IOException {
        String text = "!Hello\nWorld!";
        byte[] bytes = text.getBytes();
        int len = bytes.length;

        ByteArrayInputStream input = new ByteArrayInputStream(bytes);
        BufferedReader reader = new BufferedReader(input);

        assertEquals(reader.peek(), 'H');
        assertEquals(reader.read(), '!'); // read the first letter
        len--;

        // this only reads the next 5 bytes (Hello) because
        // the default buffer size = 5
        assertEquals(new String(reader.readBlock()), "Hello");
        len -= 5;
        assertEquals(reader.available(), len);

        // maybe kind of a practical demonstration / use case
        if (reader.read() == '\n') {
            assertEquals(reader.read(), 'W');
            assertEquals(reader.read(), 'o');

            // the rest of the blocks
            assertEquals(new String(reader.readBlock()), "rld!");
        } else {
            // should not reach
            throw new IOException("Something not right");
        }
    }
}
