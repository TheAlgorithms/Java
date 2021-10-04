# Queue
- The Queue interface is present in the `java.util` package.
- It is an ordered list of objects that follows the **FIFO** (First-In-First-Out) principle.

## Characteristics of a Queue
- The Queue is used to insert elements at the end of the queue and removes elements from the beginning of the queue.
- It supports all methods of Collection interface including insertion, deletion etc.
- LinkedList, ArrayBlockingQueue and PriorityQueue are the most commonly used implementations.

## Declaration

`Queue<Obj> queue = new PriorityQueue<Obj> ();` 

## Common methods

| Method | Description |
| ----------- | ----------- |
|`add(int index, element)` | Inserts an element in the list at the specified index (At the end, if not specified)|
| `size()` | This method is used to return the size of the list|
| `indexOf(element)` | Returns the index of the first occurrence of the element (-1 if it is not present)|
| `get(int index)` | Returns the element at the specified index|
| `set(int index, element)` | This method replaces the element at the given index with the new element|
| `remove(int index)` | This method removes an element from the specified index|


