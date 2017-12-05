public class upstair {

	public static void main(String[] args) {
		// The number of stairs.
		int number = 1;

		// Init array to 0
		int[] stairs = new int[number];
		for (int i = 0; i < number; i++) {
			stairs[i] = 0;
		}

		int output = STAIRS(number, stairs);
		System.out.println(output);
	}

	/*
	 * The number of stairs that can climb at one time is 1, 2, 3.
	 * 
	 * The number of ways to climb four stairs is
	 * (1,1,1,1),(2,1,1),(1,2,1),(1,1,2),(2,2),(3,1),(1,3).
	 */

	public static int STAIRS(int number, int[] stairs) {
		if (number == 1) {
			return 1;
		} else if (number == 2) {
			return 2;
		} else if (number == 3) {
			return 4;
		}
		stairs[0] = 1;
		stairs[1] = 2;
		stairs[2] = 4;
		for (int i = 3; i < number; i++) {
			stairs[i] = (stairs[i - 1] + stairs[i - 2] + stairs[i - 3]);
		}
		return stairs[number - 1];
	}
}
