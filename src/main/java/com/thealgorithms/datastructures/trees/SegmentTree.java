package com.thealgorithms.datastructures.trees;

public class SegmentTree {

    private int[] segTree;
    private int n;
    private int[] arr;

    /* Constructor which takes the size of the array and the array as a parameter*/
    public SegmentTree(int n, int[] arr) {
        this.n = n;
        int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));
        int segSize = 2 * (int) Math.pow(2, x) - 1;

        this.segTree = new int[segSize];
        this.arr = arr;
        this.n = n;
        constructTree(arr, 0, n - 1, 0);
    }

    /* A function which will create the segment tree*/
    public final int constructTree(int[] arr, int start, int end, int index) {
        if (start == end) {
            this.segTree[index] = arr[start];
            return arr[start];
        }

        int mid = start + (end - start) / 2;
        this.segTree[index] = constructTree(arr, start, mid, index * 2 + 1) + constructTree(arr, mid + 1, end, index * 2 + 2);
        return this.segTree[index];
    }

    /* A function which will update the value at a index i. This will be called by the
    update function internally*/
    private void updateTree(int start, int end, int index, int diff, int segIndex) {
        if (index < start || index > end) {
            return;
        }

        this.segTree[segIndex] += diff;
        if (start != end) {
            int mid = start + (end - start) / 2;
            updateTree(start, mid, index, diff, segIndex * 2 + 1);
            updateTree(mid + 1, end, index, diff, segIndex * 2 + 2);
        }
    }

    /* A function to update the value at a particular index*/
    public void update(int index, int value) {
        if (index < 0 || index > n) {
            return;
        }

        int diff = value - arr[index];
        arr[index] = value;
        updateTree(0, n - 1, index, diff, 0);
    }

    /* A function to get the sum of the elements from index l to index r. This will be called
     * internally*/
    private int getSumTree(int start, int end, int qStart, int qEnd, int segIndex) {
        if (qStart <= start && qEnd >= end) {
            return this.segTree[segIndex];
        }

        if (qStart > end || qEnd < start) {
            return 0;
        }

        int mid = start + (end - start) / 2;
        return (getSumTree(start, mid, qStart, qEnd, segIndex * 2 + 1) + getSumTree(mid + 1, end, qStart, qEnd, segIndex * 2 + 2));
    }

    /* A function to query the sum of the subarray [start...end]*/
    public int getSum(int start, int end) {
        if (start < 0 || end > n || start > end) {
            return 0;
        }
        return getSumTree(0, n - 1, start, end, 0);
    }
}
