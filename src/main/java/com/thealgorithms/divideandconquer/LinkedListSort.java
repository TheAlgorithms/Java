package com.thealgorithms.divideandconquer;
import java.util.*;

public class LinkedListSort
{

    //Recursive method for Floats, on first run left = 0, right = list.size() - 1
    public static void LLSortFloat(LinkedList<Float> list, int left, int right)
    {
        if(right >  left)
        {
            int middle = left + (right - left)/2; //determines midpoint
            LLSortFloat(list, left, middle); //recursive run for left side
            LLSortFloat(list, middle + 1, right); //recursive run for right side
            sortFloat(list, left, middle, right); //sorts
        }
    }

    private static void sortFloat(LinkedList<Float> list, int left, int mid, int right)
    {
        //Extracts/copies data from list and splits into two halves
        int val1 = mid - left + 1;
        int val2 = right - mid;

        float[] arrLeft = new float[val1];
        float[] arrRight = new float[val2];

        for(int i = 0; i < val1; i++)
            arrLeft[i] = list.get(left + i);
        
        for(int i = 0; i < val2; i++)
            arrRight[i] = list.get(mid + 1 + i);

        //finds lowest available value in each list, lowest of the two is put in earliest available position in list
        int i1 = 0, i2 = 0, index = left;
        while(i1 < val1 && i2 < val2)
        {
            if(arrLeft[i1] <= arrRight[i2])
            {
                list.set(index, arrLeft[i1]);
                i1++;
            }
            else
            {
                list.set(index, arrRight[i2]);
                i2++;
            }
            index++;
        }

        //Fills remaining spots in list with incomplete half
        while(i1 < val1)
        {
            list.set(index, arrLeft[i1]);
            i1++;
            index++;
        }

        while(i2 < val2)
        {
            list.set(index, arrRight[i2]);
            i2++;
            index++;
        }
    }

    //Exact same thing as LLSortFloat but with Integer data type instead of Float
    public static void LLSortInt(LinkedList<Integer> list, int left, int right)
    {
        if(right >  left)
        {
            int middle = left + (right - left)/2;
            LLSortInt(list, left, middle);
            LLSortInt(list, middle + 1, right);
            sortInt(list, left, middle, right);
        }
    }

    private static void sortInt(LinkedList<Integer> list, int left, int mid, int right)
    {
        int val1 = mid - left + 1;
        int val2 = right - mid;

        int[] arrLeft = new int[val1];
        int[] arrRight = new int[val2];

        for(int i = 0; i < val1; i++)
            arrLeft[i] = list.get(left + i);
        
        for(int i = 0; i < val2; i++)
            arrRight[i] = list.get(mid + 1 + i);

        int i1 = 0, i2 = 0, index = left;
        while(i1 < val1 && i2 < val2)
        {
            if(arrLeft[i1] <= arrRight[i2])
            {
                list.set(index, arrLeft[i1]);
                i1++;
            }
            else
            {
                list.set(index, arrRight[i2]);
                i2++;
            }
            index++;
        }

        while(i1 < val1)
        {
            list.set(index, arrLeft[i1]);
            i1++;
            index++;
        }

        while(i2 < val2)
        {
            list.set(index, arrRight[i2]);
            i2++;
            index++;
        }
    }
}