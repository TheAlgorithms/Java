package com.thealgorithms.conversions;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 A Java-based utility for converting English word representations of numbers
 into their numeric form. This utility supports whole numbers, decimals,
 large values up to trillions, and even scientific notation where applicable.
 It ensures accurate parsing while handling edge cases like negative numbers,
 improper word placements, and ambiguous inputs.
 *
 */

public final class WordsToNumber {
    private WordsToNumber() {
    }

    private static final HashMap<String, Integer> NUMBER_MAP = new HashMap<>();
    private static final HashMap<String, BigDecimal> POWERS_OF_TEN = new HashMap<>();

    static {
        NUMBER_MAP.put("zero", 0);
        NUMBER_MAP.put("one", 1);
        NUMBER_MAP.put("two", 2);
        NUMBER_MAP.put("three", 3);
        NUMBER_MAP.put("four", 4);
        NUMBER_MAP.put("five", 5);
        NUMBER_MAP.put("six", 6);
        NUMBER_MAP.put("seven", 7);
        NUMBER_MAP.put("eight", 8);
        NUMBER_MAP.put("nine", 9);
        NUMBER_MAP.put("ten", 10);
        NUMBER_MAP.put("eleven", 11);
        NUMBER_MAP.put("twelve", 12);
        NUMBER_MAP.put("thirteen", 13);
        NUMBER_MAP.put("fourteen", 14);
        NUMBER_MAP.put("fifteen", 15);
        NUMBER_MAP.put("sixteen", 16);
        NUMBER_MAP.put("seventeen", 17);
        NUMBER_MAP.put("eighteen", 18);
        NUMBER_MAP.put("nineteen", 19);
        NUMBER_MAP.put("twenty", 20);
        NUMBER_MAP.put("thirty", 30);
        NUMBER_MAP.put("forty", 40);
        NUMBER_MAP.put("fifty", 50);
        NUMBER_MAP.put("sixty", 60);
        NUMBER_MAP.put("seventy", 70);
        NUMBER_MAP.put("eighty", 80);
        NUMBER_MAP.put("ninety", 90);

        POWERS_OF_TEN.put("thousand", new BigDecimal("1000"));
        POWERS_OF_TEN.put("million", new BigDecimal("1000000"));
        POWERS_OF_TEN.put("billion", new BigDecimal("1000000000"));
        POWERS_OF_TEN.put("trillion", new BigDecimal("1000000000000"));
    }

