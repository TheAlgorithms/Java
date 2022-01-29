//Author: Vicente Cortes
//Github: https://github.com/vicentecortes23

//https://en.wikipedia.org/wiki/Bubble_sort
//This java program sorts a LinkedList given a head pointer, as described in the issue https://github.com/TheAlgorithms/Java/issues/2450.
package com.thealgorithms.sorts;


public class LinkedListSort {

    public static class Node {
        int value;
        Node next;
        Node prev;

       public Node(int val)
        {
            this.value = val;
            this.next = null;
            this.prev = null;
        }
    
        public void push(int val){

            Node newNode = new Node(val);

            Node currentNode = this;

            while(currentNode.next != null){
                currentNode = currentNode.next;
            }

            currentNode.next = newNode;
            newNode.prev = currentNode;
        }

    }

    public Node sortList(Node node){

        //in order to sort these two lists, we will use bubble sort starting from the head node.
        Node thisNode = node;
        Node nextNode = null;

        while(thisNode != null){

            nextNode = thisNode.next;

            while(nextNode != null){

                if (thisNode.value > nextNode.value){
                    int temp  = thisNode.value;
                    thisNode.value = nextNode.value;
                    nextNode.value = temp;
                }

                nextNode = nextNode.next;

            }
            thisNode = thisNode.next;
        }

        return node;
    }

    public static void main(String args[]){

        LinkedListSort sorter = new LinkedListSort();

        Node head = new Node(5);
        head.push(3);
        head.push(6);
        head.push(7);
        head.push(1);
        head.push(2);
        head.push(8);
        head.push(4);

        head = sorter.sortList(head);

        while(head != null){
            System.out.print(head.value + " ");
            head = head.next;
        }
    }    
};
