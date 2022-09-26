package com.thealgorithms.others;

import java.util.Scanner;

public class Fcfs {
    int[] completionTime;
    int[] turnAroundTime;
    int[] arrivalTime;
    int[] burstTime;
    int[] waitingTime;
    private double avgWaitingTime;
    private double avgTurnAroundTime;
    int n;

    public Fcfs(int size){
        n = size;
        completionTime = new int[n];
        turnAroundTime = new int[n];
        arrivalTime = new int[n];
        burstTime = new int[n];
        waitingTime = new int[n];
        avgWaitingTime = 0;
        avgTurnAroundTime = 0;
        avgWaitingTime = 0;

    }


    public void printTable(){
        System.out.printf("%s\t%s\t%s\t%s%n", "AT","BT","WT","TAT");
        for(int i = 0; i < n; i++){
            System.out.printf("%d\t%d\t%d\t%d%n", arrivalTime[i], burstTime[i], waitingTime[i], turnAroundTime[i]);
        }
    }
    public double getAvgWaitingTime(){
        double sum = 0;
        double avg = 0;
        for(int i = 0; i < n; i++){
            sum += waitingTime[i];
        }
        avg = sum / n;
        return avg;
    }

    public double getAvgTurnAroundTime(){
        double sum = 0;
        double avg = 0;
        for(int i = 0; i <n; i++){
            sum += turnAroundTime[i];
        }
        avg = sum / n;
        return avg;
    }

    public static void main(String... args){
        Scanner input = new Scanner(System.in);

        System.out.println("Enter No.of Process:");
        int n = input.nextInt();
        Fcfs fcfs = new Fcfs(n);





        for(int i = 0; i < n; i++){
            System.out.println("Enter Arrival Time of "+(i+1)+"th process:");
            int arrivalTime = input.nextInt();
            System.out.println("Enter Burst Time of "+(i+1)+"th process:");
            int burstTime = input.nextInt();
            fcfs.arrivalTime[i] = arrivalTime;
            fcfs.burstTime[i] = burstTime;
        }

        fcfs.completionTime[0] = fcfs.burstTime[0];
        for(int i = 1; i < n; i++){
            fcfs.completionTime[i] = fcfs.completionTime[i - 1] + fcfs.burstTime[i];
        }
        for(int i = 0; i < n; i++){
            fcfs.turnAroundTime[i] = fcfs.completionTime[i] - fcfs.arrivalTime[i];
        }
        for(int i = 0; i < n; i++){
            fcfs.waitingTime[i] = fcfs.turnAroundTime[i] - fcfs.burstTime[i];
        }

        fcfs.printTable();
        System.out.println("Average Waiting Time: "+fcfs.getAvgWaitingTime());
        System.out.println("Average Turn Around Time: "+fcfs.getAvgTurnAroundTime());
    }

}


