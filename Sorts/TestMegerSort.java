public abstract class SortingAlgorithmTest {

  private SortingAlgorithm sortingAlgorithm;

  public abstract SortingAlgorithm getSortingAlgorithm();

  @Before public void setUp() {
    sortingAlgorithm = getSortingAlgorithm();
  }

  @Test(expected = IllegalArgumentException.class) public void shouldNotAcceptNullArrays() {
    sortingAlgorithm.sort(null);
  }

  @Test public void shouldNotModifyArrayIfIsAlreadySorted() {
    int[] input = { 1, 2, 3, 4 };

    sortingAlgorithm.sort(input);

    int[] expectedArray = { 1, 2, 3, 4 };
    assertArrayEquals(expectedArray, input);
  }

  @Test public void shouldSortArrayWhenTheInputDataIsInDescendingOrder() {
    int[] input = { 4, 3, 2, 1 };

    sortingAlgorithm.sort(input);

    int[] expectedArray = { 1, 2, 3, 4 };
    assertArrayEquals(expectedArray, input);
  }

  @Test public void shouldSortArrayPartiallySorted() {
    int[] input = { 1, 2, 4, 3 };

    sortingAlgorithm.sort(input);

    int[] expectedArray = { 1, 2, 3, 4 };
    assertArrayEquals(expectedArray, input);
  }

  @Test public void shouldSortArray() {
    int[] input = { 3, 4, 1, 2 };

    sortingAlgorithm.sort(input);

    int[] expectedArray = { 1, 2, 3, 4 };
    assertArrayEquals(expectedArray, input);
  }

  @Test(timeout = 5 * 1000)
  public void shouldSortSpecialArray() {
    int[] input = {12, -37, -5, 43, 62, 45, -95, -70, -55, -62, -24, -14,
            -75, 43, 9, 58, -62, -22, -55};

    sortingAlgorithm.sort(input);

    int[] expectedArray = {-95, -75, -70, -62, -62, -55, -55, -37, -24, -22, -14, -5, 9, 12, 43, 43, 45, 58, 62};
    assertArrayEquals(expectedArray, input);
  }
}
