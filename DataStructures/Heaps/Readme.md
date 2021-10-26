<b><h1 align=center> HEAP DATA STRUCTURE</h1></b>
<p>A Heap is a special Tree-based data structure in which the tree is a complete binary tree.

## <h2>Complete Binary Tree</h2>
<p>A complete binary tree is a binary tree
in which all the levels except the last level, i.e., leaf node should be completely filled, and all the nodes should be left-justified.</p>


```
                            10
                          /    \
                        20      30
                       /  \    
                      40   50
                      
                    COMPLETE BINARY TREE
```


## <h2>Types of Heap</h2>
<p>Generally, Heaps can be of two types:
<br>
<strong>Max-Heap:</strong> In a Max-Heap the key present at the root node must be greatest among the keys present at all of it’s children. The same property must be recursively true for all sub-trees in that Binary Tree.
<br>
<strong>Min-Heap:</strong> In a Min-Heap the key present at the root node must be minimum among the keys present at all of it’s children. The same property must be recursively true for all sub-trees in that Binary Tree.
</p>



```
                            10
                          /    \
                        20      30
                       /  \    /  \
                      40  50  60  70
                      
                          MIN HEAP
```

```
                            70
                          /    \
                        50      60
                       /  \    /  \
                      40  30  10   20
                      
                          MAX HEAP
```

## <h2>Min Heap Construction Algorithm</h2>
```
Step 1 − Create a new node at the end of heap.
Step 2 − Assign new value to the node.
Step 3 − Compare the value of this child node with its parent.
Step 4 − If value of parent is more than child, then swap them.
Step 5 − Repeat step 3 & 4 until Heap property holds.
```

```
Add 15

                            10                         10                     10
                          /    \                     /   \                  /    \
                        20      30    ------>      20     30   ------>     20     15
                       /  \                       /  \   /                /  \    /  
                      40  50                    40   50  15              40  50  30
                                          
                                          
```

## <h2>Min Heap Deletion Algorithm</h2>
```
Step 1 − Remove root node.
Step 2 − Move the last element of last level to root.
Step 3 − Compare the value of this child node with its parent.
Step 4 − If value of parent is more than child, then swap them.
Step 5 − Repeat step 3 & 4 until Heap property holds.
```

```
Delete 10

                            10                        50                     20                   20
                          /    \                     /   \                  /   \                /  \
                        20      30    ------>      20     30   ------>     50    30   ------>   40   30
                       /  \                       /                       /                    /
                      40  50                    40                       40                   50
                                          
                                          
```

## <h2>Time Complexity (Min Heap)</h2>
<table border=1>
    <tr>
        <th>Operations</th>
        <th>Sorted Array</th>
        <th>UnSorted Array</th>
        <th>Heap</th>
    </tr>
    <tr>
        <td>Add</td>
        <td>O(N)</td>
        <td>O(1)</td>
        <td>O(logN)</td>
    </tr>  
    <tr>
        <td>Delete Minimum</td>
        <td>O(N)</td>
        <td>O(N)</td>
        <td>O(logN)</td>
    </tr>
    <tr>
        <td>Get Minimum</td>
        <td>O(1)</td>
        <td>O(N)</td>
        <td>O(1)</td>
    </tr> 
</table>

## <h2>Applications of Heap Data Structure</h2>

<p>
<strong>Heapsort:</strong> Heapsort algorithm has limited uses because Quicksort is better in practice. Nevertheless, the Heap data structure itself is enormously used.

<strong>Priority Queues:</strong> Priority queues can be efficiently implemented using Binary Heap because it supports insert(), delete() and extractmax(), decreaseKey() operations in O(logn) time. Binomoial Heap and Fibonacci Heap are variations of Binary Heap. These variations perform union also in O(logn) time which is a O(n) operation in Binary Heap. Heap Implemented priority queues are used in Graph algorithms like Prim’s Algorithm and Dijkstra’s algorithm.

<strong>Order statistics:</strong> The Heap data structure can be used to efficiently find the kth smallest (or largest) element in an array.
</p>
