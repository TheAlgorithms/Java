import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int[] array1 = new int[10];

        Reverse(array1);
    }

    public static void Reverse(int[] array){
        for(int i=0; i<array.length; i++){
            array[i]  = scanner.nextInt();
        }

        System.out.println("Your inserted array is " +Arrays.toString(array));

        int maxIndex = array.length -1;
        int halfLength = array.length /2;

        for(int i=0; i<halfLength; i++){
            int temp = array[i];
            array[i] = array[maxIndex-i];
            array[maxIndex -i] = temp;
        }

        System.out.println("reversed array is "+Arrays.toString(array));
    }
}
