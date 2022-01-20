import java.util.Scanner;

class Solution {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int[] arr;
        int i = 0, size = 0;
        String val = "";

        System.out.println("How many values would you like to enter into the array?");
        size = scnr.nextInt();
        arr = new int[size];

        System.out.println("Enter values of array and type \"done\" when finished:");
        
        while (i != size) {
            val = scnr.next();
            arr[i++] = Integer.parseInt(val);
        }

        System.out.println("Answer: " + maxSubArray(arr));
        
        scnr.close();
    }

    public static int maxSubArray(int[] nums) {
        int sum = nums[0];
        int curr= nums[0];
        for(int i=1;i<nums.length;i++){
            curr = Math.max(curr+nums[i], nums[i]);
            sum = Math.max(sum,curr);
        }
        return sum;
    }
}