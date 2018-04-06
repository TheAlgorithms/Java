import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 *
 * @author Youssef Ali (https://github.com/youssefAli11997)
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 *
 */

class CountingSort {

    /**
     * This method implements the Generic Counting Sort
     *
     * @param list The list to be sorted
     *
     * Sorts the list in increasing order
     * The method uses list elements as keys in the frequency map
     **/
    public static <T extends Comparable<T>> List<T> CS(List<T> list) {

        Map<T, Integer> frequency = new TreeMap<>();
        // The final output array
        List<T> sortedArray = new ArrayList<>(list.size());

        // Counting the frequency of @param array elements
        list.forEach(v -> frequency.put(v, frequency.getOrDefault(v, 0) + 1));

        // Filling the sortedArray
        for(Map.Entry<T, Integer> element : frequency.entrySet()) {
            for(int j=0; j<element.getValue(); j++)
                sortedArray.add(element.getKey());
        }

        return sortedArray;
    }


    /**
     * Stream Counting Sort
     * The same as method {@link CountingSort#CS(List)} but this method uses stream API
     *
     * @param list The list to be sorted
     *
     **/
    public static <T extends Comparable<T>> List<T> streamCS(List<T> list) {
        return  list.stream()
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

        System.out.println("Before Sorting:");
        printList(unsortedInts);

        // Output => 1 1 4 6 9 9 12 23 23 54 78 231
        System.out.println("After Sorting:");
        printList(CS(unsortedInts));
        System.out.println("After Sorting By Streams:");
        printList(streamCS(unsortedInts));

        System.out.println("\n------------------------------\n");

        // String Input
        List<String> unsortedStrings = Stream.of("c", "a", "e", "b","d", "a", "f", "g", "c").collect(toList());

        System.out.println("Before Sorting:");
        printList(unsortedStrings);

        //Output => a a b c c d e f g
        System.out.println("After Sorting:");
        printList(CS(unsortedStrings));

        System.out.println("After Sorting By Streams:");
        printList(streamCS(unsortedStrings));

    }

    /**
     * Just print list
     * @param toPrint - a list which should be printed
     */
    private static void printList(List<?> toPrint){
        toPrint.stream()
                .map(Object::toString)
                .map(str -> str + " ")
                .forEach(System.out::print);

        System.out.println();
    }
}
