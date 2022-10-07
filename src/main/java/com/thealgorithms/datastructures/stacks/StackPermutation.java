import java.io.*;
import java.util.*;

class IntArray
{
    public static int[] input(BufferedReader vr, int n)throws IOException
    {
        String[] s = br.readLine().trim().split(" ");
        int[] a = new int[n];
        for(int i = 0;i<n; i++)
            a[i] = Integer.parseInt(s[i]);

            return a;
    }

    public static void print(int[] a)
    {
        for(int e : a)
            System.out.print(e + " ");
        System.out.println();
    }

    public static void print(ArrayList<Integer> a)
    {
        for(int e : a)
            System.out.print(e + " ");
        System.out.println();
    }
}

class Jonsnow
{
    public static void main(String[] args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t;
        t = Integer.parseInt(br.readLine());
        while(t-- > 0)
        {
            int N;
            N = Integer.parseInt(br.readLine());

            int[] A = IntArray.input(br,N);
            int[] B = IntArray.input(br,N);

            Solution obj = new Solution();
            int res = obj.isStackPermutation(N, A, B);

            System.out.println(res);
            
        }
    }
}

class Solution
{
    public static int isStackPermutation(int n, int[] ip, int[] op) 
    {
         Stack<Integer> sk = new Stack<>();
       int j=0;
       for(int i=0;i<n;i++)
       {
           sk.push(ip[i]);
           while(!sk.isEmpty() && sk.peek() == op[j])
           {
               sk.pop();
               j++;
           }
       }
       if(sk.isEmpty())
       {
           return 1;
       }
       return 0;
    }
}