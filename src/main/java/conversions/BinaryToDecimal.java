package conversions;

/**
 * This class converts a Binary number to a Decimal number
 *
 * @author Unknown
 */
public class BinaryToDecimal {

    public static int toDecimal(int binary) {
        int d, answer = 0, count = 0;
        while (binary != 0) {
            d = binary % 10;
            answer += d * (int) Math.pow(2, count++);
            binary /= 10;
        }
        return answer;
    }
}
