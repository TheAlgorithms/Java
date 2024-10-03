package com.thealgorithms.Recursion;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public final class TowerOfHanoiTest {

    @Test
    void hanoiTowerTestOne() {

        int n = 5;
        String[] expected = {"Take disk 1 from rod A to rod B", "Take disk 2 from rod A to rod C", "Take disk 1 from rod B to rod C", "Take disk 3 from rod A to rod B", "Take disk 1 from rod C to rod A", "Take disk 2 from rod C to rod B", "Take disk 1 from rod A to rod B",
            "Take disk 4 from rod A to rod C", "Take disk 1 from rod B to rod C", "Take disk 2 from rod B to rod A", "Take disk 1 from rod C to rod A", "Take disk 3 from rod B to rod C", "Take disk 1 from rod A to rod B", "Take disk 2 from rod A to rod C", "Take disk 1 from rod B to rod C",
            "Take disk 5 from rod A to rod B", "Take disk 1 from rod C to rod A", "Take disk 2 from rod C to rod B", "Take disk 1 from rod A to rod B", "Take disk 3 from rod C to rod A", "Take disk 1 from rod B to rod C", "Take disk 2 from rod B to rod A", "Take disk 1 from rod C to rod A",
            "Take disk 4 from rod C to rod B", "Take disk 1 from rod A to rod B", "Take disk 2 from rod A to rod C", "Take disk 1 from rod B to rod C", "Take disk 3 from rod A to rod B", "Take disk 1 from rod C to rod A", "Take disk 2 from rod C to rod B", "Take disk 1 from rod A to rod B"};

        List<String> actual = TowerOfHanoi.towerOfHanoi(n);
        assertArrayEquals(expected, actual.toArray());
    }

    @Test
    void hanoiTowerTestTwo() {

        int n = 3;
        String[] expected = {"Take disk 1 from rod A to rod B", "Take disk 2 from rod A to rod C", "Take disk 1 from rod B to rod C", "Take disk 3 from rod A to rod B", "Take disk 1 from rod C to rod A", "Take disk 2 from rod C to rod B", "Take disk 1 from rod A to rod B"};

        List<String> actual = TowerOfHanoi.towerOfHanoi(n);
        assertArrayEquals(expected, actual.toArray());
    }

    @Test
    void hanoiTowerTestThree() {

        int n = 1;
        String[] expected = {"Take disk 1 from rod A to rod B"};

        List<String> actual = TowerOfHanoi.towerOfHanoi(n);
        assertArrayEquals(expected, actual.toArray());
    }
}
