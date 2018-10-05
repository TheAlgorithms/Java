import java.util.Random;
/*
Fisher and Yates is a popular and simple way to shuffle arrays 
*/
public class FisherAndYatesShuffle{
    public static int[] fisherYatesShuffle(int[] array){
        Random random = new Random();
        for (int n = 0; n < array.length; n++){
            int i = n + random.nextInt(array.length - n);
            int tmp = array[i];
            array[i] = array[n];
            array[n] = tmp; 
        }
        return array;
        
    }
    public static void main(String[] args) {
        int[] numbersToShuffle = {2, 230, 540, 10, 6, 7, 8, 11, 45};
        for (int i:fisherYatesShuffle(numbersToShuffle)){
            System.out.println(i);
        }
    }
}