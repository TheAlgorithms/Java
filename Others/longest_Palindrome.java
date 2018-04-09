import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dungeoun on 10/2/16.
 */
public class Solution {

    public static int longestPalindrome(String s) {


        HashMap<Character, Integer> hash1 = new HashMap<>();

        int length =0;


        for(int i = 0; i<s.length(); i++){

            if(hash1.containsKey(s.charAt(i))==false){

                hash1.put(s.charAt(i), 1);

            }

            else{

                hash1.put(s.charAt(i), hash1.get(s.charAt(i))+1);

            }
        }

        for(Map.Entry<Character,Integer> entry: hash1.entrySet()){

            if(entry.getValue()%2 == 0){

                length = length + entry.getValue();

            }
            else if(entry.getValue()%2 != 0){

                length = length + entry.getValue()-1;

            }


        }

        if(s.length()>length) {

            return length + 1;
        }
        else return length;

    }


    public static void main(String []args){

        int l = longestPalindrome("bb");

        System.out.println(l);

    }
}
