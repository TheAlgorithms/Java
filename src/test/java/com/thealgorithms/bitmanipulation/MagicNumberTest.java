package com.thealgorithms.bitmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MagicNumberTest {

        @Test
        public void testfor1() {
            int ans = MagicNumber.magicNum(1);
            assertEquals(5, ans);
        }

        @Test
        public void testfor2() {
            int ans = MagicNumber.magicNum(2);
            assertEquals(25,ans);
            System.out.println(ans);
        }

        @Test
        public void testfor3() {
            int ans = MagicNumber.magicNum(3);
            assertEquals(30,ans);
            System.out.println(ans);
        }

        @Test
        public void testfor4() {
            int ans = MagicNumber.magicNum(4);
            assertEquals(125,ans);
            System.out.println(ans);
        }

        @Test
        public void testfor5() {
            int ans = MagicNumber.magicNum(5);
            assertEquals(130,ans);
            System.out.println(ans);
        }

        @Test
        public void testfor6() {
            int ans = MagicNumber.magicNum(6);
            assertEquals(150,ans);
            System.out.println(ans);
        }
}
