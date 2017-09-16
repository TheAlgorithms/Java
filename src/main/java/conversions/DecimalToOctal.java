package conversions;

/**
 * This class converts Decimal numbers to Octal Numbers
 *
 * @author Unknown
 */
public class DecimalToOctal {

    public static int toOctal(int decimal) {
        int d, s = 0, c = 0;
        while (decimal != 0) {
            d = decimal % 8;
            s += d * (int) Math.pow(10, c++);
            decimal /= 8;
        }
        return s;
    }
}
