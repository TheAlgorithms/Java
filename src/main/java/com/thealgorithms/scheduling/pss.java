import java.util.PriorityQueue;
import java.util.Scanner;

// Process class representing a process with attributes: id, weight, burst time, arrival time, and time received
class Process {
    int id;
    int weight;
    int burstTime;
    int timeReceived;
    int arrivalTime;
    int priorityBoost; // Boost to prevent starvation

    public Process(int id, int weight, int burstTime, int arrivalTime) {
        this.id = id;
        this.weight = weight;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.timeReceived = 0;
        this.priorityBoost = 0;
    }
}

// Comparator to prioritize processes by weight and starvation prevention
class ProcessComparator implements java.util.Comparator<Process> {
    @Override
    public int compare(Process p1, Process p2) {
        int effectiveWeight1 = p1.weight + p1.priorityBoost;
        int effectiveWeight2 = p2.weight + p2.priorityBoost;
        return Integer.compare(effectiveWeight2, effectiveWeight1); // Higher weight gets higher priority
    }
}

public class ProportionalShareScheduling {

    // Method to implement the scheduling logic
    public static void proportionalShareScheduling(PriorityQueue<Process> readyQueue, int totalTimeQuantum) {
        int currentTime = 0;
        while (!readyQueue.isEmpty()) {
            Process current = readyQueue.poll();
            
            // Determine time quantum to allocate to the current process
            int timeGiven = Math.min(current.weight, current.burstTime - current.timeReceived);

            currentTime += timeGiven;
            current.timeReceived += timeGiven;

            System.out.println("Process " + current.id + " received " + timeGiven + " units at time " + currentTime);

            // If the process has finished execution
            if (current.timeReceived >= current.burstTime) {
                System.out.println("Process " + current.id + " completed at time " + currentTime);
            } else {
                // Starvation prevention: increase priority boost for unfinished processes
                current.priorityBoost += 1;
                readyQueue.add(current); // Reinsert into the queue for future execution
            }

            // Starvation prevention logic for all other processes in the queue
            for (Process p : readyQueue) {
                p.priorityBoost += 1; // Increase the boost to prevent starvation
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of processes
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        // Input total time quantum
        System.out.print("Enter the total time quantum: ");
        int totalTimeQuantum = sc.nextInt();

        // Create a priority queue to store processes based on the comparator
        PriorityQueue<Process> readyQueue = new PriorityQueue<>(new ProcessComparator());

        // Input the process details (id, weight, burst time, arrival time)
        for (int i = 0; i < n; i++) {
            System.out.println("Enter Process ID, Weight, Burst Time, Arrival Time for Process " + (i + 1) + ":");
            int id = sc.nextInt();
            int weight = sc.nextInt();
            int burstTime = sc.nextInt();
            int arrivalTime = sc.nextInt();

            Process process = new Process(id, weight, burstTime, arrivalTime);
            readyQueue.add(process); // Add each process to the priority queue
        }

        // Call the scheduling function
        proportionalShareScheduling(readyQueue, totalTimeQuantum);

        sc.close();
    }
}
