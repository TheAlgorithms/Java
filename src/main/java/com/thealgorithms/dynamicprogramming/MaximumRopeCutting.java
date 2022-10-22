import java.util.Scanner;
class RopeCutting 
{
	static int maxCuts(int n, int a, int b, int c)
	{
		if(n == 0) return 0;
		if(n < 0)  return -1;
		int res = Math.max(maxCuts(n-a, a, b, c), 
		          Math.max(maxCuts(n-b, a, b, c), 
		          maxCuts(n-c, a, b, c)));
		if(res == -1)
			return -1;
		return res + 1; 
	}
    public static void main(String [] args) 
    {
	Scanner i = new Scanner(System.in);  // Create a Scanner object
    	System.out.println("Enter for n");
	int n = i.nextInt();
	Scanner j = new Scanner(System.in);  // Create a Scanner object
    	System.out.println("Enter for a");
	int a = j.nextInt();
    	Scanner k = new Scanner(System.in);  // Create a Scanner object
    	System.out.println("Enter for b");
	int b = k.nextInt();
	Scanner l = new Scanner(System.in);  // Create a Scanner object
    	System.out.println("Enter for c");
	int c = l.nextInt();
    	System.out.println(maxCuts(n, a, b, c));
    }
}
