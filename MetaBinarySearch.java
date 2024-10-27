import java.util.Scanner;

public class MetaBinarySearch {
    public static int metaBinarySearch(int[] arr, int target) {
        int left = 0, right = arr.length; 

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < target) 
            {
                left = mid + 1;
            } else right = mid;
        }
        if (left == arr.length) return -1; 
        return arr[left]; 
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter the sorted elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.print("Enter the target value: ");
        int target = sc.nextInt();
        int result = metaBinarySearch(arr, target);
        if (result == -1) {
            System.out.println("No element found that is greater than or equal to " + target);
        } else {
            System.out.println("The smallest element >= " + target + " is " + result);
        }
    }
}
