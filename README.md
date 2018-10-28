# The Algorithms - Java

## A [Development](https://github.com/TheAlgorithms/Java/tree/Development) branch is made for this repo where we are trying to migrate the existing project to a Java project structure. You can switch to [Development](https://github.com/TheAlgorithms/Java/tree/Development) branch for contributions. Please refer [this issue](https://github.com/TheAlgorithms/Java/issues/474) for more info.

### All algorithms implemented in Java (for education)

These are for demonstration purposes only. There are many implementations of sorts in the Java standard library that are much better for performance reasons.

## Sort Algorithms


### Bubble
![alt text][bubble-image]

From [Wikipedia][bubble-wiki]: Bubble sort, sometimes referred to as sinking sort, is a simple sorting algorithm that repeatedly steps through the list to be sorted, compares each pair of adjacent items and swaps them if they are in the wrong order. The pass through the list is repeated until no swaps are needed, which indicates that the list is sorted.

__Properties__
* Worst case performance    O(n^2)
* Best case performance    O(n)
* Average case performance    O(n^2)

###### View the algorithm in [action][bubble-toptal]



### Insertion
![alt text][insertion-image]

From [Wikipedia][insertion-wiki]: Insertion sort is a simple sorting algorithm that builds the final sorted array (or list) one item at a time. It is much less efficient on large lists than more advanced algorithms such as quicksort, heapsort, or merge sort. 
In the figure, each bar represents an element of an array that needs to be sorted. What happens at the first intersection of the top most and second top most bars is to swap these elements, represented by bars, because the second element has a higher precedence than the first element does. By repeating this method, insertion sort completes sorting.

__Properties__
* Worst case performance    O(n^2)
* Best case performance    O(n)
* Average case performance    O(n^2)

###### View the algorithm in [action][insertion-toptal]


### Merge
![alt text][merge-image]

From [Wikipedia][merge-wiki]: In computer science, merge sort (also commonly spelt mergesort) is an efficient, general-purpose, comparison-based sorting algorithm. Most implementations produce a stable sort, which means that the implementation preserves the input order of equal elements in the sorted output. Mergesort is a divide and conquer algorithm that was invented by John von Neumann in 1945.

__Properties__
* Worst case performance    O(n log n) (typical)
* Best case performance    O(n log n)
* Average case performance    O(n log n)


###### View the algorithm in [action][merge-toptal]

### Quick
![alt text][quick-image]

From [Wikipedia][quick-wiki]: Quicksort (sometimes called partition-exchange sort) is an efficient sorting algorithm, serving as a systematic method for placing the elements of an array in order.

__Properties__
* Worst case performance    O(n^2)
* Best case performance    O(n log n) or O(n) with three-way partition
* Average case performance    O(n log n)

###### View the algorithm in [action][quick-toptal]

### Selection
![alt text][selection-image]

From [Wikipedia][selection-wiki]: The algorithm divides the input list into two parts: the sublist of items already sorted, which is built up from left to right at the front (left) of the list, and the sublist of items remaining to be sorted that occupy the rest of the list. Initially, the sorted sublist is empty and the unsorted sublist is the entire input list. The algorithm proceeds by finding the smallest (or largest, depending on sorting order) element in the unsorted sublist, exchanging (swapping) it with the leftmost unsorted element (putting it in sorted order), and moving the sublist boundaries one element to the right.

__Properties__
* Worst case performance    O(n^2)
* Best case performance    O(n^2)
* Average case performance    O(n^2)

###### View the algorithm in [action][selection-toptal]

### Shell
![alt text][shell-image]

From [Wikipedia][shell-wiki]:  Shellsort is a generalization of insertion sort that allows the exchange of items that are far apart.  The idea is to arrange the list of elements so that, starting anywhere, considering every nth element gives a sorted list.  Such a list is said to be h-sorted.  Equivalently, it can be thought of as h interleaved lists, each individually sorted.

__Properties__
* Worst case performance O(nlog2 2n)
* Best case performance O(n log n)
* Average case performance depends on gap sequence

