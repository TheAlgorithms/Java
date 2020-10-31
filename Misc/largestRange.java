package Misc;

import java.util.*;
public class largestRange
{
    // Finds the length of longest occurring consecutive numbers range in an array
    public static int longestRange(int[] nums)
    {
        int longestRange = 0;
        HashMap<Integer,Boolean> num = new HashMap<>();
        for(int x : nums)
            num.put(x,true);
        for(int x : nums)
        {
            if(!num.get(x))
                continue;
            num.replace(x, false);
            int currentRange=1;
            int left = x-1;
            int right = x+1;
            while(num.containsKey(left))
            {
                num.replace(left, false);
                currentRange+=1;
                left--;
            }
            while(num.containsKey(right))
            {
                num.replace(right, false);
                currentRange+=1;
                right++;
            }
            if(currentRange > longestRange)
                longestRange = currentRange;
        }
        return longestRange;
    }

    public static void main(String[] args) {
        // Testcases
        System.out.println(longestRange(new int[]{1, 2, 3, 4, -1, 11, 10}));
        System.out.println(longestRange(new int[]{-1, 1, 3, 5, 7}));
        System.out.println(longestRange(new int[]{0, 1, 2, 3, 4, 7, 6, 5}));
    }
}
