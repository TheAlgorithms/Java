/**
 *
 * @author Varun Upadhyay (https://github.com/varunu28)
 * @contributor Choi Ju Hun (https://github.com/JuhunC)
 */

public class CoinChange {

    // Driver Program
    public static void main(String[] args) {

        int amount = 12;
        int[] coins = {1, 2, 5};

        System.out.println("Number of combinations of getting change for " + amount + " is: " + change(coins, amount));
        System.out.println("Combination is : ");
        int[] result = minChange(coins,amount);
        int sum =0;
        for(int i =0;i<coins.length;i++) {
        	sum += result[i];
        	System.out.println(coins[i] + "coin : x" + result[i]);
        }
        System.out.println("Min Number of Coins Used for " + amount + " is " + sum);
    }
    /**
     * This method finds minimum amount of Coins
     * @param coins The List of Coins
     * @param amount The amount for which we need to find the change
     * @return Minimum amount of Coins
     */
    public static int[] minChange(int[] coins, int amount) {
    	int coin[] = new int[amount+1];
    	int comb[][] = new int[amount+1][];
    	
    	// set Combination Array
    	for(int i =0;i<amount+1;i++) {
    		comb[i] = new int[coins.length];
    	}
    	
    	// Init Combinations
    	for(int i =0;i<coins.length;i++) {
    		if(coins[i] <= amount) {
    			coin[coins[i]] = 1;
    			comb[coins[i]][i] = 1;
    		}
    	}
    	for(int i =1;i<=amount;i++) {
    		if(coin[i] != 1) {
    			coin[i] = i;
    			comb[i][0] = i;
    		}
    	}
    	
    	for(int i =1;i<=amount;i++) {
    		for(int m=0;m<coins.length;m++) {
    			if(i-coins[m] >= 1) {
    				if(coin[i-coins[m]]+1 < coin[i]) {
    					coin[i] = coin[i-coins[m]] +1;
    					for(int k=0;k<coins.length;k++) {
    						comb[i][k] = comb[i-coins[m]][k];
    					}
    					comb[i][m]++;
    				}
    			}
    		}
    	}
    	return comb[amount];
    }
    /**
     * This method finds the number of combinations of getting change for a given amount and change coins
     *
     * @param coins The list of coins
     * @param amount The amount for which we need to find the change
     * Finds the number of combinations of change
     **/
    public static int change(int[] coins, int amount) {

        int[] combinations = new int[amount+1];
        combinations[0] = 1;

        for (int coin : coins) {
            for (int i=coin; i<amount+1; i++) {
                    combinations[i] += combinations[i-coin];
            }
            // Uncomment the below line to see the state of combinations for each coin
            // printAmount(combinations);
        }

        return combinations[amount];
    }

    // A basic print method which prints all the contents of the array
    public static void printAmount(int[] arr) {

        for (int i=0; i<arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
