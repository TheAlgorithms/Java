package com.thealgorithms.simulation;

public class NRobotsCollision {
    static class Pair {
        int first;
        char second;
        Pair(int first, char second) {
            this.first = first;
            this.second = second;
        }
    }
    public static int earliestCollisionTime(int n, int[] positions, String directions) {
        List<Pair> sortedDirections = new ArrayList<>();
        for(int i = 0; i < n; i++){
            sortedDirections.add(new Pair(positions[i], directions.charAt(i)));
        }
        sortedDirections.sort((a, b) -> Integer.compare(a.first, b.first));
        int minTime = Integer.MAX_VALUE;
        for(int i = 1; i < n; i++){
            if(sortedDirections.get(i).second == 'L' && sortedDirections.get(i - 1).second == 'R'){
                minTime = Math.min(minTime, sortedDirections.get(i).first - sortedDirections.get(i - 1).first);
            }
        }
        return minTime == Integer.MAX_VALUE ? -1 : minTime / 2;
    }
}