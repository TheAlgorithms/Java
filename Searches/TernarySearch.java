    /**

     ** Java Program to Implement Ternary Search Algorithm

     **/

     

    import java.util.Scanner;

     

    /** Class TernarySearch **/

    public class TernarySearch

    {

        /** call function **/

        public static int ternarySearch (int[] A, int value)

        {

            return ternarySearch(A, value, 0, A.length - 1);

        }

        /** TernarySearch function **/

        public static int ternarySearch (int[] A, int value, int start, int end)

        {

            if (start > end) 

                return -1;       

     

            /** First boundary: add 1/3 of length to start **/

            int mid1 = start + (end-start) / 3;        

            /** Second boundary: add 2/3 of length to start **/

            int mid2 = start + 2*(end-start) / 3;

     

            if (A[mid1] == value) 

                return mid1;

     

            else if (A[mid2] == value) 

                return mid2;

            /** Search 1st third **/

            else if (value < A[mid1]) 

                return ternarySearch (A, value, start, mid1-1);

            /** Search 3rd third **/

            else if (value > A[mid2]) 

                return ternarySearch (A, value, mid2+1, end);

            /** Search middle third **/

            else 

                return ternarySearch (A, value, mid1,mid2);        

        }

        /** Main method **/

        public static void main(String[] args) 

        {

            Scanner scan = new Scanner( System.in );        

            System.out.println("Ternary Search Test\n");

            int n, i;

            /** Accept number of elements **/

            System.out.println("Enter number of integer elements");

            n = scan.nextInt();

            /** Create integer array on n elements **/

            int arr[] = new int[ n ];

            /** Accept elements **/

            System.out.println("\nEnter "+ n +" sorted integer elements");

            for (i = 0; i < n; i++)

                arr[i] = scan.nextInt();

            System.out.println("\nEnter element to search for : ");

            int key = scan.nextInt();

     

            int result = ternarySearch(arr, key);

     

            if (result == -1)

                System.out.println("\n"+ key +" element not found");

            else

                System.out.println("\n"+ key +" element found at position "+ result);

     

        }    

    }