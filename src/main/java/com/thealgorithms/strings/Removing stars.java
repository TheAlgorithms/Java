import java.util.Scanner;

public class RemoveStars {
    
    /**
     * Removes stars from the input string by deleting the closest non-star character to the left of each star along with the star itself.
     * 
     * The input string containing stars (*)
     * The resulting string after all stars and their closest left characters are removed
     */
    public static String removeStars(String s1) {
        // Use StringBuilder for efficient string manipulation
        StringBuilder sc = new StringBuilder();

        // Loop through each character in the input string
        for (int i = 0; i < s1.length(); i++) {
            char ch = s1.charAt(i);

            if (ch == '*') {
                // When star is found, remove last character from sc if it exists
                if (sc.length() > 0) {
                    sc.deleteCharAt(sc.length() - 1);
                }
            } else {
                // Append non-star characters to sc
                sc.append(ch);
            }
        }

        // Convert StringBuilder to string and return
        return sc.toString();
    }

    public static void main(String[] args) {
        // Create a Scanner object to read user input from the console
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter a string containing stars
        System.out.print("Input: ");

        // Read the entire line entered by the user
        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            // Call the removeStars method to process the string input
            String output = removeStars(input);

            // Print the resulting string after removing stars and their left characters
            System.out.println("Output: " + output);
        } else {
            // Inform user if no input was provided
            System.out.println("No input provided.");
        }

        // Close the scanner to free resources
        scanner.close();
    }
}
