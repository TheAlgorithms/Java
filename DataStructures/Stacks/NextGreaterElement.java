package DataStructures.Stacks;

import java.util.*;

/// for any element at index i, find first element either on left and right
/// which is greater. It can be done in O(n) using stacks
public class NextGreaterElement{

    static final Scanner in = new Scanner(System.in);
    public static void main(String args[]){
        int n = in.nextInt();
        int a[] = new int[n];
        for(int i = 0;i < n;i++)
            a[i] = in.nextInt();
        System.out.println(Arrays.toString(getRightArray(a)));
        System.out.println(Arrays.toString(getLeftArray(a)));
    }

    public static int[] getRightArray(int a[]){ // O(n)
        Stack<Integer> stack = new Stack<Integer>();

        int n = a.length;
        int right[] = new int[n];
        Arrays.fill(right, -1);
        for(int i = 0;i < n;i++){
            while(!stack.isEmpty() && a[stack.peek()] < a[i])
                right[stack.pop()] = i;
            stack.push(i);
        }

        return right;
    }

    public static int[] getLeftArray(int a[]){ // O(n)
        Stack<Integer> stack = new Stack<Integer>();

        int n = a.length;
        int left[] = new int[n];
        Arrays.fill(left, -1);
        for(int i = n-1;i >= 0;i--){
            while(!stack.isEmpty() && a[stack.peek()] < a[i])
                left[stack.pop()] = i;
            stack.push(i);
        }

        return left;
    }
}