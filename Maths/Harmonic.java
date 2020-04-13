package Maths;

/**
 * @author Ishan Goel <br> <a href="https://github.com/quackduck">GitHub Account<a> 
 */
public class Harmonic {

	public static boolean showProgress = false;
	public static long x = 1000;
	public static double revx = 0;

	public static void main(String[] args) {

		if (args.length>0) { x = Long.parseLong(args[0]);}
		if (args.length>1) { revx = Long.parseLong(args[1]);}
		if (args.length>2) { showProgress = Boolean.parseBoolean(args[2]);}

		long start1 = System.currentTimeMillis();
		System.out.print("Harmonic: ");
		System.out.print(harmonic(x));
		System.out.println("");
		long end1 = System.currentTimeMillis();
		System.out.print("Inverse Harmonic: ");
		System.out.print(revharmonic(revx));
		System.out.println("");
		long end2 = System.currentTimeMillis();
		System.out.print("Harmonic took ");
		System.out.print(end1-start1);
		System.out.print(" milliseconds");
		System.out.println("");
		System.out.print("RevHarmonic took ");
		System.out.print(end2-end1);
		System.out.print(" milliseconds");
		System.out.println("");
	}

	/**
	 * Returns the value of the partial sum of the Harmonic Series(1, 1/2, 1/3, 1.4...) to the {@code n}th number. This may take quite some time for large values of {@code n} 
	 *
	 * @param n the number till which the sum is to be taken inclusively.
	 * @return the partial sum till the {@code n}th number in the series.
	 * @see <a href="https://en.wikipedia.org/wiki/Harmonic_series_(mathematics)">Wikipedia on the Harmonic Series<a>
	 * @see #revharmonic(double) revharmonic()
	 */
	public static double harmonic(long n) {
		double result = 0.0;
		for (long i = 1; i <= n; i++) {
			result += (double)1/i;
		}

		return result;
	}


	/**
	 * Calculates the value of {@code n} at which the partial sum is more or equal to {@code x}. This can be thought of as the Reverse of {@link #harmonic(long) harmonic()} method. This function takes much more time than {@link #harmonic(long) harmonic()} 
	 *
	 * @param x the number that should be less or equal to the partial sum at the calculated value {@code n}
	 * @return the value of {@code n} at which the partial sum is more or equal to {@code x}
	 * @see <a href="https://en.wikipedia.org/wiki/Harmonic_series_(mathematics)">Wikipedia on the Harmonic Series<a>
	 * @see #harmonic(long) harmonic()
	 */
	public static long revharmonic(double x) {
		double harmonicresult = 0.0;
		long i = 0;
		while (harmonicresult < x) {
			i++;
			harmonicresult += (double)1 / i;
		}
		return i;

	}

}
