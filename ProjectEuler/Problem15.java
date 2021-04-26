package ProjectEuler;

public class Problem15 {
  public static void main(String[] args) {
    assert solution() == 137846528820L;
  }

  public static long solution() {
    long paths = 1;

    for (int i = 0; i < 20; i++) {
      paths *= (2 * 20) - i;
      paths /= i + 1;
    }

    return paths;
  }
}
