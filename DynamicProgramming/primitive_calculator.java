import java.util.*;
// You are given a primitive calculator that can perform the following three operations with the current number 
// x: multiply x by 2, multiply x by 3, or add 1 to x. Your goal is given a positive integer n, find the minimum 
// number of operations needed to obtain the number n starting from the number 1.
// Problem Description
// Task. Given an integer n, compute the minimum number of operations needed to obtain the number n starting from the number 1.
// Output Format. In the first line, output the minimum number k of operations needed to get n from 1. In the second line output
//  a sequence of intermediate numbers. That is, the second line should contain positive integers a0, a2,…, a(k-1) such that a0 =1, a(k-1) =n and for all 0≤i<k-1, ai+1 is equal to either ai + 1, 2 x ai, or 3 x ai. If there are many such sequences, output any one of them.
// Sample 1.
// Input: 5
// Output:
// 3
// 1 2 4 5
// Explanation:
// Here, we first multiply 1 by 2 two times and then add 1 ( ((1 x 2) x 2) + 1). Another possibility is to first multiply by 3 
// and then add 1 two times. Hence “1 3 4 5” is also a valid output in this case.
// Sample 2:
// Input: 96234
// Output:
// 14
// 1 3 9 10 11 22 66 198 594 1782 5346 16038 16039 32078 96234
// Explanation:
// Again, another valid output in this case is “1 3 9 10 11 33 99 297 891 2673 8019 16038 16039 48117 96234”.
// Your goal is to design and implement a dynamic programming solution for this problem. A natural subproblem in 
// this case is the following: C(n) is the minimum number of operations required to obtain n from 1
// (using the three primitive operations). How to express C(n) through C(n/3), C(n/2), C(n-1)?


public class PrimitiveCalculator {
    private static List<Integer> optimal_sequence(int n) {
        List<Integer> sequence = new ArrayList<Integer>();
        while (n >= 1) {
            sequence.add(n);
            if (n % 3 == 0) {
                n /= 3;
            } else if (n % 2 == 0) {
                n /= 2;
            } else {
                n -= 1;
            }
        }
        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}
