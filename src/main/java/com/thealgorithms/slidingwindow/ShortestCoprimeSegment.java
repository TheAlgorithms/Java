package com.thealgorithms.slidingwindow;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * The Sliding Window technique together with 2-stack technique is used to find coprime segment of minimal size in an array.
 * Segment a[i],...,a[i+l] is coprime if gcd(a[i], a[i+1], ..., a[i+l]) = 1
 * <p>
 * Run-time complexity: O(n log n)
 * What is special about this 2-stack technique is that it enables us to remove element a[i] and find gcd(a[i+1],...,a[i+l]) in amortized O(1) time.
 * For 'remove' worst-case would be O(n) operation, but this happens rarely.
 * Main observation is that each element gets processed a constant amount of times, hence complexity will be:
 * O(n log n), where log n comes from complexity of gcd.
 * <p>
 * More generally, the 2-stack technique enables us to 'remove' an element fast if it is known how to 'add' an element fast to the set.
 * In our case 'adding' is calculating d' = gcd(a[i],...,a[i+l+1]), when d = gcd(a[i],...a[i]) with d' = gcd(d, a[i+l+1]).
 * and removing is find gcd(a[i+1],...,a[i+l]). We don't calculate it explicitly, but it is pushed in the stack which we can pop in O(1).
 * <p>
 * One can change methods 'legalSegment' and function 'f' in DoubleStack to adapt this code to other sliding-window type problems.
 * I recommend this article for more explanations: "<a href="https://codeforces.com/edu/course/2/lesson/9/2">CF Article</a>">Article 1</a> or <a href="https://usaco.guide/gold/sliding-window?lang=cpp#method-2---two-stacks">USACO Article</a>
 * <p>
 * Another method to solve this problem is through segment trees. Then query operation would have O(log n), not O(1) time, but runtime complexity would still be O(n log n)
 *
 * @author DomTr (<a href="https://github.com/DomTr">Github</a>)
 */
public final class ShortestCoprimeSegment {
    // Prevent instantiation
    private ShortestCoprimeSegment() {
    }

    /**
     * @param arr is the input array
     * @return shortest segment in the array which has gcd equal to 1. If no such segment exists or array is empty, returns empty array
     */
    public static long[] shortestCoprimeSegment(long[] arr) {
        if (arr == null || arr.length == 0) {
            return new long[] {};
        }
        DoubleStack front = new DoubleStack();
        DoubleStack back = new DoubleStack();
        int n = arr.length;
        int l = 0;
        int shortestLength = n + 1;
        int beginsAt = -1; // beginning index of the shortest coprime segment
        for (int i = 0; i < n; i++) {
            back.push(arr[i]);
            while (legalSegment(front, back)) {
                remove(front, back);
                if (shortestLength > i - l + 1) {
                    beginsAt = l;
                    shortestLength = i - l + 1;
                }
                l++;
            }
        }
        if (shortestLength > n) {
            shortestLength = -1;
        }
        if (shortestLength == -1) {
            return new long[] {};
        }
        return Arrays.copyOfRange(arr, beginsAt, beginsAt + shortestLength);
    }

    private static boolean legalSegment(DoubleStack front, DoubleStack back) {
        return gcd(front.top(), back.top()) == 1;
    }

    private static long gcd(long a, long b) {
        if (a < b) {
            return gcd(b, a);
        } else if (b == 0) {
            return a;
        } else {
            return gcd(a % b, b);
        }
    }

    /**
     * This solves the problem of removing elements quickly.
     * Even though the worst case of 'remove' method is O(n), it is a very pessimistic view.
     * We will need to empty out 'back', only when 'from' is empty.
     * Consider element x when it is added to stack 'back'.
     * After some time 'front' becomes empty and x goes to 'front'. Notice that in the for-loop we proceed further and x will  never come back to any stacks 'back' or 'front'.
     * In other words, every element gets processed by a constant number of operations.
     * So 'remove' amortized runtime is actually O(n).
     */
    private static void remove(DoubleStack front, DoubleStack back) {
        if (front.isEmpty()) {
            while (!back.isEmpty()) {
                front.push(back.pop());
            }
        }
        front.pop();
    }

    /**
     * DoubleStack serves as a collection of two stacks. One is a normal stack called 'stack', the other 'values' stores gcd-s up until some index.
     */
    private static class DoubleStack {
        LinkedList<Long> stack;
        LinkedList<Long> values;

        DoubleStack() {
            values = new LinkedList<>();
            stack = new LinkedList<>();
            values.add(0L); // Initialise with 0 which is neutral element in terms of gcd, i.e. gcd(a,0) = a
        }

        long f(long a, long b) { // Can be replaced with other function
            return gcd(a, b);
        }

        public void push(long x) {
            stack.addLast(x);
            values.addLast(f(values.getLast(), x));
        }

        public long top() {
            return values.getLast();
        }

        public long pop() {
            long res = stack.getLast();
            stack.removeLast();
            values.removeLast();
            return res;
        }

        public boolean isEmpty() {
            return stack.isEmpty();
        }
    }
}
