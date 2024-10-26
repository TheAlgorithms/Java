package com.thealgorithms.scheduling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MultiAgentScheduling assigns tasks to different autonomous agents
 * who independently decide the execution order of their assigned tasks.
 * The focus is on collaboration between agents to optimize the overall schedule.
 *
 * Use Case: Distributed scheduling in decentralized systems like IoT networks.
 *
 * @author Hardvan
 */
public class MultiAgentScheduling {

    static class Agent {
        String name;
        List<String> tasks;

        Agent(String name) {
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

    private final Map<String, Agent> agents;

    public MultiAgentScheduling() {
        agents = new HashMap<>();
    }

    public void addAgent(String agentName) {
        agents.putIfAbsent(agentName, new Agent(agentName));
    }

    /**
     * Assign a task to a specific agent.
     *
     * @param agentName the name of the agent
     * @param task      the task to be assigned
     */
    public void assignTask(String agentName, String task) {
        Agent agent = agents.get(agentName);
        if (agent != null) {
            agent.addTask(task);
        }
    }

    /**
     * Get the scheduled tasks for each agent.
     *
     * @return a map of agent names to their scheduled tasks
     */
    public Map<String, List<String>> getScheduledTasks() {
        Map<String, List<String>> schedule = new HashMap<>();
        for (Agent agent : agents.values()) {
            schedule.put(agent.name, agent.getTasks());
        }
        return schedule;
    }
}
