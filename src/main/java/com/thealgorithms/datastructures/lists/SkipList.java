package com.thealgorithms.datastructures.lists;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Skip list is a data structure that allows {@code O(log n)} search complexity
 * as well as {@code O(log n)} insertion complexity within an ordered sequence
 * of {@code n} elements. Thus it can get the best features of a sorted array
 * (for searching) while maintaining a linked list-like structure that allows
 * insertion, which is not possible with a static array.
 * <p>
 * A skip list is built in layers. The bottom layer is an ordinary ordered
 * linked list. Each higher layer acts as an "express lane" for the lists
 * below.
 * <pre>
 * [ ] ------> [ ] --> [ ]
 * [ ] --> [ ] [ ] --> [ ]
 * [ ] [ ] [ ] [ ] [ ] [ ]
 *  H   0   1   2   3   4
 * </pre>
 *
 * @param <E> type of elements
 * @see <a href="https://en.wikipedia.org/wiki/Skip_list">Wiki. Skip list</a>
 */
public class SkipList<E extends Comparable<E>> {

    /**
     * Node before first node.
     */
    private final Node<E> head;

    /**
     * Maximum layers count.
     * Calculated by {@link #heightStrategy}.
     */
    private final int height;

    /**
     * Function for determining height of new nodes.
     * @see HeightStrategy
     */
    private final HeightStrategy heightStrategy;

    /**
     * Current count of elements in list.
     */
    private int size;

    private static final int DEFAULT_CAPACITY = 100;

    public SkipList() {
        this(DEFAULT_CAPACITY, new BernoulliHeightStrategy());
    }

    public SkipList(int expectedCapacity, HeightStrategy heightStrategy) {
        this.heightStrategy = heightStrategy;
        this.height = heightStrategy.height(expectedCapacity);
        this.head = new Node<>(null, this.height);
        this.size = 0;
    }

    public void add(E e) {
        Objects.requireNonNull(e);
        Node<E> current = head;
        int layer = height;
        Node<E>[] toFix = new Node[height + 1];

        while (layer >= 0) {
            Node<E> next = current.next(layer);
            if (next == null || next.getValue().compareTo(e) > 0) {
                toFix[layer] = current;
                layer--;
            } else {
                current = next;
            }
        }
        int nodeHeight = heightStrategy.nodeHeight(height);
        Node<E> node = new Node<>(e, nodeHeight);
        for (int i = 0; i <= nodeHeight; i++) {
            if (toFix[i].next(i) != null) {
                node.setNext(i, toFix[i].next(i));
                toFix[i].next(i).setPrevious(i, node);
            }

            toFix[i].setNext(i, node);
            node.setPrevious(i, toFix[i]);
        }
        size++;
    }

    public E get(int index) {
        int counter = -1; // head index
        Node<E> current = head;
        while (counter != index) {
            current = current.next(0);
            counter++;
        }
        return current.value;
    }

    public void remove(E e) {
        Objects.requireNonNull(e);
        Node<E> current = head;
        int layer = height;

        while (layer >= 0) {
            Node<E> next = current.next(layer);
            if (e.equals(current.getValue())) {
                break;
            } else if (next == null || next.getValue().compareTo(e) > 0) {
                layer--;
            } else {
                current = next;
            }
        }
        for (int i = 0; i <= layer; i++) {
            current.previous(i).setNext(i, current.next(i));
            if (current.next(i) != null) {
                current.next(i).setPrevious(i, current.previous(i));
            }
        }
        size--;
    }

