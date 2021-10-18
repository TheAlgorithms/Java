package Strings;

public class convertUpperToLower
{
   public static void main(String[] args) {
      assert convert("tHis IS a TesTCaSe");
      assert isAnagrams("The Fox Jumped Over tHE FeNCE");
   }
   
  public static String convert(String s)
  {
      for(int i=0; i<=s.length(); i++)
      {
          String res = "";
          char x = s.charAt(i);
          if(x>=65 && x<=90) res+=x+32;
          else if(x>=99 && x<=122) res+=x-32;
      }
      return res;
  }
}
