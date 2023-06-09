package com.thealgorithms.datastructures.heaps;

public class FibonacciHeap {

    private static final double GOLDEN_RATIO = (1 + Math.sqrt(5)) / 2;
    private HeapNode min;
    private static int totalLinks = 0;
    private static int totalCuts = 0;
    private int numOfTrees = 0;
    private int numOfHeapNodes = 0;
    private int markedHeapNoodesCounter = 0;

    /*
     * a constructor for an empty Heap
     * set the min to be null
     */
    public FibonacciHeap() {
        this.min = null;
    }

    /*
     * a constructor for a Heap with one element
     * set the min to be the HeapNode with the given key
     * @pre key>=0
     * @post empty == false
     */
    public FibonacciHeap(int key) {
        this.min = new HeapNode(key);
        this.numOfTrees++;
        this.numOfHeapNodes++;
    }

    /*
     * check if the heap is empty
     * $ret == true - if the tree is empty
     */
    public boolean empty() {
        return (this.min == null);
    }

    /**
     * Creates a node (of type HeapNode) which contains the given key, and inserts it into the heap.
     *
     * @pre key>=0
     * @post (numOfnodes = = $prev numOfnodes + 1)
     * @post empty == false
     * $ret = the HeapNode we inserted
     */
    public HeapNode insert(int key) {
        HeapNode toInsert = new HeapNode(key); // creates the node
        if (this.empty()) {
            this.min = toInsert;
        } else { // tree is not empty
            min.setNext(toInsert);
            this.updateMin(toInsert);
        }
        this.numOfHeapNodes++;
        this.numOfTrees++;
        return toInsert;
    }

    /**
     * Delete the node containing the minimum key in the heap
     * updates new min
     *
     * @post (numOfnodes = = $prev numOfnodes - 1)
     */
    public void deleteMin() {
        if (this.empty()) {
            return;
        }
        if (this.numOfHeapNodes == 1) { // if there is only one tree
            this.min = null;
            this.numOfTrees--;
            this.numOfHeapNodes--;
            return;
        }
        // change all children's parent to null//
        if (this.min.child != null) { // min has a child
            HeapNode child = this.min.child;
            HeapNode tmpChild = child;
            child.parent = null;
            while (child.next != tmpChild) {
                child = child.next;
                child.parent = null;
            }
        }
        // delete the node//
        if (this.numOfTrees > 1) {
            (this.min.prev).next = this.min.next;
            (this.min.next).prev = this.min.prev;
            if (this.min.child != null) {
                (this.min.prev).setNext(this.min.child);
            }
        } else { // this.numOfTrees = 1
            this.min = this.min.child;
        }
        this.numOfHeapNodes--;
        this.successiveLink(this.min.getNext());
    }

    /**
     * Return the node of the heap whose key is minimal.
     * $ret == null if (empty==true)
     */
    public HeapNode findMin() {
        return this.min;
    }

    /**
     * Meld the heap with heap2
     *
     * @pre heap2 != null
     * @post (numOfnodes = = $prev numOfnodes + heap2.numOfnodes)
     */
    public void meld(FibonacciHeap heap2) {
        if (heap2.empty()) {
            return;
        }
        if (this.empty()) {
            this.min = heap2.min;
        } else {
            this.min.setNext(heap2.min);
            this.updateMin(heap2.min);
        }
        this.numOfTrees += heap2.numOfTrees;
        this.numOfHeapNodes += heap2.numOfHeapNodes;
    }

    /**
     * Return the number of elements in the heap
     * $ret == 0 if heap is empty
     */
    public int size() {
        return this.numOfHeapNodes;
    }

    /**
     * Return a counters array, where the value of the i-th index is the number of trees with rank i
     * in the heap. returns an empty array for an empty heap
     */
    public int[] countersRep() {
        if (this.empty()) {
            return new int[0]; /// return an empty array
        }
        int[] rankArray = new int[(int) Math.floor(Math.log(this.size()) / Math.log(GOLDEN_RATIO)) + 1]; // creates the array
        rankArray[this.min.rank]++;
        HeapNode curr = this.min.next;
        while (curr != this.min) {
            rankArray[curr.rank]++;
            curr = curr.next;
        }
        return rankArray;
    }

    /**
     * Deletes the node x from the heap (using decreaseKey(x) to -1)
     *
     * @pre heap contains x
     * @post (numOfnodes = = $prev numOfnodes - 1)
     */
    public void delete(HeapNode x) {
        this.decreaseKey(x, x.getKey() + 1); // change key to be the minimal (-1)
        this.deleteMin(); // delete it
    }

    /**
     * The function decreases the key of the node x by delta.
     *
     * @pre x.key >= delta (we don't realize it when calling from delete())
     * @pre heap contains x
     */
    private void decreaseKey(HeapNode x, int delta) {
        int newKey = x.getKey() - delta;
        x.key = newKey;
        if (x.isRoot()) { // no parent to x
            this.updateMin(x);
            return;
        }
        if (x.getKey() >= x.parent.getKey()) {
            return;
        } // we don't need to cut
        HeapNode prevParent = x.parent;
        this.cut(x);
        this.cascadingCuts(prevParent);
    }

    /**
     * returns the current potential of the heap, which is:
     * Potential = #trees + 2*#markedNodes
     */
    public int potential() {
        return numOfTrees + (2 * markedHeapNoodesCounter);
    }

    /**
     * This static function returns the total number of link operations made during the run-time of
     * the program. A link operation is the operation which gets as input two trees of the same
     * rank, and generates a tree of rank bigger by one.
     */
    public static int totalLinks() {
        return totalLinks;
    }

