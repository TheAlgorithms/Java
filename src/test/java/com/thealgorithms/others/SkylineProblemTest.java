package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class SkylineProblemTest {

    @Test
    public void testSingleBuildingSkyline() {
        SkylineProblem skylineProblem = new SkylineProblem();
        skylineProblem.building = new SkylineProblem.Building[1];
        skylineProblem.add(2, 10, 9);

        ArrayList<SkylineProblem.Skyline> result = skylineProblem.findSkyline(0, 0);

        assertEquals(2, result.get(0).coordinates);
        assertEquals(10, result.get(0).height);
        assertEquals(9, result.get(1).coordinates);
        assertEquals(0, result.get(1).height);
    }

    @Test
    public void testTwoBuildingsSkyline() {
        SkylineProblem skylineProblem = new SkylineProblem();
        skylineProblem.building = new SkylineProblem.Building[2];
        skylineProblem.add(1, 11, 5);
        skylineProblem.add(2, 6, 7);

        ArrayList<SkylineProblem.Skyline> result = skylineProblem.findSkyline(0, 1);

        // Expected skyline points: (1, 11), (5, 6), (7, 0)
        assertEquals(1, result.get(0).coordinates);
        assertEquals(11, result.get(0).height);
        assertEquals(5, result.get(1).coordinates);
        assertEquals(6, result.get(1).height);
        assertEquals(7, result.get(2).coordinates);
        assertEquals(0, result.get(2).height);
    }

    @Test
    public void testMergeSkyline() {
        SkylineProblem skylineProblem = new SkylineProblem();
        ArrayList<SkylineProblem.Skyline> sky1 = new ArrayList<>();
        ArrayList<SkylineProblem.Skyline> sky2 = new ArrayList<>();

        sky1.add(skylineProblem.new Skyline(2, 10));
        sky1.add(skylineProblem.new Skyline(9, 0));

        sky2.add(skylineProblem.new Skyline(3, 15));
        sky2.add(skylineProblem.new Skyline(7, 0));

        ArrayList<SkylineProblem.Skyline> result = skylineProblem.mergeSkyline(sky1, sky2);

        // Expected merged skyline: (2, 10), (3, 15), (7, 10), (9, 0)
        assertEquals(2, result.get(0).coordinates);
        assertEquals(10, result.get(0).height);
        assertEquals(3, result.get(1).coordinates);
        assertEquals(15, result.get(1).height);
        assertEquals(7, result.get(2).coordinates);
        assertEquals(10, result.get(2).height);
        assertEquals(9, result.get(3).coordinates);
        assertEquals(0, result.get(3).height);
    }

    @Test
    public void testMultipleBuildingsSkyline() {
        SkylineProblem skylineProblem = new SkylineProblem();
        skylineProblem.building = new SkylineProblem.Building[3];
        skylineProblem.add(1, 10, 5);
        skylineProblem.add(2, 15, 7);
        skylineProblem.add(3, 12, 9);

        ArrayList<SkylineProblem.Skyline> result = skylineProblem.findSkyline(0, 2);

        assertEquals(1, result.get(0).coordinates);
        assertEquals(10, result.get(0).height);
        assertEquals(2, result.get(1).coordinates);
        assertEquals(15, result.get(1).height);
        assertEquals(7, result.get(2).coordinates);
        assertEquals(12, result.get(2).height);
        assertEquals(9, result.get(3).coordinates);
        assertEquals(0, result.get(3).height);
    }
}
