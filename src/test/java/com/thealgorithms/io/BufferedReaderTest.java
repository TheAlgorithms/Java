package com.thealgorithms.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class BufferedReaderTest {

    @Test
    void testPeeks() throws IOException {
        final String text = "Hello!\nWorld!";
        final byte[] bytes = text.getBytes();

        final BufferedReader reader = new BufferedReader(new ByteArrayInputStream(bytes));

        assertEquals('H', reader.read());

        assertEquals('l', reader.peek(1));
        assertEquals('l', reader.peek(2));
        assertEquals('o', reader.peek(3));
    }

    @Test
    void testMixes() throws IOException {
        final String text = "Hello!\nWorld!";
        final byte[] bytes = text.getBytes();

        final BufferedReader reader = new BufferedReader(new ByteArrayInputStream(bytes));

        assertEquals('H', reader.read());

        assertEquals('l', reader.peek(1));
        assertEquals('e', reader.read());

        assertEquals('o', reader.peek(2));
        assertEquals('!', reader.peek(3));
        assertEquals('\n', reader.peek(4));

        assertEquals('l', reader.read());
        assertEquals('o', reader.peek(1));

        // Move towards EOF
        for (int i = 0; i < text.length(); i++) {
            reader.read();
        }

        // Proper exception testing
        assertThrows(IOException.class, () -> reader.peek(4));
    }

    @Test
    void testBlockPractical() throws IOException {
        final String text = "!Hello\nWorld!";
        final byte[] bytes = text.getBytes();

        final BufferedReader reader = new BufferedReader(new ByteArrayInputStream(bytes));

        assertEquals('H', reader.peek());
        assertEquals('!', reader.read());

        assertEquals("Hello", new String(reader.readBlock()));

        if (reader.read() == '\n') {
            assertEquals('W', reader.read());
            assertEquals('o', reader.read());

            assertEquals("rld!", new String(reader.readBlock()));
        } else {
            throw new IOException("Unexpected stream state");
        }
    }

    @Test
    void testEndOfFile() throws IOException {
        final byte[] bytes = "A".getBytes();
        final BufferedReader reader = new BufferedReader(new ByteArrayInputStream(bytes));

        assertEquals('A', reader.read());
        assertEquals(-1, reader.read()); // EOF check
    }
}