###### View the algorithm in [action][shell-toptal]

### Time-Compexity Graphs

Comparing the complexity of sorting algorithms (Bubble Sort, Insertion Sort, Selection Sort)

[Complexity Graphs](https://github.com/prateekiiest/Python/blob/master/sorts/sortinggraphs.png)

----------------------------------------------------------------------------------

## Search Algorithms

### Linear
![alt text][linear-image]

From [Wikipedia][linear-wiki]: linear search or sequential search is a method for finding a target value within a list. It sequentially checks each element of the list for the target value until a match is found or until all the elements have been searched.
  The linear search runs in at the worst linear time and makes at most n comparisons, where n is the length of the list.

__Properties__
* Worst case performance    O(n)
* Best case performance    O(1)
* Average case performance    O(n)
* Worst case space complexity    O(1) iterative

### Binary
![alt text][binary-image]

From [Wikipedia][binary-wiki]: Binary search, also known as half-interval search or logarithmic search, is a search algorithm that finds the position of a target value within a sorted array. It compares the target value to the middle element of the array; if they are unequal, the half in which the target cannot lie is eliminated and the search continues on the remaining half until it is successful.

__Properties__
* Worst case performance    O(log n)
* Best case performance    O(1)
* Average case performance    O(log n)
* Worst case space complexity    O(1) 

From [Wikipedia][shell-wiki]:  Shellsort is a generalization of insertion sort that allows the exchange of items that are far apart.  The idea is to arrange the list of elements so that, starting anywhere, considering every nth element gives a sorted list.  Such a list is said to be h-sorted.  Equivalently, it can be thought of as h interleaved lists, each individually sorted.

__Properties__
* Worst case performance O(nlog2 2n)
* Best case performance O(n log n)
* Average case performance depends on gap sequence

###### View the algorithm in [action][shell-toptal]

[bubble-toptal]: https://www.toptal.com/developers/sorting-algorithms/bubble-sort
[bubble-wiki]: https://en.wikipedia.org/wiki/Bubble_sort
[bubble-image]: https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Bubblesort-edited-color.svg/220px-Bubblesort-edited-color.svg.png "Bubble Sort"

[insertion-toptal]: https://www.toptal.com/developers/sorting-algorithms/insertion-sort
[insertion-wiki]: https://en.wikipedia.org/wiki/Insertion_sort
[insertion-image]: https://upload.wikimedia.org/wikipedia/commons/7/7e/Insertionsort-edited.png "Insertion Sort"

[quick-toptal]: https://www.toptal.com/developers/sorting-algorithms/quick-sort
[quick-wiki]: https://en.wikipedia.org/wiki/Quicksort
[quick-image]: https://upload.wikimedia.org/wikipedia/commons/6/6a/Sorting_quicksort_anim.gif "Quick Sort"

[merge-toptal]: https://www.toptal.com/developers/sorting-algorithms/merge-sort
[merge-wiki]: https://en.wikipedia.org/wiki/Merge_sort
[merge-image]: https://upload.wikimedia.org/wikipedia/commons/c/cc/Merge-sort-example-300px.gif "Merge Sort"

[selection-toptal]: https://www.toptal.com/developers/sorting-algorithms/selection-sort
[selection-wiki]: https://en.wikipedia.org/wiki/Selection_sort
[selection-image]: https://upload.wikimedia.org/wikipedia/commons/thumb/b/b0/Selection_sort_animation.gif/250px-Selection_sort_animation.gif "Selection Sort Sort"

[shell-toptal]: https://www.toptal.com/developers/sorting-algorithms/shell-sort
[shell-wiki]: https://en.wikipedia.org/wiki/Shellsort
[shell-image]: https://upload.wikimedia.org/wikipedia/commons/d/d8/Sorting_shellsort_anim.gif "Shell Sort"

[linear-wiki]: https://en.wikipedia.org/wiki/Linear_search
[linear-image]: http://www.tutorialspoint.com/data_structures_algorithms/images/linear_search.gif

[binary-wiki]: https://en.wikipedia.org/wiki/Binary_search_algorithm
[binary-image]: https://upload.wikimedia.org/wikipedia/commons/f/f7/Binary_search_into_array.png


--------------------------------------------------------------------
## Links to the rest of the algorithms

Conversions          |                                          Dynamic Programming   |Ciphers|Miscellaneous|
-----------          |----------------------------------------------------------------|-------|-------------|
[Any Base to Any Base](Conversions/AnyBaseToAnyBase.java)| [Coin Change](Dynamic%20Programming/CoinChange.java)|[Caesar](ciphers/Caesar.java)|[Heap Sort](misc/heap_sort.java)|
[Any Base to Decimal](Conversions/AnyBaseToDecimal.java)|[Egg Dropping](Dynamic%20Programming/EggDropping.java)|[Columnar Transposition Cipher](ciphers/ColumnarTranspositionCipher.java)|[Palindromic Prime Checker](misc/PalindromicPrime.java)|
[Binary to Decimal](Conversions/BinaryToDecimal.java)|[Fibonacci](Dynamic%20Programming/Fibonacci.java)|[RSA](ciphers/RSA.java)|More soon...|
[Binary to HexaDecimal](Conversions/BinaryToHexadecimal.java)|[Kadane Algorithm](Dynamic%20Programming/KadaneAlgorithm.java)|more coming soon...|
[Binary to Octal](Conversions/BinaryToOctal.java)|[Knapsack](Dynamic%20Programming/Knapsack.java)|
[Decimal To Any Base](Conversions/DecimalToAnyBase.java)|[Longest Common Subsequence](Dynamic%20Programming/LongestCommonSubsequence.java)|
[Decimal To Binary](Conversions/DecimalToBinary.java)|[Longest Increasing Subsequence](Dynamic%20Programming/LongestIncreasingSubsequence.java)|
[Decimal To Hexadecimal](Conversions/DecimalToHexaDecimal.java)|[Rod Cutting](Dynamic%20Programming/RodCutting.java)|
and much more...|                                                    and more...|

### Data Structures
Graphs|Heaps|Lists|Queues|
------|-----|-----|------|
[BFS](DataStructures/Graphs/BFS.java)|[Empty Heap Exception](DataStructures/Heaps/EmptyHeapException.java)|[Circle Linked List](DataStructures/Lists/CircleLinkedList.java)|[Generic Array List Queue](DataStructures/Queues/GenericArrayListQueue.java)|
[DFS](DataStructures/Graphs/DFS.java)|[Heap](DataStructures/Heaps/Heap.java)|[Doubly Linked List](DataStructures/Lists/DoublyLinkedList.java)|[Queues](DataStructures/Queues/Queues.java)|
[Graphs](DataStructures/Graphs/Graphs.java)|[Heap Element](DataStructures/Heaps/HeapElement.java)|[Singly Linked List](DataStructures/Lists/SinglyLinkedList.java)|
[Kruskals Algorithm](DataStructures/Graphs/KruskalsAlgorithm.java)|[Max Heap](Data%Structures/Heaps/MaxHeap.java)|
[Matrix Graphs](DataStructures/Graphs/MatrixGraphs.java)|[Min Heap](DataStructures/Heaps/MinHeap.java)|
[PrimMST](DataStructures/Graphs/PrimMST.java)|

Stacks|Trees|
------|-----|
[Node Stack](DataStructures/Stacks/NodeStack.java)|[AVL Tree](DataStructures/Trees/AVLTree.java)|
[Stack of Linked List](DataStructures/Stacks/StackOfLinkedList.java)|[Binary Tree](DataStructures/Trees/BinaryTree.java)|
[Stacks](DataStructures/Stacks/Stacks.java)|And much more...|

* [Bags](DataStructures/Bags/Bag.java)
* [Buffer](DataStructures/Buffers/CircularBuffer.java)
* [HashMap](DataStructures/HashMap/HashMap.java)
* [Matrix](DataStructures/Matrix/Matrix.java)
