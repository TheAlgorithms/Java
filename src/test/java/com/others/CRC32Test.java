package com.others;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CRC32Test {

    @Test
    public void testWithEmptyString() {
        String empty = "";
        int result1 = CRC32.crc32(empty);
        Assertions.assertEquals(0x00, result1, "Fail for empty string");
    }

    @Test
    public void testWithNullString() {
        String nullString = null;
        try {
            int result2 = CRC32.crc32(nullString);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), IOException.class);
            return ;
        }
    }

    @Test
    public void testWithStringContainSpace(){
        String str = "Nguyen Thanh Nam";
        int result3 = CRC32.crc32(str);
        Assertions.assertEquals(0x9AC5F93B,result3,"Fail for string contain space");
    }

    @Test
    public void testWithStringNotContainSpace(){
        String str = "NguyenThanhNam";
        int result3 = CRC32.crc32(str);
        Assertions.assertEquals(0xF51CDA68,result3,"Fail for string not contain space");
    }

}
