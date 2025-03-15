package com.thealgorithms.conversions;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
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

    private enum NumberWord {
        ZERO("zero", 0),
        ONE("one", 1),
        TWO("two", 2),
        THREE("three", 3),
        FOUR("four", 4),
        FIVE("five", 5),
        SIX("six", 6),
        SEVEN("seven", 7),
        EIGHT("eight", 8),
        NINE("nine", 9),
        TEN("ten", 10),
        ELEVEN("eleven", 11),
        TWELVE("twelve", 12),
        THIRTEEN("thirteen", 13),
        FOURTEEN("fourteen", 14),
        FIFTEEN("fifteen", 15),
        SIXTEEN("sixteen", 16),
        SEVENTEEN("seventeen", 17),
        EIGHTEEN("eighteen", 18),
        NINETEEN("nineteen", 19),
        TWENTY("twenty", 20),
        THIRTY("thirty", 30),
        FORTY("forty", 40),
        FIFTY("fifty", 50),
        SIXTY("sixty", 60),
        SEVENTY("seventy", 70),
        EIGHTY("eighty", 80),
        NINETY("ninety", 90);

        private final String word;
        private final int value;

        NumberWord(String word, int value) {
            this.word = word;
            this.value = value;
        }

        public static Integer getValue(String word) {
            for (NumberWord num : values()) {
                if (word.equals(num.word)) {
                    return num.value;
                }
            }
            return null;
        }
    }

    private enum PowerOfTen {
        THOUSAND("thousand", new BigDecimal("1000")),
        MILLION("million", new BigDecimal("1000000")),
        BILLION("billion", new BigDecimal("1000000000")),
        TRILLION("trillion", new BigDecimal("1000000000000"));

        private final String word;
        private final BigDecimal value;

        PowerOfTen(String word, BigDecimal value) {
            this.word = word;
            this.value = value;
        }

        public static BigDecimal getValue(String word) {
            for (PowerOfTen power : values()) {
                if (word.equals(power.word)) {
                    return power.value;
                }
            }
            return null;
        }
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

        Integer number = NumberWord.getValue(nextWord);

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

            BigDecimal powerOfTen = PowerOfTen.getValue(word);
            if (powerOfTen != null) {
                handlePowerOfTen(chunks, currentChunk, powerOfTen, word, prevNumWasPowerOfTen);
                currentChunk = BigDecimal.ZERO;
                prevNumWasPowerOfTen = true;
                continue;
            }
            prevNumWasPowerOfTen = false;

            Integer number = NumberWord.getValue(word);
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
                        Integer number = NumberWord.getValue(word);
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
