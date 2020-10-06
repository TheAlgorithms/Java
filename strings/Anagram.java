public class Anagram {

	public static void main(String[] args) {
		String a="Hello";
		String b="hEllo";
		 boolean ana=true;
	      int[] u=new int[256];
	      int[] v=new int[256];
	      a=a.toLowerCase();
	      b=b.toLowerCase();
	
	      for(char c : a.toCharArray())
	      {
	          int i=(int)c;
	          u[i]++;
	      }
	       for(char c : b.toCharArray())
	      {
	          int i=(int)c;
	          v[i]++;
	      }
	      for(int j=0;j<256;j++)
	      {
	       if(u[j]!=v[j])
	       {
	           ana=false;
	           break;
	       }
	      }
	      System.out.println(ana);
	}
}
