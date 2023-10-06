import java.util.Arrays;
class Anagram {

    public static void main(String[] args) {
        String first="sooraj";
        String second="oroajs";
        Anagram obj=new Anagram();
        boolean flag=obj.isAnagram(first,second);
        if(flag)
            System.out.println("The strings are anagram");
        else
            System.out.println("Strings are not anagram");
    }
    public boolean isAnagram(String s, String t) {
        char first[]=s.toCharArray();
        char sec[]=t.toCharArray();
        Arrays.sort(first);
        Arrays.sort(sec);
        return(Arrays.equals(first,sec));
    }
}