// Algorithm Explanation: https://www.geeksforgeeks.org/count-ways-reach-nth-stair/
class ClimbStairs {

	private static int count;

	public static void main(String args[]) {
		int no = 5; // Nth stair to climb
		int steps[] = new int[] { 1, 4 }; // number of steps allowed each time.
		getPathCountToClimbNStairs(no, steps, "");
		System.out.println(count);
	}

	public static void getPathCountToClimbNStairs(int n, int allowedStepsAtATime[], String ans) {
		if (n == 0) {
			count++;
			return;
		}

		for (int item : allowedStepsAtATime) {
			if (n - item >= 0) {
				getPathCountToClimbNStairs(n - item, allowedStepsAtATime, ans + item);
			}
		}
	}
}
