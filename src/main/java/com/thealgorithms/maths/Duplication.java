//Here this code generate 4 digit numbers which are combinations of
//given digits with repeatation or duplication.

import java.util.*;
class Duplication {
    public static void main(String[] args)
    {
        int[] a = new int[4];
        Scanner input = new Scanner(System.in);
        for(int i=0;i<4;i++){
            System.out.println("Enter "+(i+1)+" number : ");
            a[i] = input.nextInt();
        }
        for(int w = 0;w<4;w++){
            for(int x = 0;x<4;x++){
                for(int y = 0;y<4;y++){
                    for(int z = 0;z<4;z++){
                        System.out.println(a[w]+""+a[x]+""+a[y]+""+a[z]);
                    }
                }
            }
        }
    }
}