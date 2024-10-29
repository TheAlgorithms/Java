import java.util.*;

public class binarysearch {
    public static void main(String[] jah) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int q = sc.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        while (q > 0) {
            boolean isfound = false;
            int x = sc.nextInt();
            int start = 0;
            int end = size - 1;
            while(start<=end) {
                
                int mid = (start + end) / 2;
                if (arr[mid] > x) {
                    end = mid - 1;
                } else if (arr[mid] < x) {
                    start = mid + 1;
                } else {
                    System.out.println("found"+" "+(mid));
                    isfound = true;
                    break;
                }
            }
            q--;
            if (!isfound) {
                System.out.println("not found");
            }
        }
        sc.close();
    }
}
