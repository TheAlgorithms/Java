import java.util.*;

class MergeMSortedArrays {
    static class Element {
        int value;
        int arrayIndex;
        int elementIndex;

        Element(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }
    }

    public static List<Integer> mergeSortedArrays(int[][] arrays) {
        PriorityQueue<Element> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e.value));
        List<Integer> result = new ArrayList<>();

        // Step 1: Push the first element of each array
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new Element(arrays[i][0], i, 0));
            }
        }

        // Step 2: Extract the smallest element and push the next from same array
        while (!minHeap.isEmpty()) {
            Element current = minHeap.poll();
            result.add(current.value);

            int nextIndex = current.elementIndex + 1;
            if (nextIndex < arrays[current.arrayIndex].length) {
                minHeap.offer(new Element(
                    arrays[current.arrayIndex][nextIndex],
                    current.arrayIndex,
                    nextIndex
                ));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] arrays = {
            {1, 4, 7},
            {2, 5, 8},
            {3, 6, 9}
        };

        System.out.println("Merged Sorted Array: " + mergeSortedArrays(arrays));
    }
}
