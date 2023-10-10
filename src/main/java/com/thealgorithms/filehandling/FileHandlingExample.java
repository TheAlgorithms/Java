import java.io.*;
public class FileHandlingExample {

    // Function to read and print the contents of a file
    public static void printFileContents(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    // Function to write content to a file
    public static void writeFileContents(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String filePath = "example.txt";

        // Writing content to the file
        String contentToWrite = "Hello, World!\nThis is a file handling example.";
        writeFileContents(filePath, contentToWrite);

        // Reading and printing the contents of the file
        System.out.println("File Contents:");
        printFileContents(filePath);
    }
}
