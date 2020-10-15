import java.util.*;

public class FibonacciHuge {
    private static long getFibonacciHugeNaive(long n, long m) {
        int fibonacci;
        if(n>m)
            {fibonacci = (int) (n % pissano(m));}
        else
            {fibonacci= (int)n;}

        long[] a = new long[fibonacci+1];
        a[0]=0;
        a[1]=1;
        for(int i=2;i<=fibonacci;i++){
            a[i] = (long)a[i-1]+a[i-2];
        }
        
        return +((a[fibonacci-1]%m)+(a[fibonacci-2]%m))%m;
    }
    private static int pissano(Long m){
        if(m==1)
        return 1;
        int first = 1, second = 1, number = 1, next = 0; 
        while (true) 
        { 
            // add previous two remainders and  
            // then take its modulo p. 
            next = (int) ((first + second) % m);
            first = second; 
            second = next; 
            number++; 
            if(first==0 && second == 1)
            break;
        } 
        return number;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.println(getFibonacciHugeNaive(n, m));
        scanner.close();
    }
}

