//this code calculate the sum of digit of factorial value for 30
class FactorialDigitSum{
	public static void main(String[]args){
		int n = 30;
		int y = n;
		while(y>1){
			y--;
			n = n*y;
		}
		System.out.println(n);

		int tot = 0;
		int z = 0;
		while(n>0){
			z = n%10;
			tot += z;
			n = n/10;
		}
		System.out.println(tot);
	}
}