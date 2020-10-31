import java.util.Random;



// This class implements the hill climbing algorithm to solve

// Sudokus.  



public class HillClimbing extends Solver {

    private final int[][] currentState = new int[4][4];

    private int[][] solutionState;



    // This variable counts the number of times climb was called

    private int iteration = 0;



    // This method takes a sudoku puzzle as input and outputs

    // the puzzle's solution.

    @Override

    public int[][] solve(Sudoku puzzle) {



        // Make a copy of puzzle to work with.

        this.temp = new Sudoku(puzzle.getInitialState());



        // Copy the initial state of the puzzle to the Hill

        // climbing class.

        this.setInitialState(this.temp);



        // Copy initial state into current state.

        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 4; j++) {

                this.currentState[i][j] = this.initialState[i][j];

            }

        }



        // This sets the stage for the recursion. Iteration

        // is used to limit the number of times climb is called

        // to prevent an infinite loop.

        this.iteration = 0;



        // Climbing the hill

        do {

            // Fill in the blanks with a random set of numbers.

            this.initializeState(this.currentState);



            this.solutionState = this.climb(this.currentState);



            this.temp.setCurrentState(this.solutionState);

        } while (this.temp.verify() != 0);



        return this.solutionState;

    }



    private int[][] climb(int[][] neighborState) {

        Random generator = new Random();

        int temporaryValue, randomRowIndex;

        int[] colIndex = new int[2];

        int neighborErrorCount;

        int currentErrorCount;



        // Get Evaluation function for currentState

        this.temp.setCurrentState(this.currentState);

        currentErrorCount = this.temp.verify();



        System.out.println("Climb Iteration: " + this.iteration);

        this.temp.printCurrentState();

        System.out.println();



        // Picks a random row in initialState, and if two random elements

        // that belong in that row both equal zero, swap those values in

        // the neighborState.

        randomRowIndex = generator.nextInt(4);



        do {

            for (int i = 0; i < 2; i++) {

                colIndex[i] = generator.nextInt(4);

            }

        } while (this.initialState[randomRowIndex][colIndex[0]] != 0

                || this.initialState[randomRowIndex][colIndex[1]] != 0);



        temporaryValue = neighborState[randomRowIndex][colIndex[0]];

        neighborState[randomRowIndex][colIndex[0]] = neighborState[randomRowIndex][colIndex[1]];

        neighborState[randomRowIndex][colIndex[1]] = temporaryValue;



        // Get Evaluation function for neighborState

        this.temp.setCurrentState(neighborState);

        neighborErrorCount = this.temp.verify();



        // Increment iteration to keep track

        // of how many times climb was called.

        // If it was called 420 times, then

        // we need to restart the whole process.

        this.iteration++;

        if (this.iteration == 420) {

            System.out.println("Neighboring State Evaluation function : "

                    + neighborErrorCount);

            System.out.println("Current State Evaluation function : "

                    + currentErrorCount);

            System.out.println("Local Minima Detected. Restarting Search.");

            this.iteration = 0;

            return this.initialState;

        }



        if (neighborErrorCount == 0) {

            System.out.println("Neighboring State Evaluation function : "

                    + neighborErrorCount);

            System.out.println("Current State Evaluation function : "

                    + currentErrorCount);

            System.out

                    .println("Neighboring State Evaluation function is 0. Changing State.");

            System.out.println("Climb Iteration: " + this.iteration);

            return neighborState;

        } else if (neighborErrorCount >= currentErrorCount) {

            System.out.println("Neighboring State Evaluation function : "

                    + neighborErrorCount);

            System.out.println("Current State Evaluation function : "

                    + currentErrorCount);

            System.out

                    .println("Current State Evaluation function is lower. Climbing current state.");

            return this.climb(this.currentState);

        } else {

            System.out.println("Neighboring State Evaluation function : "

                    + neighborErrorCount);

            System.out.println("Current State Evaluation function : "

                    + currentErrorCount);

            System.out

                    .println("Neighboring State Evaluation function is lower. Climbing neighboring state.");

            return this.climb(neighborState);

        }



    }

}