/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queue;

/**
 *
 * @author HP
 */
import java.util.Scanner;
public class Queue {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner inp=new Scanner(System.in);
        System.out.println("Enter size of array:");
        int size=inp.nextInt();
        queuap stk=new queuap(size);
        int ch;
        int element;
        while(true){
            System.out.println("Enter 1: Enqueue");
            System.out.println("Enter 2: Dequeue/Remove element");
            System.out.println("Enter 3:print Elements");
            System.out.println("Enter 0: Exit");
            System.out.println("-----------------------------");
            System.out.print("Enter :: ");
            ch=inp.nextInt();
            System.out.println("");
            if(ch==1){
                 if(!stk.isfull()){
                    System.out.println("Enter value:");
                    int v=inp.nextInt();
                    stk.enque(v);}
                else{
                    System.out.println("Over flow");
                }
            }
            else if(ch==2){
                if(!stk.isempty()){
                    System.out.println("Remove element is:"+stk.dequeue());
                }
                else{
                    System.out.println("under flow");
                }
            }
            else if(ch==3){
                stk.print();
            }
            else if(ch==0){
                System.exit(0);
            }
        
        }
    }
    
}
