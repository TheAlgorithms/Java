// Java program for stable marriage problem 
import java.util.*;
  
class StableMatching
{ 
  
// Number of Men or Women(can use scanner to define too) 
static int N = 4; 
  
// This function returns true if the woman 
// 'w' prefers a man 'm1' over a man 'm' 
static boolean wPrefersM1OverM(int prefer[][], int w, 
                               int m, int m1) 
{ 
    // Check if w prefers m over 
    // her current engagement m1 
    for (int i = 0; i < N; i++) 
    { 
        // If m1 comes before m in list of w, 
        // then w prefers her current engagement,
        // don't do anything 
        if (prefer[w][i] == m1) 
            return true; 
  
        // If m comes before m1 in w's list, 
        // then free her current engagement
        // and engage her with m 
        if (prefer[w][i] == m) 
        return false; 
    }
    return false;
} 
  
// Prints stable matching for N boys and
// N girls. Boys are numbered as 0 to 
// N-1. Girls are numbered as N to 2N-1. 
static void stableMatchingMethod(int prefer[][]) 
{ 
    // Stores partner of the women. This is our
    // output array that stores the passing information. 
    // The value of wPartner[i] indicates the partner 
    // assigned to the woman N+i. Note that the woman 
    // numbers between N and 2*N-1. The value -1 
    // indicates that (N+i)'th woman is free 
    int wPartner[] = new int[N]; 
  
    // An array to store availability of men. 
    // If mFree[i] is false, then man 'i' is 
    // free, otherwise engaged. 
    boolean mFree[] = new boolean[N]; 
  
    // Initialize all men and women as free at the start 
    Arrays.fill(wPartner, -1); 
    int freeCount = N; 
  
    // While there are free men 
    while (freeCount > 0) 
    { 
        // Pick the first free man from the list
        // (we could pick any from the list) 
        int m; 
        for (m = 0; m < N; m++) 
            if (mFree[m] == false) 
                break; 
  
        // One by one go to all women 
        // according to m's preferences. 
        // Here m is the picked free man 
        for (int i = 0; i < N && 
                        mFree[m] == false; i++) 
        { 
            int w = prefer[m][i]; 
  
            // The woman of preference is free, 
            // w and m become partners (Note that 
            // the partnership maybe changed later due to preference).
            // So we can say they are engaged not married to be exact 
            if (wPartner[w - N] == -1) 
            { 
                wPartner[w - N] = m; 
                mFree[m] = true; 
                freeCount--; 
            } 
  
            else // If w is not free 
            { 
                // Find current engagement of w 
                int m1 = wPartner[w - N]; 
  
                // If w prefers m over her current engagement with m1, 
                // then break the engagement between w and m1 and 
                // engage m with w.  
                if (wPrefersM1OverM(prefer, w, m, m1) == false) 
                { 
                    wPartner[w - N] = m; 
                    mFree[m] = true; 
                    mFree[m1] = false; 
                } 
            } // End of Else statement
        } // End of the for loop that goes 
          // to all women in the m's list 
    } // End of main while loop here
  
  
// Print the solution here
System.out.println("Woman Man"); 
for (int i = 0; i < N; i++) 
{
    System.out.print(" "); 
    System.out.println(i + N + "     " + 
                           wPartner[i]);
}
} 
  
// Driver Code to execute
public static void main(String[] args) 
{ 
    int prefer[][] = new int[][]{{7, 5, 6, 4}, 
                                 {5, 4, 6, 7}, 
                                 {4, 5, 6, 7}, 
                                 {4, 5, 6, 7}, 
                                 {0, 1, 2, 3}, 
                                 {0, 1, 2, 3}, 
                                 {0, 1, 2, 3}, 
                                 {0, 1, 2, 3}}; 
    stableMatchingMethod(prefer); 
}
} 
