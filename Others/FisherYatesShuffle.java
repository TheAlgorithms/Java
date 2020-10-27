package Others;

import java.util.Random;

/***
 * Fisher-Yates Shuffling Algorithm
 * https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
 *
 * Implemented By: PolyCole
 */
class FisherYates {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};

        System.out.println("Array before shuffling");
        for(Integer i : arr) System.out.print(i + ", ");
        System.out.println("");

        // Begin Fisher-Yates
        int switchIndex = arr.length - 1;
        int roll;

        Random rng = new Random();

        while(switchIndex != 0) {
            roll = rng.nextInt(switchIndex);
            int temp = arr[roll];
            arr[roll] = arr[switchIndex];
            arr[switchIndex] = temp;

            switchIndex--;
        }
        // End Fisher-Yates

        System.out.println("Array after shuffling");
        for(Integer i : arr) System.out.print(i + ", ");
    }
}