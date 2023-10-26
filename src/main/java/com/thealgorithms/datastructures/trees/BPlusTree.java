package com.thealgorithms.datastructures.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;
import java.util.Random;

/**
 * This example program shows how b+ tree and related functions can be done in
 * Java.
 *
 * b+ tree: https://en.wikipedia.org/wiki/B%2B_tree
 */

class BPlusNode<K extends Comparable<K>, V> {

    protected boolean isRoot;  // Indicates whether this node is the root node.
    protected boolean isLeaf;  // Indicates whether this node is a leaf node.
    protected BPlusNode<K, V> parent;  // Reference to the parent node.
    protected BPlusNode<K, V> pre;     // Reference to the previous node in a doubly linked list.
    protected BPlusNode<K, V> next;    // Reference to the next node in a doubly linked list.
    protected List<Entry<K, V>> entryList;  // List of key-value entries in the node.
    protected List<BPlusNode<K, V>> childrenList;  // List of child nodes for non-leaf nodes.


    public BPlusNode(boolean isLeaf) {
        this.isLeaf = isLeaf;
        entryList = new ArrayList();

        if (!isLeaf) {
            childrenList = new ArrayList();
        }
    }

    public BPlusNode(boolean isLeaf, boolean isRoot) {
        this(isLeaf);
        this.isRoot = isRoot;
    }


