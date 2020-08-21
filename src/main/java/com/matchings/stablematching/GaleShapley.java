package com.matchings.stablematching;

public class GaleShapley {

    /**
     * Return a stable matching between men and women according to their preferences,
     * following the Gale-Shapley algorithm.
     *
     * @param menPrefs   for each man, for each preference rank, the corresponding woman
     * @param womenPrefs for each woman, for each preference rank, the corresponding man
     * @return for each man, the associated woman (1-dimensional array)
     */

    public int[] GaleShapleyStableMarriage(int[][] menPrefs, int[][] womenPrefs) {
        assert menPrefs.length == womenPrefs.length; // there are n individuals in each group

        int n = menPrefs.length;
        if (n == 0)
            return new int[0]; // handle empty initial conditions right away

        // Some implementation details: men, women and preference ranks are all labeled
        // from 0 to n-1.

        // menMatching: for each man, the woman to whom he is engaged (or -1 if not engaged):
        int[] menMatching = new int[n];
        // the same for women:
        int[] womenMatching = new int[n];

        // asked: for each man, highest rank asked (between 0 and n-1;
        // -1 if hasn't asked anyone yet)
        int[] asked = new int[n];

        // Initialize all values of menMatching and womenMatching to -1,
        // otherwise woman 0 will be considered engaged to all men and idem for man 0.
        // Do the same for asked, otherwise each man will be considered as having
        // already asked his first choice.
        for (int i = 0; i < n; i++) {
            menMatching[i] = -1;
            womenMatching[i] = -1;
            asked[i] = -1;
        }

        // to quickly retrieve the rank of men for a given woman, we create womenRanks.
        // For each woman, the array is:
        // index: man; value: rank
        // whereas in womenPrefs it was index: rank; value: man
        // Retrieving a rank will be done be simply looking up womenRanks[woman][man]
        int[][] womenRanks = new int[n][n];
        int man;
        for (int w = 0; w < n; w++) {
            for (int rank = 0; rank < n; rank++) {
                man = womenPrefs[w][rank];
                womenRanks[w][man] = rank;
            }
        }

        int unengaged = 0; // at first all men are unengaged, we take the first one
        int notAsked; // first rank not asked by unengaged
        int prefWoman; // for the considered man, preferred woman among not asked ones
        int currentManPartner; // for the considered woman, current partner (-1 if none)
        while (unengaged != -1) { // while there is an unengaged man
            // unengaged is our proposing man.
            notAsked = asked[unengaged] + 1;
            prefWoman = menPrefs[unengaged][notAsked];
            currentManPartner = womenMatching[prefWoman];
            // now unengaged asks prefWoman for engagement.
            asked[unengaged] += 1;
            if (currentManPartner == -1) { // prefWoman is not engaged: the two engage
                menMatching[unengaged] = prefWoman;
                womenMatching[prefWoman] = unengaged;
                unengaged = getUnengaged(menMatching); // we need a new unengaged
            } else { // prefWoman is engaged to currentManPartner (therefore >= 0)
                if (womenRanks[prefWoman][unengaged] < womenRanks[prefWoman][currentManPartner]) {
                    // prefWoman prefers unengaged: split prefWoman and currentManPartner
                    menMatching[currentManPartner] = -1;
                    // and engage prefWoman and unengaged
                    menMatching[unengaged] = prefWoman;
                    womenMatching[prefWoman] = unengaged;
                    unengaged = getUnengaged(menMatching); // we need a new unengaged
                }
                // If prefWoman prefers currentManPartner over unengaged, nothing happens
                // (except that asked[unengaged] has been incremented so unengaged won't ask
                // prefWoman for engagement anymore).
            }
        }
        return menMatching;
    }

    /**
     * Get a currently unengaged man, if there is one
     *
     * @param menMatching the current men matching array (being constructed)
     * @return the first man that is not engaged, or -1 if all men are engaged
     */

    public int getUnengaged(int[] menMatching) {
        for (int m = 0; m < menMatching.length; m++) {
            if (menMatching[m] == -1)
                return m;
        }
        return -1;
    }
}
