package com.thealgorithms.maths;

import java.util.stream.IntStream;

public class AliquotSum {

    public static int getAliquotValue(int number) {
        var sumWrapper = new Object() {
            int value = 0;
        };

        IntStream.iterate(1, i -> ++i)
                .limit(number / 2)
                .filter(i -> number % i == 0)
                .forEach(i -> sumWrapper.value += i);

        return sumWrapper.value;
    }
}
