import java.util.Scanner;


class CS2{
	static void bin(int a, float c){
		int []b= new int[40];
		int count=0, dec=0;;
		while(a!=0){
			int r= a%2;
			a=a/2;
			b[20-count]=r;
			count++;
		}
		while((count)!=-1){
			System.out.print(b[20-count]);
			count--;
		}
		System.out.print("."); 
		while(c!=0){
			int r= (int)(c*2);
			c=c*2;
			b[21+dec]=r;
			if(r>=1){
				c=c-1;
			}
			dec++;
		}
		while((dec-1)!=-1){
			System.out.print(b[20+dec]);
			dec--;
		}

			
}

	public static void main(String []args){
		Scanner in =new Scanner(System.in);
		System.out.print("Enter a number: ");		
		float a= in.nextFloat();
		int b =(int)a;
		System.out.println(b);
		bin(b, a-b);
}
}
			
