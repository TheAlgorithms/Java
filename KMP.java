import org.junit.Test;
import static org.junit.Assert.*;
public class KMP{
   @test
   public static void main(String[] args) {
        final String haystack = "";       
        final String needle = "";                
        KMPmatcher(haystack, needle);
    }

   @test
   public static void main(String[] args) {
        final String haystack = "AAAB";       
        final String needle = "AAAB";                
        KMPmatcher(haystack, needle);

    }
   @test
   public static void main(String[] args) {
        final String haystack = "AABB";       
        final String needle = "AAB";                
        KMPmatcher(haystack, needle);

    }
   @test
   public static void main(String[] args) {
        final String haystack = "ABCDEABC";       
        final String needle = "ABC";                
        KMPmatcher(haystack, needle);

   }
   @test
   public static void main(String[] args) {
        final String haystack = "AB";       
        final String needle = "AABC";                
        KMPmatcher(haystack, needle);
    }

} 
