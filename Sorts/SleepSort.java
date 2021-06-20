package Sorts;
import java.util.concurrent.CountDownLatch;

public class SleepSort {
    /**
     * Sleep sorting makes use of threads to sort the elements
     * [Sleep-Sorting](https://en.wikipedia.org/wiki/Talk%3ASorting_algorithm#Sleep_sort)
     *
     * @param nums the interger array to be sorted
     */
    public static void sleepSortAndPrint(int[] nums) {
        final CountDownLatch doneSignal = new CountDownLatch(nums.length);
        for (final int num: nums) {
            new Thread(new Runnable() {
                public void run() {
                    doneSignal.countDown();
                    try {
                        doneSignal.await();

                        //using straight milliseconds produces unpredictable
                        //results with small numbers
                        //using 1000 here gives a nifty demonstration
                        Thread.sleep(num * 500);
                        System.out.println(num);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
    public static void main(String[] args) {
        int[] nums = {
            7,
            3,
            2,
            1,
            0,
            5
        };
        for (int i = 0; i < args.length; i++)
            nums[i] = Integer.parseInt(args[i]);
        sleepSortAndPrint(nums);
    }
}