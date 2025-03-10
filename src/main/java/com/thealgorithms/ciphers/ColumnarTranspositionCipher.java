package com.thealgorithms.ciphers;

import java.util.Objects;

/**
 * Columnar Transposition Cipher Encryption and Decryption.
 *
 * @author <a href="https://github.com/freitzzz">freitzzz</a>
 */
public final class ColumnarTranspositionCipher {
    private ColumnarTranspositionCipher() {
    }

    private static String keyword;
    private static Object[][] table;
    private static String abecedarium;
    public static final String ABECEDARIUM = "abcdefghijklmnopqrstuvwxyzABCDEFG"
        + "HIJKLMNOPQRSTUVWXYZ0123456789,.;:-@";
    private static final String ENCRYPTION_FIELD = "≈";
    private static final char ENCRYPTION_FIELD_CHAR = '≈';

    /**
     * Encrypts a certain String with the Columnar Transposition Cipher Rule
     *
     * @param word Word being encrypted
     * @param keyword String with keyword being used
     * @return a String with the word encrypted by the Columnar Transposition
     * Cipher Rule
     */
    public static String encrypt(final String word, final String keyword) {
        ColumnarTranspositionCipher.keyword = keyword;
        abecedariumBuilder();
        table = tableBuilder(word);
        Object[][] sortedTable = sortTable(table);
        StringBuilder wordEncrypted = new StringBuilder();
        for (int i = 0; i < sortedTable[0].length; i++) {
            for (int j = 1; j < sortedTable.length; j++) {
                wordEncrypted.append(sortedTable[j][i]);
            }
        }
        return wordEncrypted.toString();
    }

    /**
     * Encrypts a certain String with the Columnar Transposition Cipher Rule
     *
     * @param word Word being encrypted
     * @param keyword String with keyword being used
     * @param abecedarium String with the abecedarium being used. null for
     * default one
     * @return a String with the word encrypted by the Columnar Transposition
     * Cipher Rule
     */
    public static String encrypt(String word, String keyword, String abecedarium) {
        ColumnarTranspositionCipher.keyword = keyword;
        ColumnarTranspositionCipher.abecedarium = Objects.requireNonNullElse(abecedarium, ABECEDARIUM);
        table = tableBuilder(word);
        Object[][] sortedTable = sortTable(table);

        StringBuilder wordEncrypted = new StringBuilder();
        for (int i = 0; i < sortedTable[0].length; i++) {
            for (int j = 1; j < sortedTable.length; j++) {
                wordEncrypted.append(sortedTable[j][i]);
            }
        }
        return wordEncrypted.toString();
    }

    /**
     * Decrypts a certain encrypted String with the Columnar Transposition
     * Cipher Rule
     *
     * @return a String decrypted with the word encrypted by the Columnar
     * Transposition Cipher Rule
     */
    public static String decrypt() {
        StringBuilder wordDecrypted = new StringBuilder();
        for (int i = 1; i < table.length; i++) {
            for (Object item : table[i]) {
                wordDecrypted.append(item);
            }
        }
        return wordDecrypted.toString().replaceAll(ENCRYPTION_FIELD, "");
    }

    /**
     * Builds a table with the word to be encrypted in rows by the Columnar
     * Transposition Cipher Rule
     *
     * @return An Object[][] with the word to be encrypted filled in rows and
     * columns
     */
    private static Object[][] tableBuilder(String word) {
        Object[][] table = new Object[numberOfRows(word) + 1][keyword.length()];
        char[] wordInChars = word.toCharArray();
        // Fills in the respective numbers for the column
        table[0] = findElements();
        int charElement = 0;
        for (int i = 1; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (charElement < wordInChars.length) {
                    table[i][j] = wordInChars[charElement];
                    charElement++;
                } else {
                    table[i][j] = ENCRYPTION_FIELD_CHAR;
                }
            }
        }
        return table;
    }

    /**
     * Determines the number of rows the table should have regarding the
     * Columnar Transposition Cipher Rule
     *
     * @return an int with the number of rows that the table should have in
     * order to respect the Columnar Transposition Cipher Rule.
     */
    private static int numberOfRows(String word) {
        if (word.length() % keyword.length() != 0) {
            return (word.length() / keyword.length()) + 1;
        } else {
            return word.length() / keyword.length();
        }
    }

    /**
     * @return charValues
     */
    private static Object[] findElements() {
        Object[] charValues = new Object[keyword.length()];
        for (int i = 0; i < charValues.length; i++) {
            int charValueIndex = abecedarium.indexOf(keyword.charAt(i));
            charValues[i] = charValueIndex > -1 ? charValueIndex : null;
        }
        return charValues;
    }

    /**
     * @return tableSorted
     */
    private static Object[][] sortTable(Object[][] table) {
        Object[][] tableSorted = new Object[table.length][table[0].length];
        for (int i = 0; i < tableSorted.length; i++) {
            System.arraycopy(table[i], 0, tableSorted[i], 0, tableSorted[i].length);
        }
        for (int i = 0; i < tableSorted[0].length; i++) {
            for (int j = i + 1; j < tableSorted[0].length; j++) {
                if ((int) tableSorted[0][i] > (int) table[0][j]) {
                    Object[] column = getColumn(tableSorted, tableSorted.length, i);
                    switchColumns(tableSorted, j, i, column);
                }
            }
        }
        return tableSorted;
    }

    /**
     * @return columnArray
     */
    private static Object[] getColumn(Object[][] table, int rows, int column) {
        Object[] columnArray = new Object[rows];
        for (int i = 0; i < rows; i++) {
            columnArray[i] = table[i][column];
        }
        return columnArray;
    }

    private static void switchColumns(Object[][] table, int firstColumnIndex, int secondColumnIndex, Object[] columnToSwitch) {
        for (int i = 0; i < table.length; i++) {
            table[i][secondColumnIndex] = table[i][firstColumnIndex];
            table[i][firstColumnIndex] = columnToSwitch[i];
        }
    }

    /**
     * Creates an abecedarium with all available ascii values.
     */
    private static void abecedariumBuilder() {
        StringBuilder t = new StringBuilder();
        for (int i = 0; i < 256; i++) {
            t.append((char) i);
        }
        abecedarium = t.toString();
    }
}
