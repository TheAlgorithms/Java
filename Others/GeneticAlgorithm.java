/*
 * This demonstrates a GeneticAlgorithm that folds proteins, which consist of a sequence of
 * hydrophobic and hydrophilic amino acids. 0 means hydrophobic and 1 hydrophilic amino acid.
 * What this means is bascially irrelevant: It is a sequence of binary attribues, that can be folded
 * in a simpflied two dimensional system in: "N" (North), "E" (East), "S" (South) und "W" (West)
 * The scoring of each folding can be found in checkEnegery()
 *
 * Read more about genetic algorithms: https://en.wikipedia.org/wiki/Genetic_algorithm
 */

import java.util.Random;

public class GeneticAlgorithm {
    static String sequence;
    static float selectionPressure;
    static int maxGenerations;
    static float setVariance;
    static float currentSigma;
    static float prevSigma;
    static float sigmaConstant;
    static float elitePercent;
    static int generationCompare;
    static boolean stopped;

    static float[] averagePopFitnessVals;
    static float[] bestFitnessVals;
    static float[] bestCandidateOfAllGenerations;
    static String[] bestFitnessCanidates;
    static Population population;

    static int currentGeneration;

    public void geneticAlgorithm(String inSequence,
                                        float inSelectionPressure,
                                        int inMaxGenerations,
                                        float inVariance,
                                        float inElitePercent) {
        // Parameter for the Sequence
        sequence = inSequence;

        // Parameter for the Selection Pressure
        selectionPressure = inSelectionPressure;

        // Parameter for the Generations
        maxGenerations = inMaxGenerations;

        generationCompare = 25;

        // Parameter for the Variance that indicates when the Algorithm might be stopped
        setVariance = inVariance;

        elitePercent = inElitePercent;

        stopped = false;

        prevSigma = -1;

        // Arrays beeing used for storing statistic Values of each generation
        averagePopFitnessVals = new float[maxGenerations];
        bestFitnessVals = new float[maxGenerations];
        bestCandidateOfAllGenerations = new float[maxGenerations];
        bestFitnessCanidates = new String[maxGenerations];

        // -----------------------------
        // The actual Genetic Algorithm
        // -----------------------------

        // Create new Population with goven Sequence
        population = new Population(sequence, selectionPressure, sigmaConstant, elitePercent);

        // Create inital Population
        population.initalPopulation();

        // Evaluate the Population
        population.evalutaion();

        // Loop jumps out after maxGenerations has been reached
        for (int i = 0; i < maxGenerations; i++) {

            sigmaConstant = ((float) i) / ((float) maxGenerations);
            sigmaConstant = 5.0f * sigmaConstant;
            sigmaConstant = 5 - sigmaConstant;
            population.setSigmaConstant(sigmaConstant);
            currentGeneration = i;

            // Select the new Population
            population.selection();
            // Crossover the Population
            population.crossover();
            // Mutate the Population
            population.mutation();
            // Evaluate the Population
            population.evalutaion();

            averagePopFitnessVals[i] = getRealValue(population.getAveragePopulationFitness());
            bestFitnessVals[i] = getRealValue(population.getBestFitness());
            bestFitnessCanidates[i] = population.getBestString();

            // Set Best Candidate of All Generations - START
            if (i == 0) {
                bestCandidateOfAllGenerations[0] = bestFitnessVals[i];
            } else {
                if (bestFitnessVals[i] >= bestCandidateOfAllGenerations[i - 1]) {
                    bestCandidateOfAllGenerations[i] = bestFitnessVals[i];
                } else {
                    bestCandidateOfAllGenerations[i] = bestCandidateOfAllGenerations[i - 1];
                }
            }
            // Set Best Candidate of All Generations - END

            currentSigma = population.getCurrentVariance();

        }
        if (stopped) {
            currentGeneration--;
        }

    }

    private static float getRealValue(float value) {
        value -= population.getBasicFitnessValue();
        if (value > 0) {
            value /= selectionPressure;
        }
        value += population.getBasicFitnessValue();
        return value;
    }

    class Population {
        // Parameter of Population
        private int populationSize = 2000;
        private float basicFitnessValue;
        private float weakSubstraction;
        private float selectionPressure;
        private float currentVariance;
        private float sigmaConstant;

        // Helper Variables needed for the Population Class
        private String sequence;
        private String population[];
        private float fitness[];
        private float bareFitness[];
        // For Elitismus (Indices point to bareFitness and mark the Elite)
        private int eliteFitnessIndexes[];
        private boolean eliteTagged[];

