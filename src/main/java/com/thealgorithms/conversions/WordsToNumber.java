package com.thealgorithms.conversions;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private static final Map<String, Integer> NUMBER_MAP = new HashMap<>();
    private static final Map<String, BigDecimal> POWERS_OF_TEN = new HashMap<>();

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
            throw new WordsToNumberException(WordsToNumberException.ErrorType.NULL_INPUT, "");
        }

        ArrayDeque<String> wordDeque = preprocessWords(numberInWords);
        BigDecimal completeNumber = convertWordQueueToBigDecimal(wordDeque);

        return completeNumber.toString();
    }

    public static BigDecimal convertToBigDecimal(String numberInWords) {
        String conversionResult = convert(numberInWords);
        return new BigDecimal(conversionResult);
    }

    private static ArrayDeque<String> preprocessWords(String numberInWords) {
        String[] wordSplitArray = numberInWords.trim().split("[ ,-]");
        ArrayDeque<String> wordDeque = new ArrayDeque<>();
        for (String word : wordSplitArray) {
            if (word.isEmpty()) {
                continue;
            }
            wordDeque.add(word.toLowerCase());
        }
        if (wordDeque.isEmpty()) {
            throw new WordsToNumberException(WordsToNumberException.ErrorType.NULL_INPUT, "");
        }
        return wordDeque;
    }

    private static void handleConjunction(boolean prevNumWasHundred, boolean prevNumWasPowerOfTen, ArrayDeque<String> wordDeque) {
        if (wordDeque.isEmpty()) {
            throw new WordsToNumberException(WordsToNumberException.ErrorType.INVALID_CONJUNCTION, "");
        }

        String nextWord = wordDeque.pollFirst();
        String afterNextWord = wordDeque.peekFirst();

        wordDeque.addFirst(nextWord);

        Integer number = NUMBER_MAP.getOrDefault(nextWord, null);

        boolean isPrevWordValid = prevNumWasHundred || prevNumWasPowerOfTen;
        boolean isNextWordValid = number != null && (number >= 10 || afterNextWord == null || "point".equals(afterNextWord));

        if (!isPrevWordValid || !isNextWordValid) {
            throw new WordsToNumberException(WordsToNumberException.ErrorType.INVALID_CONJUNCTION, "");
        }
    }

    private static BigDecimal handleHundred(BigDecimal currentChunk, String word, boolean prevNumWasPowerOfTen) {
        boolean currentChunkIsZero = currentChunk.compareTo(BigDecimal.ZERO) == 0;
        if (currentChunk.compareTo(BigDecimal.TEN) >= 0 || prevNumWasPowerOfTen) {
            throw new WordsToNumberException(WordsToNumberException.ErrorType.UNEXPECTED_WORD, word);
        }
        if (currentChunkIsZero) {
            currentChunk = currentChunk.add(BigDecimal.ONE);
        }
        return currentChunk.multiply(BigDecimal.valueOf(100));
    }

    private static void handlePowerOfTen(List<BigDecimal> chunks, BigDecimal currentChunk, BigDecimal powerOfTen, String word, boolean prevNumWasPowerOfTen) {
        boolean currentChunkIsZero = currentChunk.compareTo(BigDecimal.ZERO) == 0;
        if (currentChunkIsZero || prevNumWasPowerOfTen) {
            throw new WordsToNumberException(WordsToNumberException.ErrorType.UNEXPECTED_WORD, word);
        }
        BigDecimal nextChunk = currentChunk.multiply(powerOfTen);

        if (!(chunks.isEmpty() || isAdditionSafe(chunks.getLast(), nextChunk))) {
            throw new WordsToNumberException(WordsToNumberException.ErrorType.UNEXPECTED_WORD, word);
        }
        chunks.add(nextChunk);
    }

    private static BigDecimal handleNumber(Collection<BigDecimal> chunks, BigDecimal currentChunk, String word, Integer number) {
        boolean currentChunkIsZero = currentChunk.compareTo(BigDecimal.ZERO) == 0;
        if (number == 0 && !(currentChunkIsZero && chunks.isEmpty())) {
            throw new WordsToNumberException(WordsToNumberException.ErrorType.UNEXPECTED_WORD, word);
        }
        BigDecimal bigDecimalNumber = BigDecimal.valueOf(number);

        if (!currentChunkIsZero && !isAdditionSafe(currentChunk, bigDecimalNumber)) {
            throw new WordsToNumberException(WordsToNumberException.ErrorType.UNEXPECTED_WORD, word);
        }
        return currentChunk.add(bigDecimalNumber);
    }

    private static void handlePoint(Collection<BigDecimal> chunks, BigDecimal currentChunk, ArrayDeque<String> wordDeque) {
        boolean currentChunkIsZero = currentChunk.compareTo(BigDecimal.ZERO) == 0;
        if (!currentChunkIsZero) {
            chunks.add(currentChunk);
        }

        String decimalPart = convertDecimalPart(wordDeque);
        chunks.add(new BigDecimal(decimalPart));
    }

    private static void handleNegative(boolean isNegative) {
        if (isNegative) {
            throw new WordsToNumberException(WordsToNumberException.ErrorType.MULTIPLE_NEGATIVES, "");
        }
        throw new WordsToNumberException(WordsToNumberException.ErrorType.INVALID_NEGATIVE, "");
    }

    private static BigDecimal convertWordQueueToBigDecimal(ArrayDeque<String> wordDeque) {
        BigDecimal currentChunk = BigDecimal.ZERO;
        List<BigDecimal> chunks = new ArrayList<>();

        boolean isNegative = "negative".equals(wordDeque.peek());
        if (isNegative) {
            wordDeque.poll();
        }

        boolean prevNumWasHundred = false;
        boolean prevNumWasPowerOfTen = false;

        while (!wordDeque.isEmpty()) {
            String word = wordDeque.poll();

            switch (word) {
                case "and" -> {
                    handleConjunction(prevNumWasHundred, prevNumWasPowerOfTen, wordDeque);
                    continue;
                }
                case "hundred" -> {
                    currentChunk = handleHundred(currentChunk, word, prevNumWasPowerOfTen);
                    prevNumWasHundred = true;
                    continue;
                }
                default -> {

                }
            }
            prevNumWasHundred = false;

            BigDecimal powerOfTen = POWERS_OF_TEN.getOrDefault(word, null);
            if (powerOfTen != null) {
                handlePowerOfTen(chunks, currentChunk, powerOfTen, word, prevNumWasPowerOfTen);
                currentChunk = BigDecimal.ZERO;
                prevNumWasPowerOfTen = true;
                continue;
            }
            prevNumWasPowerOfTen = false;

            Integer number = NUMBER_MAP.getOrDefault(word, null);
            if (number != null) {
                currentChunk = handleNumber(chunks, currentChunk, word, number);
                continue;
            }

            switch (word) {
                case "point" -> {
                    handlePoint(chunks, currentChunk, wordDeque);
                    currentChunk = BigDecimal.ZERO;
                    continue;
                }
                case "negative" -> {
                    handleNegative(isNegative);
                }
                default -> {

                }
            }

            throw new WordsToNumberException(WordsToNumberException.ErrorType.UNKNOWN_WORD, word);
        }

        if (currentChunk.compareTo(BigDecimal.ZERO) != 0) {
            chunks.add(currentChunk);
        }

        BigDecimal completeNumber = combineChunks(chunks);
        return isNegative ? completeNumber.multiply(BigDecimal.valueOf(-1))
                :
                    completeNumber;
                }

                private static boolean isAdditionSafe(BigDecimal currentChunk, BigDecimal number) {
                    int chunkDigitCount = currentChunk.toString().length();
                    int numberDigitCount = number.toString().length();
                    return chunkDigitCount > numberDigitCount;
                }

                private static String convertDecimalPart(ArrayDeque<String> wordDeque) {
                    StringBuilder decimalPart = new StringBuilder(".");

                    while (!wordDeque.isEmpty()) {
                        String word = wordDeque.poll();
                        Integer number = NUMBER_MAP.getOrDefault(word, null);
                        if (number == null) {
                            throw new WordsToNumberException(WordsToNumberException.ErrorType.UNEXPECTED_WORD_AFTER_POINT, word);
                        }
                        decimalPart.append(number);
                    }

                    boolean missingNumbers = decimalPart.length() == 1;
                    if (missingNumbers) {
                        throw new WordsToNumberException(WordsToNumberException.ErrorType.MISSING_DECIMAL_NUMBERS, "");
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
        }

        class WordsToNumberException extends RuntimeException {

                @Serial private static final long serialVersionUID = 1L;

                enum ErrorType {
                    NULL_INPUT("'null' or empty input provided"),
                    UNKNOWN_WORD("Unknown Word: "),
                    UNEXPECTED_WORD("Unexpected Word: "),
                    UNEXPECTED_WORD_AFTER_POINT("Unexpected Word (after Point): "),
                    MISSING_DECIMAL_NUMBERS("Decimal part is missing numbers."),
                    MULTIPLE_NEGATIVES("Multiple 'Negative's detected."),
                    INVALID_NEGATIVE("Incorrect 'negative' placement"),
                    INVALID_CONJUNCTION("Incorrect 'and' placement");

                    private final String message;

                    ErrorType(String message) {
                        this.message = message;
                    }

                    public String formatMessage(String details) {
                        return "Invalid Input. " + message + (details.isEmpty() ? "" : details);
                    }
                }

                public final ErrorType errorType;

                WordsToNumberException(ErrorType errorType, String details) {
                    super(errorType.formatMessage(details));
                    this.errorType = errorType;
                }

                public ErrorType getErrorType() {
                    return errorType;
                }
        }
