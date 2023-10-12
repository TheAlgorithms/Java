# Queue
- The Queue interface is present in the `java.util` package.
- It is an ordered list of objects that follows the **FIFO** (First-In-First-Out) principle.

## Characteristics of a Queue
- The Queue is used to insert elements at the end of the queue and removes elements from the beginning of the queue.
- It supports all methods of Collection interface including insertion, deletion etc.
- LinkedList, ArrayBlockingQueue and PriorityQueue are the most commonly used implementations.


## Types Of Queue:-

- **FIFO Queue (First-In-First-Out):** This is the most common type of queue where the first item added is the first one to be removed. It follows a strict order of insertion and removal.


- **Priority Queue:** Elements in this queue are assigned priorities, and the item with the highest priority is dequeued first. It doesn't strictly follow the FIFO order.


- **Double-ended Queue (Deque):** A queue that allows elements to be added and removed from both ends. It can function as both a FIFO queue and a LIFO stack.


- **Circular Queue:** In this type, the last element is connected to the first element, forming a circular structure. It's often used for tasks like managing memory buffers.


- **Blocking Queue:** Designed for multithreaded applications, it provides thread-safety and blocking operations. Threads can wait until an element is available or space is free.


- **Priority Blocking Queue:** Similar to a priority queue but thread-safe, it allows multiple threads to access and modify the queue concurrently while maintaining priority.


- **Delay Queue:** Used for scheduling tasks to run after a specific delay or at a certain time. Elements are removed from the queue when their delay expires.

## Declaration
`Queue<Obj> queue = new PriorityQueue<Obj>();`

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





