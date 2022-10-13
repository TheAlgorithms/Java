
class diffi_helman{
    private static long power(long a, long b, long p)
    {
        if (b == 1)
            return a;
        else
            return (((long)Math.pow(a, b)) % p);
    }
    public static void main(String[] args)
    {
        long P, G, x, a, y, b, ka, kb;
        P = 23;
        System.out.println("The value of P:" + P);
        G = 9;
        System.out.println("The value of G:" + G);
        a = 4;
        System.out.println("The private key a for Alice:" + a);
        x = power(G, a, P);
        b = 3;
        System.out.println("The private key b for Bob:" + b);
        y = power(G, b, P);
        ka = power(y, a, P);
        kb = power(x, b, P);
        
        System.out.println("Secret key for the Alice is:" + ka);
        System.out.println("Secret key for the Bob is:" + kb);
    }
}
    
