package Conversions;

/**
 * Converting Integers into Roman Numerals
 *
 *('I', 1);
 *('IV',4);
 *('V', 5);
 *('IV',9);
 *('X', 10);
 *('XL',40;
 *('L', 50);
 *('XC',90);
 *('C', 100);
 *('D', 500);
 *('M', 1000);
 *
 */


public class IntegerToRoman {
    private static int[] allArabianRomanNumbers = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static String[] allRomanNumbers = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    //Value must be > 0

    public static String integerToRoman(int num) {
        if (num <= 0) {
            return "";
        }

        StringBuilder builder = new StringBuilder();

        for (int a = 0; a < allArabianRomanNumbers.length; a++) {
            int times = num / allArabianRomanNumbers[a];
            for (int b = 0; b < times; b++) {
                builder.append(allRomanNumbers[a]);
            }

            num -= times * allArabianRomanNumbers[a];
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(IntegerToRoman.integerToRoman(2131));
    }
}
