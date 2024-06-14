package com.thealgorithms.dynamicprogramming;

/**
 * This class refers to the Optimal Job Scheduling problem with the following constrains:
 *  - precedence relation between the processes
 *  - machine pair dependent transportation delays
 *
 * https://en.wikipedia.org/wiki/Optimal_job_scheduling
 *
 * @author georgioct@csd.auth.gr
 */
public class OptimalJobScheduling {

    private final int numberProcesses;
    private final int numberMachines;
    private final int[][] run;
    private final int[][] transfer;
    private final int[][] cost;

    /**
     * Constructor of the class.
     * @param numberProcesses ,refers to the number of precedent processes(N)
     * @param numberMachines ,refers to the number of different machines in our disposal(M)
     * @param run , N*M matrix refers to the cost of running each process to each machine
     * @param transfer ,M*M symmetric matrix refers to the transportation delay for each pair of
     *     machines
     */
    public OptimalJobScheduling(int numberProcesses, int numberMachines, int[][] run, int[][] transfer) {
        this.numberProcesses = numberProcesses;
        this.numberMachines = numberMachines;
        this.run = run;
        this.transfer = transfer;
        this.cost = new int[numberProcesses][numberMachines];
    }

    /**
     * Function which computes the cost of process scheduling to a number of VMs.
     */
    public void execute() {
        this.calculateCost();
        this.showResults();
    }

    /**
     * Function which computes the cost of running each Process to each and every Machine
     */
    private void calculateCost() {

        for (int i = 0; i < numberProcesses; i++) { // for each Process

            for (int j = 0; j < numberMachines; j++) { // for each Machine

                cost[i][j] = runningCost(i, j);
            }
        }
    }

    /**
     * Function which returns the minimum cost of running a certain Process to a certain Machine.In
     * order for the Machine to execute the Process ,he requires the output of the previously
     * executed Process, which may have been executed to the same Machine or some other.If the
     * previous Process has been executed to another Machine,we have to transfer her result, which
     * means extra cost for transferring the data from one Machine to another(if the previous
     * Process has been executed to the same Machine, there is no transport cost).
     *
     * @param process ,refers to the Process
     * @param machine ,refers to the Machine
     * @return the minimum cost of executing the process to the certain machine.
     */
    private int runningCost(int process, int machine) {

        if (process == 0) { // refers to the first process,which does not require for a previous one
            // to have been executed
            return run[process][machine];
        } else {

            int[] runningCosts = new int[numberMachines]; // stores the costs of executing our Process depending on
            // the Machine the previous one was executed

            for (int k = 0; k < numberMachines; k++) { // computes the cost of executing the previous
                // process to each and every Machine
                runningCosts[k] = cost[process - 1][k] + transfer[k][machine] + run[process][machine]; // transferring the result to our Machine and executing
                // the Process to our Machine
            }
            return findMin(runningCosts); // returns the minimum running cost
        }
    }

    /**
     * Function used in order to return the minimum Cost.
     * @param costArr ,an Array of size M which refers to the costs of executing a Process to each
     *     Machine
     * @return the minimum cost
     */
    private int findMin(int[] costArr) {

        int min = 0;

        for (int i = 1; i < costArr.length; i++) {

            if (costArr[i] < costArr[min]) {
                min = i;
            }
        }
        return costArr[min];
    }

    /**
     * Method used in order to present the overall costs.
     */
    private void showResults() {

        for (int i = 0; i < numberProcesses; i++) {

            for (int j = 0; j < numberMachines; j++) {
                System.out.print(cost[i][j]);
                System.out.print(" ");
            }

            System.out.println();
        }
        System.out.println();
    }

    /**
     * Getter for the running Cost of i process on j machine.
     */
    public int getCost(int process, int machine) {
        return cost[process][machine];
    }
}