        private float populationFitness;
        // Bare (without Variance) average Fitness
        private float bareAverageFitness;
        // Index to the Best Fitness of current Population
        private int bestFitnessIndex;
        private int currentOverlapping;
        private static final int noAmino = -1;


        // ---------------------------------------------------------------------------
        // Private Methods
        // ---------------------------------------------------------------------------

        /**
         * This Method creates a 2 dimensional Array displaying the Folding
         * @return A 2 dimensional Array displaying the Folding. The First Array are the Rows. The Arrays in these are the Cells.
         */


        private float getFitness(String folding) {
            int arraysize = 2 * sequence.length() + 1;

            int[][] foldingMatrix;
            float helpFitness = 0.0f;

            try {
                foldingMatrix = getAndCheckNewArray(sequence, folding);
            } catch (Exception exception) {
                while (currentOverlapping > 0) {
                    helpFitness += weakSubstraction;
                    currentOverlapping--;
                }
                return basicFitnessValue - helpFitness;
            }

            helpFitness = 0.0f;

            for (int x = 0; x < arraysize; x++) {
                for (int y = 0; y < arraysize; y++) {
                    if (foldingMatrix[x][y] != noAmino && sequence.charAt(foldingMatrix[x][y]) == '1') {
                        helpFitness += (checkEnergy(sequence, foldingMatrix, x, y, x + 1, y, arraysize));
                        helpFitness += (checkEnergy(sequence, foldingMatrix, x, y, x - 1, y, arraysize));
                        helpFitness += (checkEnergy(sequence, foldingMatrix, x, y, x, y + 1, arraysize));
                        helpFitness += (checkEnergy(sequence, foldingMatrix, x, y, x, y - 1, arraysize));
                    }
                }
            }

            helpFitness /= 2;

            return basicFitnessValue + helpFitness;
        }

        private int[][] getAndCheckNewArray(String sequence, String directions) throws Exception {
            int sequenceLength = sequence.length();
            // The Array must be so big, to check if it's a valid Folding
            int arrayLength = 2 * sequenceLength + 1;

            // Initalise Array
            int[][] returnArray = new int[arrayLength][arrayLength];
            for (int x = 0; x < arrayLength; x++) {
                for (int y = 0; y < arrayLength; y++) {
                    returnArray[x][y] = noAmino;
                }
            }

            // Check the Sequence for overlapping
            // X are the Rows and Y the Cells
            int x, y;
            x = y = arrayLength / 2;


            returnArray[x][y] = 0;

            currentOverlapping = 0;

            for (int i = 1; i < directions.length() + 1; i++) {
                switch (directions.charAt(i - 1)) {
                    case 'N':
                        x--;
                        break;

                    case 'E':
                        y++;
                        break;

                    case 'S':
                        x++;
                        break;

                    case 'W':
                        y--;
                        break;
                }
                if (returnArray[x][y] == noAmino) {
                    returnArray[x][y] = i;
                } else {
                    currentOverlapping++;
                }
            }

            if (currentOverlapping != 0) {
                throw new Exception("The Folding is invalid, because it is overlapping in Row '" + x + "' and Cell '" + y + "'");
            }

            return returnArray;
        }

        private int checkEnergy(String sequence, int[][] foldingMatrix, int source_x, int source_y, int neighbhor_x, int neighbhor_y, int arraysize) {
            float strongAddition = weakSubstraction * selectionPressure;

            // The Counter that gets returned
            int counter = 0;
            // If Neighbor isn't laying behind the array borders
            if (!(neighbhor_x == -1 || neighbhor_x == arraysize || neighbhor_y == -1 || neighbhor_y == arraysize)) {
                // If Neighbor is there
                if (foldingMatrix[neighbhor_x][neighbhor_y] != noAmino) {
                    // If Neighbor is hydrophob
                    if (sequence.charAt(foldingMatrix[neighbhor_x][neighbhor_y]) == '1') {
                        // If there's no connection between them
                        if ((foldingMatrix[source_x][source_y] - 1 != foldingMatrix[neighbhor_x][neighbhor_y])
                                && (foldingMatrix[source_x][source_y] + 1 != foldingMatrix[neighbhor_x][neighbhor_y])) {
                            counter += (strongAddition);
                        }
                    }
                }
            }
            return counter;
        }

        private void evaluateFitness() {
            bestFitnessIndex = 0;
            for (int i = 0; i < populationSize; i++) {
                bareFitness[i] = this.getFitness(population[i]);
                if (bareFitness[i] > bareFitness[bestFitnessIndex]) {
                    bestFitnessIndex = i;
                }
            }
        }

