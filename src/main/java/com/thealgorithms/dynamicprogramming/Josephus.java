// Java code for Josephus Problem
import java.util.Scanner;
class josephus {
	static int josephus(int n, int k)
	{
		if (n == 1)
			return 1;
		else
			/* The position returned by josephus(n - 1, k)
			is adjusted because the recursive call
			josephus(n - 1, k) considers the original
			position k%n + 1 as position 1 */
			return (josephus(n - 1, k) + k - 1) % n + 1;
	}
	// Driver Program to test above function
	public static void main(String[] args)
	{
		Scanner a = new Scanner(System.in);  // Create a Scanner object
    		System.out.println("Enter for n");
		int n = a.nextInt();
		Scanner b = new Scanner(System.in);  // Create a Scanner object
    		System.out.println("Enter for k");
		int k = b.nextInt();
		System.out.println("The chosen place is "+ josephus(n, k));
	}
}
