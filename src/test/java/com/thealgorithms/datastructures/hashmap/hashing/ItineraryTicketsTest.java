package com.thealgorithms.datastructures.hashmap.hashing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ItineraryTicketsTest {

    private HashMap<String, String> tickets;

    @BeforeEach
    public void setUp() {
        tickets = new HashMap<>();
        tickets.put("Chennai", "Bengaluru");
        tickets.put("Mumbai", "Delhi");
        tickets.put("Goa", "Chennai");
        tickets.put("Delhi", "Goa");
    }

    @Test
    public void testGetStart() {
        String start = ItineraryTickets.getStart(tickets);
        assertEquals("Mumbai", start);
    }

    @Test
    public void testGetStartEmptyInput() {
        HashMap<String, String> emptyTickets = new HashMap<>();
        String start = ItineraryTickets.getStart(emptyTickets);
        assertNull(start);
    }

    @Test
    public void testGetStartCircularItinerary() {
        
        tickets.put("Bengaluru", "Mumbai");
        String start = ItineraryTickets.getStart(tickets);
        assertNull(start); 
    }
}