        private void evaluteSigmaFitness() {
            for (int i = 0; i < populationSize; i++) {
                fitness[i] = bareFitness[i] - bareAverageFitness - (sigmaConstant * currentVariance);
                if (fitness[i] < 0.0f) {
                    fitness[i] = 0.0f;
                }
            }

            populationFitness = 0.0f;
            for (int i = 0; i < populationSize; i++) {
                populationFitness += fitness[i];
            }
        }

        private void calculateAverageFitness() {
            float barePopulationFitness = 0.0f;
            for (int i = 0; i < populationSize; i++) {
                barePopulationFitness += bareFitness[i];
            }
            bareAverageFitness = barePopulationFitness / populationSize;
        }

        private String generateRandomDirection(int length) {
            StringBuilder randomDirection = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < length; i++) {
                switch (Math.abs(random.nextInt() % 4)) {
                    case 0:
                        randomDirection.append("N");
                        break;

                    case 1:
                        randomDirection.append("E");
                        break;

                    case 2:
                        randomDirection.append("S");
                        break;

                    case 3:
                        randomDirection.append("W");
                        break;
                }
            }
            return randomDirection.toString();
        }

        // Variance calculation
        private void calculateVariance() {
            float tempVariance = 0.0f;
            for (int i = 0; i < populationSize; i++) {
                float tempSquare = bareFitness[i] - bareAverageFitness;
                tempVariance += tempSquare * tempSquare;
            }
            tempVariance = tempVariance / (float) populationSize;
            tempVariance = new Double(Math.sqrt(new Float(tempVariance).doubleValue())).floatValue();
            currentVariance = tempVariance;
        }

        private int getIndexOfNextMaximum() {
            int nextMaximumIndex = 0;
            // Look for next free spot...
            while (eliteTagged[nextMaximumIndex]) {
                nextMaximumIndex++;
            }

            for (int i = 0; i < populationSize; i++) {
                if ((bareFitness[i] >= bareFitness[nextMaximumIndex]) && !eliteTagged[i]) {
                    nextMaximumIndex = i;
                }
            }

            return nextMaximumIndex;
        }

        private int getIndexOfMaximum() {
            int maximumIndex = 0;
            for (int i = 1; i < populationSize; i++) {
                if (bareFitness[i] >= bareFitness[maximumIndex]) {
                    maximumIndex = i;
                }
            }
            return maximumIndex;
        }

        private void chooseElite() {
            for (int i = 0; i < populationSize; i++) {
                eliteTagged[i] = false;
                if (i < eliteFitnessIndexes.length) {
                    final int noEliteTag = -1;
                    eliteFitnessIndexes[i] = noEliteTag;
                }
            }

            eliteFitnessIndexes[0] = getIndexOfMaximum();
            eliteTagged[eliteFitnessIndexes[0]] = true;

            for (int i = 1; i < eliteFitnessIndexes.length; i++) {
                eliteFitnessIndexes[i] = getIndexOfNextMaximum();
                eliteTagged[eliteFitnessIndexes[i]] = true;
            }
        }

        void setSigmaConstant(float inSigmaConstant) {
            sigmaConstant = inSigmaConstant;
        }

        // ---------------------------------------------------------------------------
        // Public Methods
        // ---------------------------------------------------------------------------

        Population(String inSequence, float inSelectionPressure, float inSigmaConstant, float inElitePercent) {
            sequence = inSequence;

            // Enough for Sequences up to a length of 99
            basicFitnessValue = sequence.length();
            weakSubstraction = 1.0f;
            selectionPressure = inSelectionPressure;
            currentVariance = 0.0f;
            sigmaConstant = inSigmaConstant;

            population = new String[populationSize];
            fitness = new float[populationSize];
            bareFitness = new float[populationSize];

            setElitism(inElitePercent);
        }

        // ---------------------------------------------------------------------------
        // Getter and Setter

        float getBestFitness() {
            return bareFitness[bestFitnessIndex];
        }

        String getBestString() {
            return population[bestFitnessIndex];
        }

        float getAveragePopulationFitness() {
            return bareAverageFitness;
        }

        float getBasicFitnessValue() {
            return basicFitnessValue;
        }

        float getCurrentVariance() {
            return currentVariance;
        }

        private void setElitism(float inElitePercent) {
            if (inElitePercent != 0) {
                int elite = Math.round(populationSize * (inElitePercent / 100.0f));

                eliteFitnessIndexes = new int[elite];
                eliteTagged = new boolean[populationSize];
            } else {
                eliteFitnessIndexes = new int[0];
            }
        }

        // ---------------------------------------------------------------------------
        // Actual GA Methods

