package com.thealgorithms.io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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
        assertEquals('H', reader.read());
        len--;
        assertEquals(len, reader.available());

        // position: H[e]llo!\nWorld!
        // reader.read() will be == 'e'
        assertEquals('l', reader.peek(1));
        assertEquals('l', reader.peek(2)); // second l
        assertEquals('o', reader.peek(3));
    }

    @Test
    public void testMixes() throws IOException {
        String text = "Hello!\nWorld!";
        int len = text.length();
        byte[] bytes = text.getBytes();

        ByteArrayInputStream input = new ByteArrayInputStream(bytes);
        BufferedReader reader = new BufferedReader(input);

        // read the first letter
        assertEquals('H', reader.read()); // first letter
        len--;

        assertEquals('l', reader.peek(1)); // third later (second letter after 'H')
        assertEquals('e', reader.read()); // second letter
        len--;
        assertEquals(len, reader.available());

        // position: H[e]llo!\nWorld!
        assertEquals('o', reader.peek(2)); // second l
        assertEquals('!', reader.peek(3));
        assertEquals('\n', reader.peek(4));

        assertEquals('l', reader.read()); // third letter
        assertEquals('o', reader.peek(1)); // fourth letter

        for (int i = 0; i < 6; i++) {
            reader.read();
        }
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

        assertEquals('H', reader.peek());
        assertEquals('!', reader.read()); // read the first letter
        len--;

        // this only reads the next 5 bytes (Hello) because
        // the default buffer size = 5
        assertEquals("Hello", new String(reader.readBlock()));
        len -= 5;
        assertEquals(reader.available(), len);

        // maybe kind of a practical demonstration / use case
        if (reader.read() == '\n') {
            assertEquals('W', reader.read());
            assertEquals('o', reader.read());

            // the rest of the blocks
            assertEquals("rld!", new String(reader.readBlock()));
        } else {
            // should not reach
            throw new IOException("Something not right");
        }
    }
}
