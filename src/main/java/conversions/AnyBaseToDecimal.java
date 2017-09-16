package conversions;

/**
 * @author Varun Upadhyay (https://github.com/varunu28)
 */
public class AnyBaseToDecimal {

    /**
     * This method produces a decimal value of any given input number of any base
     *
     * @param inputNumber String of which we need the decimal value and base in integer format
     * @return string format of the decimal value
     */
    public static String convertToDecimal(String inputNumber, int base) {
        int len = inputNumber.length();
        int num = 0;
        int pow = 1;
        for (int i = len - 1; i >= 0; i--) {
            if (valOfChar(inputNumber.charAt(i)) >= base) {
                return "Invalid Number";
            }
            num += valOfChar(inputNumber.charAt(i)) * pow;
            pow *= base;
        }
        return String.valueOf(num);
    }

    /**
     * This method produces integer value of the input character and returns it
     *
     * @param input Char of which we need the integer value of
     * @return integer value of input char
     */
    private static int valOfChar(char input) {
        if (input >= '0' && input <= '9') {
            return (int) input - '0';
        } else {
            return (int) input - 'A' + 10;
        }
    }
}
