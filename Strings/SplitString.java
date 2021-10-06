package Strings;

import java.util.*;

// i have used the same name of the variable in the function too to make it easier to understand
public class SplitString
{
    public static void main(String args[]) {
        String sentence = "Hello everyone this is an example string.";
        
        // Change the value according to which you need to split
        char delimiter = ' '; 
        
        List<String> splitSentence = split(sentence, delimiter);
        
        // Converting the ArrayList to a normal array
        String[] casualSplitArray = new String[splitSentence.size()];
        for (int j = 0; j< splitSentence.size();j++){
            casualSplitArray[j] = splitSentence.get(j);
        }
    }

    /**
      * The main function that splits the sentence
      *
      * @param sentence the main sentence to split 
      * @param delimiter the chracter with the string should split
      */
    public static List<String> split(String sentence, char delimiter) {
        List<String> splitSentence = new ArrayList<String>();
        
        String w = "";
        for(int i = 0;i<sentence.length();i++){
            char ch = sentence.charAt(i);
            
            if(ch == delimiter){
                splitSentence.add(w);
                w = "";
                continue;
            }
            w += Character.toString(ch);
            if (i == sentence.length()-1){
                splitSentence.add(w);
            }
        }
        return splitSentence;   
    }
}
