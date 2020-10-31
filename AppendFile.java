#Append text in exiting file
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class AppendFile {

    public static void main(String[] args) {

        String path = System.getProperty("user.dir") + "\\src\\test.txt";
        String text = "Added text";

        try {
            Files.write(Paths.get(path), text.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
        }
    }
}
