/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palaindrom;

/**
 *
 * @author HP
 */

import java.util.Scanner;
public class Palaindrom {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner inp=new Scanner(System.in);
        System.out.println("Enter latters:");
        String latter=inp.nextLine();
        stack stk=new stack(latter);
        System.out.println("loading.........");
        stk.insert();
        stk.pop();
        System.out.println("your stack");
        stk.print();
        stk.checking();
    }
    
}
