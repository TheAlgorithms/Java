package Others;
/**
 *
 *
 * <h2>Shortest job first.</h2>
 *
 * <p>Shortest job first (SJF) or shortest job next, is a scheduling policy that selects the waiting
 * process with the smallest execution time to execute next Shortest Job first has the advantage of
 * having minimum average waiting time among all scheduling algorithms. It is a Greedy Algorithm. It
 * may cause starvation if shorter processes keep coming. This problem has been solved using the
 * concept of aging.
 *
 * @author shivg7706
 * @since 2018/10/27
 */
import java.util.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

class Process {

  public int pid;
  public int arrivalTime;
  public int burstTime;
  public int priority;
  public int turnAroundTime;
  public int waitTime;
  public int remainingTime;
}

class Schedule {

  private int noOfProcess;
  private int timer = 0;
  private ArrayList<Process> processes;
  private ArrayList<Process> remainingProcess;
  private ArrayList<Integer> gantChart;
  private float burstAll;
  private Map<Integer, ArrayList<Process>> arrivals;

  Schedule() {
    Scanner in = new Scanner(System.in);

    processes = new ArrayList<Process>();
    remainingProcess = new ArrayList<Process>();

    gantChart = new ArrayList<>();
    arrivals = new HashMap<>();

    System.out.print("Enter the no. of processes: ");
    noOfProcess = in.nextInt();
    System.out.println("Enter the arrival, burst and priority of processes");
    for (int i = 0; i < noOfProcess; i++) {
      Process p = new Process();
      p.pid = i;
      p.arrivalTime = in.nextInt();
      p.burstTime = in.nextInt();
      p.priority = in.nextInt();
      p.turnAroundTime = 0;
      p.waitTime = 0;
      p.remainingTime = p.burstTime;

      if (arrivals.get(p.arrivalTime) == null) {
        arrivals.put(p.arrivalTime, new ArrayList<Process>());
      }
      arrivals.get(p.arrivalTime).add(p);
      processes.add(p);
      burstAll += p.burstTime;
    }
    in.close();
  }

  void startScheduling() {

    processes.sort(
        new Comparator<Process>() {
          @Override
          public int compare(Process a, Process b) {
            return a.arrivalTime - b.arrivalTime;
          }
        });

    while (!(arrivals.size() == 0 && remainingProcess.size() == 0)) {
      removeFinishedProcess();
      if (arrivals.get(timer) != null) {
        remainingProcess.addAll(arrivals.get(timer));
        arrivals.remove(timer);
      }

      remainingProcess.sort(
          new Comparator<Process>() {
            private int alpha = 6;
            private int beta = 1;

            @Override
            public int compare(Process a, Process b) {
              int aRem = a.remainingTime;
              int bRem = b.remainingTime;
              int aprior = a.priority;
              int bprior = b.priority;
              return (alpha * aRem + beta * aprior) - (alpha * bRem + beta * bprior);
            }
          });

      int k = timeElapsed(timer);
      ageing(k);
      timer++;
    }

    System.out.println("Total time required: " + (timer - 1));
  }

  void removeFinishedProcess() {
    ArrayList<Integer> completed = new ArrayList<Integer>();
    for (int i = 0; i < remainingProcess.size(); i++) {
      if (remainingProcess.get(i).remainingTime == 0) {
        completed.add(i);
      }
    }

    for (int i = 0; i < completed.size(); i++) {
      int pid = remainingProcess.get(completed.get(i)).pid;
      processes.get(pid).waitTime = remainingProcess.get(completed.get(i)).waitTime;
      remainingProcess.remove(remainingProcess.get(completed.get(i)));
    }
  }

  public int timeElapsed(int i) {
    if (!remainingProcess.isEmpty()) {
      gantChart.add(i, remainingProcess.get(0).pid);
      remainingProcess.get(0).remainingTime--;
      return 1;
    }
    return 0;
  }

  public void ageing(int k) {
    for (int i = k; i < remainingProcess.size(); i++) {
      remainingProcess.get(i).waitTime++;
      if (remainingProcess.get(i).waitTime % 7 == 0) {
        remainingProcess.get(i).priority--;
      }
    }
  }

  public void solve() {
    System.out.println("Gant chart ");
    for (int i = 0; i < gantChart.size(); i++) {
      System.out.print(gantChart.get(i) + " ");
    }
    System.out.println();

    float waitTimeTot = 0;
    float tatTime = 0;

    for (int i = 0; i < noOfProcess; i++) {
      processes.get(i).turnAroundTime = processes.get(i).waitTime + processes.get(i).burstTime;

      waitTimeTot += processes.get(i).waitTime;
      tatTime += processes.get(i).turnAroundTime;

      System.out.println(
          "Process no.: "
              + i
              + " Wait time: "
              + processes.get(i).waitTime
              + " Turn Around Time: "
              + processes.get(i).turnAroundTime);
    }

    System.out.println("Average Waiting Time: " + waitTimeTot / noOfProcess);
    System.out.println("Average TAT Time: " + tatTime / noOfProcess);
    System.out.println("Throughput: " + (float) noOfProcess / (timer - 1));
  }
}

public class SJF {
  public static void main(String[] args) {
    Schedule s = new Schedule();
    s.startScheduling();
    s.solve();
  }
}
