package Sorts;

import java.util.Random;

public class BogoSort {
    private static <T> void swap(T array[], int first, int second){
        T randomElement = array[first];
        array[first] = array[second];
        array[second] = randomElement;
    }

    private static <T extends Comparable<T>> boolean isSorted(T array[]){
        for(int i = 0; i<array.length-1; i++){
            if(array[i].compareTo(array[i+1]) > 0) return false;
        }
        return true;
    }

    // Randomly shuffles the array
    private static <T> void nextPermutation(T array[]){
        int length = array.length;
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            int randomIndex = i + random.nextInt(length - i);
            swap(array, randomIndex, i);
        }
    }

    public static <T extends Comparable<T>> void bogoSort(T array[]) {
        while(!isSorted(array)){
            nextPermutation(array);
        }
    }

    // Driver Program
    public static void main(String[] args)
    {
        // Integer Input
        int[] arr1 = {4,23,6,78,1,54,231,9,12};
        int last = arr1.length;
        Integer[] array = new Integer[last];
        for (int i=0;i<last;i++) {
            array[i] = arr1[i];
        }

        bogoSort(array);

        // Output => 1	  4	 6	9	12	23	54	78	231
        for(int i=0; i<last; i++)
        {
            System.out.print(array[i]+"\t");
        }
        System.out.println();

        // String Input
        String[] array1 = {"c", "a", "e", "b","d"};
        last = array1.length;

        bogoSort(array1);

        //Output => a	  b	 c	d	e
        for(int i=0; i<last; i++)
        {
            System.out.print(array1[i]+"\t");
        }
    }
}
