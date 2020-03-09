package com.compressions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

final class HuffmanTest {

    @Test
    void testHuffman() {
        Huffman huffman = new Huffman();

        Assertions.assertEquals("1100 1101 01 01 101 1110 1111 101 000 01 001 100", huffman.encode("Hello world!"));
        Assertions.assertEquals("0000 1101 0001 1110 0010 0011 0100 0101 0110 0111 1000 1101 1001 1010 1110 1011 1111 1100 1111", huffman.encode("The Algorithms Java"));
        Assertions.assertEquals("0100 0101 001 001 0110 0111 111 1000 1001 111 1010 1011 1100 1101 111 000", huffman.encode("Huffman encoding"));
    }
}
