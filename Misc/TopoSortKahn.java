package Misc;

import java.util.*;

class jobGraph2 {

  ArrayList<jobNode2> nodes;
  // ArrayList<Integer> jobs;
  HashMap<Integer, jobNode2> graph = new HashMap<>();

  // A graph with nodes as jobs and edges as dependencies

  jobGraph2(ArrayList<Integer> jobs) {
    // this.jobs=jobs;
    this.nodes = new ArrayList<>();
    for (int job : jobs) this.addNode(job);
  }

  void addDep(int job, int dep) {
    jobNode2 j = this.getNode(job);
    jobNode2 depNode = this.getNode(dep);
    j.deps.add(depNode);
    depNode.prereqCount++;
  }

  void addNode(int job) {
    this.graph.put(job, new jobNode2(job));
    nodes.add(graph.get(job));
  }

  jobNode2 getNode(int job) {
    if (!graph.containsKey(job)) addNode(job);
    return graph.get(job);
  }
}

// Nodes of a job-graph consisting of the job and its number of pre-requisites
class jobNode2 {

  int job, prereqCount;
  ArrayList<jobNode2> deps;

  jobNode2(int job) {
    this.job = job;
    this.deps = new ArrayList<>();
    this.prereqCount = 0;
  }
}

public class TopoSortKahn {

  // Gives the topological ordering given a set of jobs and their prerequisites
  public static ArrayList<Integer> topoSort(ArrayList<Integer> jobs, ArrayList<Integer[]> deps) {
    jobGraph2 graph = createJobGraph(jobs, deps);
    return getOrderedJobs(graph);
  }

  // Forms the job graph using given list of ordered pairs of dependencies and job list
  public static jobGraph2 createJobGraph(ArrayList<Integer> jobs, ArrayList<Integer[]> deps) {
    jobGraph2 j = new jobGraph2(jobs);
    for (Integer[] t : deps) j.addDep(t[0], t[1]);
    return j;
  }

  // Builds the Topological sort using Kahn's Algorithm
  public static ArrayList<Integer> getOrderedJobs(jobGraph2 graph) {
    ArrayList<Integer> orderedJobs = new ArrayList<>();
    ArrayList<jobNode2> nodesWithNoPrereqs = new ArrayList<>();
    for (jobNode2 x : graph.nodes) {
      if (x.prereqCount == 0) nodesWithNoPrereqs.add(x);
    }
    while (nodesWithNoPrereqs.size() != 0) {
      jobNode2 temp = nodesWithNoPrereqs.remove(nodesWithNoPrereqs.size() - 1);
      orderedJobs.add(temp.job);
      removeDeps(temp, nodesWithNoPrereqs);
    }
    boolean graphHasEdges = false;
    for (jobNode2 t : graph.nodes) {
      if (t.prereqCount != 0) {
        graphHasEdges = true;
        break;
      }
    }
    return graphHasEdges ? new ArrayList<>() : orderedJobs;
  }

  // Removes Dependencies of node currently being explored
  public static void removeDeps(jobNode2 node, ArrayList<jobNode2> nodesWithNoprereqs) {
    while (node.deps.size() != 0) {
      jobNode2 dep = node.deps.remove(node.deps.size() - 1);
      dep.prereqCount--;
      if (dep.prereqCount == 0) nodesWithNoprereqs.add(dep);
    }
  }

  public static void main(String[] args) {
    // Sample Testcase
    ArrayList<Integer> jobs = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
    ArrayList<Integer[]> deps = new ArrayList<>();
    deps.add(
        new Integer[] {
          2, 3
        }); // (2, 3) indicates that job 2 has to be completed before starting job 3
    deps.add(new Integer[] {2, 5});
    deps.add(new Integer[] {1, 3});
    deps.add(new Integer[] {3, 4});
    deps.add(new Integer[] {4, 6});
    deps.add(new Integer[] {5, 6});
    deps.add(new Integer[] {6, 7});
    assert topoSort(jobs, deps).equals(Arrays.asList(2, 5, 1, 3, 4, 6, 7));
  }
}