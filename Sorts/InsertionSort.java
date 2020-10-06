import java.util.Arrays;
import java.util.Random;

public class InsertionSort {
    public static void main(String[] args) {
        int[] stuff = getRandomArray(50000);
        //System.out.println(Arrays.toString(stuff));

        long started = System.currentTimeMillis();
        insertionSort(stuff);
        long finished = System.currentTimeMillis();
        long totalTime = finished - started;

        //System.out.println(Arrays.toString(stuff));

        System.out.println("Started: " + started);
        System.out.println("Finished: " + finished);
        System.out.println("Elapsed: " + totalTime);
    }

    public static int[] getRandomArray(int size) {
        int[] array = new int[size];
        Random r = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextInt(size);
        }
        return array;
    }

    public static void insertionSort(int[] array) {
        // Implementation goes here
    }
}
