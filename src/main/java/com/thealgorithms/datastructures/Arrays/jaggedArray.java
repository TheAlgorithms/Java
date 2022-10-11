package Arrays;

public class jaggedArray {
    public static void main(String[] args){
        //declaring a 2D array with odd columns
        int[][] arr = new int[3][];
        arr[0] = new int[3];
        arr[1] = new int[4];
        arr[2] = new int[2];
        //initializing a jagged array
        int count = 0;
        for (int i=0; i<arr.length; i++)
            for(int j=0; j<arr[i].length; j++)
                arr[i][j] = count++;

        //printing the data of a jagged array
        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();//new line
        }
    }
}
