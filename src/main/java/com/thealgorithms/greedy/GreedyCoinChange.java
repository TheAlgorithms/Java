import java.util.Arrays;

public class GreedyCoinChange {
    public static void main(String[] args) {
        int[] coinDenominations = {1, 5, 10, 25}; // Coin denominations in cents
        int targetAmount = 63; // Target amount in cents

        int[] result = getMinCoins(coinDenominations, targetAmount);
        System.out.println("Minimum number of coins needed: " + Arrays.toString(result));
    }

    public static int[] getMinCoins(int[] coinDenominations, int targetAmount) {
        int numDenominations = coinDenominations.length;
        int[] result = new int[numDenominations];
        Arrays.fill(result, 0);

        // Traverse the denominations in reverse order (greedy choice)
        for (int i = numDenominations - 1; i >= 0; i--) {
            while (targetAmount >= coinDenominations[i]) {
                targetAmount -= coinDenominations[i];
                result[i]++;
            }
        }

        return result;
    }
}
