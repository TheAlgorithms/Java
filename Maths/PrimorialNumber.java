/*
 * Primorial number denoted by Pn# is the product of the first n prime numbers
 */
import java.util.*;

public class PrimorialNumber {
	
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
        System.out.print("Enter the value of n : ");
        int n = sc.nextInt();
        int Pn=1;
        for(int i=2;n>0;i++){
            if(isPrime(i)){
                Pn*=i;
                if(n>1){
                    System.out.print(i+"*");
                }
                else{
                    System.out.print(i+"=");
                }
                n--;
            }
        }
        System.out.print(Pn);
	}
    public static boolean isPrime(int x){
        if(x<=1){
            return false;
        }
        for(int i=2;i<=x/2;i++){
            if(x%i==0){
                return false;
            }
        }
        return true;
    }
}

