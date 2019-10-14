package DataStructures.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author https://github.com/Barca88
 */

 public class CompareSortArray{

    public static void main(String[] args) {
        int[] listA = new int[10];
        int i;
        int size = 10;
        Random rand = new Random();

        /* init ListA and List B */
        for (i = 0; i < size; i++) {
            listA[i] = i + rand.nextInt(100);         /* random list */
        }

        System.out.println("listA before: ");
        for(i = 0; i<size;i++){
            System.out.println(i + " " + listA[i]);
        }

        /* merge listA and listB to listC */
        int[] r = compareSort(listA);

        System.out.println("Sorted List: ");
        for(i = 0; i<size;i++){
            System.out.println(i + " " + r[i]);
        }

    }
    
    public static int[] compareSort(int[] l){
        int size = l.length;
        if (size == 0)
            return l;
        int r[] = new int[size];  // return value
        int i;
        int max = l[0];

        for(i = 1; i<size;i++){   // verifying the biggest element
            if(max < l[i])
                max = l[i];
        }

        int aux[] = new int[max+1]; //used to count the occurrences of each element

        for(i = 0; i<size; i++){
            aux[l[i]]++;
        }

        int a = aux[0];           // accumulated value
        aux[0] = 0;
        int a2;
        //transforming the number of occurrences in the first position of that element
        for(i = 1; i<max+1; i++){
            if(aux[i] == 0) continue;
            a2 = aux[i];
            aux[i] = a;
            a += a2;
        }
        // create the sorted output
        for(i = 0; i<size; i++){
            r[aux[l[i]]] = l[i];
            aux[l[i]]++;
        }
        return r;
    }
}