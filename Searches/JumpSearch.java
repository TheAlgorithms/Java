import java.util.Scanner;
import java.lang.Math;

/**
 *
 * @author Sarbajit Saha (https://github.com/sarbajitsaha)
 *
 */

class JumpSearch
{
    public static int JS(int[] arr, int x)
    {
        int n = arr.length;
 
        // Finding block size to be jumped
        int step = (int) Math.floor(Math.sqrt(n));
 
        // Finding the block where element is present 
        int prev = 0;
        while (arr[Math.min(step, n)-1] < x)
        {
            prev = step;
            step += (int) Math.floor(Math.sqrt(n));
            if (prev >= n)
                return -1;
        }
 
        // Linear search for x in block beginning with prev.
        while (arr[prev] < x)
        {
            prev++;
            // At end of next block or end of array, hence element is not present.
            if (prev == Math.min(step, n))
                return -1;
        }
 
        if (arr[prev] == x)
            return prev;

        return -1;
    }

    // Driver Program
    public static void main(String[] args)
    {
        int[] arr = new int[10];
        int key = 5;

        for (int i = 0; i < 10 ; i++ )
            arr[i] = i+1;

        int index = JS(arr, key);

        if (index != -1)
            System.out.println("Number " +  key + " found at index number : " + index);
        else
            System.out.println("Not found");
    }
}
