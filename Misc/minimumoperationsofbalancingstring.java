package Misc;
import java.util.*;
//this code is used to calculate minimum number of operations required to convert a string to a balanced string
/*Let's call a string balanced if all characters that occur in this string occur in it the same number of times.
You are given a string S this string may only contain uppercase English letters. 
You may perform the following operation any number of times (including zero): choose one letter in S and replace it by another uppercase English letter. 
Note that even if the replaced letter occurs in S multiple times, only the chosen occurrence of this letter is replaced*/
public class minimumoperationsofbalancingstring {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int m, count = 0, ans = 0, ans2 = 0, j, l, m1 = 0, n, o;
		ArrayList<Integer> list = new ArrayList<>();
		int a = s.nextInt();
		for (int i = 0; i < a; i++) {
			n = Integer.MAX_VALUE;
			String str = s.next();
			// System.out.println(str.length());
			int arr[] = new int[26];
			count = 0;
			for (j = 0; j < str.length(); j++)
				arr[str.charAt(j) - 65]++;
			Arrays.sort(arr);
			int t;
			for (j = 0; j < arr.length / 2; j++) {
				t = arr[j];
				arr[j] = arr[arr.length - j - 1];
				arr[arr.length - j - 1] = t;
			}
			for (j = 0; j < 26; j++)
				if (arr[j] != 0)
					count++;
			for (int k = 26; k > 0; k--) {
				ans = 0;
				ans2 = 0;
				if ((str.length()) % k == 0) {
					m = (str.length()) / k;
					//System.out.println(k + " " + m);
					if (k < count) {
						for (j = 0; j < 26; j++)
							if (arr[j] >= m)
								ans2 += arr[j] - m;
							else
								break;
						l = j + 1;
						for (j = l - 1; j < k; j++)
							ans += Math.abs(arr[j] - m);
						// System.out.println(ans+" "+ans2+"aaaaaaaaaaa");
						ans = ans - ans2;
						ans += ans2;

					} else {
						for (j = 0; j < 26; j++) {
							if (arr[j] > m)
								ans += (arr[j] - m);
						}
					}
					if (ans < n)
						n = ans;
				}
			}
			list.add(n);
		}
		for (int i = 0; i < list.size(); i++)
			System.out.println(list.get(i));
	}

}
