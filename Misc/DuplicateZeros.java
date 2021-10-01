package org.rytnic.see;

import java.sql.SQLOutput;
import java.util.Scanner;

public class DuplicateZeros {
       static public void duplicateZeros(int[] arr)
        {

            aa:
            for(int i =0;i<arr.length;i++)
            {
                if (arr[i] == 0) {
                    if (i != arr.length-1) {
                        for(int j = arr.length -1; j>i+1;j--) {
                            arr[j] = arr[j-1];
                        }
                        arr[i+1] = 0;
                        i++;
                    } else
                        break aa;
                }

            }
            for(int i : arr)
                System.out.println(i);
        }

        public static void main(String [] args ){
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the no of elements");
            int a = sc.nextInt();
            System.out.println("Enter the array value");
            int arr[] = new int[a];
            for (int i=0 ; i<a;i++)
                arr[i] = sc.nextInt();

            duplicateZeros(arr);
        }
    }

