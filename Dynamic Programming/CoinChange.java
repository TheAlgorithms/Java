class CoinChange {
    public staitc int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for(int i = 1; i <= amount; i++) {
            for(int j = 0; j < coins.length; j++)
                if(i >= coins[j])
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
    public static void main(String args[]) {
	int[] coins = new int[] {1, 2, 5};
	int amount = 11;
	System.out.println(coinChange(coins, amount)); // answer should be 3
    }
}
