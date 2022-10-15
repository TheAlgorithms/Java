/* PROBLEM STATEMENT:-
Given a keypad, and an n digit number, list all words which are possible by pressing these numbers.
Before the advent of QWERTY keyboards, texts and numbers were placed on the same key. 
For example, 2 has “ABC”, so if we wanted to write anything starting with ‘A’ we need to type key 2 once. 
If we wanted to type ‘B’, press key 2 twice and thrice for typing ‘C’. Below is a picture of such a keypad.

Sample Input 1: digits = "23"Sample Output 1: "ad", "ae", "af", "bd", "be", "bf","cd", "ce", "cf"
Sample Output 1: "ad", "ae", "af", "bd", "be", "bf","cd", "ce", "cf"

Sample Input 2: digits = "2"
Sample Output 2: "a", "b", "c"

Sample Input 3: digits = ""
Sample Output 3: ""
*/

// Article :- https://www.interviewbit.com/blog/letter-combinations-of-a-phone-number/

// Contributed by @PranavAvasthi

public class KeypadCombinations {
    final static char[][] L = {{}, {}, {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, 
                                {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'}, 
                                {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};

    public static void letterCombinations(String D) {
        int len = D.length();
        if(len == 0) {
            System.out.println("");
        }
        bfs(0, len, new StringBuilder(), D);
    }

    public static void bfs(int pos, int len, StringBuilder sb, String D) {
        if(pos == len) {
            System.out.println(sb.toString());
        } else {
            char[] letters = L[Character.getNumericValue(D.charAt(pos))];
            for(int i=0; i<letters.length; i++) {
                bfs(pos+1, len, new StringBuilder(sb).append(letters[i]), D);
            }
        }
    }

    public static void main(String[] args) {
        letterCombinations("2");
    }
}
