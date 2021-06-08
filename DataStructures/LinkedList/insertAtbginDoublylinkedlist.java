package LinkedList;

public class insertAtbginDoublylinkedlist {
    class node{
        int data;
        node next;
      node prev;
      node(int data){
          this.data=data;
      }
    }
    node head;
    Boolean isEmpty(){
        return head==null;
    }
    void insertAtbegin(int data)
    {  node toAdd=new node(data);
        if(isEmpty()){
            head=toAdd;
        }
       else
       {
        toAdd.next=head;
        head.prev=toAdd;
        head=toAdd;
        toAdd.prev=null;
       } 
    }
    void add(int data){
        node toAdd =new node(data);
        
        if(isEmpty()){
       head=toAdd;
        }
       
       
        else
        {
        
            node previous=head;
                         
            while(previous.next!=null){
         
             previous=previous.next;
            }
            previous.next=toAdd;
            toAdd.prev=previous;
            toAdd.next=null;
        }

    }
    void print(){
        node temp=head;
        while(temp!=null){
          System.out.print(temp.data+" ");
          temp=temp.next;
        }
    
}
public static void main(String s[]){
    insertAtbginDoublylinkedlist obj=new insertAtbginDoublylinkedlist();
    obj.add(12);
obj.add(13);
obj.add(14);
obj.print();
System.out.println();
obj.insertAtbegin(11);
obj.print();
}
}