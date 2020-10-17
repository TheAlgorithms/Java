package DataStructures.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author https://github.com/shellhub
 */

public class MergeSortedArrayList {
    public static void main(String[] args) {
        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();

        /* init ListA and List B */
        for (int i = 1; i <= 10; i += 2) {
            listA.add(i);          /* listA: [1, 3, 5, 7, 9]  */
            listB.add(i + 1);      /* listB: [2, 4, 6, 8, 10] */
        }

        /* merge listA and listB to listC */
        List<Integer> listC=merge(listA, listB);

        System.out.println("listA: " + listA);
        System.out.println("listB: " + listB);
        System.out.println("listC: " + listC);
    }

    /**
     * merge two sorted ArrayList
     *
     * @param listA the first list to merge
     * @param listB the second list to merge
     * @param listC the result list after merging
     */
    public static List<Integer> merge(List<Integer> listA, List<Integer> listB) {
        int pa = 0; /* the index of listA */
        int pb = 0; /* the index of listB */

        List<Integer> listC=new ArrayList<>();

        while (pa < listA.size() && pb < listB.size()) {
            if (listA.get(pa) <= listB.get(pb)) {
                listC.add(listA.get(pa++));
            } else {
                listC.add(listB.get(pb++));
            }
        }

        /* copy left element of listA to listC */
        while (pa < listA.size()) {
            listC.add(listA.get(pa++));
        }

        /* copy left element of listB to listC */
        while (pb < listB.size()) {
            listC.add(listB.get(pb++));
        }

        return listC;
    }

}
