// To check if two strings are anagrams of each other (consider all the letters in both the strings in lowercase)

public class Main {
    public static boolean isAnagram(String s, String t) {
        int[] arr = new int[26];
        if (s.length() != t.length()) {
            return false;
        }
        
        for(int i = 0; i < s.length(); i++){
            arr[s.charAt(i) - 'a']++;
            arr[t.charAt(i) - 'a']--;
        }
        
        for(int i : arr){
            if(i != 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        String str1 = "silent";
        String str2 = "listen";
        System.out.println(isAnagram(str1, str2));
    }
}

//Output
//true