    /**
     * A search for a target element begins at the head element in the top
     * list, and proceeds horizontally until the current element is greater
     * than or equal to the target. If the current element is equal to the
     * target, it has been found. If the current element is greater than the
     * target, or the search reaches the end of the linked list, the procedure
     * is repeated after returning to the previous element and dropping down
     * vertically to the next lower list.
     *
     * @param e element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     */
    public boolean contains(E e) {
        Objects.requireNonNull(e);
        Node<E> current = head;
        int layer = height;

        while (layer >= 0) {
            Node<E> next = current.next(layer);
            if (e.equals(current.getValue())) {
                return true;
            } else if (next == null || next.getValue().compareTo(e) > 0) {
                layer--;
            } else {
                current = next;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    /**
     * Print height distribution of the nodes in a manner:
     * <pre>
     * [ ] --- --- [ ] --- [ ]
     * [ ] --- [ ] [ ] --- [ ]
     * [ ] [ ] [ ] [ ] [ ] [ ]
     *  H   0   1   2   3   4
     * </pre>
     * Values of nodes is not presented.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        List<boolean[]> layers = new ArrayList<>();
        int sizeWithHeader = size + 1;
        for (int i = 0; i <= height; i++) {
            layers.add(new boolean[sizeWithHeader]);
        }

        Node<E> current = head;
        int position = 0;
        while (current != null) {
            for (int i = 0; i <= current.height; i++) {
                layers.get(i)[position] = true;
            }
            current = current.next(0);
            position++;
        }

        Collections.reverse(layers);
        String result = layers.stream()
                            .map(layer -> {
                                StringBuilder acc = new StringBuilder();
                                for (boolean b : layer) {
                                    if (b) {
                                        acc.append("[ ]");
                                    } else {
                                        acc.append("---");
                                    }
                                    acc.append(" ");
                                }
                                return acc.toString();
                            })
                            .collect(Collectors.joining("\n"));
        String positions = IntStream.range(0, sizeWithHeader - 1).mapToObj(i -> String.format("%3d", i)).collect(Collectors.joining(" "));

        return result + String.format("%n H %s%n", positions);
    }

    /**
     * Value container.
     * Each node have pointers to the closest nodes left and right from current
     * on each layer of nodes height.
     * @param <E> type of elements
     */
    private static class Node<E> {

        private final E value;
        private final int height;
        private final List<Node<E>> forward;
        private final List<Node<E>> backward;

        @SuppressWarnings("unchecked")
        public Node(E value, int height) {
            this.value = value;
            this.height = height;

            // predefined size lists with null values in every cell
            this.forward = Arrays.asList(new Node[height + 1]);
            this.backward = Arrays.asList(new Node[height + 1]);
        }

        public Node<E> next(int layer) {
            checkLayer(layer);
            return forward.get(layer);
        }

        public void setNext(int layer, Node<E> node) {
            forward.set(layer, node);
        }

        public void setPrevious(int layer, Node<E> node) {
            backward.set(layer, node);
        }

        public Node<E> previous(int layer) {
            checkLayer(layer);
            return backward.get(layer);
        }

        public E getValue() {
            return value;
        }

        private void checkLayer(int layer) {
            if (layer < 0 || layer > height) {
                throw new IllegalArgumentException();
            }
        }
    }

    /**
     * Height strategy is a way of calculating maximum height for skip list
     * and height for each node.
     * @see BernoulliHeightStrategy
     */
    public interface HeightStrategy {
        int height(int expectedSize);
        int nodeHeight(int heightCap);
    }

    /**
     * In most common skip list realisation element in layer {@code i} appears
     * in layer {@code i+1} with some fixed probability {@code p}.
     * Two commonly used values for {@code p} are 1/2 and 1/4.
     * Probability of appearing element in layer {@code i} could be calculated
     * with <code>P = p<sup>i</sup>(1 - p)</code>
     * <p>
     * Maximum height that would give the best search complexity
     * calculated by <code>log<sub>1/p</sub>n</code>
     * where {@code n} is an expected count of elements in list.
     */
    public static class BernoulliHeightStrategy implements HeightStrategy {

        private final double probability;

        private static final double DEFAULT_PROBABILITY = 0.5;
        private static final Random RANDOM = new Random();

        public BernoulliHeightStrategy() {
            this.probability = DEFAULT_PROBABILITY;
        }

        public BernoulliHeightStrategy(double probability) {
            if (probability <= 0 || probability >= 1) {
                throw new IllegalArgumentException("Probability should be from 0 to 1. But was: " + probability);
            }
            this.probability = probability;
        }

        @Override
        public int height(int expectedSize) {
            long height = Math.round(Math.log10(expectedSize) / Math.log10(1 / probability));
            if (height > Integer.MAX_VALUE) {
                throw new IllegalArgumentException();
            }
            return (int) height;
        }

        @Override
        public int nodeHeight(int heightCap) {
            int level = 0;
            double border = 100 * (1 - probability);
            while (((RANDOM.nextInt(Integer.MAX_VALUE) % 100) + 1) > border) {
                if (level + 1 >= heightCap) {
                    return level;
                }
                level++;
            }
            return level;
        }
    }
}
