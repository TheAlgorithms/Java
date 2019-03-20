import java.util.*;

public class Merge_K_SortedLinkedlist {
   
   /*
    * This function merge K sorted LinkedList
    *  @param Node []a : array of LinkedList 
    *  @param  int N  : size of array
    *  
    * author Arun Pandey (https://github.com/pandeyarun709)
   */
    Node mergeKList(Node[]a, int N)
    {
        // Min Heap
        PriorityQueue<Node> min = new PriorityQueue<>(new Comparator<Node>(){
              
              public int compare(Node x , Node y){
                  return x.data - y.data;
              } 
        }); 
        
        // adding head of all linkedList in min heap
        for(int i =0 ; i< N ;i++)
        {
            min.add(a[i]);
        }
        
        // Make new head among smallest heads in K linkedList 
        Node head = min.poll();
        min.add(head.next);
        Node curr = head;

        //merging LinkedList
        while(!min.isEmpty()) {
            
            Node temp = min.poll();
            curr.next = temp;
            curr = temp;
            
            //Add Node in min Heap only if temp.next is not null
            if(temp.next != null){
                min.add(temp.next);
            }
        }
        
       return head;
        
   
    }
    
    // Node Class
    private  class Node{
    	private int data;
    	private Node next;
    	
    	public Node(int d)
    	{
    		this.data = d;
    		next = null;
    	}
    }

}