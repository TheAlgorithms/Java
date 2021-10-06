##Sorting N Arrays using Multi-Threading approach and Arrays.sort()
This approach will be take very less time than a normal sorting approach for a 2-D array.
There are many alternate ways of sorting the arrays in the 2D-array, but, this one is good if you want to implement your own sorting algorithm
on each arrays of a 2D array, and to achieve that just replace the Arrays.sort() with your own algo initializing method.
________________________________________________________________________________________________________________________________________________

import java.util.*;

 public class Sort_N_Array {
	 public static void main(String... args) throws Exception {
		 int M, N;
		 if (args.length == 0) {
		 	final Scanner sc = new Scanner(System.in);
		 	System.out.print("Enter the value of M and N: ");
		 	M = sc.nextInt();
		 	N = sc.nextInt();
		 } else {
			 M = Integer.parseInt(args[0]);
			 N = Integer.parseInt(args[1]);
		}
		 
		 int[][] arr1 = new int[M][N];
		 int[][] arr2 = new int[M][N];
		 
		 RandomInput random = new RandomInput(N);
		 
		 for (int i = 0; i < M; i++) {
			 arr1[i] = arr2[i] = new RandomInput(N).random();
		}
		
		 long startTimeIO = System.currentTimeMillis();
		 for (int[] a : arr1) {
			 sort(a);
			 printArr(a);
		 }
		 long endTimeIO = System.currentTimeMillis();
		 
		 double timeIO = (((endTimeIO) - startTimeIO));
		 System.out.println("Without Thread, the 2d array is shorted in "+timeIO+" milli seconds.\n");
		 
		 startTimeIO = System.currentTimeMillis();
		 for (int i = 0; i < 5; i++) {
			 new ArrayObject(arr2[i]).start();
		}
		 endTimeIO = System.currentTimeMillis();
		 try { Thread.sleep(10); } catch(java.lang.InterruptedException e) {}
		 timeIO = ((endTimeIO - startTimeIO) - 1);
		 System.out.println("With Thread, the 2d array is shorted in "+timeIO+" milli seconds.\n");
	}
	public static void sort(int[] arr) {
		java.util.Arrays.sort(arr);
	}
	static void printArr(int[] arr) {
		System.out.println("Array is: " + Arrays.toString(arr));
	}
}
class ArrayObject extends Thread {
	int[] arr;
	ArrayObject(int[] arr) {
		this.arr = arr;
	}
	public void run(){
		sort(arr);
		printArr(arr);
	}
	public void sort(int[] arr) {
		java.util.Arrays.sort(arr);
	}
	public void printArr(int[] arr) {
		System.out.println("Array is: " + Arrays.toString(arr));
	}
}

// The RandomInput class that'll automatically generate an array of a specific size. This is helpful in testing the code for large number of elements
class RandomInput{
	int n;
	int arr[];
	public RandomInput(int n){
		this.n = n;
		arr=new int[n];
	}
	public int[] random(){
		int limit = 10000;
		if (limit <= n)
			limit = ((n<<1)+(n>>1));
		
		for (int i=0; i<n; i++){
			arr[i] = (int)(Math.random() * limit) + 1;
		}
		return arr;
	}
}
