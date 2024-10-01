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
    public static Map<String, String> stableMatch(Map<String, LinkedList<String>> womenPrefs, 
                                                  Map<String, LinkedList<String>> menPrefs) {
        Map<String, String> engagements = new HashMap<>();
        Queue<String> freeMen = new LinkedList<>(menPrefs.keySet());

        while (!freeMen.isEmpty()) {
            String man = freeMen.poll();
            LinkedList<String> manPref = menPrefs.get(man);

            // Ensure manPref is not null before proceeding
            if (manPref == null) {
                continue;
            }

            // Propose to the first woman in the man's preference list
            String woman = manPref.poll();
            String fiance = engagements.get(woman);

            if (fiance == null) {
                // Woman is free, engage the man and woman
                engagements.put(woman, man);
            } else {
                // Woman is already engaged, check if she prefers this man over her current fiance
                LinkedList<String> womanPrefList = womenPrefs.get(woman);

                // Ensure womanPrefList is not null before comparing
                if (womanPrefList == null) {
                    continue;
                }

                // If the woman prefers the current man over her current fiance
                if (womanPrefList.indexOf(man) < womanPrefList.indexOf(fiance)) {
                    engagements.put(woman, man);
                    freeMen.add(fiance); // Previous fiance becomes free
                } else {
                    // Woman stays with her current fiance, man remains free
                    freeMen.add(man);
                }
            }
        }

        return engagements;
    }
}
