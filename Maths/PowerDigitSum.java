//this code calculate the 2 to the power 30 value and get sum of digit of value
class PowerDigitSum{
	public static void main(String[]args){
		int x = 30;
		double power = Math.pow(2,x);
		int i = (int)power;
		int re = 0;
		int sum = 0;
		while(i!=0){
			re = i%10;
			i = i/10;
			sum += re;
		}
		System.out.println(sum);
	}
}