package Sorts;

/**
* This class implements Sleep Sort.
* In sleep sort, for every element in the array, we create a new thread 
* and make it sleep for a time that is proportional to the value of the 
* element. After the thread 'wakes' up, it prints the value of the 
* element(or the time for which that particular thread slept). After 
* all the threads have run, the values of the array elements will be 
* printed in a sorted order.
*
* In the below example we multiply the variable number by 1000 to convert it into seconds(from milliseconds)
*/

import java.util.concurrent.CountDownLatch;
 
public class SleepSort {
	public static void printSleepSort(int[] num) {
		final CountDownLatch done = new CountDownLatch(num.length);
		for (final int number : num) {
			new Thread(new Runnable() {
				public void run() {
					done.countDown();
					try {
						done.await();
						Thread.sleep(number * 1000);
						System.out.println(number);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
	public static void main(String[] args) {
		int[] num = new int[]{10,1,7,4,2,9,3,6,5,8};
		printSleepSort(num);
	}
}
