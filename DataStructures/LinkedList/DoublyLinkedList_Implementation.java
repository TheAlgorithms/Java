import java.util.*;

class jsol{
    static class Node{
        int data; Node next; Node prev;
        Node(int data){
            this.data = data;
        }
    }
    static class DLL{
        Node head; Node tail;
        int sz = 0;
        
        //add element at index 0 efficiently
        void addFirst(int data){
            if(this.head == null){
                head = new Node(data);
                tail = head;
            }else{
                Node newNode = new Node(data);
                newNode.next = head; head.prev = newNode;
                head = newNode;
            }
            sz++;
        }
        int size(){
            return this.sz;
        }
        //add element at end efficiently
        void addLast(int data){
            if(this.head == null){
                head = new Node(data);
                tail = head;
            }else{
                Node newNode = new Node(data);
                this.tail.next = newNode; newNode.prev = this.tail;
                this.tail = newNode;
            }
            sz++;
        }
        
        // adds at specific index
        void addAt(int idx,int data){
            if(idx <0 || idx>this.sz){
                System.out.println("Please enter valid index - addAt");
                return;
            }
            if(idx == 0){
                addFirst(data);
            }else if(idx == sz){
                addLast(data);
            }else{  
                int i = 1;
                Node curr = this.head;
                while(i<idx){
                    curr = curr.next; i++;
                }
                Node newNode = new Node(data);
                Node next = curr.next;
                curr.next = newNode;
                newNode.prev = curr;
                newNode.next = next; next.prev = newNode;
                sz++;
            }   
        }
        
        // removes first element and returns its value
        int removeFirst(){
            if(head == null){
                System.out.println("List is empty- removeFirst");
                return -1;
            }else if(head.next == null){
                int ret = head.data;
                head = tail = null;
                sz--;
                return ret;
            }
            int ret = head.data;
            head = head.next; head.prev = null;
            sz--;
            return ret;
        }
        
        // removes last element in linkedlist
        int removeLast(){
            if(head == null){
                System.out.println("List is empty - removeLast");
                return -1;
            }else if(head.next == null){
                int ret = head.data;
                head = tail = null;
                sz--;
                return ret;
            }
            int ret = tail.data;
            this.tail = tail.prev; tail.next = null;
            sz--;
            return ret;
        }
        
        // removes at specific index
        int removeAt(int idx){
            if(idx == this.sz || idx < 0){
                System.out.println("Enter valid index removeAt");
                return -1;
            }
            if(idx == 0) return removeFirst();
            else if(idx == this.sz-1) return removeLast();
            int i = 1;
            Node curr = this.head;
            while(i<idx){
                curr = curr.next; i++;
            }
            int ret = curr.next.data;
            curr.next = curr.next.next;
            curr.next.prev = curr;
            sz--;
            return ret;
        }
        
        // gets the node at particular address
        Node getAt(int idx){
            if(idx == this.sz || idx < 0){
                System.out.println("Please enter valid index - getAt"); return null;
            }
            if(idx == 0) return this.head;
            else if(idx == this.sz-1)return this.tail;
            Node curr = this.head; int i = 0;
            while(i<idx){
                i++; curr = curr.next;
            }
            return curr;
        }
        
        //a simple display function
        String tostring(){
            Node curr = head;
            String ans = "[";
            while(curr!=null){
                ans+= curr.data;
                if(curr.next!=null) ans+=", ";
                curr = curr.next;
            }
            ans+= "]";
            return ans;
        }
        
        //finds the first node with the given data and removes it
        void removeData(int data){
            if(head.data == data){
                removeFirst();return;
            }else if(tail.data == data){
                removeLast();return;
            }
            Node curr = head;
            while(curr.next.data !=data ) curr= curr.next;
            curr.next = curr.next.next;
            curr.next.prev = curr;
            sz--;
        }
    }

    //basic test case to check if the linkedlist is working or not
    
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        DLL l1 = new DLL();
        int[] arr = {1,2,3,4,5};
        for(int x: arr) l1.addLast(x);
        // System.out.println(l1.tostring());
        l1.addFirst(10);
        System.out.println(l1.tostring());
        System.out.println(l1.removeAt(0)); 
        System.out.println(l1.removeAt(l1.sz-1)); 
        System.out.println(l1.removeAt(l1.sz));
        System.out.println(l1.tostring());
        System.out.println(l1.removeAt(2));
        System.out.println(l1.tostring());
        System.out.println(l1.getAt(3).data);
    }
} 
