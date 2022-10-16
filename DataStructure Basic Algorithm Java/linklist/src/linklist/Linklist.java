/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linklist;

/**
 *
 * @author HP
 */
import java.util.Scanner;
public class Linklist {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner inp=new Scanner(System.in);
        single sl=new single();
        int ch;
        int data;
        while(true){
            System.out.println("1: insertion");
            System.out.println("12: atend");
            System.out.println("2: delete node");
            System.out.println("3: print nodes");
            System.out.println("4: exit");
            System.out.println("------------------");
            System.out.print("Enter:");
            System.out.println("");
            ch=inp.nextInt();
            if(ch==1){
                System.out.println("Enter value:");
                data=inp.nextInt();
                sl.insertionstart(data);
            }
            else if(ch==12){
                System.out.println("Enter value");
                data=inp.nextInt();
                sl.atend(data);
            
            }
            else if(ch==2){
            
            }
            else if(ch==3){
                sl.display();
            }
            else if(ch==4){
                System.exit(0);
            }
        }
    }
    
}
