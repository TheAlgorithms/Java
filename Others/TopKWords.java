package Others;

import java.io.*;
import java.util.*;

/* display the most frequent K words in the file and the times it appear
    in the file – shown in order (ignore case and periods) */

public class TopKWords {
    static class CountWords {
        private String fileName;

        public CountWords(String fileName) {
            this.fileName = fileName;
        }

        public Map<String, Integer> getDictionary() {
            Map<String, Integer> dictionary = new HashMap<>();

            //Java 7's try-with-resources structure automatically handles closing the resources that the try itself opens.
            //Thus, adding an explicit close() call is redundant and potentially confusing.
            try (FileInputStream fis = new FileInputStream(fileName)) {
                StringBuilder stringBuilder = new StringBuilder();  // init a empty word
                int in = fis.read();  // read one character

                while (-1 != in) {
                    if (Character.isLetter((char) in)) {
                        stringBuilder.append(in);  //if get a letter, append to s
                    } else {
                        // this branch means an entire word has just been read
                        String word = stringBuilder.toString();
                        if (word.length() > 0) {
                            // see whether word exists or not
                            if (dictionary.containsKey(word)) {
                                // if exist, count++
                                dictionary.put(word, dictionary.get(word) + 1);
                            } else {
                                // if not exist, initiate count of this word with 1
                                dictionary.put(word, 1);
                            }
                        }
                        stringBuilder = new StringBuilder(); // reInit a empty word
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
        Map<String, Integer> dictionary = cw.getDictionary();  // get the words dictionary: {word: frequency}

        // we change the map to list for convenient sort
        List<Map.Entry<String, Integer>> list = new ArrayList<>(dictionary.entrySet());

        // sort by lambda valueComparator
        list.sort(Map.Entry.comparingByValue());

        Scanner input = new Scanner(System.in);
        int inputInteger = input.nextInt();
        while (inputInteger > list.size()) {
            System.out.println("Retype a number, your number is too large");
            input = new Scanner(System.in);
            inputInteger = input.nextInt();
        }

        for (int i = 0; i < inputInteger; i++) {
            System.out.println(list.get(list.size() - i - 1));
        }
        input.close();
    }
}

