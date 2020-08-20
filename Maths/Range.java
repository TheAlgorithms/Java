package Maths;

/*
 * Calculate the range of an array of numbers
 * 
 * The range of an array of numbers is the difference between the lowest and highest number in the array
 */
public class Range {
	
	private static final float SMALL_FLOAT_VALUE = 0.00000001f;
	private static final double SMALL_DOUBLE_VALUE = 0.00000000001d;
	
	public static void main(String[] args) {
		
		/* Test array of bytes */
		assert range(new byte[]{4, 0, 1, 11, 9, 9}) == 11;
		assert range(new byte[]{55, 127, 91, 22}) == 105;
		
		/* Test array of shorts */
		assert range(new short[]{0}) == 0;
		assert range(new short[]{5, 12, 7, 199}) == 194;
		
		/* Test array of integers */
		assert range(new int[]{}) == 0;
		assert range(new int[]{4}) == 0;
		assert range(new int[]{7, 3}) == 4;
		assert range(new int[]{6, 2, 1, 0, 8, 4, 9}) == 9;
		assert range(new int[]{5, 3, 9, 27, 10}) == 24;
		assert range(new int[]{-8, -12, 5}) == 17;
		
		/* Test array of longs */
		assert range(new long[]{4019358l, 0l, 32590958l, 3928578774746567497l, 83927598729857l}) == 3928578774746567497l;
		assert range(new long[]{58927859283975l, 32894618949l, 58923758972l, 914301948094l}) == 58894964665026l;
		
		/* Test array of floats using small value */
		assert (range(new float[]{0.0f}) - 0.0f) < SMALL_FLOAT_VALUE;
		assert (range(new float[]{1.8f, 1.92f, 1.61f}) - 0.31f) < SMALL_FLOAT_VALUE;
		
		/* Test array of doubles using small value */
		assert (range(new double[]{8.48394793278d, 7.39401340970d, 0.0d, 2.2489236149849d}) - 8.48394793278d) < SMALL_DOUBLE_VALUE;
		assert (range(new double[]{0.0d, -1.38749738597d, 5.9589237825d, 3.9241089214098d, 1.19304780937d}) - 7.34642116847d) < SMALL_DOUBLE_VALUE;
		
	}
	
	/*
	 * Find the range of an array of bytes
	 * 
	 * @param array of bytes, whose range does not exceed Byte.MAX_VALUE
	 * @return range of the array
	 */
	public static byte range(byte[] numbers) {
		
		if(numbers.length == 0 || numbers.length == 1) return 0;
		
		byte min = numbers[0];
		byte max = numbers[0];
		for(int i = 1; i < numbers.length; i++) {
			
			byte num = numbers[i];
			
			if(num < min) {
				min = num;
			} else if(num > max) {
				max = num;
			}
			
		}
		
		return (byte) (max - min);
		
	}
	
	/*
	 * Find the range of an array of shorts
	 * 
	 * @param array of shorts, whose range does not exceed Short.MAX_VALUE
	 * @return range of the array
	 */
	public static short range(short[] numbers) {
		
		if(numbers.length == 0 || numbers.length == 1) return 0;
		
		short min = numbers[0];
		short max = numbers[0];
		for(int i = 1; i < numbers.length; i++) {
			
			short num = numbers[i];
			
			if(num < min) {
				min = num;
			} else if(num > max) {
				max = num;
			}
			
		}
		
		return (short) (max - min);
		
	}
	
	/*
	 * Find the range of an array of integers
	 * 
	 * @param array of integers, whose range does not exceed Integer.MAX_VALUE
	 * @return range of the array
	 */
	public static int range(int[] numbers) {
		
		if(numbers.length == 0 || numbers.length == 1) return 0;
		
		int min = numbers[0];
		int max = numbers[0];
		for(int i = 1; i < numbers.length; i++) {
			
			int num = numbers[i];
			
			if(num < min) {
				min = num;
			} else if(num > max) {
				max = num;
			}
			
		}
		
		return max - min;
		
	}
	
	/*
	 * Find the range of an array of longs
	 * 
	 * @param array of longs, whose range does not exceed Long.MAX_VALUE
	 * @return range of the array
	 */
	public static long range(long[] numbers) {
		
		if(numbers.length == 0 || numbers.length == 1) return 0;
		
		long min = numbers[0];
		long max = numbers[0];
		for(int i = 1; i < numbers.length; i++) {
			
			long num = numbers[i];
			
			if(num < min) {
				min = num;
			} else if(num > max) {
				max = num;
			}
			
		}
		
		return max - min;
		
	}
	
	/*
	 * Find the range of an array of floats
	 * 
	 * @param array of floats, whose range does not exceed Float.MAX_VALUE
	 * @return range of the array
	 */
	public static float range(float[] numbers) {
		
		if(numbers.length == 0 || numbers.length == 1) return 0.0f;
		
		float min = numbers[0];
		float max = numbers[0];
		for(int i = 1; i < numbers.length; i++) {
			
			float num = numbers[i];
			
			if(num < min) {
				min = num;
			} else if(num > max) {
				max = num;
			}
			
		}
		
		return max - min;
		
	}
	
	/*
	 * Find the range of an array of doubles
	 * 
	 * @param array of doubles, whose range does not exceed Double.MAX_VALUE
	 * @return range of the array
	 */
	public static double range(double[] numbers) {
		
		if(numbers.length == 0 || numbers.length == 1) return 0.0d;
		
		double min = numbers[0];
		double max = numbers[0];
		for(int i = 1; i < numbers.length; i++) {
			
			double num = numbers[i];
			
			if(num < min) {
				min = num;
			} else if(num > max) {
				max = num;
			}
			
		}
		
		return max - min;
		
	}
	
}
