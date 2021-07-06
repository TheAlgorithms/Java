package Others;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/* display the most frequent K words in the file and the times it appear
in the file – shown in order (ignore case and periods) */

public class TopKWords {
    static class CountWords {
        private final String fileName;

        public CountWords(String fileName) {
            this.fileName = fileName;
        }

        public Map<String, Integer> getDictionary() {
            Map<String, Integer> dictionary = new HashMap<>();
            try (FileInputStream fis = new FileInputStream(fileName)) {
                int in;
                StringBuilder s = new StringBuilder(); // init a empty word
                in = fis.read(); // read one character

                while (-1 != in) {
                    if (Character.isLetter((char) in)) {
                        s.append((char) in); // if get a letter, append to s
                    } else {
                        // this branch means an entire word has just been read
                        if (s.length() > 0) {
                            // count frequency of this word
                            dictionary.put(s.toString(), dictionary.getOrDefault(s.toString(), 0) + 1);
                        }
                        s.setLength(0);
                    }
                    in = fis.read();
                }
                return dictionary;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static void main(String[] args) {
        // you can replace the filePath with yours
        CountWords cw = new CountWords("/Users/lisanaaa/Desktop/words.txt");
        Map<String, Integer> dictionary =
                cw.getDictionary(); // get the words dictionary: {word: frequency}

        // we change the map to list for convenient sort
        List<Map.Entry<String, Integer>> list = new ArrayList<>(dictionary.entrySet());

        // sort by lambda valueComparator
        list.sort(Map.Entry.comparingByValue());

        Scanner input = new Scanner(System.in);
        int k = input.nextInt();
        while (k > list.size()) {
            System.out.println("Retype a number, your number is too large");
            input = new Scanner(System.in);
            k = input.nextInt();
        }
        for (int i = 0; i < k; i++) {
            System.out.println(list.get(list.size() - i - 1));
        }
        input.close();
    }
}
