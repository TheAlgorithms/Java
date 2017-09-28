package conversions;

/**
 * Converts any Octal Number to a Decimal Number
 *
 * @author Zachary Jones
 */
public class OctalToDecimal {

    /**
     * This method converts an octal number to
     * a decimal number.
     *
     * @param o The octal number
     * @return The decimal number
     */
    public static int convertOctalToDecimal(int o) {
        System.out.print("Octal Input: ");
        // Read the input from the console which we are expecting as an octal number:
        try {
            // Actual conversion of Octal to Decimal:
            return Integer.parseInt(String.valueOf(o), 8);
        } catch (NumberFormatException ne) {
            // Printing a warning message if the input is not a valid octal number:
            System.out.println("Invalid Input, Expecting octal number 0-7");
            return 0;
        }
    }
}