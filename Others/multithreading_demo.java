import java.util.*;

class GenRandThread extends Thread{
	static int num;
	GenRandThread(){
		super("GenRandThread");
		start();
	}
	public void run(){
		Random r = new Random();
		for(int i = 0;i<10;i++)
		{
			num = r.nextInt(50);
			System.out.println("\nThe Thread is : " + Thread.currentThread().getName() + " and the number Generated is : " + num);
			try{
				sleep(1000);
			}catch(InterruptedException e){}
		}
	}
}

class SquareThread extends Thread{
	SquareThread(){
		super("SqaureThread");
		start();
	}
	public void run(){
		for(int i = 0;i<10;i++)
		{
			System.out.println("The Thread is : " + Thread.currentThread().getName() + " and the Square of the number is : " +  Math.pow(GenRandThread.num,2));
                	try{
				sleep(1000);
                   	}catch(InterruptedException e){}
		}
	}
}

class CubeThread implements Runnable{
	CubeThread(){
		new Thread(this,"CubeThread").start();
	}
	public void run(){
		for(int i = 0;i<10;i++)
		{
			System.out.println("The Thread is : " + Thread.currentThread().getName() + " and the Cube of the number is : " + Math.pow(GenRandThread.num,3));
                	try{
				Thread.sleep(1000);
                   	}catch(InterruptedException e){}
		}
	}
}
class Lab3b{
	public static void main(String[] args){
		GenRandThread t1 = new GenRandThread();
		SquareThread t2 = new SquareThread();
		CubeThread t3 = new CubeThread();
	}
}
