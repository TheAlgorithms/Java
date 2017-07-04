import javax.swing.JOptionPane;
public class Factorial {

	public static long factorial(long n){
          return n > 0 ? n*factorial(n-1) : 1;
	}

	public static long input() {
		long number = 0;
		try { 
			number = Long.parseLong(JOptionPane.showInputDialog("pick a non negative number"));
			if (number < 0) throw new Exception();
			return number;
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
			return input();				
		}
	}

	public static void main(String[] args) {
		
		JOptionPane.showMessageDialog(null, "The number fatorial is " + factorial(input()));
	}
}
