package com.thealgorithms.strings;

import java.util.*;

public class LetterCombinationsOfPhoneNumber {

    static Character[][] numberToCharMap;

    protected static List<String> printWords(int[] numbers, int len, int numIndex, String s) {
        if (len == numIndex) {
            return new ArrayList<>(Collections.singleton(s));
        }

        List<String> stringList = new ArrayList<>();

        for (int i = 0; i < numberToCharMap[numbers[numIndex]].length; i++) {
            String sCopy = String.copyValueOf(s.toCharArray());
            sCopy = sCopy.concat(numberToCharMap[numbers[numIndex]][i].toString());
            stringList.addAll(printWords(numbers, len, numIndex + 1, sCopy));
        }
        return stringList;
    }

    private static void printWords(int[] numbers) {
        generateNumberToCharMap();
        List<String> stringList = printWords(numbers, numbers.length, 0, "");
        stringList.stream().forEach(System.out::println);
    }

    protected static void generateNumberToCharMap() {
        numberToCharMap = new Character[10][5];
        numberToCharMap[0] = new Character[] {'\0'};
        numberToCharMap[1] = new Character[] {'\0'};
        numberToCharMap[2] = new Character[] {'a', 'b', 'c'};
        numberToCharMap[3] = new Character[] {'d', 'e', 'f'};
        numberToCharMap[4] = new Character[] {'g', 'h', 'i'};
        numberToCharMap[5] = new Character[] {'j', 'k', 'l'};
        numberToCharMap[6] = new Character[] {'m', 'n', 'o'};
        numberToCharMap[7] = new Character[] {'p', 'q', 'r', 's'};
        numberToCharMap[8] = new Character[] {'t', 'u', 'v'};
        numberToCharMap[9] = new Character[] {'w', 'x', 'y', 'z'};
    }

    // Driver code
    public static void main(String[] args) {
        int[] number = {2, 3, 4};
        printWords(number);
    }
}
