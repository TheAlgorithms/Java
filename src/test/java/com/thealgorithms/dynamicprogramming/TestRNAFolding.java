package com.thealgorithms.dynamicprogramming;

import java.io.IOException;
import java.util.List;

import es.uma.eda.AlgorithmUtil;
import es.uma.eda.problem.combinatorial.sequence.SequenceUtil;
import es.uma.eda.problem.combinatorial.sequence.folding.RNASecondaryStructurePredictor;

/**
 * Main class for testing RNA folding algorithms
 * @author ccottap
 *
 */
public class TestRNAFolding {

	/**
	 * Filename for storing statistics
	 */
	private final static String outputFilename = "folding.txt";
	/**
	 * how much the size of sequences is scaled-up on each step 
	 */
	private final static double scaleFactor = 1.5;
	
	/**
	 * Finds the best folding of an RNA sequence
	 * @param args command-line arguments (to read data from file or generate at random)
	 * @throws IOException if data cannot be read from file or statistics cannot be written to file
	 */
	public static void main(String[] args) throws IOException {
		if (args.length<1) {
			System.out.println("java TestRNAFolding (-s|-f|-r) <arguments>");
			System.out.println("\t-s: folds a string given as parameter");
			System.out.println("\t-f: performs a batch test from a file");
			System.out.println("\t-r: measures time");
		}
		else {
			List<String> sequences = null;
			RNASecondaryStructurePredictor predictor = new Nussinov();
			
			switch (args[0]) {
			case "-s":
				if (args.length < 3) {
					System.out.println("java TestRNAFolding -s <string> <min-loop-size>");
					System.exit(-1);
				}
				else {
					String f = predictor.run(args[1], Integer.parseInt(args[2]));
					System.out.println("Folding " + args[1] + "...");
					System.out.println("Result:\n\t" + (int)predictor.evaluate(args[1],f) + " base pairs\n\t" +  args[1] + "\n\t" + f);
				}
				break;
				
			case "-f":
				if (args.length < 3) {
					System.out.println("java TestRNAFolding -f <filename> <min-loop-size>");
					System.exit(-1);
				}
				else {
					sequences = SequenceUtil.readSequencesFromFile(args[1]);
					predictor.setVerbosity(false);
					for (String s: sequences) {
						String f = predictor.run(s, Integer.parseInt(args[2]));
						System.out.println((int)predictor.evaluate(s, f));
					}
				}
				break;
				
			case "-r":
				if (args.length < 5) {
					System.out.println("java TestRNAFolding -r <initial-lenght> <doublings> <tests-per-length> <min-loop-size>");
					System.exit(-1);
				}
				else {
					int initialLength = Integer.parseUnsignedInt(args[1]);
					int doublings = Integer.parseUnsignedInt(args[2]);
					int testsPerLength = Integer.parseUnsignedInt(args[3]);
					int minLoopSize = Integer.parseUnsignedInt(args[4]);
					double[][] statistics = runTimer(predictor, initialLength, doublings, testsPerLength, minLoopSize);
					AlgorithmUtil.writeStats(outputFilename, statistics);
				}
				break;
				
			default:
				System.out.println("Wrong argument: " + args[0]);
				System.exit(-1);
			}
		}

	}

	/**
	 * Performs a series of timed experiments with sequences of different sizes
	 * @param predictor the structure prediction algorithm	 
	 * @param initialLength initial length of sequences
	 * @param doublings number of times the sequence length is doubled
	 * @param testsPerLength number of tests per sequence length
	 * @param minLoopSize minimum number of nucleotides enclosed in loops
	 * @return a matrix with computational times (one row per size, one column per test).
	 */
	private static double[][] runTimer(RNASecondaryStructurePredictor predictor , int initialLength, int doublings, int testsPerLength, int minLoopSize) {
		final String alphabet = "ACGU";
		double[][] statistics = new double[doublings][testsPerLength+1];
		double interval;
		
		predictor.setVerbosity(false);
		for (int l = initialLength, dup = 0; dup < doublings; l *= scaleFactor, dup++) {
			statistics[dup][0] = l;
	        System.out.println("Trying sequences of length " + l);
			for (int j=0; j<testsPerLength; j++) {
				String s = SequenceUtil.randomString(l, alphabet);
				predictor.run(s, minLoopSize);
		        interval = predictor.getTime();
		        statistics[dup][j+1] = interval;
		        System.out.println(predictor.getName() + " took " + interval + "s");
			}
		}
		
		return statistics;
		
	}

}
