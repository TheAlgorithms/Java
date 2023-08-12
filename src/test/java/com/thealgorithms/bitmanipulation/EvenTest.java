package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
class EvenTest {
@Test
void testEven(){
assertEquals(1, Even.isEven(2));
assertEquals(-1, Even.isEven(3));
}
}
