package array.security;

import java.util.Scanner;

/**
 * @author Grzegorz Kawalec
 */
public class ProjectAlgorithmsScanner {

    private static Scanner scanner = new Scanner(System.in);

    public static int getInteger() {
        try {
            return scanner.nextInt();
        } catch (Exception ex) {
            System.err.println("The value entered is not an integer. Returns 0.");
            return 0;
        }
    }

    public static int getIntegerWithException() throws ProjectAlgorithmsException {
        try {
            return scanner.nextInt();
        } catch (Exception ex) {
            throw new ProjectAlgorithmsException("The value entered is not an integer.");
        }
    }

}
