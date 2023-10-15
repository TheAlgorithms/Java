package com.thealgorithms.maths;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DhondtElectoralSystemAlgorithm {
    public static void main(String[] args) {
        // Example usage
        Map<String, Integer> partyVotes = new HashMap<>();
        partyVotes.put("Party A", 30000);
        partyVotes.put("Party B", 25000);
        partyVotes.put("Party C", 20000);
        partyVotes.put("Party D", 18000);
        
        int totalSeats = 10; // Total number of seats to be allocated
        
        List<String> result = allocateSeats(partyVotes, totalSeats);
        
        System.out.println("Seat allocation results:");
        for (String party : result) {
            System.out.println(party + " gets a seat.");
        }
    }
    
    public static List<String> allocateSeats(Map<String, Integer> partyVotes, int totalSeats) {
        List<String> parties = new ArrayList<>(partyVotes.keySet());
        
        Map<String, Integer> seatsAllocated = new HashMap<>();
        
        for (int seat = 1; seat <= totalSeats; seat++) {
            List<Entry<String, Integer>> sortedParties = new ArrayList<>(partyVotes.entrySet());
            Collections.sort(sortedParties, (a, b) -> Integer.compare(b.getValue(), a.getValue()));
            
            String winningParty = sortedParties.get(0).getKey();
            seatsAllocated.put(winningParty, seatsAllocated.getOrDefault(winningParty, 0) + 1);
            
            // Recalculate party votes for the next round
            partyVotes.put(winningParty, partyVotes.get(winningParty) / (seatsAllocated.get(winningParty) + 1));
        }
        
        List<String> result = new ArrayList<>();
        for (String party : parties) {
            int seats = seatsAllocated.getOrDefault(party, 0);
            for (int i = 0; i < seats; i++) {
                result.add(party);
            }
        }
        
        return result;
    }
}
