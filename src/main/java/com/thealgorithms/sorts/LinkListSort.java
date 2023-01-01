/** Author : Siddhant Swarup Mallick
 * Github : https://github.com/siddhant2002
 */

/** Program description - To sort the LinkList as per sorting technique */

package com.thealgorithms.sorts;

import java.util.*;

public class LinkListSort {

    public static boolean isSorted(int p[], int option) {
        try (Scanner sc = new Scanner(System.in)) {
        }
        int a[] = p;
        // Array is taken as input from test class
        int b[] = p;
        // array similar to a
        int ch = option;
        // Choice is choosed as any number from 1 to 3 (So the linked list will be
        // sorted by Merge sort technique/Insertion sort technique/Heap sort technique)
        switch (ch) {
            case 1:
                Task nm = new Task();
                Node start = null, prev = null, fresh, ptr;
                for (int i = 0; i < a.length; i++) {
                    // New nodes are created and values are added
                    fresh = new Node(); // Node class is called
                    fresh.val = a[i]; // Node val is stored
                    if (start == null)
                        start = fresh;
                    else
                        prev.next = fresh;
                    prev = fresh;
                }
                start = nm.sortByMergeSort(start);
                // method is being called
                int i = 0;
                for (ptr = start; ptr != null; ptr = ptr.next) {
                    a[i++] = ptr.val;
                    // storing the sorted values in the array
                }
                Arrays.sort(b);
                // array b is sorted and it will return true when checked with sorted list
                LinkListSort uu = new LinkListSort();
                return uu.compare(a, b);
                // The given array and the expected array is checked if both are same then true
                // is displayed else false is displayed
            case 2:
                Node start1 = null, prev1 = null, fresh1, ptr1;
                for (int i1 = 0; i1 < a.length; i1++) {
                    // New nodes are created and values are added
                    fresh1 = new Node(); // New node is created
                    fresh1.val = a[i1]; // Value is stored in the value part of the node
                    if (start1 == null)
                        start1 = fresh1;
                    else
                        prev1.next = fresh1;
                    prev1 = fresh1;
                }
                Task1 kk = new Task1();
                start1 = kk.sortByInsertionSort(start1);
                // method is being called
                int i1 = 0;
                for (ptr1 = start1; ptr1 != null; ptr1 = ptr1.next) {
                    a[i1++] = ptr1.val;
                    // storing the sorted values in the array
                }
                LinkListSort uu1 = new LinkListSort();
                // array b is not sorted and it will return false when checked with sorted list
                return uu1.compare(a, b);
                // The given array and the expected array is checked if both are same then true
                // is displayed else false is displayed
            case 3:
                Task2 mm = new Task2();
                Node start2 = null, prev2 = null, fresh2, ptr2;
                for (int i2 = 0; i2 < a.length; i2++) {
                    // New nodes are created and values are added
                    fresh2 = new Node(); // Node class is created
                    fresh2.val = a[i2]; // Value is stored in the value part of the Node
                    if (start2 == null)
                        start2 = fresh2;
                    else
                        prev2.next = fresh2;
                    prev2 = fresh2;
                }
                start2 = mm.sortByHeapSort(start2);
                // method is being called
                int i3 = 0;
                for (ptr2 = start2; ptr2 != null; ptr2 = ptr2.next) {
                    a[i3++] = ptr2.val;
                    // storing the sorted values in the array
                }
                Arrays.sort(b);
                // array b is sorted and it will return true when checked with sorted list
                LinkListSort uu2 = new LinkListSort();
                return uu2.compare(a, b);
                // The given array and the expected array is checked if both are same then true
                // is displayed else false is displayed
            default:
                // default is used incase user puts a unauthorized value
                System.out.println("Wrong choice");
        }
        // Switch case is used to call the classes as per the user requirement
        return false;
    }

    boolean compare(int a[], int b[]) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i])
                return false;
        }
        return true;
        // Both the arrays are checked for equalness. If both are equal then true is
        // returned else false is returned
    }
    /**
     * OUTPUT :
     * Input - {89,56,98,123,26,75,12,40,39,68,91} is same for all the 3 classes
     * Output: [12 26 39 40 56 68 75 89 91 98 123] is same for all the 3 classes
     * 1st approach Time Complexity : O(n logn)
     * Auxiliary Space Complexity : O(n)
     * 2nd approach Time Complexity : O(n^2)
     * Auxiliary Space Complexity : O(n)
     * 3rd approach Time Complexity : O(n logn)
     * Auxiliary Space Complexity : O(n)
     */
}

