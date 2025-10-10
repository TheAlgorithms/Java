import java.util.*;

public class TaskScheduler {

    public static int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char c : tasks) {
            freq[c - 'A']++;
        }
        
        Arrays.sort(freq);
        int maxFreq = freq[25];
        int idleSlots = (maxFreq - 1) * n;

        // Fill idle slots with remaining tasks
        for (int i = 24; i >= 0 && idleSlots > 0; i--) {
            idleSlots -= Math.min(freq[i], maxFreq - 1);
        }

        idleSlots = Math.max(0, idleSlots);

        return tasks.length + idleSlots;
    }

    public static void main(String[] args) {
        char[] tasks1 = {'A','A','A','B','B','B'};
        int n1 = 2;
        System.out.println("Output 1: " + leastInterval(tasks1, n1)); // 8

        char[] tasks2 = {'A','C','A','B','D','B'};
        int n2 = 1;
        System.out.println("Output 2: " + leastInterval(tasks2, n2)); // 6

        char[] tasks3 = {'A','A','A','B','B','B'};
        int n3 = 0;
        System.out.println("Output 3: " + leastInterval(tasks3, n3)); // 6
    }
}
