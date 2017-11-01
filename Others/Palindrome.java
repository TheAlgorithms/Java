/**
Class implementing the logic for checking if a string x given is Palindrome or not
A Palindrome String is that if read backwards is the same as if read right to left.
 */
class Palindrome { 

	public Boolean isPalindrome(String num) {
        char d[] = num.toCharArray();
        int i = 0;
        int j = d.length - 1;
        while (i < j) {
            if (d[i] != d[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

}
