import java.util.*;

public class KthOccurring {
    public static void mostOccurred(int[] arr, int k) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : arr) {
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>(
            (a, b) -> b.getValue().compareTo(a.getValue())
        );

        maxHeap.addAll(hashMap.entrySet());

        for (int i = 0; i < k && !maxHeap.isEmpty(); i++) {
            System.out.println(maxHeap.poll().getKey());
        }
    }

    public static void main(String[] args) {
        int arr[] = {3, 1, 4, 4, 5, 2, 2, 1, 3, 3};
        int k = 3;
        mostOccurred(arr, k);
    }
}
