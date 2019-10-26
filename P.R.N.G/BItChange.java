import java.util.Scanner;
/**
 * @author Abhinandan Pal (https://github.com/Abhinandan-Pal)
 */
public class PRNG 
{
    int key = 1356501810;// to store random number initialized with seed
    String KeyC;// to store the key String based on which bits will be changed
    String KeyCGenerate()
    {
        /*
         * Generating the Key String by taking the previous random number as String.
         * Then adding it's reverse at the begining and even position values at last.
         */
        KeyC = String.valueOf(key);
        String a="";
        String b ="";
        for(int i =0;i<KeyC.length();i++)
        {
            a = KeyC.charAt(i)+a;
            if(i%2==0)
               b = b + KeyC.charAt(i);
        }
        KeyC = a+KeyC+b;
        return KeyC;
    }
    int modifyBit(int n,int p) 
    { 
            //to modify a bit at position p in n to its opposite.
            // Change Zero at position p to one or vice-versa.
            byte ChangeTo;
            if (n << ~p < 0) {
               ChangeTo = 0;// pth bit was 1
            } 
            else {
               ChangeTo = 1;// pth bit was 0
            }   
            int mask = 1 << p; 
            return (n & ~mask) |  
                   ((ChangeTo << p) & mask); 
    } 
    double nextRand()
    {
        /*
         * To find the next random number based on the previos number 
         * and to trigger the other function in the correct order.
         */
        int n = key;
        KeyCGenerate();
        int Key_len = KeyC.length(); 
        for(int i = 0;i<Key_len-4;i++)
        {
            int pos = Integer.parseInt(KeyC.substring(i,i+3))%31;
            n = modifyBit(n,pos);
        }
        key = n;
        
        return key/2147483647.0;
    }
     double nextRand(int  min,int max)
    {
        /*
         * To find the next random number based on the previos number 
         * and to trigger the other function in the correct order.
         * The random number generated X must follow min<=X<=max.
         */
        int n = key;
        KeyCGenerate();
        int Key_len = KeyC.length(); 
        for(int i = 0;i<Key_len-4;i++)
        {
            int pos = Integer.parseInt(KeyC.substring(i,i+3))%31;
            n = modifyBit(n,pos);
        }
        key = n;
        int value = min + (int)(key/2147483647.0 * ((max - min) + 1));
        return value;
    }
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of required Random numbers");
        int No_of_rand = sc.nextInt();
        System.out.println("Enter lower bound");
        int min = sc.nextInt();
        System.out.println("Enter upper bound");
        int max = sc.nextInt();
        PRNG PRNG_object1 = new PRNG();
        for(int i =0;i<No_of_rand;i++)
        {
            System.out.println(PRNG_object1.nextRand(min,max));
        }
    }
}
