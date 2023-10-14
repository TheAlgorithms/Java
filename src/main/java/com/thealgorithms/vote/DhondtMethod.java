package com.thealgorithms.vote;

import java.util.*;

/**
 * The D'Hondt method, also called the Jefferson method or the greatest divisors method,
 * is an apportionment method for allocating seats in parliaments among federal states,
 * or in proportional representation among political parties.
 * It belongs to the class of highest-averages methods.
 *
 *  Wikipedia: https://en.wikipedia.org/wiki/D%27Hondt_method
 */
public class DhondtMethod {

    public static void apply(int numberOfSeats, PoliticalParty... parties) {
        if (parties.length == 0) return;
        for (int round = 0; round < numberOfSeats; round++) {
            PoliticalParty selectedParty = Arrays.stream(parties).max(Comparator.comparing(party -> party.votesNumber / (party.gainedSeatsNumber + 1))).get();
            selectedParty.gainedSeatsNumber++;
        }
    }

    public static class PoliticalParty {
        public int votesNumber;
        public int gainedSeatsNumber;

        public PoliticalParty(int votesNumber) {
            this.votesNumber = votesNumber;
        }
    }
}
