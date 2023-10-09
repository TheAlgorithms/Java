# Queue
- The Queue interface is present in the `java.util` package.
- It is an ordered list of objects that follows the **FIFO** (First-In-First-Out) principle.

## Characteristics of a Queue
- The Queue is used to insert elements at the end of the queue and removes elements from the beginning of the queue.
- It supports all methods of Collection interface including insertion, deletion etc.
- LinkedList, ArrayBlockingQueue and PriorityQueue are the most commonly used implementations.

## Declaration
  //queue implementation using PriorityQueue
`Queue<Obj> queue = new PriorityQueue<Obj> ();`

## Important operations

| Operations | Description |Time Complexity
| ----------- | ----------- |-----------
|Enqueue|Adds an item to the queue|O(1)
|Dequeue|Removes an item from the queue|O(1)
|Front|Gets the front item from the queue|O(1)
|Rear|Gets the last item from the queue|O(n)

## Enqueue
 It adds  an item to the rear of the queue.

 For example: If we have `1, 2, 3, 4, 5` in queue, and if we call Enqueue(8),

`8` will be added to last index of queue -> `1, 2, 3, 4, 5, 8`.
## Dequeue
 
 It removes an item to the front of the queue.
 
 For example: If we have `1, 2, 3, 4, 5` in queue, and we call Dequeue(),

`1` will be removed from front of queue and returned -> `2, 3, 4, 5`.

## Front 
  It returns an item to the front of the queue.

For example: If we have `1, 2, 3, 5` in queue, and we call Front(),

`1` will be returned (without removing it from the queue).

## Rear
  It returns an item to the rear of the queue.
  
  For example: If we have `1, 2, 3, 5` in queue, and we call Rear(),

`5` will be returned (without removing it from the queue).

# Real Life Applications
`Task Scheduling in Operating Systems:`

Processes in a multitasking system are often scheduled using queues. For example, the ready queue contains processes ready to be executed.

`Multi-threaded Programming:`

Queues are often used to facilitate communication and synchronization between different threads.

`Breadth-First Search (BFS) in Graphs:`

Queues are used in algorithms like BFS to explore a graph level by level.



