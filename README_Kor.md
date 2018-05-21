# 알고리즘 - Java [![Build Status](https://travis-ci.org/TheAlgorithms/Java.svg)](https://travis-ci.org/TheAlgorithms/Java)

### Java에서 사용되는 모든 알고리즘 (교육용)

이 모든 것은 오직 설명을 위한 것입니다. Java 표준 라이브러리에는 성능상 훨씬 우수한 여러 종류의 구현들이 존재합니다.

## 정렬 알고리즘


### 버블 정렬 
![alt text][bubble-image]

From [Wikipedia][bubble-wiki]: 버블 정렬(싱크 정렬)은 인접한 것들끼리 비교하여 잘못된 순서인 경우 값을 서로 교환하여 저장하는 과정을 반복해 나가는 간단한 정렬 알고리즘입니다. 

__시간 복잡도__
* 최악의 경우    O(n^2)
* 최선의 경우    O(n)
* 평균적인 경우    O(n^2)

###### 더 자세히 보고 싶다면? [action][bubble-toptal]


### 삽입 정렬
![alt text][insertion-image]

From [Wikipedia][insertion-wiki]: 삽입 정렬은 한 번에 한 항목씩 최종 정렬 된 배열 (또는 목록)을 작성해주는 간단한 정렬 알고리즘입니다. quicksort, heaport 또는 병합 정렬과 같은 고급 알고리즘보다 크기가 큰 목록을 다룰 때 훨씬 효율이 떨어집니다.

__시간 복잡도__
* 최악의 경우    O(n^2)
* 최선의 경우    O(n)
* 평균적인 경우    O(n^2)

###### 더 자세히 보고 싶다면? [action][insertion-toptal]


### 병합 정렬
![alt text][merge-image]

From [Wikipedia][merge-wiki]: 컴퓨터 공학에서 병합 정렬 (Merge sort 대신, 흔히 mergesort로 적기도 합니다.)은 효율적이고 범용이며 비교 기반 정렬 알고리즘입니다. 대부분의 구현은 안정적인 정렬을 생성하는 데, 구현시 정렬된 출력에서 동일한 요소의 입력 순서가 보존되는 것을 의미합니다. 1945년 존 폰 노이만(John von Neumann)이 발명했습니다.

__시간 복잡도__
* 최악의 경우    O(n log n) (형식적)
* 최선의 경우    O(n log n)
* 평균적인 경우    O(n log n)

###### 더 자세히 보고 싶다면? [action][merge-toptal]


### 퀵 정렬
![alt text][quick-image]

From [Wikipedia][quick-wiki]: 퀵 정렬 (파티션 교환 정렬)는 배열의 요소를 순서대로 배치하는 체계적인 방법으로, 효율적인 정렬 알고리즘입니다.

__시간 복잡도__
* 최악의 경우    O(n^2)
* 최선의 경우    O(n log n) or O(n) (3개의 영역으로 나눌 경우)
* 평균적인 경우    O(n^2)


###### 더 자세히 보고 싶다면? [action][quick-toptal]


### 선택 정렬
![alt text][selection-image]

From [Wikipedia][selection-wiki]: 알고리즘은 입력 목록을 두 부분으로 나눕니다 -> 이미 정렬 된 항목의 하위 목록 (목록의 앞 (왼쪽)에서 오른쪽으로 작성된 목록) 그리고 나머지 항목을 차지하며 정렬되야 하위 목록. 처음에 정렬된 하위 목록은 비어 있으며 정렬되지 않은 하위 목록은 전체 입력 목록입니다. 알고리즘은 정렬되지 않은 하위 목록에서 가장 작은 (크기가 아닐 수 있으며 정렬 기준상 작다는 의미) 요소를 찾아 정렬되지 않은 목록중 가장 좌측 요소와 값을 교환하고 하위 목록 경계를 한 요소만큼 오른쪽으로 이동하는 방식으로 진행합니다.

__시간 복잡도__
* 최악의 경우    O(n^2)
* 최선의 경우    O(n^2)
* 평균적인 경우    O(n^2)


###### 더 자세히 보고 싶다면? [action][selection-toptal]


### 셸 정렬
![alt text][shell-image]

From [Wikipedia][shell-wiki]: Shellsort는 멀리 떨어져있는 항목 교환을 허용하는 삽입 정렬의 일반화입니다. 아이디어는 모든 n 번째 요소가 정렬 된 목록을 제공한다는 것을 고려하여 어디서든지 시작하도록 요소 목록을 정렬하는 것입니다. 그러한 목록은 h-정렬(h-sorted) 라고 합니다. 동일하게, 사이사이 벌어져 있으면서 개별적으로 정렬된 h 목록들이라고 생각할 수 있습니다.

__시간 복잡도__
* 최악의 경우 O(nlog2 2n)
* 최선의 경우 O(n log n)
* 평균적인 경우: 정렬된 목록 사이의 차이에 따라 달라집니다.


###### 더 자세히 보고 싶다면? [action][shell-toptal]


### 시간 복잡도 그래프

정렬 알고리즘의 복잡도 비교(버블 정렬, 삽입 정렬, 선택 정렬)

[Complexity Graphs](https://github.com/prateekiiest/Python/blob/master/sorts/sortinggraphs.png)

----------------------------------------------------------------------------------

## 탐색 알고리즘

### 선형 검색
![alt text][linear-image]

From [Wikipedia][linear-wiki]: 선형 검색 또는 순차 검색은 목록 내의 대상을 찾는 방법입니다. 찾는 값과 요소의 값이 일치하거나 모든 요소가 검색 될 때까지 목록의 요소 값에 대해 순차적으로 탐색합니다.
   선형 검색은 최악의 시간복잡도 내에서 움직이며 최대 n 개의 비교를 수행합니다. 여기서 n은 목록의 길이입니다.

__복잡도__
* 최악의 경우(시간)    O(n)
* 최선의 경우(시간)    O(1)
* 평균적인 경우(시간)    O(n)
* 최악의 경우(공간)    O(1) 


### 이진 탐색
![alt text][binary-image]

From [Wikipedia][binary-wiki]: 반 간격 검색 또는 로그 검색이라고도 하는 이진 검색은 정렬된 배열 내에서 대상 값의 위치를 찾는 탐색 알고리즘입니다. 목표 값을 배열의 중간 요소와 비교하여 동일하지 않으면, 절반을 배제한 상태로 다시 성공할 때까지 나머지 절반의 탐색을 진행합니다.


__복잡도__
* 최악의 경우(시간)    O(log n)
* 최선의 경우(시간)    O(1)
* 평균적인 경우(시간)    O(log n)
* 최악의 경우(공간)    O(1) 


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
