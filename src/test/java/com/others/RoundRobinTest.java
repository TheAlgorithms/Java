package src.test.java.com.others;

import org.junit.Assert;
import org.junit.Test;

import src.main.java.com.others.RoundRobin;

public class RoundRobinTest {
    private final int[] burstTime = {5, 15, 4, 3};
    private final RoundRobin roundRobin = new RoundRobin();

    @Test
    public void testWaitingTime() {
        int[] expectedTime = {9, 12, 14, 9};
        int[] realtime = roundRobin.calcWaitingTime(burstTime, 3);
        Assert.assertArrayEquals(realtime, expectedTime);
    }

    @Test
    public void testTurnAroundTIme() {
        int[] expectedTIme = {14, 27, 18, 12};
        int[] waitingTime = {9, 12, 14, 9};
        int[] realTime = roundRobin.calcTurnAroundTime(burstTime, waitingTime);
        Assert.assertArrayEquals(realTime, expectedTIme);
    }
}
