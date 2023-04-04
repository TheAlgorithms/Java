
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
This class generates all possible combinations of length k from a set of integers 1 to n.
*/
public class AllCombinations {

    /**
     Generates all possible combinations of k numbers out of 1...n.
     @param n The maximum number to consider for the combinations.
     @param k The size of each combination.
     @return A list of all possible combinations of k numbers out of 1....n.
     */
    public static List<List<Integer>> generateCombinations(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), 1, n, k);
        return result;
    }

    /**

     Helper method to generate all possible combinations of k numbers out of 1....n.
     @param result A list to store all the generated combinations.
     @param temp A temporary list to store each combination during the recursive process.
     @param start The starting number to consider for the current combination.
     @param n The maximum number to consider for the combinations.
     @param k The size of each combination.
     */
    private static void backtrack(List<List<Integer>> result, List<Integer> temp, int start, int n, int k) {
        if (temp.size() == k) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i <= n; i++) {
            temp.add(i);
            backtrack(result, temp, i + 1, n, k);
            temp.remove(temp.size() - 1);
        }
    }
    @Test
    public void testGenerateCombinations() {
        List<List<Integer>> expected = List.of(
                List.of(1, 2, 3),
                List.of(1, 2, 4),
                List.of(1, 2, 5),
                List.of(1, 3, 4),
                List.of(1, 3, 5),
                List.of(1, 4, 5),
                List.of(2, 3, 4),
                List.of(2, 3, 5),
                List.of(2, 4, 5),
                List.of(3, 4, 5)
        );
        List<List<Integer>> actual = AllCombinations.generateCombinations(5, 3);
        assertEquals(expected, actual);
    }
}
