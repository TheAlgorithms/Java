package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.*;

import com.thealgorithms.others.TowerOfHanoi.Peg.*;
import org.junit.jupiter.api.Test;
/**
Author @Atharva Joshi (https://github.com/atharva20-coder)
@TowerOfHanoi
*/
public class TowerOfHanoiTest {
 
    private static final Integer[] EMPTY = {};
    private static final Integer[] ONE_DISK = {1};
    private static final Integer[] THREE_DISKS = {1, 2, 3};
    private static final Integer[] FIVE_DISKS = {1, 2, 3, 4, 5};
    private static final Integer[] TWENTY_DISKS
            = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
 
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForNegativePegs() {
        new TowerOfHanoi(-1);
    }
 
    @Test
    public void shouldInitializePegsWithGivenNumberOfDisks() {
        TowerOfHanoi hanoi = new TowerOfHanoi(0);
        assertPegs(hanoi, EMPTY, EMPTY, EMPTY);
 
        hanoi = new TowerOfHanoi(1);
        assertPegs(hanoi, ONE_DISK, EMPTY, EMPTY);
 
        hanoi = new TowerOfHanoi(5);
        assertPegs(hanoi, FIVE_DISKS, EMPTY, EMPTY);
    }
 
    @Test
    public void shouldDoNothingWithEmptyPegs() {
        TowerOfHanoi hanoi = new TowerOfHanoi(0);
        hanoi.solve(A, B);
        assertPegs(hanoi, EMPTY, EMPTY, EMPTY);
    }
 
    @Test
    public void shouldMoveTheOnlyDiskToSelectedPeg() {
        TowerOfHanoi hanoi = new TowerOfHanoi(1);
        hanoi.solve(A, B);
        assertPegs(hanoi, EMPTY, ONE_DISK, EMPTY);
 
        hanoi.solve(B, C);
        assertPegs(hanoi, EMPTY, EMPTY, ONE_DISK);
 
        hanoi.solve(C, A);
        assertPegs(hanoi, ONE_DISK, EMPTY, EMPTY);
    }
 
    @Test
    public void shouldMoveAllDisksToSelectedPeg() {
        TowerOfHanoi hanoi = new TowerOfHanoi(3);
        hanoi.solve(A, C);
        assertPegs(hanoi, EMPTY, EMPTY, THREE_DISKS);
 
        hanoi = new TowerOfHanoi(5);
        hanoi.solve(A, C);
        assertPegs(hanoi, EMPTY, EMPTY, FIVE_DISKS);
 
        hanoi = new TowerOfHanoi(20);
        hanoi.solve(A, C);
        assertPegs(hanoi, EMPTY, EMPTY, TWENTY_DISKS);
    }
 
    private void assertPegs(TowerOfHanoi hanoi, Integer[] pegA,
                            Integer[] pegB, Integer[] pegC) {
        assertPeg(hanoi, A, pegA);
        assertPeg(hanoi, B, pegB);
        assertPeg(hanoi, C, pegC);
    }
 
    private void assertPeg(TowerOfHanoi hanoi, Peg peg,
                           Integer[] actual) {
        assertArrayEquals(hanoi.toString(),
                hanoi.peg(peg), actual);
    }
}
