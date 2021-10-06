# HASHMAP DATA STRUCTURE

A hash map organizes data so you can quickly look up values for a given key.

## Strengths:
- **Fast lookups**: Lookups take O(1) time on average.
- **Flexible keys**: Most data types can be used for keys, as long as they're hashable.

## Weaknesses:
- **Slow worst-case**: Lookups take O(n) time in the worst case.
- **Unordered**: Keys aren't stored in a special order. If you're looking for the smallest key, the largest key, or all the keys in a range, you'll need to look through every key to find it.
- **Single-directional lookups**: While you can look up the value for a given key in O(1) time, looking up the keys for a given value requires looping through the whole dataset—O(n) time.
- **Not cache-friendly**: Many hash table implementations use linked lists, which don't put data next to each other in memory.

## Time Complexity
|        | AVERAGE | WORST |
|--------|---------|-------|
| Space  | O(n)    | O(n)  |
| Insert | O(1)    | O(n)  |
| Lookup | O(1)    | O(n)  |
| Delete | O(1)    | O(n)  |

## Internal Structure of HashMap
Internally HashMap contains an array of Node and a node is represented as a class that contains 4 fields:
- int hash
- K key
- V value
- Node next

It can be seen that the node is containing a reference to its own object. So it’s a linked list.

## Performance of HashMap
Performance of HashMap depends on 2 parameters which are named as follows:
- Initial Capacity
- Load Factor


**Initial Capacity**: It is the capacity of HashMap at the time of its creation (It is the number of buckets a HashMap can hold when the HashMap is instantiated). In java, it is 2^4=16 initially, meaning it can hold 16 key-value pairs.

**Load Factor**: It is the percent value of the capacity after which the capacity of Hashmap is to be increased (It is the percentage fill of buckets after which Rehashing takes place). In java, it is 0.75f by default, meaning the rehashing takes place after filling 75% of the capacity.

**Threshold**: It is the product of Load Factor and Initial Capacity. In java, by default, it is (16 * 0.75 = 12). That is, Rehashing takes place after inserting 12 key-value pairs into the HashMap.

**Rehashing** : It is the process of doubling the capacity of the HashMap after it reaches its Threshold. In java, HashMap continues to rehash(by default) in the following sequence – 2^4, 2^5, 2^6, 2^7, …. so on. 

If the initial capacity is kept higher then rehashing will never be done. But by keeping it higher increases the time complexity of iteration. So it should be chosen very cleverly to increase performance. The expected number of values should be taken into account to set the initial capacity. The most generally preferred load factor value is 0.75 which provides a good deal between time and space costs. The load factor’s value varies between 0 and 1. 

```
Note: From Java 8 onward, Java has started using Self Balancing BST instead of a linked list for chaining. 
The advantage of self-balancing bst is, we get the worst case (when every key maps to the same slot) search time is O(Log n). 
```

Java has two hash table classes: HashTable and HashMap. In general, you should use a HashMap.

While both classes use keys to look up values, there are some important differences, including:

- A HashTable doesn't allow null keys or values; a HashMap does.
- A HashTable is synchronized to prevent multiple threads from accessing it at once; a HashMap isn't.

## When Hash Map operations cost O(n) time?

**Hash collisions**: If all our keys caused hash collisions, we'd be at risk of having to walk through all of our values for a single lookup (in the example above, we'd have one big linked list). This is unlikely, but it could happen. That's the worst case.

**Dynamic array resizing**: Suppose we keep adding more items to our hash map. As the number of keys and values in our hash map exceeds the number of indices in the underlying array, hash collisions become inevitable. To mitigate this, we could expand our underlying array whenever things start to get crowded. That requires allocating a larger array and rehashing all of our existing keys to figure out their new position—O(n) time.

