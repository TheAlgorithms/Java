package com.thealgorithms.searches;

import com.thealgorithms.devutils.searches.SearchAlgorithm;

/**
 * The “Ubiquitous Search Algorithm” would typically refer to a straightforward search method 
 * where you start searching from the beginning of a dataset, 
 * compare each element with the target you’re looking for, 
 * and continue until you find a match or reach the end of the dataset.
 * It’s a basic and common approach to searching and is often used when efficiency is not a primary concern, 
 * and simplicity is valued.
 *
 * <p>
 * Worst-case performance Θ(N) 
 * Best-case performance O(1) 
 * Average-case performance Θ(log(N))
 *
 * @author Anijit Pandey
 */

public class UbiquitousBinarySearch{

    // Returns location of key, or -1 if not found
    static int BinarySearch(int A[], int l, int r, int key)
    {
        int m;

        while( l < r )
        {
            m = l + (r-l)/2;

            // first comparison
            if( A[m] == key ) 
                return m;

            // second comparison
            if( A[m] < key ) 
                l = m + 1;
            else
                r = m - 1;
        }

        return -1;
    }
}