import java.util.Scanner;

public class MooreVotingAlgorithm {
    public static int findMajorityElement(int[] arr) {
        int candidate = -1;
        int count = 0;
        for (int num : arr) {
            if (count == 0) {
                candidate = num; // New candidate
            }
            count += (num == candidate) ? 1 : -1; 
        }
        count = 0;
        for (int num : arr) {
            if (num == candidate) {
                count++;
            }
        }
        return (count > arr.length / 2) ? candidate : -1; 
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int majorityElement = findMajorityElement(arr);
        if (majorityElement != -1) {
            System.out.println("The majority element is: " + majorityElement);
        } else {
            System.out.println("There is no majority element.");
        }

        sc.close();
    }
}
