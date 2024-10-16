package com.thealgorithms.greedyalgorithms;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Implementation of the Gale-Shapley Algorithm for Stable Matching.
 * Problem link: https://en.wikipedia.org/wiki/Stable_marriage_problem
 */
public final class GaleShapley {

    private GaleShapley() {
    }

    /**
     * Function to find stable matches between men and women.
     *
     * @param womenPrefs A map containing women's preferences where each key is a woman and the value is an array of men in order of preference.
     * @param menPrefs   A map containing men's preferences where each key is a man and the value is an array of women in order of preference.
     * @return A map containing stable matches where the key is a woman and the value is her matched man.
     */
    public static Map<String, String> stableMatch(Map<String, LinkedList<String>> womenPrefs, Map<String, LinkedList<String>> menPrefs) {
        // Initialize all men as free
        Map<String, String> engagements = new HashMap<>();
        LinkedList<String> freeMen = new LinkedList<>(menPrefs.keySet());

        // While there are free men
        while (!freeMen.isEmpty()) {
            String man = freeMen.poll(); // Get the first free man
            LinkedList<String> manPref = menPrefs.get(man); // Get the preferences of the man

            // Check if manPref is null or empty
            if (manPref == null || manPref.isEmpty()) {
                continue; // Skip if no preferences
            }

            // Propose to the first woman in the man's preference list
            String woman = manPref.poll();
            String fiance = engagements.get(woman);

            // If the woman is not engaged, engage her with the current man
            if (fiance == null) {
                engagements.put(woman, man);
            } else {
                // If the woman prefers the current man over her current fiance
                LinkedList<String> womanPrefList = womenPrefs.get(woman);

                // Check if womanPrefList is null
                if (womanPrefList == null) {
                    continue; // Skip if no preferences for the woman
                }

                if (womanPrefList.indexOf(man) < womanPrefList.indexOf(fiance)) {
                    engagements.put(woman, man);
                    freeMen.add(fiance); // Previous fiance becomes free
                } else {
                    // Woman rejects the new proposal, the man remains free
                    freeMen.add(man);
                }
            }
        }
        return engagements; // Return the stable matches
    }
}
