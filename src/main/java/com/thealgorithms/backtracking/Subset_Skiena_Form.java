/** Author : Siddhant Swarup Mallick
 * Github : https://github.com/siddhant2002
 */

/** Program description - To find all the subsets of an given elements*/

package com.thealgorithms.backtracking;

import java.util.*;
public class Subset_Skiena_Form {
    static boolean subset_Skiena_Form(int arr[], int index, int len, int num[], List<List<Integer>> nm)
    {
        int p[] = new int[len];
        // empty array initialized
        int freq;
        if(index == len)
        {
            answer_store(num,len,nm,arr);
            // method call for answer store
        }
        else
        {
            index++;
            freq = validation(p);
            for(int i = 0; i < freq; i++)
            {
                num[index - 1] = p[i];
                subset_Skiena_Form(arr,index,len,num,nm);
                // recursion process
            }
        }
        return true;
    }
    static int validation(int arr[])
    {
        arr[0] = 0;
        arr[1] = 1;
        return 2;
        // Array validation
    }
    static void answer_store(int arr[], int len, List<List<Integer>> nm, int ans[])
    {
        List<Integer> kk=new ArrayList<>();
        for(int i = 0; i < len; i++)
        {
            if(arr[i] == 1)
            {
                kk.add(ans[i]);
            }
        }
        nm.add(new ArrayList<>(kk));
        // stores answer in list
    }
}


/**
     * OUTPUT : [[], [3], [2], [2, 3], [1], [1, 3], [1, 2], [1, 2, 3]]
     * Input - {1,2,3}
     * Output: it returns either true or false
     * Time Complexity : O(2^n) - Exponential
     * Auxiliary Space Complexity : O(n) - Recursion stack space
     * Space Complexity : O(n) - List for storing answers
     */