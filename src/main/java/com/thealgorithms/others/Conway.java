package com.thealgorithms.others;

import java.util.*;

public class Conway {

    /*
     * This class will generate the conway sequence also known as the look and say sequence.
     * To generate a member of the sequence from the previous member, read off the digits of the previous member, counting the number of digits in groups of the same digit. For example:
     *1 is read off as "one 1" or 11.
     *11 is read off as "two 1s" or 21.
     *21 is read off as "one 2, one 1" or 1211.
     *1211 is read off as "one 1, one 2, two 1s" or 111221.
     *111221 is read off as "three 1s, two 2s, one 1" or 312211.
     * https://en.wikipedia.org/wiki/Look-and-say_sequence
     * */

    private static final StringBuilder builder = new StringBuilder();

    private static final Integer MAX_ITERATION_ALLOWED = 50;
    private static final Integer MIN_ITERATION_ALLOWED = 1;

    public static void main(String[]args) {
        Scanner scanner;
        String originalString;
        int maxIteration;
        System.out.println("Enter any character you want : ");
        scanner = new Scanner(System.in);
        originalString = scanner.nextLine();

        do {
        System.out.println("Enter the maximum of iteration (max allowed 50) : ");
        scanner = new Scanner(System.in);
        maxIteration = scanner.nextInt();
        }
        while (maxIteration < MIN_ITERATION_ALLOWED || maxIteration > MAX_ITERATION_ALLOWED);
        List<String> strings = generateList(originalString, maxIteration);
        System.out.println(strings);
    }

    protected static List<String> generateList(String originalString, int maxIteration) {
        List<String> numbers = new ArrayList<>();
        for(int i=0; i<maxIteration; i++) {
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
