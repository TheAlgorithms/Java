package com.thealgorithms.scheduling.entities;

public class Process {
    private String processId;
    private int arrivalTime;
    private int burstTime;

    public Process(final String processId, final int arrivalTime, final int burstTime) {
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

    public void setProcessId(final String processId) {
        this.processId = processId;
    }

    public void setArrivalTime(final int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setBurstTime(final int burstTime) {
        this.burstTime = burstTime;
    }
}
