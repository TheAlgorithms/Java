package com.sorts;

/**
 * 
 * A Java implementation of multikey quicksort, translated from the
 * original C implementation by J. Bentley and R. Sedgewick, from
 * their "Fast algorithms for sorting and searching strings" paper
 * published in 1997.
 *
 * @author Nathan Fiedler
 */
public class MultiKeyQuickSort {

    /** As with GCC std::sort delegate to insertion sort for ranges of
     * size below 16. */
    private static final int THRESHOLD = 16;

    /**
     * Creates a new instance of MultikeyQuicksort.
     */
    private MultiKeyQuickSort() {
    }

    /**
     * Retrieve the character in string s at offset d. If d is greater
     * than or equal to the length of the string, return zero. This
     * simulates fixed-length strings that are zero-padded.
     *
     * @param  s  string.
     * @param  d  offset.
     * @return  character in s at d, or zero.
     */
    private static char charAt(CharSequence s, int d) {
        return d < s.length() ? s.charAt(d) : 0;
    }

    /**
     * Swap the elements between to subarrays.
     *
     * @param  a  the array of elements.
     * @param  i  offset of first subarray.
     * @param  j  offset of second subarray.
     * @param  n  number of elements to swap.
     */
    private static void vecswap(Object[] a, int i, int j, int n) {
        while (n-- > 0) {
            Object t = a[i];
            a[i] = a[j];
            a[j] = t;
            i++;
            j++;
        }
    }

    /**
     * Sorts the array of strings using a multikey quicksort that chooses
     * a pivot point using a "median of three" rule (or pseudo median of
     * nine for arrays over a certain threshold). For very small subarrays,
     * an insertion sort is used.
     *
     * @param  strings  array of strings to be sorted.
     */
    public static void sort(CharSequence[] strings) {
        if (strings != null && strings.length > 1) {
            ssort(strings, 0, strings.length, 0);
        }
    }

    /**
     * Sorts the array of strings using a multikey quicksort that chooses
     * a pivot point using a "median of three" rule (or pseudo median of
     * nine for arrays over a certain threshold). For very small subarrays,
     * an insertion sort is used.
     * 
     * <p>Only characters in the strings starting from the given offset
     * <em>depth</em> are considered. That is, the method will ignore all
     * characters appearing before the <em>depth</em> character.</p>
     *
     * @param  strings  array of strings to sort.
     * @param  low      low offset into the array (inclusive).
     * @param  high     high offset into the array (exclusive).
     * @param  depth    offset of first character in each string to compare.
     */
    public static void sort(CharSequence[] strings, int low, int high, int depth) {
        if (strings != null && strings.length > 1 && low >= 0 && low < high && depth >= 0) {
            ssort(strings, low, high - low, depth);
        }
    }

    /**
     * Find the median of three characters, found in the given strings
     * at character position <em>depth</em>. One of the three integer
     * values will be returned based on the comparisons.
     *
     * @param  a      array of strings.
     * @param  l      low index.
     * @param  m      middle index.
     * @param  h      high index.
     * @param  depth  character offset.
     * @return  the position of the median string.
     */
    private static int med3(CharSequence[] a, int l, int m, int h, int depth) {
        char va = charAt(a[l], depth);
        char vb = charAt(a[m], depth);
        if (va == vb) {
            return l;
        }
        char vc = charAt(a[h], depth);
        if (vc == va || vc == vb) {
            return h;
        }
        return va < vb ? (vb < vc ? m : (va < vc ? h : l))
                : (vb > vc ? m : (va < vc ? l : h));
    }


    public static void insertionSort(CharSequence[] strings, int low, int high, int depth) {
        if (strings == null || low < 0 || high <= low || depth < 0) {
            return;
        }
        for (int i = low + 1; i < high; i++) {
            for (int j = i; j > low; j--) {
                int idx = depth;
                char s = idx < strings[j - 1].length() ? strings[j - 1].charAt(idx) : 0;
                char t = idx < strings[j].length() ? strings[j].charAt(idx) : 0;
                while (s == t && idx < strings[j - 1].length()) {
                    idx++;
                    s = idx < strings[j - 1].length() ? strings[j - 1].charAt(idx) : 0;
                    t = idx < strings[j].length() ? strings[j].charAt(idx) : 0;
                }
                if (s <= t) {
                    break;
                }
                CharSequence tmp = strings[j];
                strings[j] = strings[j - 1];
                strings[j - 1] = tmp;
            }
        }
    }

    /**
     * The recursive portion of multikey quicksort.
     *
     * @param  strings  the array of strings to sort.
     * @param  base     zero-based offset into array to be considered.
     * @param  length   length of subarray to consider.
     * @param  depth    the zero-based offset into the strings.
     */
    private static void ssort(CharSequence[] a, int base, int n, int depth) {
        if (n < THRESHOLD) {
            insertionSort(a, base, base + n, depth);
            return;
        }
        int pl = base;
        int pm = base + n / 2;
        int pn = base + n - 1;
        int r;
        if (n > 30) {
            // On larger arrays, find a pseudo median of nine elements.
            int d = n / 8;
            pl = med3(a, base, base + d, base + 2 * d, depth);
            pm = med3(a, base + n / 2 - d, pm, base + n / 2 + d, depth);
            pn = med3(a, base + n - 1 - 2 * d, base + n - 1 - d, pn, depth);
        }
        pm = med3(a, pl, pm, pn, depth);
        CharSequence t = a[base];
        a[base] = a[pm];
        a[pm] = t;
        int v = charAt(a[base], depth);
        boolean allzeros = v == 0;
        int le = base + 1, lt = le;
        int gt = base + n - 1, ge = gt;
        while (true) {
            for (; lt <= gt && (r = charAt(a[lt], depth) - v) <= 0; lt++) {
                if (r == 0) {
                    t = a[le];
                    a[le] = a[lt];
                    a[lt] = t;
                    le++;
                } else {
                    allzeros = false;
                }
            }
            for (; lt <= gt && (r = charAt(a[gt], depth) - v) >= 0; gt--) {
                if (r == 0) {
                    t = a[gt];
                    a[gt] = a[ge];
                    a[ge] = t;
                    ge--;
                } else {
                    allzeros = false;
                }
            }
            if (lt > gt) {
                break;
            }
            t = a[lt];
            a[lt] = a[gt];
            a[gt] = t;
            lt++;
            gt--;
        }
        pn = base + n;
        r = Math.min(le - base, lt - le);
        vecswap(a, base, lt - r, r);
        r = Math.min(ge - gt, pn - ge - 1);
        vecswap(a, lt, pn - r, r);
        if ((r = lt - le) > 1) {
            ssort(a, base, r, depth);
        }
        if (!allzeros) {
            // Only descend if there was at least one string that was
            // of equal or greater length than current depth.
            ssort(a, base + r, le + n - ge - 1, depth + 1);
        }
        if ((r = ge - gt) > 1) {
            ssort(a, base + n - r, r, depth);
        }
    }
}
