package test.java.com.dataStructures;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import main.java.com.dataStructures.*;
public class DequeueListTest {
    DequeueList<Integer> dq;

    @Before
    public void setUp() {
        dq = new DequeueList<Integer>();
    }

    @Test
    public void insertionAtHeadTestWhenListIsEmpty(){
        Integer expected = 10;
        dq.insertHead(expected);
        Integer result = dq.getFront().getKey();
//        java.com.dataStructures.Node<Integer> result = dq.getFront() ;
        Assert.assertEquals(expected , result );
    }

    @Test
    public void insertHeadWhenListIsNotEmpty() {
        Integer expected = 10;
        dq.insertHead(30);
        dq.insertHead(40);
        dq.insertHead(expected);
        Integer result = dq.getFront().getKey();

        Assert.assertEquals(expected , result);
    }

    @Test
    public void insertTailWhenListIsEmpty() {
        Integer expected = 20;
        dq.insertTail(expected);
        Integer result = dq.getTail().getKey();
        Assert.assertEquals(expected, result);
    }

    @Test
    public void removeHead() {
        Integer expected = 40;
        dq.insertHead(10);
        dq.insertHead(20);
        dq.insertHead(30);
        dq.insertHead(40);
        Integer result = dq.removeHead().getKey();
        Assert.assertEquals(expected, result);
    }

    @Test
    public void removeTailWhenListIsEmpty(){
        DeqNode<Integer> n = dq.removeTail();
        Assert.assertNull(n);
    }

    @Test
    public void removeHeadWhenListIsEmpty(){
        DeqNode<Integer> n = dq.removeHead();
        Assert.assertNull(n);
    }

    @Test
    public void removeTail() {
        Integer expected = 40;
        dq.insertTail(10);
        dq.insertTail(20);
        dq.insertTail(30);
        dq.insertTail(40);
        Integer result = dq.removeTail().getKey();
        Assert.assertEquals(expected, result);
    }

    @Test
    public void getFront() {
        Integer expected = 10;
        dq.insertHead(expected);

        DeqNode<Integer> n = dq.getFront();
        Integer result = n.getKey();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void getTail() {
        Integer expected = 20;
        dq.insertHead(10);
        dq.insertTail(expected);

        DeqNode<Integer> n = dq.getTail();
        Integer result = n.getKey();

        Assert.assertEquals(expected, result);

    }
}