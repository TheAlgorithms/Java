package com.thealgorithms.searches;

import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
    public static int majorityElement(int[] arr) {
        int cnt = 0;
        int el = -1;

        for (int i = 0; i < arr.length; i++) {
            if (cnt == 0) {
                cnt = 1;
                el = arr[i];
            } else if (arr[i] == el) {
                cnt++;
            } else {
                cnt--;
            }
        }

        int cnt1 = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == el)
                cnt1++;
        }
        if (cnt1 > (arr.length / 2)) {
            return el;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 1, 2, 1, 3, 5, 1 };
        System.out.println(majorityElement(arr));
    }
}
