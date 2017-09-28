package searches;

import static searches.BinarySearch.BS;

public class BinarySearchTest {

    public static void main(String[] args) {
        Integer[] array = new Integer[10];
        int key = 5;
        for (int i = 0; i < 10; i++)
            array[i] = i + 1;
        int index = BS(array, key, 0, 9);
        if (index != -1)
            System.out.println("Number " + key + " found at index number : " + index);
        else
            System.out.println("Not found");

        String[] array1 = {"a", "b", "c", "d", "e"};
        String key1 = "d";
        int index1 = BS(array1, key1, 0, array1.length - 1);
        if (index1 != -1)
            System.out.println("String " + key1 + " found at index number : " + index1);
        else
            System.out.println("Not found");
    }
}