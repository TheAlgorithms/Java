import java.util.*;	
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimpleCalculator extends JFrame implements ActionListener
{
	JFrame jf;
	JTextField screen;
	JTextField num1;
	JButton plus,clear,close;
	JButton minus,div,mul;
	JTextField num2;
	public Calc()
	{
		jf=new JFrame();
		jf.setTitle("Calculator");
		jf.setLayout(null);
		jf.setSize(600,700);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		screen=new JTextField("");
		screen.setBounds(50,100,500,50);
		jf.add(screen);
		JLabel s=new JLabel("result :");
		s.setBounds(50,50,400,70);
		jf.add(s);
		
		JLabel n1=new JLabel("number 1:");
		n1.setBounds(50,150,220,70);
		jf.add(n1);
		num1=new JTextField("");
		num1.setBounds(50,200,220,70);
		jf.add(num1);
		
		JLabel n2=new JLabel("number 2:");
		n2.setBounds(300,150,220,70);
		jf.add(n2);
		num2=new JTextField("");
		num2.setBounds(300,200,220,70);
		jf.add(num2);
		
		plus=new JButton("+");
		plus.setBounds(50,350,50,50);
		plus.setFont(new Font("Arial", Font.PLAIN, 20));
		jf.add(plus);
		
		minus=new JButton("-");
		minus.setBounds(130,350,50,50);
		minus.setFont(new Font("Arial", Font.PLAIN, 20));
		jf.add(minus);
		
		div=new JButton("/");
		div.setBounds(210,350,50,50);
		div.setFont(new Font("Arial", Font.PLAIN, 20));
		jf.add(div);
		
		mul=new JButton("x");
		mul.setBounds(290,350,50,50);
		mul.setFont(new Font("Arial", Font.PLAIN, 20));
		jf.add(mul);
		
		clear=new JButton("clear");
		clear.setBounds(370,350,100,50);
		clear.setFont(new Font("Arial", Font.PLAIN, 20));
		jf.add(clear);
		
		close=new JButton("close");
		close.setBounds(130,500,150,50);
		close.setFont(new Font("Arial", Font.PLAIN, 20));
		jf.add(close);
		
		plus.addActionListener(this);
		minus.addActionListener(this);
		div.addActionListener(this);
		mul.addActionListener(this);
		clear.addActionListener(this);
		close.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==plus)
		{
			int a=Integer.parseInt(num1.getText());
			int b=Integer.parseInt(num2.getText());
			float c =a+b;
			screen.setText(c+"");
		}
		
		else if(e.getSource()==minus)
		{
			int a=Integer.parseInt(num1.getText());
			int b=Integer.parseInt(num2.getText());
			float c =a-b;
			screen.setText(c+"               +");
		}
		
		else if(e.getSource()==div)
		{
			int a=Integer.parseInt(num1.getText());
			int b=Integer.parseInt(num2.getText());
			float c =a/b;
			screen.setText(c+"");
		}
		
		else if(e.getSource()==mul)
		{
			int a=Integer.parseInt(num1.getText());
			int b=Integer.parseInt(num2.getText());
			float c =a*b;
			screen.setText(c+"");
		}
		else if(e.getSource()==clear)
		{
			screen.setText("");
			num1.setText("");
			num2.setText("");
		}
		else if(e.getSource()==close)
		{
			System.exit(0);
		}
	}
		
	
	public static void main(String args[])
	{
		new Calc();
	}
}
