import java.util.Scanner;
public class BubbleSortUserInput {

    void sort(int[] arr) {
        int temp;
        for(int a = 0 ; a < arr.length  ; a++) {
            for(int b = 0 ; b < arr.length - 1 ; b++) {
                if(arr[b] > arr[b + 1]) {
                    temp = arr[b];
                    arr[b] = arr[b + 1];
                    arr[b + 1] = temp;
                }
            }
        }
    }
    public static void main(String[] args) {
        int n;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of elements you want to store: ");
        n = sc.nextInt();

        int[] arr = new int[n];
       
        System.out.print("Enter value into array: ");
        for(int i=0; i<n; i++) {
            arr[i]=sc.nextInt();
        }
        sc.close();

        System.out.println("Before sort: ");

        for(int i=0 ; i < arr.length ; i++) {
            System.out.println(arr[i]);
        }
        
        sortarray s = new sortarray();
        s.sort(arr);

        System.out.println("After sort: ");

        for(int i=0 ; i < arr.length ; i++) {
            System.out.println(arr[i]);
        } 
    }
}
