
import java.util.*;
public class PostEval {
    public static int evaluatePostfix(String str) {
        int ans = 0;
        Stack<Integer> s = new Stack<Integer>();
        char ch[] = str.toCharArray();
        for (int i = 0; i <ch.length; i++) {
            if (ch[i] == ' ')
            {
                continue;
            }
            else if (Character.isDigit(ch[i]))
            {
                ans = 0;
                while (Character.isDigit(ch[i]))
                {

                    ans = ans * 10 + (int)ch[i] - '0';
                    i++;
                }

                s.push(ans);
                i--;
            }
            else
                {
                int first = s.pop();
                int second = s.pop();
                switch (ch[i]) {
                    case '+':
                        s.push(first + second);
                        break;
                    case '-':
                        s.push(second - first);
                        break;
                    case '*':
                        s.push(first * second);
                        break;
                    case '/':
                        s.push(second / first);
                        break;
                }
                //System.out.println(first);
                //System.out.println(second);
            }
        }
//return 1;
        return s.pop();
    }


    public static void main(String[] args)
    {
        String exp = "100 200 + 2 / 5 * 7 +";
        System.out.println(evaluatePostfix(exp));
    }
}
