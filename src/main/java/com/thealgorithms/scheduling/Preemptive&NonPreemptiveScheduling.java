import java.util.*;

class SchedulingAlgorithms 
{

    static class Process 
    {
        int pid;
        int priority;
        int arrivalTime;
        int burstTime;
        int remainingTime;
        int completionTime;
        int waitingTime;
        int turnaroundTime;
        int responseTime;
        boolean completed;

        Process(int pid, int priority, int arrivalTime, int burstTime) 
        {
            this.pid = pid;
            this.priority = priority;
            this.arrivalTime = arrivalTime;
            this.burstTime = burstTime;
            this.remainingTime = burstTime;
            this.completed = false;
        }
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        Process[] processes = new Process[n];
        for (int i = 0; i < n; i++) 
        {
            System.out.println("Process " + (i + 1));
            System.out.print("Enter arrival time: ");
            int at = sc.nextInt();
            System.out.print("Enter burst time: ");
            int bt = sc.nextInt();
            System.out.print("Enter priority (integer): ");
            int priority = sc.nextInt();
            processes[i] = new Process(i + 1, priority, at, bt);
        }

        System.out.println("Choose the scheduling algorithm:");
        System.out.println("1. Non-Preemptive");
        System.out.println("2. Preemptive");
        System.out.print("Enter your choice (1 or 2): ");
        int choice = sc.nextInt();

        switch (choice) 
        {
            case 1:
                nonPreemptive(processes);
                break;
            case 2:
                preemptive(processes);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
    }

    private static void nonPreemptive(Process[] processes) 
    {
        int n = processes.length;
        int completed = 0;
        int currentTime = 0;
        boolean[] isCompleted = new boolean[n];

        while (completed != n) 
        {
            int idx = -1;
            int minBurst = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) 
            {
                if ((processes[i].arrivalTime <= currentTime) && !isCompleted[i]) 
                {
                    if (processes[i].burstTime < minBurst) 
                    {
                        minBurst = processes[i].burstTime;
                        idx = i;
                    }
                    if (processes[i].burstTime == minBurst) 
                    {
                        if (processes[i].priority < processes[idx].priority) 
                        {
                            idx = i;
                        }
                    }
                }
            }

            if (idx != -1) 
            {
                processes[idx].waitingTime = currentTime - processes[idx].arrivalTime;
                currentTime += processes[idx].burstTime;
                processes[idx].completionTime = currentTime;
                processes[idx].turnaroundTime = processes[idx].completionTime - processes[idx].arrivalTime;
                processes[idx].responseTime = processes[idx].waitingTime;
                isCompleted[idx] = true;
                completed++;
            } 
            else 
            {
                currentTime++;
            }
        }

        printResults(processes, "Non-Preemptive SJF (Shortest Job First) Scheduling");
    }

    private static void preemptive(Process[] processes) 
    {
        int n = processes.length;
        int completed = 0;
        int currentTime = 0;
        int minRemainingTime;
        int shortest = -1;
        boolean check = false;

        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] completionTime = new int[n];
        int[] responseTime = new int[n];

        int[] remainingTime = new int[n];
        for (int i = 0; i < n; i++) 
        {
            remainingTime[i] = processes[i].burstTime;
        }

        while (completed != n) 
        {
            minRemainingTime = Integer.MAX_VALUE;
            shortest = -1;
            check = false;

            for (int i = 0; i < n; i++) 
            {
                if ((processes[i].arrivalTime <= currentTime) &&
                    (remainingTime[i] < minRemainingTime) && ( remainingTime[i] > 0)) 
                {
                    minRemainingTime = remainingTime[i];
                    shortest = i;
                    check = true;
                }
            }

            if (!check) 
            {
                currentTime++;
                continue;
            }

            if (responseTime[shortest] == 0) 
            {
                responseTime[shortest] = currentTime - processes[shortest].arrivalTime;
            }

            remainingTime[shortest]--;
            currentTime++;

            if (remainingTime[shortest] == 0) 
            {
                completed++;
                completionTime[shortest] = currentTime;
                turnaroundTime[shortest] = completionTime[shortest] - processes[shortest].arrivalTime;
                waitingTime[shortest] = turnaroundTime[shortest] - processes[shortest].burstTime;
                if (waitingTime[shortest] < 0)
                {
                    waitingTime[shortest] = 0;
                }
            }
        }

        for (int i = 0; i < n; i++) 
        {
            processes[i].completionTime = completionTime[i];
            processes[i].turnaroundTime = turnaroundTime[i];
            processes[i].waitingTime = waitingTime[i];
            processes[i].responseTime = responseTime[i];
        }

        printResults(processes, "Preemptive SRTF (Shortest Remaining Time First) Scheduling");
    }

    private static void printResults(Process[] processes, String title) 
    {
        System.out.println("\n" + title);
        System.out.printf("%-10s%-10s%-15s%-15s%-17s%-15s%-17s%-15s\n",
                          "Process", "Priority", "Arrival Time", "Burst Time",
                          "Completion Time", "Waiting Time", "Turnaround Time", "Response Time\n");

        double totalWT = 0, totalTAT = 0;

        Arrays.sort(processes, (a, b) -> a.pid - b.pid);

        for (Process p : processes) 
        {
            totalWT += p.waitingTime;
            totalTAT += p.turnaroundTime;
            System.out.printf("%-10d%-10d%-15d%-15d%-17d%-15d%-17d%-15d\n",
                              p.pid, p.priority, p.arrivalTime, p.burstTime,
                              p.completionTime, p.waitingTime, p.turnaroundTime, p.responseTime);
        }

        System.out.printf("Average Waiting Time: %.2f\n", totalWT / processes.length);
        System.out.printf("Average Turnaround Time: %.2f\n", totalTAT / processes.length);
    }
}