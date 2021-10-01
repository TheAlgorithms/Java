/**
 * @author Sreejita Roy (https://github.com/pseudonerd16)
 */

public static int exponentialSearch(int[] integers, int elementToSearch) {

    if (integers[0] == elementToSearch)
        return 0;
    if (integers[integers.length - 1] == elementToSearch)
        return integers.length;

    int range = 1;

    while (range < integers.length && integers[range] <= elementToSearch) {
        range = range * 2;
    }

    return Arrays.binarySearch(integers, range / 2, Math.min(range, integers.length), elementToSearch);
}