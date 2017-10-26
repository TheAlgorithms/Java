/*

an implementation of bogosort
input from command line
to increasing order

*/

import java.util.Random;

public class BogoSort {
	
	public static void main(String[] args) {
		int[] arr = new int[args.length];
		for (int i = 0; i < args.length; i++)
			arr[i] = Integer.parseInt(args[i]);
		while(!isSorted(arr)) arr = scramble(arr);
		disp(arr);
	}
	
	private static boolean isSorted(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++)
			if (arr[i + 1] < arr[i])
				return false;
		return true;
	}
	
	private static int[] scramble(int[] arr) {
		Random rand = new Random();
		int[] scr = new int[arr.length], chk = new int[arr.length];
		int count = 0, index;
		while (count < arr.length) {
			index = rand.nextInt(arr.length);
			if (chk[index] != 1) {
				scr[index] = arr[count];
				chk[index] = 1;
				count++;
			}
		}
		return scr;
	}
	
	private static void disp(int[] arr) {
		for(int i : arr) System.out.print(i + " ");
		System.out.println();
	}
}
