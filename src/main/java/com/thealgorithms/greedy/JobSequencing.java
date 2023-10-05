import java.util.Arrays;
import java.util.Comparator;

// Job structure for Job Sequencing Problem
class Job {
    char id;
    int deadline, profit;

    Job(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class JobSequencing {
    // Function to schedule jobs and print the schedule
    public static void scheduleJobs(Job[] jobs) {
        int n = jobs.length;

        // Sort jobs in decreasing order of profit
        Arrays.sort(jobs, Comparator.comparingInt(job -> -job.profit));

        int maxDeadline = Arrays.stream(jobs).mapToInt(job -> job.deadline).max().orElse(0);
        char[] result = new char[maxDeadline];
        Arrays.fill(result, ' ');

        int totalProfit = 0;
        for (Job job : jobs) {
            for (int i = Math.min(maxDeadline - 1, job.deadline - 1); i >= 0; i--) {
                if (result[i] == ' ') {
                    result[i] = job.id;
                    totalProfit += job.profit;
                    break;
                }
            }
        }

        // Print the schedule and total profit
        System.out.println("Job Schedule:");
        for (char jobId : result) {
            if (jobId != ' ') {
                System.out.print(jobId + " ");
            }
        }
        System.out.println("\nTotal Profit: " + totalProfit);
    }

    public static void main(String[] args) {
        Job[] jobs = {
            new Job('a', 2, 100),
            new Job('b', 1, 19),
            new Job('c', 2, 27),
            new Job('d', 1, 25),
            new Job('e', 3, 15)
        };

        scheduleJobs(jobs);
    }
}

// These examples illustrate the use of greedy algorithms to solve various problems.
// Greedy algorithms make locally optimal choices at each stage, aiming to find a global optimum.
// However, not all problems can be solved optimally using greedy algorithms, so careful analysis is required to determine if the greedy approach is suitable.
