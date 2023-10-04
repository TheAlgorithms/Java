package com.thealgorithms.datastructures.hashmap.hashing;

import java.util.HashMap;

/**
 * This class represents an itinerary of tickets. Given a list of tickets, it finds the
 * complete itinerary.
 */
public final class ItineraryTickets {
    private ItineraryTickets() {
    }

    /**
     * This method finds the starting point of the itinerary.
     *
     * @param tickets A map of tickets where the key is the departure point, and the value is the destination.
     * @return The starting point of the itinerary.
     */
    public static String getStart(HashMap<String, String> tickets) {
        HashMap<String, String> revTickets = new HashMap<>();
        for (String key : tickets.keySet()) {
            revTickets.put(tickets.get(key), key);
        }
        for (String key : tickets.keySet()) {
            if (!revTickets.containsKey(key)) {
                return key; // Starting point
            }
        }
        return null;
    }
}
