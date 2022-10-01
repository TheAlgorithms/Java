import java.util.Scanner;
//https://en.wikipedia.org/wiki/Array_(data_structure)
public class ArrayInsertion {
    public static void main(String[] args) {
        System.out.print("Enter size of the Array: ");
        Scanner input = new Scanner(System.in);
        int n= input.nextInt();
        System.out.println("Enter "+n+" elements: ");
        int[] insert=new int[n];
        for (int i = 0; i < n; i++)
            insert[i]=input.nextInt();
        System.out.print("Your array is: ");
        for (int i = 0; i < n; i++) {
            System.out.print(insert[i]+" ");
        }
    }
}