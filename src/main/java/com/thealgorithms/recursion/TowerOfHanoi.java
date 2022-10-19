public class TowerOfHanoi {
    public static void FuntowerOfHanoi(int n, String S, String H, String D){
        if (n==1) {
            System.out.println("transfer disk "+n+" from "+S+" to "+D);
            return;
        }
        FuntowerOfHanoi(n-1, S, D, H);
        System.out.println("transfer disk "+n+" from "+S+" to "+D);
        FuntowerOfHanoi(n-1, H, S, D);
    }

    public static void main(String[] args){
        // just to keep things simple, n is harcoded,
        // but it can be taken as input from the user
        int n=4;
        FuntowerOfHanoi(n,"S","H","D");
    }
}
