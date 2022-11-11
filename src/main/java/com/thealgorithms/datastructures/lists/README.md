## Linked List
### Description

LinkedList is a data structure in which data is stored in a linear manner. It usually contains a data field and a link to the memory location of the next mode.

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

1. `CircleLinkedList.java` : A circular linked list where next pointer of last node points to first node of linked list.
2. `SinglyLinkedList.java` : The classic case of single links.
3. `CountSinglyLinkedListRecursion.java`: Recursively counts the size of a list.
4. `CreateAndDetectLoop.java` : Create and detect a loop in a linked list.
5. `DoublyLinkedList.java` : A modification of singly linked list which has a `prev` pointer to point to the previous node.
6. `Merge_K_SortedLinkedlist.java` : Merges K sorted linked list with mergesort (mergesort is also the most efficient sorting algorithm for linked list).
7. `RandomNode.java` : Selects a random node from given linked list and diplays it.