    /**
     * Retrieves the value associated with the given key in the B+ tree.
     * @param key The key for which to retrieve the value.
     * @return The value associated with the key, or null if the key is not found.
     */
    public V get(K key) {
        if (isLeaf == true) {
            int low = 0, high = entryList.size() - 1, mid, comp;
            while (high >= low) {
                mid = (high + low) / 2;
                comp = entryList.get(mid).getKey().compareTo(key);
                if (comp == 0) {
                    return entryList.get(mid).getValue();
                } else if (comp > 0) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            return null;
        }

        if (key.compareTo(entryList.get(0).getKey()) < 0) {
            return childrenList.get(0).get(key);
        } else if (key.compareTo(entryList.get(entryList.size() - 1).getKey()) >= 0) {
            return childrenList.get(childrenList.size() - 1).get(key);
        } else {
            int low = 0, high = entryList.size() - 1, mid = 0, comp;
            while (high >= low) {
                mid = (high + low) / 2;
                comp = entryList.get(mid).getKey().compareTo(key);
                if (comp == 0) {
                    return childrenList.get(mid + 1).get(key);
                } else if (comp > 0) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            return childrenList.get(low).get(key);
        }
    }

    /**
     * Inserts or updates a key-value pair in the B+ tree.
     * @param key The key to insert or update.
     * @param value The value associated with the key.
     * @param tree The B+ tree to which the node belongs.
     */
    public void insertOrUpdate(K key, V value, BPlusTree<K, V> tree) {
        if (isLeaf) {
            if (entryList.size() < tree.getOrder() || contains(key) != -1 ) {
                insertOrUpdate(key, value);
                if (tree.getHeight() == 0) {
                    tree.setHeight(1);
                }
                return;
            }
            BPlusNode<K, V> right = new BPlusNode<K, V>(true);
            BPlusNode<K, V> left = new BPlusNode<K, V>(true);
            if (pre != null) {
                pre.next = left;
                left.pre = pre;
            }
            if (next != null) {
                next.pre = right;
                right.next = next;
            }
            if (pre == null) {
                tree.setHead(left);
            }

            left.next = right;
            right.pre = left;
            next = null;
            pre = null;

            copy2Nodes(key, value, left, right, tree);

            if (parent == null) {
                isRoot = false;
                BPlusNode<K, V> parent = new BPlusNode<K, V>(false, true);
                tree.setRoot(parent);
                left.parent = parent;
                right.parent = parent;
                parent.childrenList.add(left);
                parent.childrenList.add(right);
                parent.entryList.add(right.entryList.get(0));
                entryList = null;
                childrenList = null;
            } else {
                int index = parent.childrenList.indexOf(this);
                parent.childrenList.remove(this);
                left.parent = parent;
                right.parent = parent;
                parent.childrenList.add(index, left);
                parent.childrenList.add(index + 1, right);
                parent.entryList.add(index, right.entryList.get(0));
                entryList = null;
                childrenList = null;
                parent.updateInsert(tree);
                parent = null;
            }
            return;

        }

        if (key.compareTo(entryList.get(0).getKey()) < 0) {
            childrenList.get(0).insertOrUpdate(key, value, tree);
        } else if (key.compareTo(entryList.get(entryList.size() - 1).getKey()) >= 0) {
            childrenList.get(childrenList.size() - 1).insertOrUpdate(key, value, tree);
        } else {
            int low = 0, high = entryList.size() - 1, mid = 0, comp;
            while (high >= low) {
                mid = (high + low) / 2;
                comp = entryList.get(mid).getKey().compareTo(key);
                if (comp == 0) {
                    childrenList.get(mid + 1).insertOrUpdate(key, value, tree);
                    break;
                } else if (comp > 0) {
                    low = mid + 1;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            if (low > high) {
                childrenList.get(low).insertOrUpdate(key, value, tree);
            }
        }
    }

    private void copy2Nodes(K key, V value, BPlusNode<K, V> left,
                            BPlusNode<K, V> right, BPlusTree<K, V> tree) {
        int leftSize = (tree.getOrder() + 1) / 2 + (tree.getOrder() + 1) % 2;
        boolean record = false;
        for (int i = 0; i < entryList.size(); i++) {
            if (leftSize != 0) {
                leftSize -= 1;
                if (!record && entryList.get(i).getKey().compareTo(key) > 0) {
                    left.entryList.add(new SimpleEntry<K, V>(key, value));
                    record = true;
                    i -= 1;
                } else {
                    left.entryList.add(entryList.get(i));
                }
            } else {
                if (!record && entryList.get(i).getKey().compareTo(key) > 0) {
                    right.entryList.add(new SimpleEntry<K, V>(key, value));
                    record = true;
                    i -= 1;
                } else {
                    right.entryList.add(entryList.get(i));
                }
            }
        }
        if (record == false) {
            right.entryList.add(new SimpleEntry<K, V>(key, value));
        }
    }

    protected void updateInsert(BPlusTree<K, V> tree) {

        if (childrenList.size() > tree.getOrder()) {
            BPlusNode<K, V> right = new BPlusNode<K, V>(false);
            BPlusNode<K, V> left = new BPlusNode<K, V>(false);
            int rightSize = (tree.getOrder() + 1) / 2;
            int leftSize = (tree.getOrder() + 1) / 2 + (tree.getOrder() + 1) % 2;
            for (int i = 0; i < leftSize; i++) {
                left.childrenList.add(childrenList.get(i));
                childrenList.get(i).parent = left;
            }
            for (int i = 0; i < rightSize; i++) {
                right.childrenList.add(childrenList.get(leftSize + i));
                childrenList.get(leftSize + i).parent = right;
            }
            for (int i = 0; i < leftSize - 1; i++) {
                left.entryList.add(entryList.get(i));
            }
            for (int i = 0; i < rightSize - 1; i++) {
                right.entryList.add(entryList.get(leftSize + i));
            }

            if (parent != null) {
                int index = parent.childrenList.indexOf(this);
                parent.childrenList.remove(this);
                right.parent = parent;
                left.parent = parent;
                parent.childrenList.add(index, left);
                parent.childrenList.add(index + 1, right);
                parent.entryList.add(index, entryList.get(leftSize - 1));
                entryList = null;
                childrenList = null;

                parent.updateInsert(tree);
                parent = null;
            } else {
                isRoot = false;
                BPlusNode<K, V> parent = new BPlusNode<K, V>(false, true);
                tree.setRoot(parent);
                tree.setHeight(tree.getHeight() + 1);
                right.parent = parent;
                left.parent = parent;
                parent.childrenList.add(left);
                parent.childrenList.add(right);
                parent.entryList.add(entryList.get(leftSize - 1));
                entryList = null;
                childrenList = null;
            }
        }
    }

    protected void updateRemove(BPlusTree<K, V> tree) {

        if (childrenList.size() < tree.getOrder() / 2 || childrenList.size() < 2) {
            if (isRoot == true) {
                if (childrenList.size() >= 2) return;
                BPlusNode<K, V> root = childrenList.get(0);
                tree.setRoot(root);
                tree.setHeight(tree.getHeight() - 1);
                root.parent = null;
                root.isRoot = true;
                entryList = null;
                childrenList = null;
                return;
            }
            int currIdx = parent.childrenList.indexOf(this);
            int prevIdx = currIdx - 1;
            int nextIdx = currIdx + 1;
            BPlusNode<K, V> previous = null, next = null;
            if (prevIdx >= 0) {
                previous = parent.childrenList.get(prevIdx);
            }
            if (nextIdx < parent.childrenList.size()) {
                next = parent.childrenList.get(nextIdx);
            }

            if (previous != null && previous.childrenList.size() > tree.getOrder() / 2 && previous.childrenList.size() > 2) {
                int index = previous.childrenList.size() - 1;
                BPlusNode<K, V> borrow = previous.childrenList.get(index);
                previous.childrenList.remove(index);
                borrow.parent = this;
                childrenList.add(0, borrow);
                int preIndex = parent.childrenList.indexOf(previous);

                entryList.add(0, parent.entryList.get(preIndex));
                parent.entryList.set(preIndex, previous.entryList.remove(index - 1));
                return;
            }

            if (next != null && next.childrenList.size() > tree.getOrder() / 2 && next.childrenList.size() > 2) {
                BPlusNode<K, V> node = next.childrenList.get(0);
                next.childrenList.remove(0);
                node.parent = this;
                childrenList.add(node);
                int preIndex = parent.childrenList.indexOf(this);
                entryList.add(parent.entryList.get(preIndex));
                parent.entryList.set(preIndex, next.entryList.remove(0));
                return;
            }

            if (previous != null && (previous.childrenList.size() <= 2 || previous.childrenList.size() <= tree.getOrder() / 2)) {
                for (int i = 0; i < childrenList.size(); i++) {
                    previous.childrenList.add(childrenList.get(i));
                }
                for (int i = 0; i < previous.childrenList.size(); i++) {
                    previous.childrenList.get(i).parent = this;
                }
                int indexPre = parent.childrenList.indexOf(previous);
                previous.entryList.add(parent.entryList.get(indexPre));
                for (int i = 0; i < entryList.size(); i++) {
                    previous.entryList.add(entryList.get(i));
                }
                childrenList = previous.childrenList;
                entryList = previous.entryList;

                parent.childrenList.remove(previous);
                previous.parent = null;
                previous.childrenList = null;
                previous.entryList = null;
                parent.entryList.remove(parent.childrenList.indexOf(this));
                if ((!parent.isRoot
                        && (parent.childrenList.size() >= tree.getOrder() / 2
                        && parent.childrenList.size() >= 2))
                        || parent.isRoot && parent.childrenList.size() >= 2) {
                    return;
                }
                parent.updateRemove(tree);
                return;
            }

            if (next != null && (next.childrenList.size() <= 2 || next.childrenList.size() <= tree.getOrder() / 2)) {
                for (int i = 0; i < next.childrenList.size(); i++) {
                    BPlusNode<K, V> child = next.childrenList.get(i);
                    childrenList.add(child);
                    child.parent = this;
                }
                int index = parent.childrenList.indexOf(this);
                entryList.add(parent.entryList.get(index));
                for (int i = 0; i < next.entryList.size(); i++) {
                    entryList.add(next.entryList.get(i));
                }
                parent.childrenList.remove(next);
                next.parent = null;
                next.childrenList = null;
                next.entryList = null;
                parent.entryList.remove(parent.childrenList.indexOf(this));
                if ((!parent.isRoot && (parent.childrenList.size() >= tree.getOrder() / 2
                        && parent.childrenList.size() >= 2))
                        || parent.isRoot && parent.childrenList.size() >= 2) {
                    return;
                }
                parent.updateRemove(tree);
                return;
            }
        }
    }

    /**
     * Removes a key from the B+ tree.
     * @param key The key to remove.
     * @param tree The B+ tree from which to remove the key.
     * @return The value associated with the removed key, or null if the key is not found.
     */
    public V remove(K key, BPlusTree<K, V> tree) {
        if (isLeaf == true) {
            if (contains(key) == -1) {
                return null;
            }
            if (isRoot) {
                if (entryList.size() == 1) {
                    tree.setHeight(0);
                }
                return remove(key);
            }
            if (entryList.size() > tree.getOrder() / 2 && entryList.size() > 2) {
                return remove(key);
            }
            if (pre != null &&
                    pre.parent == parent
                    && pre.entryList.size() > tree.getOrder() / 2
                    && pre.entryList.size() > 2) {
                int size = pre.entryList.size();
                entryList.add(0, pre.entryList.remove(size - 1));
                int index = parent.childrenList.indexOf(pre);
                parent.entryList.set(index, entryList.get(0));
                return remove(key);
            }
            if (next != null
                    && next.parent == parent
                    && next.entryList.size() > tree.getOrder() / 2
                    && next.entryList.size() > 2) {
                entryList.add(next.entryList.remove(0));
                int index = parent.childrenList.indexOf(this);
                parent.entryList.set(index, next.entryList.get(0));
                return remove(key);
            }

            if (pre != null
                    && pre.parent == parent
                    && (pre.entryList.size() <= tree.getOrder() / 2
                    || pre.entryList.size() <= 2)) {
                V returnValue = remove(key);
                for (int i = 0; i < entryList.size(); i++) {
                    pre.entryList.add(entryList.get(i));
                }
                entryList = pre.entryList;
                parent.childrenList.remove(pre);
                pre.parent = null;
                pre.entryList = null;
                if (pre.pre != null) {
                    BPlusNode<K, V> temp = pre;
                    temp.pre.next = this;
                    pre = temp.pre;
                    temp.pre = null;
                    temp.next = null;
                } else {
                    tree.setHead(this);
                    pre.next = null;
                    pre = null;
                }
                parent.entryList.remove(parent.childrenList.indexOf(this));
                if ((!parent.isRoot && (parent.childrenList.size() >= tree.getOrder() / 2
                        && parent.childrenList.size() >= 2))
                        || parent.isRoot && parent.childrenList.size() >= 2) {
                    return returnValue;
                }
                parent.updateRemove(tree);
                return returnValue;
            }

            if (next != null
                    && next.parent == parent
                    && (next.entryList.size() <= tree.getOrder() / 2
                    || next.entryList.size() <= 2)) {
                V returnValue = remove(key);
                for (int i = 0; i < next.entryList.size(); i++) {
                    entryList.add(next.entryList.get(i));
                }
                next.parent = null;
                next.entryList = null;
                parent.childrenList.remove(next);
                if (next.next != null) {
                    BPlusNode<K, V> temp = next;
                    temp.next.pre = this;
                    next = temp.next;
                    temp.pre = null;
                    temp.next = null;
                } else {
                    next.pre = null;
                    next = null;
                }
                parent.entryList.remove(parent.childrenList.indexOf(this));
                if ((!parent.isRoot && (parent.childrenList.size() >= tree.getOrder() / 2
                        && parent.childrenList.size() >= 2))
                        || parent.isRoot && parent.childrenList.size() >= 2) {
                    return returnValue;
                }
                parent.updateRemove(tree);
                return returnValue;
            }
        }

        if (key.compareTo(entryList.get(0).getKey()) < 0) {
            return childrenList.get(0).remove(key, tree);
        } else if (key.compareTo(entryList.get(entryList.size() - 1).getKey()) >= 0) {
            return childrenList.get(childrenList.size() - 1).remove(key, tree);
        } else {
            int low = 0, high = entryList.size() - 1, mid = 0, comp;
            while (high >= low) {
                mid = (high + low) / 2;
                comp = entryList.get(mid).getKey().compareTo(key);
                if (comp == 0) {
                    return childrenList.get(mid + 1).remove(key, tree);
                } else if (comp > 0) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            return childrenList.get(low).remove(key, tree);
        }
    }

    /**
     * Checks if a key exists in the B+ tree node.
     * @param key The key to check for existence.
     * @return The index of the key in the node's entry list, or -1 if the key is not found.
     */
    protected int contains(K key) {
        int low = 0, high = entryList.size() - 1, mid, comp;
        while (low <= high) {
            mid = (high + low) / 2;
            comp = entryList.get(mid).getKey().compareTo(key);
            if (comp == 0) {
                return mid;
            } else if (comp > 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    protected void insertOrUpdate(K key, V value) {
        int low = 0, high = entryList.size() - 1, mid, comp;
        while (high >= low) {
            mid = (high + low) / 2;
            comp = entryList.get(mid).getKey().compareTo(key);
            if (comp == 0) {
                entryList.get(mid).setValue(value);
                break;
            } else if (comp > 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        if (low > high) {
            entryList.add(low, new SimpleEntry<K, V>(key, value));
        }
    }

    protected V remove(K key) {
        int low = 0, high = entryList.size() - 1, mid, comp;
        while (high >= low) {
            mid = (high + low) / 2;
            comp = entryList.get(mid).getKey().compareTo(key);
            if (comp == 0) {
                return entryList.remove(mid).getValue();
            } else if (comp > 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return null;
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("isRoot: ");
        string.append(isRoot);
        string.append(", ");
        string.append("isLeaf: ");
        string.append(isLeaf);
        string.append(", ");
        string.append("keys: ");
        for (Entry<K, V> entry : entryList) {
            string.append(entry.getKey());
            string.append(", ");
        }
        string.append(", ");
        return string.toString();
    }

    /**
     * Prints the B+ tree structure.
     */
    public void printBPlusTree(int index) {
        if (this.isLeaf) {
            System.out.print("layer：" + index + ",leaf node，keys are: ");
            for (int i = 0; i < entryList.size(); ++i)
                System.out.print(entryList.get(i) + " ");
            System.out.println();
        } else {
            System.out.print("layer：" + index + ",Non-leaf node，keys are: ");
            for (int i = 0; i < entryList.size(); ++i)
                System.out.print(entryList.get(i) + " ");
            System.out.println();
            for (int i = 0; i < childrenList.size(); ++i)
                childrenList.get(i).printBPlusTree(index + 1);
        }
    }

}


class BPlusTree<K extends Comparable<K>, V> {

    protected BPlusNode<K, V> root;  // The root node of the B+ tree.
    protected int order;  // The order of the B+ tree.
    protected BPlusNode<K, V> head;  // The head of the doubly linked list.
    protected int height = 0;  // The height of the B+ tree.

    public BPlusNode<K, V> getHead() {
        return head;
    }

    public void setHead(BPlusNode<K, V> head) {
        this.head = head;
    }

    public BPlusNode<K, V> getRoot() {
        return root;
    }

    public void setRoot(BPlusNode<K, V> root) {
        this.root = root;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public V get(K key) {
        return root.get(key);
    }

    public V remove(K key) {
        return root.remove(key, this);
    }

    public void insertOrUpdate(K key, V value) {
        root.insertOrUpdate(key, value, this);
    }

    public BPlusTree(int order) {
        if (order < 3) {
            System.out.print("order must be greater than 2");
            System.exit(0);
        }
        this.order = order;
        root = new BPlusNode<K, V>(true, true);
        head = root;
    }

    public void printBPlusTree() {
        this.root.printBPlusTree(0);
    }
}

class BPlusTreeInernTest {
    public static void main(String[] args) {

        int size = 15;
        int order = 3;


        testRandomInsert(size, order);

        testOrderInsert(size, order);

        testRandomSearch(size, order);

        testOrderSearch(size, order);

        testRandomRemove(size, order);

        testOrderRemove(size, order);
    }

    private static void testOrderRemove(int size, int order) {
        BPlusTree<Integer, Integer> tree = new BPlusTree<Integer, Integer>(order);
        System.out.println("\nTest order remove " + size + " datas, of order:"
                + order);
        System.out.println("Begin order insert...");
        for (int i = 0; i < size; i++) {
            tree.insertOrUpdate(i, i);
        }
        tree.printBPlusTree();
        System.out.println("Begin order remove...");
        long current = System.currentTimeMillis();
        for (int j = 0; j < size; j++) {
            if (tree.remove(j) == null) {
                System.err.println("No data:" + j);
                break;
            }
        }
        long duration = System.currentTimeMillis() - current;
        System.out.println("time elpsed for duration: " + duration);
        tree.printBPlusTree();
        System.out.println(tree.getHeight());
    }

    private static void testRandomRemove(int size, int order) {
        BPlusTree<Integer, Integer> tree = new BPlusTree<Integer, Integer>(order);
        System.out.println("\nTest random remove " + size + " datas, of order:"
                + order);
        Random random = new Random();
        boolean[] a = new boolean[size + 10];
        List<Integer> list = new ArrayList<Integer>();
        int randomNumber = 0;
        System.out.println("Begin random insert...");
        for (int i = 0; i < size; i++) {
            randomNumber = random.nextInt(size);
            a[randomNumber] = true;
            list.add(randomNumber);
            tree.insertOrUpdate(randomNumber, randomNumber);
        }
        tree.printBPlusTree();
        System.out.println("Begin random remove...");
        long current = System.currentTimeMillis();
        for (int j = 0; j < size; j++) {
            randomNumber = list.get(j);
            if (a[randomNumber]) {
                if (tree.remove(randomNumber) == null) {
                    System.err.println("No data:" + randomNumber);
                    break;
                } else {
                    a[randomNumber] = false;
                }
            }
        }
        long duration = System.currentTimeMillis() - current;
        System.out.println("time elpsed for duration: " + duration);
        tree.printBPlusTree();
        System.out.println(tree.getHeight());
    }

    private static void testOrderSearch(int size, int order) {
        BPlusTree<Integer, Integer> tree = new BPlusTree<Integer, Integer>(order);
        System.out.println("\nTest order search " + size + " datas, of order:"
                + order);
        System.out.println("Begin order insert...");
        for (int i = 0; i < size; i++) {
            tree.insertOrUpdate(i, i);
        }
        tree.printBPlusTree();
        System.out.println("Begin order search...");
        long current = System.currentTimeMillis();
        for (int j = 0; j < size; j++) {
            if (tree.get(j) == null) {
                System.err.println("No data:" + j);
                break;
            }
        }
        long duration = System.currentTimeMillis() - current;
        System.out.println("time elpsed for duration: " + duration);
    }

    private static void testRandomSearch(int size, int order) {
        BPlusTree<Integer, Integer> tree = new BPlusTree<Integer, Integer>(order);
        System.out.println("\nTest random search " + size + " datas, of order:"
                + order);
        Random random = new Random();
        boolean[] a = new boolean[size + 10];
        int randomNumber = 0;
        System.out.println("Begin random insert...");
        for (int i = 0; i < size; i++) {
            randomNumber = random.nextInt(size);
            a[randomNumber] = true;
            tree.insertOrUpdate(randomNumber, randomNumber);
        }
        tree.printBPlusTree();
        System.out.println("Begin random search...");
        long current = System.currentTimeMillis();
        for (int j = 0; j < size; j++) {
            randomNumber = random.nextInt(size);
            if (a[randomNumber]) {
                if (tree.get(randomNumber) == null) {
                    System.err.println("No data:" + randomNumber);
                    break;
                }
            }
        }
        long duration = System.currentTimeMillis() - current;
        System.out.println("time elpsed for duration: " + duration);
    }

    private static void testRandomInsert(int size, int order) {
        BPlusTree<Integer, Integer> tree = new BPlusTree<Integer, Integer>(order);
        System.out.println("\nTest random insert " + size + " datas, of order:"
                + order);
        Random random = new Random();
        int randomNumber = 0;
        long current = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            randomNumber = random.nextInt(size * 10);
            System.out.print(randomNumber + " ");
            tree.insertOrUpdate(randomNumber, randomNumber);
        }
        long duration = System.currentTimeMillis() - current;
        System.out.println("time elpsed for duration: " + duration);
        tree.printBPlusTree();
        System.out.println(tree.getHeight());
    }

    private static void testOrderInsert(int size, int order) {
        BPlusTree<Integer, Integer> tree = new BPlusTree<Integer, Integer>(order);
        System.out.println("\nTest order insert " + size + " datas, of order:"
                + order);
        long current = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            tree.insertOrUpdate(i, i);
        }
        long duration = System.currentTimeMillis() - current;
        System.out.println("time elpsed for duration: " + duration);
        tree.printBPlusTree();
    }

}