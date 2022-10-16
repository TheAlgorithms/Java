/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circularqueue;

/**
 *
 * @author HP
 */
import java.util.Scanner;
public class Circularqueue {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         Scanner inp=new Scanner(System.in);
        System.out.println("Enter size:");
        int size=inp.nextInt();
        queue cq=new queue(size);
        int choice,element;
        while(true){
            System.out.println("\n 1.Enqueue 2.Dequeue 3.show front 4.Display 5.exit \n");
            System.out.print("Enter:");
            choice=inp.nextInt();
            
            if(choice==1){
                if(cq.isfull()){
                    System.out.println("THE Queue is Full");
                }
                else{
                    System.out.println("Enter Element:");
                    element=inp.nextInt();
                    cq.enqueue(element);
                }
            }
            else if(choice==2){
                if(cq.isempty()){
                    System.out.println("The Queue is Empty");
                }
                else{
                    cq.deque();
                }
            }
            else if(choice==3){
                if(!cq.isempty()){
                    cq.front();
                }
                else{
                    System.out.println("Queue is EMPTY");
                }
            }
            else if(choice==4){
                 if(!cq.isempty()){
                    cq.display();
                }
                else{
                    System.out.println("Queue is EMPTY");
                }
            }
            else if(choice==5){
                System.exit(0);
            }
        
        
        }
    }
    
}
