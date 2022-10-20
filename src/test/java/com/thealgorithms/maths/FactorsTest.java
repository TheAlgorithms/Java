package com.thealgorithms.maths;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FactorsTest {

    @Test
    public void test1() {
        ArrayList<Long> out = new ArrayList<Long>();
        out.add(toLong(1));
        assertEquals(out, Factors.factors(1));
    }

    @Test
    public void test2() {
        ArrayList<Long> out = new ArrayList<Long>();
        out.add(toLong(1));
        out.add(toLong(5));
        assertEquals(out, Factors.factors(5));
    }

    @Test
    public void test3() {
        assertEquals(new ArrayList<>(), Factors.factors(-6));
    }

    @Test
    public void test4() {
        ArrayList<Long> out = new ArrayList<Long>();
        out.add(toLong(1));
        out.add(toLong(2));
        out.add(toLong(3));
        out.add(toLong(6));
        assertEquals(out, Factors.factors(6));
    }

    private Long toLong(int n) {
        return Long.valueOf(n);
    }
}
