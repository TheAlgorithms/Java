import java.util.*;

public class BooleanParenthesis
{
	static int t[][][];

	static void  initializeDP(int n)
	{
		t = new int [n+1][n+1][2];

		for (int i=0; i<n+1; i++)
		{
			for (int j=0; j<n+1; j++)
			{
				for (int k=0; k<2; k++)
				{
					t[i][j][k] = -1;
				}
			}
		}
	}

	static int solve(String s, int i, int j, boolean isTrue)
	{
		if(i>j)
			return 0;

		if(i==j)
		{
			if(isTrue == true)
				return (s.charAt(i) == 'T') ? 1:0;
			else
				return (s.charAt(i) == 'F') ? 1:0;
		}

		if(t[i][j][isTrue ? 1:0] != -1)
			return t[i][j][isTrue ? 1:0];

		int ans = 0, lt, lf, rt, rf;

		for (int k=i+1; k<=j-1; k++) 
		{
			if(t[i][k-1][1] != -1)
				lt = t[i][k-1][1];
			else
				lt = solve(s,i,k-1,true);

			if(t[i][k-1][0] != -1)
				lf = t[i][k-1][0];
			else
				lf = solve(s,i,k-1,false);

			if(t[k+1][j][1] != -1)
				rt = t[k+1][j][1];
			else
				rt = solve(s,k+1,j,true);

			if(t[k+1][j][0] != -1)
				rf = t[k+1][j][0];
			else
				rf = solve(s,k+1,j,false);

			char ch = s.charAt(k);

			if(ch == '&')
			{
				if(isTrue == true)
					ans =  ans + lt*rt;
				else
					ans = ans + lt*rf + rt*lf + lf*rf;
			}

			else if(ch == '|')
			{
				if(isTrue == true)
					ans = ans + lf*rt + lt*rf + lt*rt;
				else
					ans = ans + lf*rf;
			}

			else if(ch == '^')
			{
				if(isTrue == true)
					ans = ans + lt*rf + lf*rt;
				else
					ans = ans + lf*rf + lt*rt;
			}
		}
		return t[i][j][isTrue ? 1:0] = ans;
	}

	public static void main(String[] args) 
	{
		String s;
		Scanner in = new Scanner(System.in);
		s = in.next();
		initializeDP(s.length()-1);
		System.out.println(solve(s,0,s.length()-1,true));
	}
}