    public static String convert(String numberInWords) {
        if (numberInWords == null) {
            return "Null Input";
        }

        String[] wordSplitArray = numberInWords.trim().split("[ ,-]");
        ArrayDeque<String> wordDeque = new ArrayDeque<>();
        for (String word : wordSplitArray) {
            if (word.isEmpty()) {
                continue;
            }
            wordDeque.add(word.toLowerCase());
        }

        List<BigDecimal> chunks = new ArrayList<>();
        BigDecimal currentChunk = BigDecimal.ZERO;

        boolean isNegative = false;
        boolean prevNumWasHundred = false;
        boolean prevNumWasPowerOfTen = false;

        String errorMessage = null;

        while (!wordDeque.isEmpty() && errorMessage == null) {
            String word = wordDeque.poll();
            boolean currentChunkIsZero = currentChunk.compareTo(BigDecimal.ZERO) == 0;

            boolean isConjunction = word.equals("and");
            if (isConjunction && isValidConjunction(prevNumWasHundred, prevNumWasPowerOfTen, wordDeque)) {
                continue;
            }

            if (word.equals("hundred")) {
                if (currentChunk.compareTo(BigDecimal.TEN) >= 0 || prevNumWasPowerOfTen) {
                    errorMessage = "Invalid Input. Unexpected Word: " + word;
                    continue;
                }
                if (currentChunkIsZero) {
                    currentChunk = currentChunk.add(BigDecimal.ONE);
                }
                currentChunk = currentChunk.multiply(BigDecimal.valueOf(100));
                prevNumWasHundred = true;
                continue;
            }
            prevNumWasHundred = false;

            BigDecimal powerOfTen = POWERS_OF_TEN.getOrDefault(word, null);
            if (powerOfTen != null) {
                if (currentChunkIsZero || prevNumWasPowerOfTen) {
                    errorMessage = "Invalid Input. Unexpected Word: " + word;
                    continue;
                }
                BigDecimal nextChunk = currentChunk.multiply(powerOfTen);

                if (!(chunks.isEmpty() || isAdditionSafe(chunks.getLast(), nextChunk))) {
                    errorMessage = "Invalid Input. Unexpected Word: " + word;
                    continue;
                }
                chunks.add(nextChunk);
                currentChunk = BigDecimal.ZERO;
                prevNumWasPowerOfTen = true;
                continue;
            }
            prevNumWasPowerOfTen = false;

            Integer number = NUMBER_MAP.getOrDefault(word, null);
            if (number != null) {
                if (number == 0 && !(currentChunkIsZero && chunks.isEmpty())) {
                    errorMessage = "Invalid Input. Unexpected Word: " + word;
                    continue;
                }
                BigDecimal bigDecimalNumber = BigDecimal.valueOf(number);

                if (currentChunkIsZero || isAdditionSafe(currentChunk, bigDecimalNumber)) {
                    currentChunk = currentChunk.add(bigDecimalNumber);
                } else {
                    errorMessage = "Invalid Input. Unexpected Word: " + word;
                }
                continue;
            }

            if (word.equals("point")) {
                if (!currentChunkIsZero) {
                    chunks.add(currentChunk);
                }
                currentChunk = BigDecimal.ZERO;

                String decimalPart = convertDecimalPart(wordDeque);
                if (!decimalPart.startsWith("I")) {
                    chunks.add(new BigDecimal(decimalPart));
                } else {
                    errorMessage = decimalPart;
                }
                continue;
            }

            if (word.equals("negative")) {
                if (isNegative) {
                    errorMessage = "Invalid Input. Multiple 'Negative's detected.";
                } else {
                    isNegative = chunks.isEmpty() && currentChunkIsZero;
                }
                continue;
            }

            errorMessage = "Invalid Input. " + (isConjunction ? "Unexpected 'and' placement" : "Unknown Word: " + word);
        }

        if (errorMessage != null) {
            return errorMessage;
        }

        if (!(currentChunk.compareTo(BigDecimal.ZERO) == 0)) {
            chunks.add(currentChunk);
        }
        BigDecimal completeNumber = combineChunks(chunks);

        return isNegative ? completeNumber.multiply(BigDecimal.valueOf(-1)).toString() : completeNumber.toString();
    }

    private static boolean isValidConjunction(boolean prevNumWasHundred, boolean prevNumWasPowerOfTen, ArrayDeque<String> wordDeque) {
        if (wordDeque.isEmpty()) {
            return false;
        }

        String nextWord = wordDeque.pollFirst();
        String afterNextWord = wordDeque.peekFirst();

        wordDeque.addFirst(nextWord);

        Integer number = NUMBER_MAP.getOrDefault(nextWord, null);

        boolean isPrevWordValid = prevNumWasHundred || prevNumWasPowerOfTen;
        boolean isNextWordValid = number != null && (number >= 10 || afterNextWord == null || afterNextWord.equals("point"));

        return isPrevWordValid && isNextWordValid;
    }

    private static boolean isAdditionSafe(BigDecimal currentChunk, BigDecimal number) {
        int chunkDigitCount = currentChunk.toString().length();
        int numberDigitCount = number.toString().length();
        return chunkDigitCount > numberDigitCount;
    }

    private static String convertDecimalPart(ArrayDeque<String> wordDeque) {
        StringBuilder decimalPart = new StringBuilder(".");
        String errorMessage = null;

        while (!wordDeque.isEmpty()) {
            String word = wordDeque.poll();
            Integer number = NUMBER_MAP.getOrDefault(word, null);
            if (number == null) {
                errorMessage = "Invalid Input. Unexpected Word (after Point): " + word;
                break;
            }
            decimalPart.append(number);
        }

        if (errorMessage != null) {
            return errorMessage;
        }

        if (decimalPart.length() == 1) {
            return "Invalid Input. Decimal Part is missing Numbers.";
        }
        return decimalPart.toString();
    }

    private static BigDecimal combineChunks(List<BigDecimal> chunks) {
        BigDecimal completeNumber = BigDecimal.ZERO;
        for (BigDecimal chunk : chunks) {
            completeNumber = completeNumber.add(chunk);
        }
        return completeNumber;
    }

    public static BigDecimal convertToBigDecimal(String numberInWords) {
        String conversionResult = convert(numberInWords);
        if (conversionResult.startsWith("I")) {
            return null;
        }
        return new BigDecimal(conversionResult);
    }
}
