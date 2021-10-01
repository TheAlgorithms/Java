import java.util.Scanner;
public class stringsort
{
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        int n, m, i = 0 , j = 0;
        System.out.println("Enter Size of First String");
        n = in.nextInt();
        System.out.println("Enter String of length "+n+":");
        String S1 = in.next();
        System.out.println("Enter Size of Second String");
        m= in.nextInt();
        System.out.println("Enter String of length "+m+":");
        String S2 = in.next();
        String S3 = S1+S2;
        String S4 = "";
        char a[] = new char[n+m];
        S3 = S3.toUpperCase();
        for( i = 0 ; i < (n+m) ; i++){
            a[i] = S3.charAt(i);
        }
        for(i = 0; i < n+m ; i++)
        {
            for(j = 0; j < n+m-1; j++)
            {
                if(a[j+1] < a[j])
                {
                    char temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }
        for( i = 0 ; i < (n+m) ; i++){
            S4 = S4+a[i];
        }
        System.out.println("Merged String : "+S4);
    }
}