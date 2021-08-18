package Others;

import java.util.*;

/*
*In Fractional Knapsack, we can break items for maximizing the total value of knapsack.
*This problem in which we can break an item is also called the fractional knapsack problem.
*You can read more about Fractional Knapsack on "https://en.wikipedia.org/wiki/Knapsack_problem"
*/

// Greedy approach
public class FractionalKnapsack {
    // function to get maximum value based on their cost which is the product of weight and value of the item.
    private static double getMaxValue(int[] wt, int[] val,int capacity){
        int n=val.length;
        Pair[] item = new Pair[n];
 
        for (int i = 0; i < n; i++) {
            item[i] = new Pair(wt[i], val[i]);
        }
 
        // sorting items by cost;
        Arrays.sort(item);
 
        double totalValue = 0;
 
        for (Pair p : item) {
 
            int curWt = p.wt;
            int curVal = p.val;
 
            if (capacity - curWt >= 0) {
                // this weight can be picked whole
                capacity = capacity - curWt;
                totalValue += curVal ;
            }
            else {
                // item can't be picked whole
                double fraction= ((double)capacity / (double)curWt);
                totalValue += (curVal * fraction);
                capacity=0;
                break;
            }
        }
 
        return totalValue;
    }
 
    // Pair class
    static class Pair implements Comparable<Pair>{
        Double cost;
        int wt, val;
 
        // item value function
        public Pair(int wt, int val){
            this.wt = wt;
            this.val = val;
            cost = (double)val / (double)wt;
        }
        public int compareTo(Pair o){
            return o.cost.compareTo(this.cost);
        }
    }
 
        public static void main(String[] args){
            Scanner sc=new Scanner(System.in);
            
            //Size of arrays
            int size=sc.nextInt();      
            int[] wt = new int[size];

            //Input of weight Array
            for(int i=0;i<size;i++){
                wt[i]=sc.nextInt();
            }
            int[] val = new int[size];

            //Input of value Array
            for(int i=0;i<size;i++){
                val[i]=sc.nextInt();
            }

            int capacity = sc.nextInt();
            //Function Call
            double maxValue = getMaxValue(wt, val, capacity);
    
            System.out.println("Maximum value to be obtained = " + maxValue);
    }
}