    /**
     * This static function returns the total number of cut operations made during the run-time of
     * the program. A cut operation is the operation which disconnects a subtree from its parent
     * (during decreaseKey/delete methods).
     */
    public static int totalCuts() {
        return totalCuts;
    }

    /*
     * updates the min of the heap (if needed)
     * @pre this.min == @param (posMin) if and only if (posMin.key < this.min.key)
     */
    private void updateMin(HeapNode posMin) {
        if (posMin.getKey() < this.min.getKey()) {
            this.min = posMin;
        }
    }

    /*
     * Recursively "runs" all the way up from @param (curr) and mark the nodes.
     * stop the recursion if we had arrived to a marked node or to a root.
     * if we arrived to a marked node, we cut it and continue recursively.
     * called after a node was cut.
     * @post (numOfnodes == $prev numOfnodes)
     */
    private void cascadingCuts(HeapNode curr) {
        if (!curr.isMarked()) { // stop the recursion
            curr.mark();
            if (!curr.isRoot()) this.markedHeapNoodesCounter++;
        } else {
            if (curr.isRoot()) {
                return;
            }
            HeapNode prevParent = curr.parent;
            this.cut(curr);
            this.cascadingCuts(prevParent);
        }
    }

    /*
     * cut a node (and his "subtree") from his origin tree and connect it to the heap as a new tree.
     * called after a node was cut.
     * @post (numOfnodes == $prev numOfnodes)
     */
    private void cut(HeapNode curr) {
        curr.parent.rank--;
        if (curr.marked) {
            this.markedHeapNoodesCounter--;
            curr.marked = false;
        }
        if (curr.parent.child == curr) { // we should change the parent's child
            if (curr.next == curr) { // curr do not have brothers
                curr.parent.child = null;
            } else { // curr have brothers
                curr.parent.child = curr.next;
            }
        }
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
        curr.next = curr;
        curr.prev = curr;
        curr.parent = null;
        this.min.setNext(curr);
        this.updateMin(curr);
        this.numOfTrees++;
        totalCuts++;
    }

    /*
     *
     */
    private void successiveLink(HeapNode curr) {
        HeapNode[] buckets = this.toBuckets(curr);
        this.min = this.fromBuckets(buckets);
    }

    /*
     *
     */
    private HeapNode[] toBuckets(HeapNode curr) {
        HeapNode[] buckets = new HeapNode[(int) Math.floor(Math.log(this.size()) / Math.log(GOLDEN_RATIO)) + 1];
        curr.prev.next = null;
        HeapNode tmpCurr;
        while (curr != null) {
            tmpCurr = curr;
            curr = curr.next;
            tmpCurr.next = tmpCurr;
            tmpCurr.prev = tmpCurr;
            while (buckets[tmpCurr.rank] != null) {
                tmpCurr = this.link(tmpCurr, buckets[tmpCurr.rank]);
                buckets[tmpCurr.rank - 1] = null;
            }
            buckets[tmpCurr.rank] = tmpCurr;
        }
        return buckets;
    }

    /*
     *
     */
    private HeapNode fromBuckets(HeapNode[] buckets) {
        HeapNode tmpMin = null;
        this.numOfTrees = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                this.numOfTrees++;
                if (tmpMin == null) {
                    tmpMin = buckets[i];
                    tmpMin.next = tmpMin;
                    tmpMin.prev = tmpMin;
                } else {
                    tmpMin.setNext(buckets[i]);
                    if (buckets[i].getKey() < tmpMin.getKey()) {
                        tmpMin = buckets[i];
                    }
                }
            }
        }
        return tmpMin;
    }

    /*
     * link between two nodes (and their trees)
     * defines the smaller node to be the parent
     */
    private HeapNode link(HeapNode c1, HeapNode c2) {
        if (c1.getKey() > c2.getKey()) {
            HeapNode c3 = c1;
            c1 = c2;
            c2 = c3;
        }
        if (c1.child == null) {
            c1.child = c2;
        } else {
            c1.child.setNext(c2);
        }
        c2.parent = c1;
        c1.rank++;
        totalLinks++;
        return c1;
    }

    /**
     * public class HeapNode
     * each HeapNode belongs to a heap (Inner class)
     */
    public class HeapNode {

        public int key;
        private int rank;
        private boolean marked;
        private HeapNode child;
        private HeapNode next;
        private HeapNode prev;
        private HeapNode parent;

        /*
         * a constructor for a heapNode withe key @param (key)
         * prev == next == this
         * parent == child == null
         */
        public HeapNode(int key) {
            this.key = key;
            this.marked = false;
            this.next = this;
            this.prev = this;
        }

        /*
         * returns the key of the node.
         */
        public int getKey() {
            return this.key;
        }

        /*
         * checks whether the node is marked
         * $ret = true if one child has been cut
         */
        private boolean isMarked() {
            return this.marked;
        }

        /*
         * mark a node (after a child was cut)
         * @inv root.mark() == false.
         */
        private void mark() {
            if (this.isRoot()) {
                return;
            } // check if the node is a root
            this.marked = true;
        }

        /*
         * add the node @param (newNext) to be between this and this.next
         * works fine also if @param (newNext) does not "stands" alone
         */
        private void setNext(HeapNode newNext) {
            HeapNode tmpNext = this.next;
            this.next = newNext;
            this.next.prev.next = tmpNext;
            tmpNext.prev = newNext.prev;
            this.next.prev = this;
        }

        /*
         * returns the next node to this node
         */
        private HeapNode getNext() {
            return this.next;
        }

        /*
         * check if the node is a root
         * root definition - this.parent == null (uppest in his tree)
         */
        private boolean isRoot() {
            return (this.parent == null);
        }
    }
}
