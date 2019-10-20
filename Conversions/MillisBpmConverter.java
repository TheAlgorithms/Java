import java.security.InvalidParameterException;
import java.util.Scanner;

/**
 * Handles the convertion between an interval expressed in the number of milliseconds from
 * one beat and the next and an interval expressed in the number of beats per minute (bpm).
 */
public class MillisBpmConverter {

    /**
     * Converts an interval expressed in milliseconds to an interval expressed in beats per minute.
     *
     * @param millis The number of milliseconds from a quarter note to the next.
     * @return The number of beats per minute that corresponds to the interval in milliseconds.
     * @throws InvalidParameterException if the parameter is less than or equal to zero.
     */
    public static int millisToBpm(long millis) throws InvalidParameterException {

        if (millis > 0) {

            return (int) (Math.round((double) 60000 / millis));

        } else {

            throw new InvalidParameterException("The number of milliseconds to convert has to be greater than 0");

        }

    }

    /**
     * Converts an interval expressed in beats per minute to an interval expressed in milliseconds.
     *
     * @param bpm The number of beats per minute that expresses the interval between a beat and the next.
     * @return The number of milliseconds from one quarter note to the next that corresponds to the interval in beats per minute.
     * @throws InvalidParameterException if the parameter is less than or equal to zero.
     */
    public static long bpmToMillis(int bpm) {

        if (bpm > 0) {

            return Math.round((double) 60000 / bpm);

        } else {

            throw new InvalidParameterException("The number of beats per minute has to be greater than 0");

        }

    }

    /**
     * Asks the user to convert milliseconds to bpm or bpm to milliseconds, then proceeds to ask the
     * user to input the respective number and shows the result of the conversion to standard output.
     *
     * @param args The arguments passed via command line.
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String programChoice = "";
        while (!programChoice.equalsIgnoreCase("q")) {

            System.out.print("Would you like to convert a time interval from milliseconds to bpm (0) or from bpm to milliseconds (1) [Type q to quit]? ");
            programChoice = scanner.nextLine();
            switch (programChoice.trim()) {

                case "0":
                    System.out.print("Insert the number of milliseconds from one quarter note to the next: ");
                    String millisString = scanner.nextLine();
                    try {
                        long millis = Long.parseLong(millisString);
                        int bpm = millisToBpm(millis);
                        System.out.println(String.format("%1d milliseconds are equal to %2d bpm", millis, bpm));
                    } catch (NumberFormatException | InvalidParameterException ex) {
                        System.out.println(String.format("The inserted number of milliseconds (%1s) is invalid", millisString));
                    }
                    break;

                case "1":
                    System.out.print("Insert the number of beats per minute that represent the interval: ");
                    String bpmString = scanner.nextLine();
                    try {
                        int bpm = Integer.parseInt(bpmString);
                        long millis = bpmToMillis(bpm);
                        System.out.println(String.format("%1d beats per minute are equal to %2d milliseconds", bpm, millis));
                    } catch (NumberFormatException | InvalidParameterException ex) {
                        System.out.println(String.format("The inserted number of beats per minute (%1s) is invalid", bpmString));
                    }
                    break;

                case "q":
                    break;

                default:
                    System.out.println(String.format("The inserted program (%1s) is not a valid selection.", programChoice));
                    break;

            }

        }

        scanner.close();

    }

}