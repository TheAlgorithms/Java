package com.matchings.stablematching;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class GaleShapleyTest {

    /**
     * Test a number of GaleShapley executions on pseudo-random instances of the
     * stable marriage problem.
     */

    @Test
    void testGaleShapley() {
        GaleShapley galeShapley = new GaleShapley();
        int N = 10;
        int[][] menPrefs;
        int[][] womenPrefs;
        int[] GaleShapleyMenMatching; // the solution returned by GaleShapley.java
        // for each n from 0 to N-1, create and test an instance of the problem.
        for (int n = 0; n < N; n++) {
            System.out.println("testing n = " + n);
            menPrefs = new int[n][n];
            womenPrefs = new int[n][n];
            // set all other sex individuals in each individual's preference list,
            // then shuffle
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    menPrefs[i][j] = j;
                    womenPrefs[i][j] = j;
                }
                shuffleArray(menPrefs[i], i);
                shuffleArray(womenPrefs[i], n + i);
            }
            // Now we have pseudo-random preferences for each man and each woman.
            GaleShapleyMenMatching = galeShapley.GaleShapleyStableMarriage(menPrefs, womenPrefs);
            Assertions.assertTrue(isStable(GaleShapleyMenMatching, menPrefs, womenPrefs), "Unstable matching");
        }
    }

    /**
     * Determine if the proposed menMatching is stable, i.e. if there is no
     * potential couple in which both members would strictly prefer being with each
     * other than being with their current partner.
     *
     * @param menMatching
     * @param menPrefs
     * @param womenPrefs
     * @return whether menMatching is stable according to menPrefs and womenPrefs
     */

    private boolean isStable(int[] menMatching, int[][] menPrefs, int[][] womenPrefs) {
        int n = menMatching.length;
        // reconstruct womenMatching (for each woman, the associated man):
        int[] womenMatching = new int[n];
        int man;
        int woman;
        for (man = 0; man < n; man++) {
            woman = menMatching[man];
            womenMatching[woman] = man;
        }

        // construct menRanks and womenRanks to quickly compare preferences:
        int[][] menRanks = new int[n][n];
        int[][] womenRanks = new int[n][n];
        int individualAtThisRank;
        for (int i = 0; i < n; i++) {
            for (int rank = 0; rank < n; rank++) {
                // womenRanks
                individualAtThisRank = womenPrefs[i][rank];
                womenRanks[i][individualAtThisRank] = rank;

                // menRanks
                individualAtThisRank = menPrefs[i][rank];
                menRanks[i][individualAtThisRank] = rank;
            }
        }

        // Do the actual test by considering all potential n*n couples and verifying
        // that at least one of them is happier now than they
        // would be in the potential couple

        int currentEngagedMan; // man currently engaged to considered woman
        int currentEngagedWoman; // woman currently engaged to considered man
        for (man = 0; man < n; man++) {
            for (woman = 0; woman < n; woman++) {
                currentEngagedMan = womenMatching[woman];
                currentEngagedWoman = menMatching[man];
                if (womenRanks[woman][man] < womenRanks[woman][currentEngagedMan]
                        && menRanks[man][woman] < menRanks[man][currentEngagedWoman]) {
                    // man prefers woman over currentEngagedWoman, and
                    // woman prefers man over currentEngagedMan.
                    // The marriage therefore isn't stable.
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Shuffle an array using Collections.shuffle
     *
     * @param array array to be shuffled
     * @param seed  fixed seed, for reproducibility
     */

    private void shuffleArray(int[] array, long seed) {
        List<Integer> list = new ArrayList<>();
        for (int i : array) {
            list.add(i);
        }
        Collections.shuffle(list, new Random(seed));
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
    }
}
