package numerical;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.StringTokenizer;

/**
 * This class contain just static methods for numerical-systems and it's uses ,
 * The allowed systems (bases) is from 1 to 36 because 10 normal base and 26 is
 * the number of the Latin letters.
 *
 * those methodology based on book :Prentice Digital Design With an Introduction
 * to the Verilog HDL 5th Edition.
 *
 * @see Prentice Digital Design With an Introduction to the Verilog HDL 5th
 * Edition.
 * @author <a href="https://github.com/khalil2535">khalil2535</a>
 */
public class NumeralSystems {

    /**
     * The allowed characters for using in this class
     */
    public static final char[] ALLOWED_CHARACTERS = {'0', '1', '2', '3', '4',
        '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
        'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
        'X', 'Y', 'Z'};

    /**
     *
     * @param args
     * @deprecated TODO remove main method and Test using JUnit or other
     * methodology
     */
    public static void main(String[] args) {
        System.out.println(changeRadix("-JBD.0GEJ9LH36K17BHIAFAKJ", 22, 32));
    }

    /**
     * This method check if the number can be used at the input radix (base)
     *
     * @param number the number we want to check it's base
     * @param radix the base to check
     * @return true if the every number digit less than radix
     */
    public static boolean checkRadix(String number, int radix) {
        if (radix <= 0) {
            return false;
        }
        String allowedCharaters
                = new String(ALLOWED_CHARACTERS).substring(0, radix);

        if (number.charAt(0) == '-') {
            number = number.substring(1);
        }
        if (number.contains("-")) {
            return false;
        }
        boolean Integer = true;
        char[] numberCharaters = number.toUpperCase().toCharArray();
        for (char numberCharater : numberCharaters) {
            if (allowedCharaters.indexOf(numberCharater) < 0) {
                if (Integer && numberCharater == '.') {
                    Integer = false;
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    /**
     * This method check if the number can be used at the input radix (base)
     *
     * @param number the number we want to check it's base
     * @param radix the base to check
     * @return true if the every number digit less than radix
     */
    public static boolean checkRadix(int number, int radix) {
        return checkRadix(String.valueOf(number), radix);
    }

    /**
     * This method check if the number can be used at the input radix (base)
     *
     * @param number the number we want to check it's base
     * @param radix the base to check
     * @return true if the every number digit less than radix
     */
    public static boolean checkRadix(double number, int radix) {
        // because It's rounding after 15 digit
        return checkRadix(new BigDecimal(number).toPlainString()
                .substring(0, 14), radix);
    }

    /**
     * This method check if the number can be used at the input radix (base)
     *
     * @param number the number we want to check it's base
     * @param radix the base to check
     * @return true if the every number digit less than radix
     */
    public static boolean checkRadix(float number, int radix) {
        // because It's rounding after that
        return checkRadix(new BigDecimal(number).toPlainString()
                .substring(0, 7), radix);
    }

    /**
     * This method check if the number can be used at the input radix (base)
     *
     * @param number the number we want to check it's base
     * @param radix the base to check
     * @return true if the every number digit less than radix
     */
    public static boolean checkRadix(long number, int radix) {
        return checkRadix(String.valueOf(number), radix);
    }

    /**
     * this method change the radix (base) of the number from any allowed base
     * to 10 (Decimal)
     *
     * @param number in any base
     * @param from the current base for the number
     * @return number in decimal -radix(base) 10-
     */
    public static String toDecimal(String number, int from) {
        if (!checkRadix(number, from)) {
            throw new IllegalArgumentException(
                    "The radix you've entered is incorrect");
        }
        boolean negative = number.charAt(0) == '-';

        if (negative) {
            number = number.substring(1);
        }

        String charcters
                = new String(ALLOWED_CHARACTERS).substring(0, from);

        BigDecimal result = new BigDecimal(0);
        String positiveNum = "";
        String negativeNum = "";

        StringTokenizer num = new StringTokenizer(number + "", ".");
        if (num.hasMoreTokens()) {
            positiveNum = num.nextToken();
        }
        if (num.hasMoreTokens()) {
            negativeNum = num.nextToken();
        }
        while (positiveNum.length() > 0) {
            result = result.add(new BigDecimal(
                    charcters.indexOf(positiveNum.charAt(0))).multiply(
                    bigDecimalPow(new BigDecimal(from),
                            positiveNum.length() - 1)));

            positiveNum = positiveNum.substring(1);
        }
        int x = -1;
        while (negativeNum.length() > 0) {
            result = result.add(new BigDecimal(
                    charcters.indexOf(negativeNum.charAt(0))).multiply(
                    bigDecimalPow(new BigDecimal(from), x--)));

            negativeNum = negativeNum.substring(1);
        }

        return negative ? '-' + result.toPlainString() : result.toPlainString();
    }

    /**
     * private method to make a power for Decimal numbers because pow method in
     * BigDecimal class throws error when the power is less than 1
     *
     * @param number to make power for
     * @param pow the power
     * @return number^(pow)
     */
    private static BigDecimal bigDecimalPow(BigDecimal number, int pow) {
        return pow > 0 ? number.pow(pow)
                : new BigDecimal(BigInteger.ONE).divide(
                        number.pow(-pow), 50, RoundingMode.CEILING);
    }

    /**
     * Change from decimal to any base you choose.
     *
     * @param number in decimal
     * @param newRadix
     * @return number in the new Radix
     */
    public static String decimalToAnyBase(BigDecimal number, int newRadix) {
        if (!checkRadix(number.toPlainString(), 10)) {
            throw new IllegalArgumentException(
                    "The number you've entered isn't decimal");
        }
        if (newRadix <= 1) {
            throw new IllegalArgumentException(
                    "Can't change from radix less than 2");
        }
        String charcters
                = new String(ALLOWED_CHARACTERS).substring(0, newRadix);
        boolean negative = false;
        if (number.signum() == 0) {
            return "0";
        } else if (number.signum() < 0) {
            number = (number.subtract(number)).subtract(number);
            negative = true;
        }

        StringBuilder result = new StringBuilder(10);

        BigDecimal IntegerNum = new BigDecimal(number.toBigInteger());
        BigDecimal fraction = number.subtract(IntegerNum);
        final BigDecimal radix = new BigDecimal(newRadix);

        while (IntegerNum.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal dividedNum
                    = IntegerNum.divide(radix, 50, RoundingMode.CEILING);

            IntegerNum = new BigDecimal(dividedNum.toBigInteger());

            result.append(charcters.charAt(
                    dividedNum.subtract(IntegerNum)
                            .multiply(radix).intValue()));
        }

        if (negative) {
            result.append("-");
        }

        result = result.reverse();
        if (fraction.compareTo(BigDecimal.ZERO) != 0) {
            result.append(".");
            int requestDigits = 50;
            while (fraction.compareTo(BigDecimal.ZERO) != 0
                    && requestDigits > 0) {

                BigDecimal multiplied = fraction.multiply(radix);

                fraction = multiplied.subtract(
                        new BigDecimal(multiplied.toBigInteger()));

                result.append(charcters.charAt(
                        multiplied.subtract(fraction).intValue()));
                requestDigits--;
            }
        }

        return result.toString();
    }

    /**
     * Change the number from radix(base) 10 to any radix you want.
     *
     * @param number Decimal number
     * @param newRadix
     * @return number in the newBase
     */
    public static String decimalToAnyBase(String number, int newRadix) {
        return decimalToAnyBase(new BigDecimal(number), newRadix);
    }

    /**
     * Change the radix(base) of any number to any other.
     *
     * @param number want to change radix
     * @param from the old radix
     * @param to the new Radix
     * @return number in the new Radix (to)
     */
    public static String changeRadix(String number, int from, int to) {
        return decimalToAnyBase(toDecimal(number, from), to);
    }

    private NumeralSystems() {
    }

}
