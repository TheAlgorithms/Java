package com.datastructures;




public class SinglyLinkedList<T> {
	
     /**
     * Define a node in the singly linked list
     */
	private static class Node<T> {
        T data;
        Node<T> next;
 
        Node(T data) {
            this.data = data;
        }
    }

	 /**
     * Reference to the first node of the singly linked list
     */	
    private Node<T> head;    
	
	/**
    * Method to return the size of the singly linked list
    */	
	public int size() {
		int size = 0;
	    Node<T> node = head;
	    while (node != null) {
	        ++size;
	        node = node.next;
	    }
	    return size;
	}
		 
	/**
    * Method to add a node at the end of the singly linked list
    */		 
	 public void add(T data) {
		Node<T> newNode = new Node<>(data);
		    if (head == null) {
		        head = newNode;
		    } else {
		        Node<T> last = head;
		        while (last.next != null) {
		            last = last.next;
		        }
		        last.next = newNode;
		    }
		}
	 
     /**
      * Method to add a node at the beginning of the singly linked list
      */
     public void insertAtStart (T data) {
    	 Node<T> node = new Node<>(data);
		 node.data = data;
		 node.next = null;
		 node.next = head;
		 head = node;
    	 
     }
     
     /**
      * Method to remove a node at any position in the singly linked list
      */
    public void remove(int position) {
        Node<T> prev = null, node = head;
    	    while (position > 0 && node != null) {
    	        --position;
    	        prev = node;
    	        node = node.next;
    	    }
    	    if (node != null) {
    	        if (prev == null) {
    	            head = node.next;
    	        } else {
    	            prev.next = node.next;
    	        }
    	    }
    	}
     
     /**
      * Method to print the content of the singly linked list for better
      *  understanding
      */
    public void printContent() {
 	    StringBuilder sb = new StringBuilder();
 	    Node<T> node = head;
 	    while (node != null) {
 	        sb.append(node.data).append('-').append('>');
 	        node = node.next;
 	    }
 	    int lastComma = sb.lastIndexOf(",");
 	    if (lastComma != -1) {
 	        sb.deleteCharAt(lastComma);
 	    }
 	    System.out.println(sb);
 	}
 		 }