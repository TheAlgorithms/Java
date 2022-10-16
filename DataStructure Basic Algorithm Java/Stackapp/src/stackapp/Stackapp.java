/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackapp;

/**
 *
 * @author HP
 */
import java.util.Scanner;
public class Stackapp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner inp=new Scanner(System.in);
        System.out.println("Enter size of array:");
        int size=inp.nextInt();
        stack stk=new stack(size);
        int ch;
        int element;
        while(true){
            System.out.println("Enter 1: insert element");
            System.out.println("Enter 2: POP/Remove element");
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
                    stk.insert(v);}
                else{
                    System.out.println("Over flow");
                }
            }
            else if(ch==2){
                if(!stk.isempty()){
                    System.out.println("Remove element is:"+stk.pop());
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
