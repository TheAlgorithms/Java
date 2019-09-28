package DynamicProgramming;

import org.junit.Test;
import DynamicProgramming.Knapsack;
import static org.junit.Assert.assertEquals;

public class KnapsackTest{
/*
1	private static int knapSack(int W, int wt[], int val[], int n) throws IllegalArgumentException {
2        if(wt == null || val == null)
3            throw new IllegalArgumentException();
4        int i, w;
5        int rv[][] = new int[n + 1][W + 1];
6        for (i = 0; i <= n; i++) {
7            for (w = 0; w <= W; w++) {
8                if (i == 0 || w == 0)
9                    rv[i][w] = 0;
10                else if (wt[i - 1] <= w)
11                    rv[i][w] = Math.max(val[i - 1] + rv[i - 1][w - wt[i - 1]], rv[i - 1][w]);
12               else
13                    rv[i][w] = rv[i - 1][w];
14            }
15        }
16        return rv[n][W];
17    }
*/

	//all du-path for W
	@Test
	public void test1(){
		int w = 5;
	    int val[] = new int[]{};
	    int wt[] = new int[]{};
	    Knapsack.knapSack(w, val, wt, wt.length);
	    //throws error
	}
	// 1 -> 5 -> 7 -> 16

	public void test2(){
		int w = 1;
	    int val[] = new int[]{2, 4};
	    int wt[] = new int[]{1, 2};
	    assertEquals(Knapsack.knapSack(w, val, wt, val.length), 2);
	}
	// 1 -> 5 -> 7 -> 16

	//all du-path for wt[]
	@Test
	public void test3(){
		int w = 6;
	    int val[] = new int[]{};
	    int wt[] = new int[]{};
	    Knapsack.knapSack(w, val, wt, wt.length);
	    //throws error
	}
	// 1 -> 2 -> 3

	@Test
	public void test4(){
		int w = 0;
	    int val[] = new int[]{2, 4};
	    int wt[] = new int[]{1, 2};
	    assertEquals(Knapsack.knapSack(w, val, wt, val.length), 0);
	}
	// 1 -> 2 -> 4 -> 16

	@Test
	public void test5(){
		int w = 2;
	    int val[] = new int[]{2, 4};
	    int wt[] = new int[]{1, 2};
	    assertEquals(Knapsack.knapSack(w, val, wt, val.length), 2);
	}
	// 1 -> 2 -> 7 -> (-> 10 -> 11) (loop)

	//all du-path for val[]

	@Test
	public void test6(){
		int w = 7;
	    int val[] = new int[]{};
	    int wt[] = new int[]{};
	    Knapsack.knapSack(w, val, wt, wt.length);
	    //throws error
	}
	// 1 -> 2 -> 3

	@Test
	public void test7(){
		int w = 0;
	    int val[] = new int[]{2, 4};
	    int wt[] = new int[]{1, 2};
	    assertEquals(Knapsack.knapSack(w, val, wt, val.length), 0);
	}
	// 1 -> 2 -> 4 -> 16

	@Test
	public void test8(){
		int w = 2;
	    int val[] = new int[]{2, 4};
	    int wt[] = new int[]{1, 2};
	    assertEquals(Knapsack.knapSack(w, val, wt, val.length), 2);
	}
	// 1 -> 2 -> 7 -> (-> 10 -> 11) (loop) -> 16

}
