package ProjectEuler;

/**
 * In the United Kingdom the currency is made up of pound (£) and pence (p). There are eight coins
 * in general circulation:
 *
 * <p>1p, 2p, 5p, 10p, 20p, 50p, £1 (100p), and £2 (200p).
 *
 * <p>It is possible to make £2 in the following way:
 *
 * <p>1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
 *
 * <p>How many different ways can £2 be made using any number of coins?
 *
 * <p>link: https://projecteuler.net/problem=31
 */
public class Problem31 {
  public static void main(String[] args) {
    assert solution1() == 1;
  }

  public static int solution1() {
    int target = 200;
    int[] coins = {1, 2, 5, 10, 20, 50, 100, 200};
    int[] combos = new int[201];
    combos[0] = 1;

    for (int coin : coins) {
      for (int j = coin; j <= target; j++) {
        combos[j] += combos[j - coin];
      }
    }

    return combos[200];
  }
}
