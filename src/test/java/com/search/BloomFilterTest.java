package com.search;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class BloomFilterTest {

    private static final char[] CHAR_POOL;

    static {
        CHAR_POOL = new char[52];
        int i = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            CHAR_POOL[i++] = c;
            CHAR_POOL[i++] = (char) (c - 32);
        }
    }

    public static String randomString(int minLength, int maxLength) {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        int chLen = r.nextInt(minLength, maxLength),
                poolSize = CHAR_POOL.length;
        char[] chars = new char[chLen];
        for (int i = 0; i < chLen; i++) {
            chars[i] = CHAR_POOL[r.nextInt(poolSize)];
        }

        return new String(chars);
    }

    @Test
    public void test() {
        int count = 100000;
        int low = 50, up = 100;
        BloomFilter filter = BloomFilter.builder(10000).build();
        String[] data = new String[count];
        Set<String> dataSet = new HashSet<>();
        for (int i = 0; i < count; i++) {
            String str = randomString(low, up);
            data[i] = str;
            if (i % 2 == 0) {
                dataSet.add(str);
                filter.add(str);
            }
        }

        int error = 0, total = 0;
        for (int i = 0; i < count; i++) {
            String str = data[i];
            if (filter.contains(str)) {
                total++;
                if (!dataSet.contains(str)) {
                    error++;
                }
            } else {
                assertFalse(dataSet.contains(str));
            }
        }
        System.out.println("error: " + error);
        System.out.println("total: " + total);
        System.out.println("error rate : " + (double) error / total);
    }

}