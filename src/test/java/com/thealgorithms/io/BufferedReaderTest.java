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

        // Pointer at start: [H]
        assertEquals('H', reader.peek(1));
        assertEquals('e', reader.peek(2));
        assertEquals('l', reader.peek(3));
    }

    @Test
    void testMixes() throws IOException {
        final String text = "Hello!\nWorld!";
        final byte[] bytes = text.getBytes();

        final BufferedReader reader = new BufferedReader(new ByteArrayInputStream(bytes));

        // Read first character
        assertEquals('H', reader.read());

        // Pointer now at 'e'
        assertEquals('e', reader.peek(1));
        assertEquals('e', reader.read());

        // Pointer at first 'l'
        assertEquals('l', reader.peek(1));
        assertEquals('l', reader.peek(2));
        assertEquals('o', reader.peek(3));

        assertEquals('l', reader.read());
        assertEquals('o', reader.peek(1));

        // Move to EOF safely
        while (reader.read() != -1) {
            // consume remaining
        }

        // Exception when peeking beyond available
        assertThrows(IOException.class, () -> reader.peek(1));
    }

    @Test
    void testBlockPractical() throws IOException {
        final String text = "!Hello\nWorld!";
        final byte[] bytes = text.getBytes();

        final BufferedReader reader = new BufferedReader(new ByteArrayInputStream(bytes));

        // Peek before reading
        assertEquals('!', reader.peek());

        // Read first character
        assertEquals('!', reader.read());

        // Read next block (default buffer size = 5 → "Hello")
        assertEquals("Hello", new String(reader.readBlock()));

        // Continue reading
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
