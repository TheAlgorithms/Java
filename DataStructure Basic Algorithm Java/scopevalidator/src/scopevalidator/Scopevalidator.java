/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scopevalidator;

/**
 *
 * @author HP
 */
import java.util.Scanner;
public class Scopevalidator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner inp=new Scanner(System.in);
        System.out.println("Enter expression:");
        String exp=inp.nextLine();
        stack stk=new stack(exp);
        for(int i=0;i<exp.length();i++){
            char symbol=exp.charAt(i);
            if(symbol=='(' || symbol=='{' || symbol=='['){
                stk.push(symbol);
            }
            else{
                if(stk.isempty()){
                    System.out.println("invalid expression");
                
                }
                char top=stk.peek();
                if(symbol==')' && top=='(' || symbol=='}' && top=='{' || symbol==']' && top=='['){
                   stk.pop();
                }
            
            
            }
        
        }
        if(stk.isempty()){
            System.out.println("Valid expression");
            
        }
        else{
            System.out.println("invalid expression");
        }
        
    }
    
}
