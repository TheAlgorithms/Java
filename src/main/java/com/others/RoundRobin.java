package src.main.java.com.others;

import java.util.ArrayList;

/**
 * This class implements the Round Robin Algorithm which is an cpu scheduling algorithm.
 *
 * @author George Giannios
 */
public class RoundRobin {
    /**
     * This method calculates the waiting time for all processes
     *
     * @param burstTime an array with burst time for all processes
     * @param quantum the quantum quantity
     *
     * @return an array with waiting time for all processes
     */
    public int[] calcWaitingTime(int[] burstTime, int quantum)
    {
        int n= burstTime.length;
        //create a copy of burstTime table to executeTime table
        int[] executeTIme= new int [n];
        for (int i=0;i<n;i++)
            executeTIme[i]=burstTime[i];

        //initialize the waiting time table and set all waiting times equal to zero
        int[] waitingTime= new int [n];
        for (int i=0;i<n;i++)
            waitingTime[i]=0;

        //initialize an array list to emulate the queue of ready processes
        ArrayList<Integer> readyQueue = new ArrayList<>();
        for(int i=0;i<n;i++)
            readyQueue.add(i);

        //the total time that processes need to be finished
        int time=0;
        int i=0;
        //calculate waiting times while there are uncompleted processes
        while (!readyQueue.isEmpty())
        {
            //check if a process has finished
            if (executeTIme[i]>=0) {
                if (executeTIme[i] - quantum > 0) {
                    //add time that have been passed
                    time += quantum;
                    //this is the remaining burst time for the process i
                    executeTIme[i] -= quantum;

                } else if (executeTIme[i] - quantum == 0) {
                    //add time that have been passed
                    time += quantum;
                    //calculate the total waiting time
                    waitingTime[i] = time - burstTime[i];

                    //mark the process as finished
                    executeTIme[i] = -1;
                    //remove the process that have finished by shrinking queue's length
                    readyQueue.remove(readyQueue.size()-1);

                } else {
                    //add time that have been passed
                    time += executeTIme[i];
                    //calculate the total waiting time
                    waitingTime[i] = time - burstTime[i];

                    //mark the process as finished
                    executeTIme[i] = -1;
                    //remove the process that have finished by shrinking queue's length
                    readyQueue.remove(readyQueue.size()-1);
                }
            }
            i++;
            if(i>=n) i=0;
        }

        return waitingTime;
    }


    /**
     * This method calculates turn around time for all processes
     *
     * @param burstTime an array with burst time for all processes
     * @param waitingTime an array with waiting time for all processes
     *
     * @return an array with turnaround time for all processes
     */
    public int[] calcTurnAroundTime(int[] burstTime, int[] waitingTime)
    {
        int n= burstTime.length;
        //initialize the turnaround time table
        int[] turnAroundTime= new int [n];

        //calculate turnaround time for each process (T.T= W.T + B.T)
        for (int i=0; i<n;i++)
            turnAroundTime[i]=waitingTime[i]+burstTime[i];

        //return the turnaround time table
        return turnAroundTime;
    }


    /**
     * This method prints the results and calculates the average waiting and turnaround times
     *
     * @param burstTime an array with burst time for all processes
     * @param quantum the quantum quantity
     */
    void printAvgTimes(int[] burstTime, int quantum)
    {
        int n = burstTime.length;
        int totalWaitingTime = 0;
        int totalTurnAroundTime = 0;

        // Find waiting time of all processes
        int[] waitingTime = calcWaitingTime(burstTime, quantum);

        // Find turn around time for all processes
        int[] turnAroundTime = calcTurnAroundTime(burstTime, waitingTime);

        // Display processes along with all details
        System.out.println("Process " + " Burst Time " +
                " Waiting Time " + " Turnaround Time");
        System.out.println("=======  ==========  ============  ===============");
        // Calculate total waiting time and total turn around time
        for (int i = 0; i < n; i++) {
            totalWaitingTime += waitingTime[i];
            totalTurnAroundTime += turnAroundTime[i];
            System.out.println(i + "\t\t " + burstTime[i] +"\t\t\t " +
                    waitingTime[i] +"\t\t\t   " + turnAroundTime[i]);
        }

        System.out.println("\nAverage waiting time = " +
                (float)totalWaitingTime / (float)n);
        System.out.println("Average turnaround time = " +
                (float)totalTurnAroundTime / (float)n);
    }
}

