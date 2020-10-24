import java.util.Scanner;
public class MulAddSubThreads{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in); 
		System.out.println("Enter the First Operand: ");
		int n1 = sc.nextInt();
		System.out.println("Enter the Second Operand: ");
		int n2 = sc.nextInt();
		Add t2 = new Add(n1, n2);
        Mul t1 = new Mul(n1, n2);
        Sub t3 = new Sub(n1, n2);
		try {
            t1.start(); t1.join();
            t2.start(); t2.join();
            t3.start(); t3.join();
		} 
		catch (Exception e) {
		    e.printStackTrace();
		}
		finally{
			sc.close();
		}
	}
}
 
class Mul extends Thread {
	int value, a, b;
	Mul(int p, int q){
		this.a = p;
		this.b = q;
	}
	public void run() {
        value =  a * b;
        System.out.println ("Multiplication: " +value); 
	}
}

class Add extends Thread {
	int value, a, b;
	Add(int p, int q){
		this.a = p;
		this.b = q;
	}
	public void run() {
        value = a + b;
        System.out.println ("Addition: " +value); 
	}
}

class Sub extends Thread {
	int value, a, b;
	Sub(int p, int q){
		this.a = p;
		this.b = q;
	}
	public void run() {
        value = a - b;
        System.out.println ("Subtraction: " +value); 
	}
}


// https://en.wikipedia.org/wiki/Thread_(computing)
