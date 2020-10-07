// A Java Program for the Egg Dropping Problem [exponential Time complexity]

//https://www.geeksforgeeks.org/egg-dropping-puzzle-dp-11/
// https://www.youtube.com/watch?v=S49zeUjeUL0&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=43

class Egg_Dropping_Recursive{ 

        /* Function to get minimum number of 
        trials needed in worst case with n 
        eggs and k floors */
        static int eggDrop(int n, int k) 
        { 
            // If there are no floors, then 
            // no trials needed. OR if there 
            // is one floor, one trial needed. 
            if (k == 1 || k == 0) 
                return k; 
    
            // We need k trials for one egg 
            // and k floors 
            if (n == 1) 
                return k; 
    
            int min = Integer.MAX_VALUE; 
            int x, res; 
    
            // Consider all droppings from 
            // 1st floor to kth floor and 
            // return the minimum of these 
            // values plus 1. 
            for (x = 1; x <= k; x++) { 
                res = Math.max(eggDrop(n - 1, x - 1), eggDrop(n, k - x)); //using max because we want answer for worst case .
                if (res < min)   //Here using minimum because we want min no. of trails.
                    min = res; 
            } 
    
            return min + 1; 
        } 
    
        // Driver code 
        public static void main(String args[]) 
        { 
            int n = 2, k = 10; 
            System.out.print("Minimum number of "+ "trials in worst case with "
                            + n + " eggs and " + k + " floors is " + eggDrop(n, k)); 
        }  
} 
    