        void initalPopulation() // Bestlist nur bei der ersten Erzeugung generieren
        {
            int populationStringLength = this.sequence.length() - 1;
            for (int i = 0; i < populationSize; i++) {
                population[i] = this.generateRandomDirection(populationStringLength);
            }
        }

        void evalutaion() {
            evaluateFitness();
            calculateAverageFitness();

            calculateVariance();
            evaluteSigmaFitness();
        }

        void selection() {
            String newPopulation[] = new String[populationSize];

            int startOfSelection = 0;

            Random random = new Random(System.currentTimeMillis());

            if (eliteFitnessIndexes.length != 0) {
                // Choose the Elite
                chooseElite();

                // Save the Elite
                for (int i = 0; i < eliteFitnessIndexes.length; i++) {
                    newPopulation[i] = population[eliteFitnessIndexes[i]];
                }

                // Set Start of the Selection
                startOfSelection = eliteFitnessIndexes.length;
            }

            for (int i = startOfSelection; i < populationSize; i++) {
                random = new Random(random.nextLong() + System.currentTimeMillis());

                // No Candidate is selected
                int selectedCandidate = -1;
                // High and Low are the borders that mark the Section to see if the Candidate is selected
                float high = 0.0f, low = 0.0f;

                // Get a Random float value
                float rand = random.nextFloat();

                // Selecting populationSize as much as candidates
                for (int j = startOfSelection; j < populationSize && selectedCandidate == -1; j++) {
                    float currentCandidateChance;
                    if (populationFitness == 0.0f) {
                        currentCandidateChance = 1.0f / populationSize;
                    } else {
                        if (fitness[j] == 0.0) {
                            currentCandidateChance = (1.0f / populationSize) / populationFitness;
                        } else {
                            // Get the Chance for this Candidat to be selected
                            currentCandidateChance = (fitness[j] / populationFitness);
                        }
                    }

                    // Increase the High Border
                    high += currentCandidateChance;

                    // Is the Candidate the selected
                    if ((rand <= high && rand >= low) || j + 1 == populationSize) {
                        selectedCandidate = j;
                    }
                    // Set the Low Border from the High Border (because the new low is the old high)
                    low = high;
                }
                // Set the new Canidadate in the New Population
                newPopulation[i] = population[selectedCandidate];
            }

            // Write over the new Population in the current Population
            population = newPopulation;
        }


        void crossover() {
            int rate = 24;
            int iterations = (rate * populationSize) / 100;
            if (iterations % 2 != 0) {
                iterations--;
            }
            iterations /= 2;

            while (iterations > 0) {
                // Choose first Candidate
                int candidate1 = Math.abs((new Random().nextInt()) % populationSize);
                int candidate2;
                do {
                    // Choose second Candidate
                    candidate2 = Math.abs((new Random().nextInt()) % populationSize);
                } while (candidate1 == candidate2);

                int cutPosition = Math.abs((new Random().nextInt()) % sequence.length());

                String candidate1Front = population[candidate1].substring(0, cutPosition);
                String candidate1Back = population[candidate1].substring(cutPosition, population[candidate1].length());

                String candidate2Front = population[candidate2].substring(0, cutPosition);
                String candidate2Back = population[candidate2].substring(cutPosition, population[candidate2].length());

                population[candidate1] = candidate1Front + candidate2Back;
                population[candidate2] = candidate2Front + candidate1Back;
                iterations--;
            }
        }

        void mutation() {
            int rate = 1;
            int changes = (populationSize * population[0].length()) * rate / 100;
            while (changes > 0) {
                long seed = System.currentTimeMillis();
                Random rand = new Random(seed);

                int candidate = (rand.nextInt()) % populationSize;
                if (candidate < 0) {
                    candidate *= -1;
                }

                seed = System.currentTimeMillis();
                rand = new Random(seed);
                int position = (rand.nextInt()) % population[candidate].length();
                if (position < 0) {
                    position *= -1;
                }

                seed = System.currentTimeMillis();
                rand = new Random(seed);
                int newCharRand = (rand.nextInt()) % 4;
                if (newCharRand < 0) {
                    newCharRand *= -1;
                }

                char newChar = 'N';

                switch (newCharRand) {
                    case 0:
                        newChar = 'N';
                        break;
                    case 1:
                        newChar = 'E';
                        break;
                    case 2:
                        newChar = 'S';
                        break;
                    case 3:
                        newChar = 'W';
                        break;
                }

                population[candidate] = population[candidate].substring(0, position) +
                        newChar +
                        population[candidate].substring(position + 1, population[candidate].length());
                changes--;
            }
        }
    }

}
