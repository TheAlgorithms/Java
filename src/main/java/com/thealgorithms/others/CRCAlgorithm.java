package com.thealgorithms;

import java.util.ArrayList;
import java.util.Arrays;

import com.thealgorithms.datastructures.trees.BinaryTree;
import com.thealgorithms.others.CRCAlgorithm;

public class CoverageTest {
    public static void main(String[] args) {
        CRCAlgorithm c = new CRCAlgorithm("1001", 10, 0.1);

        c.generateRandomMess();
        c.divideMessageWithP(false);
        c.changeMess();

        System.out.println("\n===== CRCAlgorithm coverage test =====\n");
        c.getCoverage();
    }
}
