// This is the Stable Matching Gale Shapley Algorithm 
// Gale Shapley describes the method of Stable One to One matching
// According to it A man can be engaged to a woman based on their Preferences


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StableMatching {

    private static Map<String, List<String>> preferredRankingMen = new HashMap<>();
    private static Map<String, List<String>> preferredRankingWomen = new HashMap<>();
    private static List<String[]> temporaryEngagement = new ArrayList<>();
    private static List<String> freeMen = new ArrayList<>();

    public static void main(String[] args) {
        initializeData();

        initializeFreeMen();
        stableMatch();
        printFinalMatching();
    }

    private static void initializeData() {
        preferredRankingMen.put("Alice", List.of("Frank", "Eva", "Dave"));
        preferredRankingMen.put("Bob", List.of("Frank", "Dave", "Eva"));
        preferredRankingMen.put("Carol", List.of("Dave", "Eva", "Frank"));

        preferredRankingWomen.put("Dave", List.of("Carol", "Alice", "Bob"));
        preferredRankingWomen.put("Eva", List.of("Alice", "Bob", "Carol"));
        preferredRankingWomen.put("Frank", List.of("Carol", "Alice", "Bob"));
    }

    private static void initializeFreeMen() {
        freeMen.addAll(preferredRankingMen.keySet());
    }

    private static void stableMatch() {
        while (!freeMen.isEmpty()) {
            for (String man : new ArrayList<>(freeMen)) {
                startMatching(man);
            }
        }
    }

    private static void startMatching(String man) {
        for (String woman : preferredRankingMen.get(man)) {
            List<String[]> takenMatches = new ArrayList<>();

            for (String[] couple : temporaryEngagement) {
                if (couple[1].equals(woman)) {
                    takenMatches.add(couple);
                }
            }

            if (takenMatches.isEmpty()) {
                temporaryEngagement.add(new String[] {man, woman});
                freeMen.remove(man);
                break;
            } else {
                int currentGuyIndex = preferredRankingWomen.get(woman).indexOf(takenMatches.get(0)[0]);
                int potentialGuyIndex = preferredRankingWomen.get(woman).indexOf(man);

                if (currentGuyIndex >= potentialGuyIndex) {
                    freeMen.remove(man);
                    freeMen.add(takenMatches.get(0)[0]);
                    takenMatches.get(0)[0] = man;
                    break;
                }
            }
        }
    }

    private static void printFinalMatching() {
        System.out.println("Final Matching:");
        for (String[] couple : temporaryEngagement) {
            System.out.println("[" + couple[0] + ", " + couple[1] + "]");
        }
    }
}
