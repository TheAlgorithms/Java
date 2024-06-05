package com.thealgorithms.datastructures.lists;

import java.util.Objects;

/**
 * This class implements a Cursor Linked List.
 *
 * A CursorLinkedList is an array version of a Linked List. Essentially you have
 * an array of list nodes but instead of each node containing a pointer to the
 * next item in the linked list, each node element in the array contains the
 * index for the next node element.
 *
 */
public class CursorLinkedList<T> {

    private static class Node<T> {

        T element;
        int next;

        Node(T element, int next) {
            this.element = element;
            this.next = next;
        }
    }

    private final int os;
    private int head;
    private final Node<T>[] cursorSpace;
    private int count;
    private static final int CURSOR_SPACE_SIZE = 100;

    {
        // init at loading time
        cursorSpace = new Node[CURSOR_SPACE_SIZE];
        for (int i = 0; i < CURSOR_SPACE_SIZE; i++) {
            cursorSpace[i] = new Node<>(null, i + 1);
        }
        cursorSpace[CURSOR_SPACE_SIZE - 1].next = 0;
    }

    public CursorLinkedList() {
        os = 0;
        count = 0;
        head = -1;
    }

    public void printList() {
        if (head != -1) {
            int start = head;
            while (start != -1) {
                T element = cursorSpace[start].element;
                System.out.println(element.toString());
                start = cursorSpace[start].next;
            }
        }
    }

    /**
     * @return the logical index of the element within the list , not the actual
     * index of the [cursorSpace] array
     */
    public int indexOf(T element) {
        Objects.requireNonNull(element);
        Node<T> iterator = cursorSpace[head];
        for (int i = 0; i < count; i++) {
            if (iterator.element.equals(element)) {
                return i;
            }
            iterator = cursorSpace[iterator.next];
        }

        return -1;
    }

    /**
     * @param position , the logical index of the element , not the actual one
     * within the [cursorSpace] array . this method should be used to get the
     * index give by indexOf() method.
     * @return
     */
    public T get(int position) {
        if (position >= 0 && position < count) {
            int start = head;
            int counter = 0;
            while (start != -1) {
                T element = cursorSpace[start].element;
                if (counter == position) {
                    return element;
                }

                start = cursorSpace[start].next;
                counter++;
            }
        }

        return null;
    }

    public void removeByIndex(int index) {
        if (index >= 0 && index < count) {
            T element = get(index);
            remove(element);
        }
    }

    public void remove(T element) {
        Objects.requireNonNull(element);

        // case element is in the head
        T tempElement = cursorSpace[head].element;
        int tempNext = cursorSpace[head].next;
        if (tempElement.equals(element)) {
            free(head);
            head = tempNext;
        } else { // otherwise cases
            int prevIndex = head;
            int currentIndex = cursorSpace[prevIndex].next;

            while (currentIndex != -1) {
                T currentElement = cursorSpace[currentIndex].element;
                if (currentElement.equals(element)) {
                    cursorSpace[prevIndex].next = cursorSpace[currentIndex].next;
                    free(currentIndex);
                    break;
                }

                prevIndex = currentIndex;
                currentIndex = cursorSpace[prevIndex].next;
            }
        }

        count--;
    }

    private void free(int index) {
        Node<T> osNode = cursorSpace[os];
        int osNext = osNode.next;
        cursorSpace[os].next = index;
        cursorSpace[index].element = null;
        cursorSpace[index].next = osNext;
    }

    public void append(T element) {
        Objects.requireNonNull(element);
        int availableIndex = alloc();
        cursorSpace[availableIndex].element = element;

        if (head == -1) {
            head = availableIndex;
        }

        int iterator = head;
        while (cursorSpace[iterator].next != -1) {
            iterator = cursorSpace[iterator].next;
        }

        cursorSpace[iterator].next = availableIndex;
        cursorSpace[availableIndex].next = -1;

        count++;
    }

    /**
     * @return the index of the next available node
     */
    private int alloc() {
        // 1- get the index at which the os is pointing
        int availableNodeIndex = cursorSpace[os].next;

        if (availableNodeIndex == 0) {
            throw new OutOfMemoryError();
        }

        // 2- make the os point to the next of the  @var{availableNodeIndex}
        cursorSpace[os].next = cursorSpace[availableNodeIndex].next;

        // this to indicate an end of the list , helpful at testing since any err
        // would throw an outOfBoundException
        cursorSpace[availableNodeIndex].next = -1;

        return availableNodeIndex;
    }
}
