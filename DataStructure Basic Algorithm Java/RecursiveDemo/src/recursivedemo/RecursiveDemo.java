/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursivedemo;

/**
 *
 * @author HP
 */
import java.util.Scanner;
public class RecursiveDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int N1, N2;
        RecursiveAlgos recObj = new RecursiveAlgos();
        
        int choice;            
        while(true)
        {
            System.out.println("\n########## RECURSION MENUE ##########");
            System.out.println("\t1. Euclidean GCD");
            System.out.println("\t2. Fibonacci Term");
            System.out.println("\t3. Fibonacci Series");
            System.out.println("\t4. Triangular Series");
            System.out.println("\t5. Sum of Squares");
	    System.out.println("\t6. Factorial ");
            System.out.println("\t7. Sum of Array ");
            System.out.println("\t8. Reverse an Array");
            System.out.println("\t9. Binary Search:");
            System.out.println("\t0. Exit Programe");
            System.out.println("------------------------------------");
            System.out.print("Enter Your Choice (0/1/2/3/4/5/6/7/8/9) : ");
            
            choice =  input.nextInt();
            
            if(choice == 1)
            {
                System.out.println("\n------------------------------------");
                System.out.print("\nEnter First Number ");
                N1 = input.nextInt();
                System.out.print("\nEnter Second Number ");
                N2 = input.nextInt();
                int GCD = recObj.EuGCD(N1, N2);
                System.out.print("\nThe GCD of "+N1+" and "+N2+" : "+GCD);
        
            }
            else if(choice == 2)
            {
		/*
                System.out.println("\n------------------------------------");
                System.out.print("\nEnter Number for Fib Term : ");
                int F = input.nextInt();
                int Term = recObj.Fibonacci(F);
                System.out.print("\nThe "+F+"th Term of Fibonacci Seireis is "+Term);
                */
		System.out.println("Not Yet Implemented");
            }
            else if(choice == 3)
            {
		/*
                System.out.println("\n------------------------------------");
                System.out.print("\nEnter Number of Fib Terms : ");
                int N = input.nextInt();
                int Term;
                for(int i=1; i<=N; i++)
                {
                    Term = recObj.Fibonacci(i);
                    System.out.print(Term+" ");
                }
                System.out.println();
                System.out.println("\n------------------------------------");
		*/
		System.out.println("Not Yet Implemented");
                
            }
            else if(choice == 4)
            {
		/*
                System.out.println("\n------------------------------------");
                System.out.print("Enter the Triangular Series Range : ");
                int R = input.nextInt();
                int Term = 0;
                System.out.println("The Triangular Series : ");
                for(int i=1; i<=R; i++)
                {
                    Term = recObj.Triangular(i);
                    System.out.print(Term+" ");
                }
                System.out.println();
                System.out.println("\n------------------------------------");
		*/
		System.out.println("Not Yet Implemented");
                
            }
            else if(choice == 5)
            {
		/*
                System.out.println("\n------------------------------------");
                System.out.print("Sum of Square Range: ");
                int R = input.nextInt();
                System.out.print("The Sum of First"+R+" integers:");
                int SUM = recObj.SquarSum(R);
                System.out.println(SUM);
                System.out.println("\n------------------------------------");
		*/
		System.out.println("Not Yet Implemented");
                
            }
	    else if(choice == 6)
            {
		
                System.out.println("\n------------------------------------");
                System.out.print("Enter Number to Find its Factorial : ");
                int N = input.nextInt();
                System.out.print("The Factorial of "+N+" :");
                int Fact = recObj.Factorial(N);
                System.out.println(Fact);
                System.out.println("\n------------------------------------");
		

                
            }
            else if(choice==7){
                System.out.println("\n------------------------------------");
                System.out.print("Enter Size of array : ");
                int N = input.nextInt();
                int Arr[]=new int[N];
                for (int i=0;i<Arr.length;i++){
                    System.out.print("Enter number on Index-"+i+"=");
                    Arr[i]=input.nextInt();
                    }
                int Linear = recObj.LinearSum(Arr,N);
                System.out.println("Sum Of Array :");
                System.out.println(Linear);
                System.out.println("\n------------------------------------");
            
            }
            else if (choice==8){
                System.out.println("\n------------------------------------");
                System.out.print("Enter Size of array : ");
                int N = input.nextInt();
                int Arr[]=new int[N];
                for (int i=0;i<Arr.length;i++){
                    System.out.print("Enter number on Index-"+i+"=");
                    Arr[i]=input.nextInt();
                    }
                
                //recObj.reverseArray(Arr,int i=0,int j=N-1);
                System.out.println("Reverse Array :");
                
            }
            else if(choice==9){
                System.out.println("\n------------------------------------");
                System.out.print("Enter Size of array : ");
                int N = input.nextInt();
                int Arr[]=new int[N];
                for (int i=0;i<Arr.length;i++){
                    System.out.print("Enter number on Index-"+i+"=");
                    Arr[i]=input.nextInt();
                }
                System.out.println("---Your List---");
                for(int i=0;i<Arr.length;i++){
                    System.out.print(Arr[i]+" ");
                }
                System.out.println(" ");
                int low=0;
                int high=N-1;
                
                System.out.println("Enter target To Find");
                int key=input.nextInt();
                int binarysearch =recObj.BinarySearch(key, Arr, low, high);
                System.out.println("Your target index : "+binarysearch+" value: "+Arr[binarysearch]);
                
            }
            
            else if(choice == 0)
            {
                System.out.println("\n--------------------------------");
                System.out.println("Programe Exiting.......! \nThank You for Being Here");
                System.out.println("\n--------------------------------");
                System.exit(0);
            }
        }
    
    }
    
}
