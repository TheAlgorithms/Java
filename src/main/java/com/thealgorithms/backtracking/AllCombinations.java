import java.util.*;

public class AllCombinations {
    public static void main(String[] args) {
        int n = 5;
        int k = 3;
        List<List<Integer>> result = generateCombinations(n, k);
        System.out.println(result);
    }

    public static List<List<Integer>> generateCombinations(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), 1, n, k);
        return result;
    }

    public static void backtrack(List<List<Integer>> result, List<Integer> temp, int start, int n, int k) {
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
}
