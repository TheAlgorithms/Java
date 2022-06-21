package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArmstrongPartitionTest {

    public void CheckLess100Armstrong() {
        // Partition1 : Armstrong number that is less than 100
        Armstrong armstrong = new Armstrong();
        assertThat(armstrong.isArmstrong(1)).isTrue();

    }

    public void CheckGreater100Less300Armstrong() {
        // Partition2 : Armstrong number that is greater than 100 and less than 300
        Armstrong armstrong = new Armstrong();
        assertThat(armstrong.isArmstrong(153)).isTrue();

    }

    public void CheckGreater300Armstrong() {
        // Partition3 : Armstrong number that is greater than 300
        Armstrong armstrong = new Armstrong();
        assertThat(armstrong.isArmstrong(370)).isTrue();

    }

}
