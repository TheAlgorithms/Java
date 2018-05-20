# 알고리즘 - Java [![Build Status](https://travis-ci.org/TheAlgorithms/Java.svg)](https://travis-ci.org/TheAlgorithms/Java)

### Java에서 사용되는 모든 알고리즘 (교육용)

이 모든 것은 오직 설명을 위한 것입니다. Java 표준 라이브러리에는 성능상 훨씬 우수한 여러 종류의 구현들이 존재합니다.

## 정렬 알고리즘


### 버블 정렬 
![alt text][bubble-image]

From [Wikipedia][bubble-wiki]: 버블 정렬(싱크 정렬)은 인접한 것들끼리 비교하여 잘못된 순서인 경우 값을 서로 교환하여 저장하는 과정을 반복해 나가는 간단한 정렬 알고리즘입니다. 


__속성__
* 최악의 경우    O(n^2)
* 최선의 경우    O(n)
* 평균적인 경우    O(n^2)

###### 더 자세히 보고 싶다면? [action][bubble-toptal]



### 삽입 정렬
![alt text][insertion-image]

From [Wikipedia][insertion-wiki]: 삽입 정렬은 한 번에 한 항목씩 최종 정렬 된 배열 (또는 목록)을 작성해주는 간단한 정렬 알고리즘입니다. quicksort, heaport 또는 병합 정렬과 같은 고급 알고리즘보다 크기가 큰 목록을 다룰 때 훨씬 효율이 떨어집니다.


__속성__
* 최악의 경우    O(n^2)
* 최선의 경우    O(n)
* 평균적인 경우    O(n^2)

###### 더 자세히 보고 싶다면? [action][insertion-toptal]


### 병합 정렬
![alt text][merge-image]

From [Wikipedia][merge-wiki]: 컴퓨터 공학에서 병합 정렬 (Merge sort 대신, 흔히 mergesort로 적기도 합니다.)은 효율적이고 범용이며 비교 기반 정렬 알고리즘입니다. 대부분의 구현은 안정적인 정렬을 생성하는 데, 구현시 정렬된 출력에서 동일한 요소의 입력 순서가 보존되는 것을 의미합니다. 1945년 존 폰 노이만(John von Neumann)이 발명했습니다.

In computer science, merge sort (also commonly spelt mergesort) is an efficient, general-purpose, comparison-based sorting algorithm. Most implementations produce a stable sort, which means that the implementation preserves the input order of equal elements in the sorted output. Mergesort is a divide and conquer algorithm that was invented by John von Neumann in 1945.

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
* Average case performance    O(n^2)

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
[BFS](Data%20Structures/Graphs/BFS.java)|[Empty Heap Exception](Data%20Structures/Heaps/EmptyHeapException.java)|[Circle Linked List](Data%20Structures/Lists/CircleLinkedList.java)|[Generic Array List Queue](Data%20Structures/Queues/GenericArrayListQueue.java)|
[DFS](Data%20Structures/Graphs/DFS.java)|[Heap](Data%20Structures/Heaps/Heap.java)|[Doubly Linked List](Data%20Structures/Lists/DoublyLinkedList.java)|[Queues](Data%20Structures/Queues/Queues.java)|
[Graphs](Data%20Structures/Graphs/Graphs.java)|[Heap Element](Data%20Structures/Heaps/HeapElement.java)|[Singly Linked List](Data%20Structures/Lists/SinglyLinkedList.java)|
[Kruskals Algorithm](Data%20Structures/Graphs/KruskalsAlgorithm.java)|[Max Heap](Data%Structures/Heaps/MaxHeap.java)|
[Matrix Graphs](Data%20Structures/Graphs/MatrixGraphs.java)|[Min Heap](Data%20Structures/Heaps/MinHeap.java)|
[PrimMST](Data%20Structures/Graphs/PrimMST.java)|

Stacks|Trees|
------|-----|
[Node Stack](Data%20Structures/Stacks/NodeStack.java)|[AVL Tree](Data%20Structures/Trees/AVLTree.java)|
[Stack of Linked List](Data%20Structures/Stacks/StackOfLinkedList.java)|[Binary Tree](Data%20Structures/Trees/BinaryTree.java)|
[Stacks](Data%20Structures/Stacks/Stacks.java)|And much more...|

* [Bags](Data%20Structures/Bags/Bag.java)
* [Buffer](Data%20Structures/Buffers/CircularBuffer.java)
* [HashMap](Data%20Structures/HashMap/HashMap.java)
* [Matrix](Data%20Structures/Matrix/Matrix.java)
