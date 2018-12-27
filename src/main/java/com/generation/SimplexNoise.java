package src.main.java.com.generation;

import java.util.Random;

/**
 * Implementation of the simplex noise algorithm.
 */
public class SimplexNoise {

	private SimplexNoiseOctave[] octaves;
	private double[] frequencys;
	private double[] amplitudes;
	private int largestFeature;
	private double persistance;
	private long seed;
	
	/**
	 * @param largestFeature the diameter of the largest possible "cloud".
	 * @param persistence the persistence. a low persistence causes smoother transition between the features while a high one makes the transition hard. (range = {@code 0.0F} - {@code 1.0F})
	 * @param seed the seed this algorithm will use to generate pseudo random numbers. The generation will always look the same if the seed and the other parameters have the same value as in a previous generation
	 */
	public SimplexNoise(int largestFeature, double persistence, long seed) {
		
		this.largestFeature = largestFeature;
		this.persistance = persistence;
		this.seed = seed;
		
		int octaveCount = (int)Math.ceil(Math.log10(largestFeature) / Math.log10(2.0D));
		this.octaves = new SimplexNoiseOctave[octaveCount];
		this.frequencys = new double[octaveCount];
		this.amplitudes = new double[octaveCount];
		
		Random random = new Random(seed);
		
		for(int index = 0; index < octaveCount; index++) {
			
			this.octaves[index] = new SimplexNoiseOctave(random.nextInt());
			this.frequencys[index] = Math.pow(2, index);
			this.amplitudes[index] = Math.pow(persistence, octaveCount - index);
		}
	}
	
	/**
	 * Generates a height map.
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param width width
	 * @param height height
	 * @return the generated height map
	 */
	public float[][] generateHeightMap(int x, int y, int width, int height) {
		
		int xEnd = x + width;
		int yEnd = y + height;
		
		float[][] result = new float[width][height];
		
		for(int i = 0; i < width; i++) {
			
			for(int j = 0; j < height; j++) {
				
				int posX = x + i * ((xEnd - x) / width);
				int posY = y + j * ((yEnd - y) / height);
				result[i][j] = Math.min(1.0F, Math.max(0.0F, (float)(0.5D * (1 + this.getNoise(posX, posY)))));
			}
		}
		
		return result;
	}
	
	/**
	 * Generates a two dimensional noise.
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @return the generated noise
	 */
	public double getNoise(int x, int y) {
		
		double result = 0;
		
		for(int index = 0; index < this.octaves.length; index++) {
			
			result += this.octaves[index].noise(x / this.frequencys[index], y / this.frequencys[index]) * this.amplitudes[index];
		}
		
		return result;
	}
	
	/**
	 * Generates a three dimensional noise.
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param z Z coordinate
	 * @return the generated noise
	 */
	public double getNoise(int x, int y, int z) {
		
		double result = 0;
		
		for(int index = 0; index < this.octaves.length; index++) {
			
			double frequency = Math.pow(2, index);
	        double amplitude = Math.pow(this.persistance, this.octaves.length - index);
	        
	        result += this.octaves[index].noise(x / frequency, y / frequency, z / frequency) * amplitude;
		}
		
		return result;
	}
	
	/**
	 * @return the largest possible feature
	 */
	public int getLargestFeature() {
		
		return this.largestFeature;
	}
	
	/**
	 * @return the persistence
	 */
	public double getPersistance() {
		
		return this.persistance;
	}
	
	/**
	 * @return the seed
	 */
	public long getSeed() {
		
		return this.seed;
	}
}
