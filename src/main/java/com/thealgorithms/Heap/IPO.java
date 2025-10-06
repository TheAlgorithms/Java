import java.util.*;

public class IPO {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int[][] projects = new int[n][2];
        
        // Combine capital and profit for each project
        for (int i = 0; i < n; i++) {
            projects[i][0] = capital[i];
            projects[i][1] = profits[i];
        }

        // Sort projects by required capital (ascending)
        Arrays.sort(projects, Comparator.comparingInt(a -> a[0]));

       
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        int i = 0;
        for (int t = 0; t < k; t++) {
            // Add all affordable projects to the heap
            while (i < n && projects[i][0] <= w) {
                maxHeap.offer(projects[i][1]);
                i++;
            }

            // If no project is affordable, break early
            if (maxHeap.isEmpty()) break;

                                  
            w += maxHeap.poll();  // Pick the most profitable project
        }

        return w;
    }

    public static void main(String[] args) {
        IPO obj = new IPO();
        int k = 2, w = 0;
        int[] profits = {1, 2, 3};
        int[] capital = {0, 1, 1};

        System.out.println(obj.findMaximizedCapital(k, w, profits, capital)); // Output: 4
    }
}
