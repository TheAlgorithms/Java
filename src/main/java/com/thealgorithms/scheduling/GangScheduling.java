package com.thealgorithms.scheduling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GangScheduling groups related tasks (gangs) to run simultaneously on multiple processors.
 * All tasks in a gang are executed together or not at all.
 *
 * Use Case: Parallel computing environments where multiple threads of a program
 * need to run concurrently for optimal performance.
 *
 * @author Hardvan
 */
public final class GangScheduling {

    static class Gang {
        String name;
        List<String> tasks;

        Gang(String name) {
            this.name = name;
            this.tasks = new ArrayList<>();
        }

        void addTask(String task) {
            tasks.add(task);
        }

        List<String> getTasks() {
            return tasks;
        }
    }

    private final Map<String, Gang> gangs;

    public GangScheduling() {
        gangs = new HashMap<>();
    }

    public void addGang(String gangName) {
        gangs.putIfAbsent(gangName, new Gang(gangName));
    }

    public void addTaskToGang(String gangName, String task) {
        Gang gang = gangs.get(gangName);
        if (gang != null) {
            gang.addTask(task);
        }
    }

    public Map<String, List<String>> getGangSchedules() {
        Map<String, List<String>> schedules = new HashMap<>();
        for (Gang gang : gangs.values()) {
            schedules.put(gang.name, gang.getTasks());
        }
        return schedules;
    }
}
