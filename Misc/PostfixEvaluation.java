import java.util.Scanner;
import java.util.Stack;

/*
This Program will evaluate the value of given Postfix Expression
 */

public class PostfixEvaluation {
    public static void main(String[] args) {
        // Inserting Postfix Expression
        System.out.println("Enter Postfix Expression");
        Scanner s=new Scanner(System.in);
        String exp=s.next();
        int answer=PostEval(exp);
        System.out.println("The Value of expression is : "+answer);
    }

    //Function to Evaluate Postfix Expression

    private static int PostEval(String exp) {
        Stack<Integer> helperStack=new Stack<>();
        int i=0;
        while(i<exp.length())
        {
            if(exp.charAt(i)=='*')
            {
               Integer  A=helperStack.pop();
               Integer  B=helperStack.pop();
               Integer  C=B*A;
               helperStack.push(C);
            }
            else if(exp.charAt(i)=='/')
            {
                Integer  A=helperStack.pop();
                Integer  B=helperStack.pop();
                Integer  C=B/A;
                helperStack.push(C);
            }
            else if(exp.charAt(i)=='^')
            {
                Integer  A=helperStack.pop();
                Integer  B=helperStack.pop();
                Integer  C=(int) Math.pow((double) B,(double)A);
                helperStack.push(C);
            }
            else if(exp.charAt(i)=='+')
            {
                Integer  A=helperStack.pop();
                Integer  B=helperStack.pop();
                Integer  C=B+A;
                helperStack.push(C);
            }
            else if(exp.charAt(i)=='-')
            {
                Integer  A=helperStack.pop();
                Integer  B=helperStack.pop();
                Integer  C=B-A;
                helperStack.push(C);
            }
            else
            {
                helperStack.push(Integer.parseInt(""+exp.charAt(i)));
            }
            i++;
        }
        int ans=helperStack.pop();
        return ans;

    }
}
