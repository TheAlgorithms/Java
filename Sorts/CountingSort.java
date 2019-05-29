package Sorts;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static Sorts.SortUtils.print;

/**
 * @author Youssef Ali (https://github.com/youssefAli11997)
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 */
class CountingSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        return sort(Arrays.asList(unsorted)).toArray(unsorted);
    }

    /**
     * This method implements the Generic Counting Sort
     *
     * @param list The list to be sorted
     *             <p>
     *             Sorts the list in increasing order
     *             The method uses list elements as keys in the frequency map
     **/
    @Override
    public <T extends Comparable<T>> List<T> sort(List<T> list) {

        Map<T, Integer> frequency = new TreeMap<>();
        // The final output array
        List<T> sortedArray = new ArrayList<>(list.size());

        // Counting the frequency of @param array elements
        list.forEach(v -> frequency.put(v, frequency.getOrDefault(v, 0) + 1));

        // Filling the sortedArray
        for (Map.Entry<T, Integer> element : frequency.entrySet()) {
            for (int j = 0; j < element.getValue(); j++) {
                sortedArray.add(element.getKey());
            }
        }

        return sortedArray;
    }


    /**
     * Stream Counting Sort
     * The same as method {@link CountingSort#sort(List)} } but this method uses stream API
     *
     * @param list The list to be sorted
     **/
    private static <T extends Comparable<T>> List<T> streamSort(List<T> list) {
        return list.stream()
                .collect(toMap(k -> k, v -> 1, (v1, v2) -> v1 + v2, TreeMap::new))
                .entrySet()
                .stream()
                .flatMap(entry -> IntStream.rangeClosed(1, entry.getValue()).mapToObj(t -> entry.getKey()))
                .collect(toList());
    }

    // Driver Program
    public static void main(String[] args) {
        // Integer Input
        List<Integer> unsortedInts = Stream.of(4, 23, 6, 78, 1, 54, 23, 1, 9, 231, 9, 12).collect(toList());
        CountingSort countingSort = new CountingSort();

        System.out.println("Before Sorting:");
        print(unsortedInts);

        // Output => 1 1 4 6 9 9 12 23 23 54 78 231
        System.out.println("After Sorting:");
        print(countingSort.sort(unsortedInts));
        System.out.println("After Sorting By Streams:");
        print(streamSort(unsortedInts));

        System.out.println("\n------------------------------\n");

        // String Input
        List<String> unsortedStrings = Stream.of("c", "a", "e", "b", "d", "a", "f", "g", "c").collect(toList());

        System.out.println("Before Sorting:");
        print(unsortedStrings);

        //Output => a a b c c d e f g
        System.out.println("After Sorting:");
        print(countingSort.sort(unsortedStrings));

        System.out.println("After Sorting By Streams:");
        print(streamSort(unsortedStrings));

    }
}
