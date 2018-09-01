
public class Prob1 {
    public static void main(String[] args) {
        int i;
        int sum = 0;
        
        for(i=0; i<1000; i++){
            if(i%3 == 0 || i%5 == 0){
                sum += i;
            }
        }
        
        System.out.println("Sum: " + sum);
    }
}