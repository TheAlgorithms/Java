import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PriorityScheduling {
    private ArrayList<Process> processes;
    private ArrayList<String> schedule;

    public PriorityScheduling(ArrayList<Process> processes) {
        this.processes = processes;
        schedule = new ArrayList<>();
    }

    public void scheduleProcesses() {
        int time = 0;
        int size = processes.size();
        int executed = 0;
        Process running = null;

        while (executed < size) {
            ArrayList<Process> ready = new ArrayList<>();
            for (Process process : processes) {
                if (process.getArrivalTime() <= time && !process.isCompleted()) {
                    ready.add(process);
                }
            }
            if (ready.isEmpty()) {
                time++;
                continue;
            }
            running = Collections.min(ready, Comparator.comparing(Process::getPriority));
            running.execute();
            schedule.add(running.getId());
            if (running.isCompleted()) {
                executed++;
            }
            time++;
        }
    }

    public ArrayList<String> getSchedule() {
        return schedule;
    }
}

class Process {
    private String id;
    private int arrivalTime;
    private int burstTime;
    private int priority;
    private int remainingTime;

    public Process(String id, int arrivalTime, int burstTime, int priority) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime;
    }

    public String getId() {
        return id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getPriority() {
        return priority;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void execute() {
        remainingTime--;
    }

    public boolean isCompleted() {
        return remainingTime == 0;
    }
}
