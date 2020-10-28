import java.io.IOException;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter time = ");
        String time = in.nextLine();
        System.out.println();

        String listTime[] = time.split(":");

        String hour = listTime[0];
        String min = listTime[1];
        String seconds = listTime[2].substring(0,2);
        String caser = listTime[2].substring(2,4);

        if(caser.equals("AM")){
            if(hour.equals("12"))
                hour="00";

            System.out.println(hour+":"+min+":"+seconds);
        }else{
            if(!hour.equals("12")){
                int h = Integer.parseInt(hour);
                h = h +12;
                hour =""+h;
            }
            System.out.println(hour+":"+min+":"+seconds);
        }
    }
}
