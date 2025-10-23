import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

class SchedulingAlgorithms {
    static class Process {
        private int pid;
        private int priority;
        private int arrivalTime;
        private int burstTime;
        private int remainingTime;
        private int completionTime;
        private int waitingTime;
        private int turnaroundTime;
        private int responseTime;

        Process(int pid, int priority, int arrivalTime, int burstTime) {
            this.pid = pid;
            this.priority = priority;
            this.arrivalTime = arrivalTime;
            this.burstTime = burstTime;
            this.remainingTime = burstTime;
            this.responseTime = -1;
        }
        public int getPid() { return pid; }
        public int getPriority() { return priority; }
        public int getArrivalTime() { return arrivalTime; }
        public int getBurstTime() { return burstTime; }
        public int getRemainingTime() { return remainingTime; }
        public int getCompletionTime() { return completionTime; }
        public int getWaitingTime() { return waitingTime; }
        public int getTurnaroundTime() { return turnaroundTime; }
        public int getResponseTime() { return responseTime; }

        public void setRemainingTime(int remainingTime) { this.remainingTime = remainingTime; }
        public void setCompletionTime(int completionTime) { this.completionTime = completionTime; }
        public void setWaitingTime(int waitingTime) { this.waitingTime = waitingTime; }
        public void setTurnaroundTime(int turnaroundTime) { this.turnaroundTime = turnaroundTime; }
        public void setResponseTime(int responseTime) { this.responseTime = responseTime; }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter number of processes: ");
            int n = sc.nextInt();

            Process[] processes = new Process[n];
            for (int i = 0; i < n; i++) {
                System.out.println("Process " + (i + 1));
                System.out.print("Enter arrival time: ");
                int at = sc.nextInt();
                System.out.print("Enter burst time: ");
                int bt = sc.nextInt();
                System.out.print("Enter priority (integer): ");
                int priority = sc.nextInt();
                processes[i] = new Process(i + 1, priority, at, bt);
            }

            System.out.println("\nChoose the scheduling algorithm:");
            System.out.println("1. Non-Preemptive (Shortest Job First with Priority)");
            System.out.println("2. Preemptive (Shortest Remaining Time First)");
            System.out.print("Enter your choice (1 or 2): ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    nonPreemptive(processes);
                    break;
                case 2:
                    preemptive(processes);
                    break;
                default:
                    System.out.println("Invalid choice. Exiting.");
                    return;
            }
        }
    }
    
    private static void nonPreemptive(Process[] processes) {
        int n = processes.length;
        int completedProcesses = 0;
        int currentTime = 0;
        boolean[] isCompleted = new boolean[n];

        while (completedProcesses < n) {
            int selectedProcessIndex = -1;
            int minBurstTime = Integer.MAX_VALUE;
            int highestPriority = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (processes[i].getArrivalTime() <= currentTime && !isCompleted[i]) {
                    if (processes[i].getBurstTime() < minBurstTime) {
                        minBurstTime = processes[i].getBurstTime();
                        highestPriority = processes[i].getPriority();
                        selectedProcessIndex = i;
                    } 
                    else if (processes[i].getBurstTime() == minBurstTime) {
                        if (processes[i].getPriority() < highestPriority) {
                            highestPriority = processes[i].getPriority();
                            selectedProcessIndex = i;
                        }
                    }
                }
            }

            if (selectedProcessIndex != -1) {
                Process currentProcess = processes[selectedProcessIndex];
                
                if (currentProcess.getResponseTime() == -1) {
                     currentProcess.setResponseTime(currentTime - currentProcess.getArrivalTime());
                }

                currentProcess.setWaitingTime(currentTime - currentProcess.getArrivalTime());
                currentTime += currentProcess.getBurstTime();
                currentProcess.setCompletionTime(currentTime);
                currentProcess.setTurnaroundTime(currentProcess.getCompletionTime() - currentProcess.getArrivalTime());
                
                isCompleted[selectedProcessIndex] = true;
                completedProcesses++;
            } else {
                currentTime++;
            }
        }

        printResults(processes, "Non-Preemptive SJF (with Priority) Scheduling");
    }

    private static void preemptive(Process[] processes) {
        int n = processes.length;
        int completedProcesses = 0;
        int currentTime = 0;

        while (completedProcesses < n) {
            int shortestJobIndex = -1;
            int minRemainingTime = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (processes[i].getArrivalTime() <= currentTime && processes[i].getRemainingTime() > 0) {
                    if (processes[i].getRemainingTime() < minRemainingTime) {
                        minRemainingTime = processes[i].getRemainingTime();
                        shortestJobIndex = i;
                    }
                }
            }

            if (shortestJobIndex == -1) {
                currentTime++;
                continue;
            }

            Process currentProcess = processes[shortestJobIndex];

            if (currentProcess.getResponseTime() == -1) {
                currentProcess.setResponseTime(currentTime - currentProcess.getArrivalTime());
            }

            currentProcess.setRemainingTime(currentProcess.getRemainingTime() - 1);
            currentTime++;

            if (currentProcess.getRemainingTime() == 0) {
                completedProcesses++;
                currentProcess.setCompletionTime(currentTime);
                currentProcess.setTurnaroundTime(currentProcess.getCompletionTime() - currentProcess.getArrivalTime());
                currentProcess.setWaitingTime(currentProcess.getTurnaroundTime() - currentProcess.getBurstTime());
                 
                if (currentProcess.getWaitingTime() < 0) {
                    currentProcess.setWaitingTime(0);
                }
            }
        }

        printResults(processes, "Preemptive SRTF Scheduling");
    }

    private static void printResults(Process[] processes, String title) {
        System.out.println("\n" + title);
        System.out.printf("%-10s%-10s%-15s%-15s%-17s%-15s%-17s%-15s\n",
                "Process", "Priority", "Arrival Time", "Burst Time",
                "Completion Time", "Waiting Time", "Turnaround Time", "Response Time");

        double totalWT = 0, totalTAT = 0;

        Comparator<Process> byPid = (p1, p2) -> Integer.compare(p1.getPid(), p2.getPid());
        Arrays.sort(processes, byPid);

        for (Process p : processes) {
            totalWT += p.getWaitingTime();
            totalTAT += p.getTurnaroundTime();
            System.out.printf("%-10d%-10d%-15d%-15d%-17d%-15d%-17d%-15d\n",
                    p.getPid(), p.getPriority(), p.getArrivalTime(), p.getBurstTime(),
                    p.getCompletionTime(), p.getWaitingTime(), p.getTurnaroundTime(), p.getResponseTime());
        }

        System.out.printf("\nAverage Waiting Time: %.2f\n", totalWT / processes.length);
        System.out.printf("Average Turnaround Time: %.2f\n", totalTAT / processes.length);
    }
}
