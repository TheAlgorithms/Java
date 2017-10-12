//Oskar Enmalm 29/9/17
//An Abecadrian is a word where each letter is in alphabetical order
    
class Abecedarian{

    public static boolean isAbecedarian(String s) {
        int index = s.length() - 1;

        for (int i = 0; i < index; i++) {

            if (s.charAt(i) <= s.charAt(i + 1)) {
                continue;
            }
            return false;
        }
        return true;
    }
}
