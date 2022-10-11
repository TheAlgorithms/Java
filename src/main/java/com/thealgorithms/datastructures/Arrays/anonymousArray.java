package Arrays;

public class anonymousArray {
    static void printArray(int[] arr){
        for (int j : arr) System.out.println(j);
    }

    public static void main(String[] args){
        printArray(new int[]{10,22,44,66});//passing anonymous array to method without declaring it
    }
}
