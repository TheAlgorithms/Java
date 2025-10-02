## Linked List
### Description

LinkedList is a data structure in which data is stored in a linear manner. It usually contains a data field and a link to the memory location of the next node.

### Structure

```
class LinkedList<E>{
    E value;
    LinkedList next;
}
```

The `next` variable points to the next node in the data structure and value stores the data. Any number of nodes can be linked in this manner. The structure will be:


### Properties
1. Linked list does not provide indexing like an array. For accessing a node at position `p` , &theta;(p) nodes need to be accessed.
2. Main advantage of linked list is addition and removal of nodes near the end and beginning of lists. It can be done just by updating the link (O(1) time)
3. Unlike an array, its size is not predefined. So any number of nodes can be appended.

### File descriptions:

1.CircleLinkedList.java : A circular linked list where the next pointer of the last node points to the first node of the linked list.

2.CircularDoublyLinkedList.java : A circular doubly linked list with next and prev pointers, where the last node points back to the first node.

3.SinglyLinkedList.java : The classic single linked list implementation.

4.SinglyLinkedListNode.java : Node class used for singly linked lists.

5.CountSinglyLinkedListRecursion.java : Recursively counts the size of a singly linked list.

6.CreateAndDetectLoop.java : Creates and detects a loop in a linked list.

7.DoublyLinkedList.java : A modification of singly linked list with a prev pointer to point to the previous node.

8.MergeKSortedLinkedList.java : Merges K sorted linked lists using merge sort.

9.MergeSortedSinglyLinkedList.java : Merges two sorted singly linked lists.

10.MergeSortedArrayList.java : Merges sorted array lists (linked list variant).

11.QuickSortLinkedList.java : Implements quicksort on a linked list.

12.RandomNode.java : Selects a random node from a given linked list and displays it.

13.ReverseKGroup.java : Reverses nodes in k-sized groups in a linked list.

14.RotateSinglyLinkedLists.java : Rotates a singly linked list by k positions.

15.SearchSinglyLinkedListRecursion.java : Searches a node in a singly linked list recursively.

16.SortedLinkedList.java : Implements a linked list that maintains sorted order on insertion.

17.CursorLinkedList.java : Implements linked list using cursor-based approach.

18.SkipList.java : Stores a sorted list of elements using a linked list hierarchy connecting subsequences of elements.
