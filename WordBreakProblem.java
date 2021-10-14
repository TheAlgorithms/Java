// For a given string and dictionary of words determine if the string can be segmented into a sequence of one or more dictionary words.
//belove is the implementation of for this problem using dynamic programming

// time complexity of the belove implementation is exponential and occupies space in call stack

package DynamicProgramming;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordBreakProblem {
	
	 public static boolean wordBreak(Set<String> dict, String word, int[] lookup)
	    {
	        // `n` stores length of the current substring
	        int n = word.length();
	 
	        // return true if the end of the string is reached
	        if (n == 0) {
	            return true;
	        }
	 
	        // if the subproblem is seen for the first time
	        if (lookup[n] == -1)
	        {
	            // mark subproblem as seen (0 initially assuming string
	            // can't be segmented)
	            lookup[n] = 0;
	 
	            for (int i = 1; i <= n; i++)
	            {
	                // consider all prefixes of the current string
	                String prefix = word.substring(0, i);
	 
	                // if the prefix is found in the dictionary, then recur for the suffix
	                if (dict.contains(prefix) && wordBreak(dict, word.substring(i), lookup))
	                {
	                    // return true if the string can be segmented
	                    lookup[n] = 1;
	                    return true;
	                }
	            }
	        }
	 
	        // return solution to the current subproblem
	        return lookup[n] == 1;
	    }

	public static void main(String[] args) {
	 // Set of strings to represent a dictionary
	Set<String> dict = Stream.of("this", "th", "is", "famous","Word", "break", "b", "r", "e", "a", "k","br", "bre", "brea", "ak", "problem")
            .collect(Collectors.toSet());

    // input string
    String word = "Wordbreakproblem";

    // lookup array to store solutions to subproblems
    // `lookup[i]` stores if substring `word[n-i…n)` can be segmented or not
    int[] lookup = new int[word.length() + 1];
    	Arrays.fill(lookup, -1);

	    if (wordBreak(dict, word, lookup)) {
	        System.out.print("The string can be segmented");
	    }
	    else {
	        System.out.print("The string can't be segmented");
	    }

	}

}