class Node {

    int val;
    Node next;
    // Node class for creation of linklist nodes
}

class Task {

    static int a[];

    public Node sortByMergeSort(Node head) {
        if (head == null || head.next == null)
            return head;
        int c = count(head);
        a = new int[c];
        // Array of size c is created
        int i = 0;
        for (Node ptr = head; ptr != null; ptr = ptr.next) {
            a[i++] = ptr.val;
        }
        // values are stored in the array
        i = 0;
        task(a, 0, c - 1);
        // task method will be executed
        for (Node ptr = head; ptr != null; ptr = ptr.next) {
            ptr.val = a[i++];
            // Value is stored in the linklist after being sorted
        }
        return head;
    }

    int count(Node head) {
        int c = 0;
        Node ptr;
        for (ptr = head; ptr != null; ptr = ptr.next) {
            c++;
        }
        return c;
        // This Method is used to count number of elements/nodes present in the linklist
        // It will return a integer type value denoting the number of nodes present
    }

    void task(int n[], int i, int j) {
        if (i < j) {
            int m = (i + j) / 2;
            task(n, i, m);
            task(n, m + 1, j);
            task1(n, i, m, j);
            // Array is halved and sent for sorting
        }
    }

    void task1(int n[], int s, int m, int e) {
        int i = s, k = 0, j = m + 1;
        int b[] = new int[e - s + 1];
        while (i <= m && j <= e) {
            if (n[j] >= n[i])
                b[k++] = n[i++];
            else
                b[k++] = n[j++];
        }
        // Smallest number is stored after checking from both the arrays
        while (i <= m) {
            b[k++] = n[i++];
        }
        while (j <= e) {
            b[k++] = n[j++];
        }
        for (int p = s; p <= e; p++) {
            a[p] = b[p - s];
        }
    }
    // The method task and task1 is used to sort the linklist using merge sort
}

class Task1 {

    public Node sortByInsertionSort(Node head) {
        if (head == null || head.next == null)
            return head;
        int c = count(head);
        int a[] = new int[c];
        // Array of size c is created
        a[0] = head.val;
        int i;
        Node ptr;
        for (ptr = head.next, i = 1; ptr != null; ptr = ptr.next, i++) {
            int j = i - 1;
            while (j >= 0 && a[j] > ptr.val) {
                // values are stored in the array
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = ptr.val;
        }
        i = 0;
        for (ptr = head; ptr != null; ptr = ptr.next) {
            ptr.val = a[i++];
            // Value is stored in the linklist after being sorted
        }
        return head;
    }

    static int count(Node head) {
        Node ptr;
        int c = 0;
        for (ptr = head; ptr != null; ptr = ptr.next) {
            c++;
        }
        return c;
        // This Method is used to count number of elements/nodes present in the linklist
        // It will return a integer type value denoting the number of nodes present
    }
    // The method task and task1 is used to sort the linklist using insertion sort
}

class Task2 {

    static int a[];

    public Node sortByHeapSort(Node head) {
        if (head == null || head.next == null)
            return head;
        int c = count(head);
        a = new int[c];
        // Array of size c is created
        int i = 0;
        for (Node ptr = head; ptr != null; ptr = ptr.next) {
            a[i++] = ptr.val;
            // values are stored in the array
        }
        i = 0;
        task(a);
        for (Node ptr = head; ptr != null; ptr = ptr.next) {
            ptr.val = a[i++];
            // Value is stored in the linklist after being sorted
        }
        return head;
    }

    int count(Node head) {
        int c = 0;
        Node ptr;
        for (ptr = head; ptr != null; ptr = ptr.next) {
            c++;
        }
        return c;
        // This Method is used to count number of elements/nodes present in the linklist
        // It will return a integer type value denoting the number of nodes present
    }

    void task(int n[]) {
        int k = n.length;
        for (int i = k / 2 - 1; i >= 0; i--) {
            task1(n, k, i);
        }
        for (int i = k - 1; i > 0; i--) {
            int d = n[0];
            n[0] = n[i];
            n[i] = d;
            task1(n, i, 0);
            // recursive calling of task1 method
        }
    }

    void task1(int n[], int k, int i) {
        int p = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < k && n[l] > n[p])
            p = l;
        if (r < k && n[r] > n[p])
            p = r;
        if (p != i) {
            int d = n[p];
            n[p] = n[i];
            n[i] = d;
            task1(n, k, p);
        }
    }
    // The method task and task1 is used to sort the linklist using heap sort
}
