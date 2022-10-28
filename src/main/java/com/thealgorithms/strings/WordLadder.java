package com.thealgorithms.strings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
    **Problem Statement:**
    A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

    Every adjacent pair of words differs by a single letter.
    Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
    sk == endWord
    Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

    **Example 1:**
    Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
    Output: 5 
    Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.

    **Example 2:**
    Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
    Output: 0
    Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.

    **Constraints:**
    1 <= beginWord.length <= 10
    endWord.length == beginWord.length
    1 <= wordList.length <= 5000
    wordList[i].length == beginWord.length
    beginWord, endWord, and wordList[i] consist of lowercase English letters.
    beginWord != endWord
    All the words in wordList are unique.
 */

class WordLadder {

    /**
     * This function finds the ladderLength
     *
     * @param beginWord: Starting word of the ladder
     * @param endWord: Ending word of the ladder
     * @param wordList: This list contains the words which needs to be included
     * in ladder.
     * @return ladderLength: This function will return the ladderLength(level)
     * if the endword is there. Otherwise, will return the length as 0.
     */
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet();
        for (String word : wordList) {
            set.add(word);
        }

        if (!set.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList();
        queue.offer(beginWord);
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                char[] words_chars = curr.toCharArray();
                for (int j = 0; j < words_chars.length; j++) {
                    char original_chars = words_chars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (words_chars[j] == c) {
                            continue;
                        }
                        words_chars[j] = c;
                        String new_word = String.valueOf(words_chars);
                        if (new_word.equals(endWord)) {
                            return level + 1;
                        }
                        if (set.contains(new_word)) {
                            set.remove(new_word);
                            queue.offer(new_word);
                        }
                    }
                    words_chars[j] = original_chars;
                }
            }
            level++;
        }
        return 0;
    }
}
