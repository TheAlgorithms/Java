package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

class SkylineProblemTest {

    @Test
    void testSingleBuildingSkyline() {
        SkylineProblem skylineProblem = new SkylineProblem();
        skylineProblem.building = new SkylineProblem.Building[1];
        skylineProblem.add(2, 10, 9);

        List<SkylineProblem.Skyline> result = skylineProblem.findSkyline(0, 0);

        assertEquals(List.of(new SkylineProblem.Skyline(2, 10), new SkylineProblem.Skyline(9, 0)), result);
    }

    @Test
    void testTwoBuildingsSkyline() {
        SkylineProblem skylineProblem = new SkylineProblem();
        skylineProblem.building = new SkylineProblem.Building[2];
        skylineProblem.add(1, 11, 5);
        skylineProblem.add(2, 6, 7);

        List<SkylineProblem.Skyline> result = skylineProblem.findSkyline(0, 1);

        // Expected skyline points: (1, 11), (5, 6), (7, 0)
        assertEquals(List.of(new SkylineProblem.Skyline(1, 11), new SkylineProblem.Skyline(5, 6), new SkylineProblem.Skyline(7, 0)), result);
    }

    @Test
    void testMergeSkyline() {
        List<SkylineProblem.Skyline> sky1 = List.of(new SkylineProblem.Skyline(2, 10), new SkylineProblem.Skyline(9, 0));
        List<SkylineProblem.Skyline> sky2 = List.of(new SkylineProblem.Skyline(3, 15), new SkylineProblem.Skyline(7, 0));
        SkylineProblem skylineProblem = new SkylineProblem();
        List<SkylineProblem.Skyline> result = skylineProblem.mergeSkyline(sky1, sky2);

        // Expected merged skyline: (2, 10), (3, 15), (7, 10), (9, 0)
        assertEquals(List.of(new SkylineProblem.Skyline(2, 10), new SkylineProblem.Skyline(3, 15), new SkylineProblem.Skyline(7, 10), new SkylineProblem.Skyline(9, 0)), result);
    }

    @Test
    void testMultipleBuildingsSkyline() {
        SkylineProblem skylineProblem = new SkylineProblem();
        skylineProblem.building = new SkylineProblem.Building[3];
        skylineProblem.add(1, 10, 5);
        skylineProblem.add(2, 15, 7);
        skylineProblem.add(3, 12, 9);

        List<SkylineProblem.Skyline> result = skylineProblem.findSkyline(0, 2);

        assertEquals(List.of(new SkylineProblem.Skyline(1, 10), new SkylineProblem.Skyline(2, 15), new SkylineProblem.Skyline(7, 12), new SkylineProblem.Skyline(9, 0)), result);
    }

    @Test
    void testAddBuildingInvalidCases() {
        SkylineProblem skylineProblem = new SkylineProblem();
        // Not initialized
        Exception ex = assertThrows(IllegalStateException.class, () -> skylineProblem.add(1, 2, 3));
        assertTrue(ex.getMessage().contains("not initialized"));

        skylineProblem.building = new SkylineProblem.Building[1];
        skylineProblem.add(1, 2, 3);
        // Array full
        Exception ex2 = assertThrows(IllegalStateException.class, () -> skylineProblem.add(4, 5, 6));
        assertTrue(ex2.getMessage().contains("full"));

        // Invalid left >= right
        SkylineProblem skylineProblem2 = new SkylineProblem();
        skylineProblem2.building = new SkylineProblem.Building[1];
        Exception ex3 = assertThrows(IllegalArgumentException.class, () -> skylineProblem2.add(5, 2, 2));
        assertTrue(ex3.getMessage().contains("Left coordinate"));

        // Invalid height < 0
        Exception ex4 = assertThrows(IllegalArgumentException.class, () -> skylineProblem2.add(1, -1, 2));
        assertTrue(ex4.getMessage().contains("Height must be non-negative"));
    }

    @Test
    void testFindSkylineInvalidCases() {
        SkylineProblem skylineProblem = new SkylineProblem();
        // Not initialized
        Exception ex = assertThrows(IllegalArgumentException.class, () -> skylineProblem.findSkyline(0, 0));
        assertTrue(ex.getMessage().contains("not initialized"));

        skylineProblem.building = new SkylineProblem.Building[2];
        skylineProblem.count = 1;
        Exception ex2 = assertThrows(IllegalArgumentException.class, () -> skylineProblem.findSkyline(0, 1));
        assertTrue(ex2.getMessage().contains("Invalid start or end index"));
    }

    @Test
    void testMergeSkylineNullCases() {
        SkylineProblem skylineProblem = new SkylineProblem();
        Exception ex1 = assertThrows(NullPointerException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() {
                skylineProblem.mergeSkyline(null, List.of());
            }
        });
        Exception ex2 = assertThrows(NullPointerException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() {
                skylineProblem.mergeSkyline(List.of(), null);
            }
        });
        assertTrue(ex1.getMessage().contains("sky1"));
        assertTrue(ex2.getMessage().contains("sky2"));
    }

    @Test
    void testSkylineEqualsAndHashCode() {
        SkylineProblem.Skyline s1 = new SkylineProblem.Skyline(1, 2);
        SkylineProblem.Skyline s2 = new SkylineProblem.Skyline(1, 2);
        SkylineProblem.Skyline s3 = new SkylineProblem.Skyline(2, 2);
        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());
        assertNotEquals(s1, s3);
        assertNotEquals(null, s1);
        assertNotEquals("string", s1);
    }

    @Test
    void testSkylineToString() {
        SkylineProblem.Skyline s = new SkylineProblem.Skyline(5, 10);
        assertEquals("(5, 10)", s.toString());
    }

    @Test
    void testBuildingToString() {
        SkylineProblem.Building b = new SkylineProblem.Building(1, 2, 3);
        assertEquals("Building{left=1, height=2, right=3}", b.toString());
    }
}
