class Solution {
    public int calculate(String s)
    {
        Stack<Integer>a=new Stack<Integer>();
        int i=0,f=1,ans=0;
        int n=s.length();
        long num=0;
        i=0;
        char signprev='+';
        while(i<n)
        {
            if(s.charAt(i)=='(')
            {
                if(i==0)
                {
                    a.push(1);
                }
                else if(s.charAt(i-1)=='-' || signprev=='-')
                {
                    a.push(-1);
                    f=f*-1;
                }
                else if(s.charAt(i-1)=='+' || s.charAt(i-1)=='(' || signprev=='+')
                {
                    a.push(1);
                }
                i++;
            }
            else if(s.charAt(i)==')')
            {
                if(!a.empty())
                {
                    f=f*a.peek();
                    a.pop();
                }
                i++;
            }
            else if(s.charAt(i)=='+')
            {
                if(s.charAt(i+1)=='(')
                {
                    i++;
                    signprev='+';
                    continue;
                }
                i++;
                num=0;
                while(i<n && Character.isDigit(s.charAt(i)))
                {
                    num=10*num-'0'+s.charAt(i);
                    i++;
                }
                ans=ans+f*(int)num;
                signprev='+';
            }
            else if(s.charAt(i)=='-')
            {
                if(s.charAt(i+1)=='(')
                {
                    i++;
                    signprev='-';
                    continue;
                }
                num=0;
                i++;
                while(i<n && Character.isDigit(s.charAt(i)))
                {
                    num=10*num-s.charAt(i)+'0';
                    i++;
                }
                ans=ans+f*(int)num;
                signprev='-';
                
            }
            else if(Character.isDigit(s.charAt(i)))
            {
                num=0;
                while(i<n && Character.isDigit(s.charAt(i)))
                {
                    num=10*num-'0'+s.charAt(i);
                    i++;
                }
                ans=ans+f*(int)num;
            }
            else
            {
                i++;
            }
        }
        return ans;
    }
}
