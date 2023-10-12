import java.io.*;
import java.util.*;

public class Top_k_Frequent_Elements {
    // Create a HashMap to store the frequency of each element
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Convert the HashMap entries to a list of Map.Entry objects
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());

        // Sort the list in descending order of frequencies
        list.sort((a, b) -> b.getValue() - a.getValue());

        // Extract the top k frequent elements
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(list.get(i).getKey());
        }

        return result;
    }

    public static void main(String[] args) {
        Top_k_Frequent_Elements solution = new Top_k_Frequent_Elements();
        Scanner scanner = new Scanner(System.in);

        // Input the number of elements in the array
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();

        int[] nums = new int[n];
        System.out.println("Enter the elements of the array:");

        // Input the elements of the array
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        // Input the value of k
        System.out.print("Enter the value of k: ");
        int k = scanner.nextInt();

        // Find and print the top k frequent elements
        List<Integer> topK = solution.topKFrequent(nums, k);
        System.out.println("Top " + k + " frequent elements: " + topK);
    }
}
