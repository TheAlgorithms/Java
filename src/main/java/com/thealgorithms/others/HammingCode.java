 //Hamming code for 7-bit
 import java.util.*;
public class HammingCode{

    public static void main(String[] args){
        char[] code = new char[7];
        
        

        Scanner s = new Scanner(System.in);

        System.out.println("Enter the data");
        char[] m = s.next().toCharArray();
        
        
        code[2]=m[0];
        code[4]=m[1];
        code[5]=m[2];
        code[6]=m[3];
        
        char[] r1 = new char[3];
        char[] r2 = new char[3];
        char[] r3 = new char[3];

        r1[0] = m[0];
        r1[1] = m[1];
        r1[2] = m[2];
        

        r2[0] = m[0];
        r2[1] = m[2];
        r2[2] = m[3];


        r3[0] = m[1];
        r3[1] = m[2];
        r3[2] = m[3];

        int countr1=0;
        int countr2=0;
        int countr3=0;

        for(int i=0;i<3;i++){
            if(r1[i]=='1') countr1++;
            if(r2[i]=='1') countr2++;
            if(r3[i]=='1') countr3++;
        }

        if(countr1%2!=0) {
            code[0] = '1';
        }
        else{
            code[0] = '0';
        }
        if(countr2%2!=0) {
            code[1] = '1';
        }
        else{
            code[1] = '0';
        }
        if(countr3%2!=0) {
            code[3] = '1';
        }
        else{
            code[3] = '0';
        }



        for(int i=0;i<7;i++){
            System.out.print(code[i]);
        }

    }
}












