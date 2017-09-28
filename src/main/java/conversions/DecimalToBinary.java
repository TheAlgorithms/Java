package conversions;

/**
 * This class converts a Decimal number to a Binary number
 *
 * @author Unknown
 */
public class DecimalToBinary {

    public static int convertToBinary(int decimal) {
        int d, s = 0, c = 0;
        while (decimal != 0) {
            d = decimal % 2;
            s = s + d * (int) Math.pow(10, c++);
            decimal /= 2;
        }
        return s;
    }
}
