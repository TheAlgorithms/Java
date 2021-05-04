
import java.util.Scanner;
import java.util.Stack;

class Problem_394_DecodeString{

    public static void main(String arr[])
    {
        Scanner sc = new Scanner(System.in);
        String value = sc.nextLine();

        System.out.println(decode(value));
    }

    public static String decode(String s) {

        Stack<Integer> numStack = new Stack<>();
        Stack<String> strStrack = new Stack<>();

        StringBuilder sb = new StringBuilder();

        int n = s.length();

        for(int i=0;i<n;i++)
        {
            char ch = s.charAt(i);
            if(Character.isDigit(ch))
            {
                int num = ch-'0';
                while (i<n && Character.isDigit(s.charAt(i+1)))
                {
                    num = num*10 + s.charAt(i+1)-'0';
                    i++;
                }
                numStack.push(num);
            }
            else if(s.charAt(i)=='[')
            {
                strStrack.push(sb.toString());
                sb = new StringBuilder();
            }
            else if(s.charAt(i)==']')
            {
                StringBuilder temp = new StringBuilder(strStrack.pop());
                int rep = numStack.pop();
                for(int j =0;j<rep;j++)
                    temp.append(sb);
                sb= temp;

            }else{
                sb.append(ch);
            }
        }
        return sb.toString();

    }
}