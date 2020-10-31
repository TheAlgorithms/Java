package strings;

import java.util.*;
public class MinSubstrContainingChars
{
    public static void main(String[] args)
    {
        // Testcases
        System.out.println(smallestSubstrContaining("workforcode","wfc"));
        System.out.println(smallestSubstrContaining("abcdeklmhactr", "abcl"));
        System.out.println(smallestSubstrContaining("SachinTendulkar", "SrT"));

    }

    // Find the smallest length substring of a string containing all the characters in smaller string
    public static String smallestSubstrContaining(String bigString, String smallString)
    {
        HashMap<Character, Integer> targetCharCounts = getCharCounts(smallString);
        int[] substrBounds = getSubstringBounds(bigString, targetCharCounts);
        return getStringFromBounds(bigString, substrBounds);
    }

    // Builds HashMap of characters to its counts
    public static HashMap<Character,Integer> getCharCounts(String str)
    {
        HashMap<Character, Integer> charCounts = new HashMap<>();
        for(char x : str.toCharArray())
        {
            increaseCharCount(x, charCounts);
        }
        return charCounts;
    }

    public static void increaseCharCount(Character x, HashMap<Character,Integer> charCounts)
    {
        charCounts.put(x, charCounts.getOrDefault(x,0)+1);
    }

    public static void decreaseCharCount(char x, HashMap<Character, Integer> charCounts)
    {
        charCounts.put(x, charCounts.get(x)-1);
    }

    // Gets the bounds of substring occurrence
    public static int[] getSubstringBounds(String str, HashMap<Character,Integer> charCounts)
    {
        int[] substrBounds = new int[]{0, (int)Double.POSITIVE_INFINITY};
        HashMap<Character,Integer> substrCharCounts = new HashMap<>();
        int numUniqueChars = charCounts.size();
        int numUniqueCharsDone = 0;
        int left = 0, right = 0;
        while(right < str.length())
        {
            char rightChar = str.charAt(right);
            if(!charCounts.containsKey(rightChar))
            {
                right++;
                continue;
            }
            increaseCharCount(rightChar,substrCharCounts);
            if(substrCharCounts.get(rightChar).equals(charCounts.get(rightChar)))
                numUniqueCharsDone++;
            while(numUniqueCharsDone == numUniqueChars && left <= right)
            {
                substrBounds = getCloserBounds(left, right, substrBounds[0], substrBounds[1]);
                char leftChar = str.charAt(left);
                if(!charCounts.containsKey(leftChar))
                {
                    left++;
                    continue;
                }
                if(substrCharCounts.get(leftChar).equals(charCounts.get(leftChar)))
                    numUniqueCharsDone--;
                decreaseCharCount(leftChar, substrCharCounts);
                left++;
            }
            right++;
        }

        //System.out.println((int)Double.POSITIVE_INFINITY+" "+Arrays.toString(substrBounds));
        return substrBounds;
    }

    public static int[] getCloserBounds(int index1, int index2, int index3, int index4)
    {
        return (index2-index1 < index4-index3) ? new int[]{index1, index2} : new int[]{index3, index4};
    }

    public static String getStringFromBounds(String str, int[] bounds)
    {
        int start = bounds[0], end = bounds[1];
        if(end == (int)Double.POSITIVE_INFINITY)
            return "";
        return str.substring(start, end+1);
    }
}
