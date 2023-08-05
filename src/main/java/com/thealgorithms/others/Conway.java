package com.thealgorithms.others;

import java.util.*;

public class Conway {

    /*
     * This class will generate the conway sequence also known as the look and say sequence.
     * To generate a member of the sequence from the previous member, read off the digits of the
     *previous member, counting the number of digits in groups of the same digit. For example: 1 is
     *read off as "one 1" or 11. 11 is read off as "two 1s" or 21. 21 is read off as "one 2, one 1"
     *or 1211. 1211 is read off as "one 1, one 2, two 1s" or 111221. 111221 is read off as "three
     *1s, two 2s, one 1" or 312211. https://en.wikipedia.org/wiki/Look-and-say_sequence
     * */

    private static final StringBuilder builder = new StringBuilder();

    protected static List<String> generateList(String originalString, int maxIteration) {
        List<String> numbers = new ArrayList<>();
        for (int i = 0; i < maxIteration; i++) {
            originalString = generateNextElement(originalString);
            numbers.add(originalString);
        }
        return numbers;
    }

    public static String generateNextElement(String originalString) {
        builder.setLength(0);
        String[] stp = originalString.split("(?<=(.))(?!\\1)");
        Arrays.stream(stp).forEach(s -> builder.append(s.length()).append(s.charAt(0)));
        return builder.toString();
    }
}
