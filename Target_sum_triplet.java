//Take as input N, the size of array. Take N more inputs and store that in an array.
//Take as input “target”, a number. Write a function which prints all triplets of numbers which sum to target.

import java.util.Arrays;
import java.util.Scanner;

public class array_target_sum_triplets {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = s.nextInt();
		}
		int tar = s.nextInt();
		Arrays.sort(arr);
		sum(arr, tar);

	}

	public static void sum(int[] arr, int target) {
		for (int i = 0; i < arr.length; i++) {
			int left = i + 1;
			int right = arr.length - 1;
			while (right > left) {
				if (arr[i] + arr[left] + arr[right] == target) {
					System.out.println(arr[i] + ", " + arr[left] + " and " + arr[right]);
					left++;
					right--;
				} else if (arr[i] + arr[left] + arr[right] > target) {
					right--;
				} else if (arr[i] + arr[left] + arr[right] < target) {
					left++;
				}
			}
		}
	}
}
