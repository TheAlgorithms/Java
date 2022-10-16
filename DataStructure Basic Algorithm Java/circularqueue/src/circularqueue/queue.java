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
public class queue {
    int front,rear,cq[],s;
    queue(int size){
        s=size;
        front=-1;
        rear=-1;
        cq= new int[size];
    }
    void enqueue(int e){
        if((rear+1)%s==front){
            System.out.println("full");
        }
        else{
            if(front==-1){
                front=0;
            }
            rear=(rear+1)%s;
            cq[rear]=e;
        }
        
    
    }
    void dequeue(){}
    boolean isempty(){
        if(front==-1 && rear==-1){
            return true;
        
        }
        else{
            return false;
        }
    
    }
    void display(){
        for(int i=front;i<=rear;i++){
            System.out.println("|"+cq[i]+"|");
        
        }
    }
    boolean isfull(){
        if(front==0 && rear==-1){
            return true;
        }
        else{
            return false;
        }
    }
    
    
}
