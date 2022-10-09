public class reverseString {

    public static void main(String[] args) {
        String name = "Manan";
        int n = name.length();
        for(int i=0;i<name.length();i++)
        {
            System.out.print(name.charAt(n-i-1));//charAt()
        }
    }
    
}
