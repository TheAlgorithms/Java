import  java.io.*;
import  java.util.*;

/* display the most frequent K words in the file and the times it appear 
    in the file – shown in order (ignore case and periods) */

public   class  TopKWords {
    public static void main(String[] a) {
        // you can replace the filePath with yours
        CountWords cw = new CountWords("/Users/lisanaaa/Desktop/words.txt");
        Map<String, Integer> dictionary = cw.getDictionary();  // get the words dictionary: {word: frequency}

        // we change the map to list for convenient sort
        List<Map.Entry<String, Integer>> list = new ArrayList<>(dictionary.entrySet());

        // sort by lambda valueComparator
        list.sort(Comparator.comparing(
                m -> m.getValue())
        );

        Scanner input = new Scanner(System.in);
        Integer k = new Integer(input.nextLine());
        while (k > list.size()) {
            System.out.println("Retype a number, your number is too large");
            input = new Scanner(System.in);
            k = new Integer(input.nextLine());
        }
        for (int i = 0; i<k; i++) {
            System.out.println(list.get(list.size()-i-1));
        }
    }
}

class  CountWords {
    private String fileName;

    public CountWords(String fileName) {
        this.fileName = fileName;
    }

    public Map<String, Integer> getDictionary() {
        Map<String, Integer> dictionary = new HashMap<>();
        FileInputStream fis = null;

        try {

            fis = new FileInputStream(fileName);  // open the file
            int in = 0;
            StringBuffer sb = new StringBuffer();  // load the word
            in = fis.read();  // read one character
            boolean notEnd = true;  // signal whether is the end of file

            while (notEnd) {
                // when in == -1 means get the end of the file
                if (-1 == in) {
                    notEnd = false;  //if false, end the while loop
                }
                if (Character.isLetter((char)in)) {
                    sb.append((char)in);  //if get a letter, put it in StringBuffer
                } else {
                    // this branch means an entire word has just been read
                    if (sb.length() > 0) {
                        //see whether word exists in StringBuffer or not
                        if (dictionary.containsKey(sb.toString())) {
                            //if exist, count++
                            dictionary.put(sb.toString(), dictionary.get(sb.toString()) + 1);
                        } else {
                            // if not exist, initiate count of this word with 1
                            dictionary.put(sb.toString(), 1);
                        }
                    }
                    sb = new StringBuffer(); //reload the StringBuffer
                }
                in = fis.read(); //read the character
            }
            return dictionary;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                // you always have to close the I/O streams
                fis.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
