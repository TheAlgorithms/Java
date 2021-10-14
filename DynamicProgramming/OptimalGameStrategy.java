package challengesDP;

import java.util.Scanner;

public class OptimalGameStrategy {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		System.out.println(maxValue(a, 0, a.length - 1));
	}

	public static int maxValue(int[] a, int l, int h) {
		if (l + 1 == h) {
			return Math.max(a[l], a[h]);
		}
		return Math.max(a[l] + Math.min(maxValue(a, l + 2, h), maxValue(a, l + 1, h - 1)),
				a[h] + Math.min(maxValue(a, l + 1, h - 1), maxValue(a, l, h - 2)));
	}

}
