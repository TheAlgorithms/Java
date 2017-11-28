/**
 *
 * @author Varun Upadhyay (https://github.com/varunu28)
 *
 */

public class CoinChange {

    // Driver Program
    public static void main(String[] args) {

        int amount = 12;
        int[] coins = {1, 2, 5};

        System.out.println("Number of combinations of getting change for " + amount + " is: " + change(coins, amount));
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
                if (i>=coin) {
                    combinations[i] += combinations[i-coin];
                }
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
