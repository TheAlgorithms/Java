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
public class queuap {
    int front =0;
    int rear=-1;
    int s;
    int qstk[];
    queuap(int size){
        s=size;
        qstk=new int[size];
    
    }
    void enque(int v){
        if(s==front){
            System.out.println("overflow");
        }
        else{
            rear ++;
            qstk[rear]=v;
        }
    }
    int dequeue(){
        if(front==rear){
            System.out.println("underflow");
            return 0;
        }
        else{
            int v=qstk[front];
            front++;
            return v;
        }
    }
    boolean isfull(){
        if(front==0 && rear==s-1){
            return true;
        }
        else{
            return false;
        }
    
    }
    boolean isempty(){
        if(front==-1){
            return true;
        }
        else{
            return false;
        }
    
    }
    void print(){
        for(int i=front;i<=rear;i++){
            System.out.println("|"+qstk[i]+"|");
        
        }
    
    }
    
}
