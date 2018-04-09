import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dungeoun on 10/2/16.
 */



public class Solution {

    public static boolean isAnagram(String s, String t) {


        HashMap<Character, Integer> hash1 = new HashMap<>();

        HashMap<Character, Integer> hash2 = new HashMap<>();

        boolean a = false;


        for (int i = 0; i < s.length(); i++) {

            if (hash1.containsKey(s.charAt(i)) == false) {

                hash1.put(s.charAt(i), 1);

            } else {

                hash1.put(s.charAt(i), hash1.get(s.charAt(i)) + 1);

            }

        }

        for (int i = 0; i < t.length(); i++) {

            if (hash2.containsKey(t.charAt(i)) == false) {

                hash2.put(t.charAt(i), 1);

            } else {

                hash2.put(t.charAt(i), hash2.get(t.charAt(i)) + 1);

            }

        }


        if(hash1.size()!=hash2.size()){
            a = false;
        }

        else if((hash1.size()==0 && hash2.size()==0) ) {
            a = true;
        }
        else if(hash1.size()!=hash2.size()){
            a = false;
        }

        else {
            for (Map.Entry<Character, Integer> entry : hash1.entrySet()) {


                if (hash2.containsKey(entry.getKey()) == true && hash2.get(entry.getKey()) == entry.getValue()) {

                    a = true;

                }
                else {
                    a = false;
                    break;
                }

            }
        }
        return a;

    }

    public static void main( String []args){

        boolean b = isAnagram("a","ab");

        System.out.println(b);

    }
}
