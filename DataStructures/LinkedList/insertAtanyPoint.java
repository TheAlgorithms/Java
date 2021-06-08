package LinkedList;

public class insertAtanyPoint {
    class node{
        int data;
        node next;
        node(int data){
            this.data=data;
        }
    }
    node head;
    void add(int data){
        node toAdd=new node(data);
        if(isEmpty()){
          head=toAdd;
        }
        else
        {
            node temp=head;
            while(temp.next!=null){
                temp=temp.next;
            }
            temp.next=toAdd;
            toAdd.next=null;
        }
    }
    Boolean isEmpty(){
        return head==null;
    }
    void print(){
        node temp=head;
        while(temp!=null){
            System.out.print(temp.data+" ");
            temp=temp.next;
        }
        System.out.println();
    }
    void insertEnd(int data){
        node toAdd=new node(data);
        
        if(head.next==null){
            head.next=toAdd;
            toAdd.next=null;
        }
        else
        {
            node temp=head;
        while(temp.next!=null){
            temp=temp.next;
        }
        temp.next=toAdd;
        toAdd.next=null;
        }
}
void deleteFromFirst(){
    if(head.next==null){
        head=null;
    }
    else
    {
        
        head=head.next;
    }
}
int  length(){
  
    int count=1;
    if(head==null){
       return  count=0;
    }
    if(head.next==null){
        return count=1;
    }
    else
    {   
        node temp=head;
        while(temp.next!=null){
            count++;
            temp=temp.next;

        }
    }
    return count;
}
void insertAtAnyPoint(int data,int pos){
    if(head==null){
        return ;
    }
    node toAdd=new node(data);
    if(head.next==null){
      head.next=toAdd;
    }
   else if(pos==1){
     
    toAdd.next=head;
     
   }
    else
    {
        node temp=head;
        int count=1;
        while(count!=pos-1){
            temp=temp.next;
            count++;
        }
   
       toAdd.next=temp.next;
       temp.next=toAdd;
        
    }
}
public static void main(String sp[]){
    insertAtanyPoint obj=new insertAtanyPoint();
    
    obj.add(12);
    obj.add(13);
    obj.add(14);
    obj.add(15);
    obj.add(16);
    obj.print();
    obj.insertAtAnyPoint(17,1);
    obj.print();
}
}