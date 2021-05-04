import java.util.*;

class Problem139_wordBreak{

    public static void main(String arr[]) {
        Scanner sc = new Scanner(System.in);
        String value = sc.nextLine();
        ArrayList list = new ArrayList<String>();
        list.add("apple");
        list.add("pen");
       // list.add("Le");

        if (wordBreak(value, list))
            System.out.println("Valid Word");
        else
            System.out.println("Invalid");

    }

    public static boolean wordBreak(String s, List<String> wordDict) {

        boolean dp[] = new boolean[s.length() + 1];
        dp[0] = true;

        Set<String> set = new HashSet<>();
        for (String word : wordDict)
            set.add(word);

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}