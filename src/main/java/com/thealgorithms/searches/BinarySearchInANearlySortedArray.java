import java.util.Scanner;

/**
 * @author wwe14
 */
public class NearlySortedArray {

    public static void main(String[] args){

        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i=0; i<n; i++){
            arr[i] = scn.nextInt();
        }

        int data = scn.nextInt();

        int start = 0;
        int end = arr.length-1;

        while (start <= end){
            int mid = start + (end - start)/2;
            if (arr[mid] == data){
                System.out.print(mid);
                break;
            }
            else if (mid >= start && arr[mid-1] == data){
                System.out.println(mid-1);
                break;
            }
            else if (mid <= end && arr[mid+1] == data){
                System.out.println(mid+1);
                break;
            }
            else if (arr[mid] > data){
                end = mid - 2;
            }
            else if (arr[mid] < data){
                start = mid + 2;
            }
        }

    }

}
import java.util.Scanner;

/**
 * @author wwe14
 */
public class NearlySortedArray {

    public static void main(String[] args){

        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i=0; i<n; i++){
            arr[i] = scn.nextInt();
        }

        int data = scn.nextInt();

        int start = 0;
        int end = arr.length-1;

        while (start <= end){
            int mid = start + (end - start)/2;
            if (arr[mid] == data){
                System.out.print(mid);
                break;
            }
            else if (mid >= start && arr[mid-1] == data){
                System.out.println(mid-1);
                break;
            }
            else if (mid <= end && arr[mid+1] == data){
                System.out.println(mid+1);
                break;
            }
            else if (arr[mid] > data){
                end = mid - 2;
            }
            else if (arr[mid] < data){
                start = mid + 2;
            }
        }

    }

}
import java.util.Scanner;

/**
 * @author wwe14
 */
public class NearlySortedArray {

    public static void main(String[] args){

        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i=0; i<n; i++){
            arr[i] = scn.nextInt();
        }

        int data = scn.nextInt();

        int start = 0;
        int end = arr.length-1;

        while (start <= end){
            int mid = start + (end - start)/2;
            if (arr[mid] == data){
                System.out.print(mid);
                break;
            }
            else if (mid >= start && arr[mid-1] == data){
                System.out.println(mid-1);
                break;
            }
            else if (mid <= end && arr[mid+1] == data){
                System.out.println(mid+1);
                break;
            }
            else if (arr[mid] > data){
                end = mid - 2;
            }
            else if (arr[mid] < data){
                start = mid + 2;
            }
        }

    }

}
