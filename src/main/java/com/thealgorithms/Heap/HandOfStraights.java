import java.util.*;

public class HandOfStraights {

    public static boolean isNStraightHand(int[] hand, int groupSize) {
        // Base check
        if (hand.length % groupSize != 0) return false;

        // Step 1: Count frequency of each card
        TreeMap<Integer, Integer> cardCount = new TreeMap<>();
        for (int card : hand) {
            cardCount.put(card, cardCount.getOrDefault(card, 0) + 1);
        }

        // Step 2: Iterate over cards in sorted order
        for (int card : cardCount.keySet()) {
            int freq = cardCount.get(card);
            if (freq > 0) { // If we still have cards to group
                
                for (int next = card; next < card + groupSize; next++) {
                    if (cardCount.getOrDefault(next, 0) < freq) {
                        return false; 
                    }
                    cardCount.put(next, cardCount.get(next) - freq);
                }
            }
        }

        return true; 
    }

    
    public static void main(String[] args) {
        int[] hand1 = {1,2,3,6,2,3,4,7,8};
        int groupSize1 = 3;
        System.out.println("Output 1: " + isNStraightHand(hand1, groupSize1)); // true

        int[] hand2 = {1,2,3,4,5};
        int groupSize2 = 4;
        System.out.println("Output 2: " + isNStraightHand(hand2, groupSize2)); // false
    }
}
