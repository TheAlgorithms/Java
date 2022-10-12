package com.thealgorithms.devutils.entities;

public class ProcessDetails {
    private String processId;
    private int arrivalTime;
    private int burstTime;
    private int waitingTime;
    private int turnAroundTime;

    public ProcessDetails(final String processId, final int arrivalTime, final int burstTime) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }

    public String getProcessId() {
        return processId;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }


    public int getWaitingTime() {
        return waitingTime;
    }

    public int getTurnAroundTimeTime() {
        return turnAroundTime;
    }

    public void setProcessId(final String processId) {
        this.processId = processId;
    }

    public void setArrivalTime(final int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setBurstTime(final int burstTime) {
        this.burstTime = burstTime;
    }

    public void setWaitingTime(final int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void setTurnAroundTimeTime(final int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }
}
