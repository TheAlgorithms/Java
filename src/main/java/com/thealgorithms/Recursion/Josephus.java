public class Josephus {
    public static int remaining(int n, int k){
        if(n==1){
            return 0;
        }
        return (remaining(n-1,k)+k)%n;
    }
    public static void main(String []args){
        System.out.println(remaining(4,3));
    }
}
