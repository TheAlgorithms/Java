import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class MyFrame extends JFrame implements ActionListener
{
	JLabel l1,l2,l3;
	JTextField tf1,tf2,tf3;
	JButton b1,b2;
	MyFrame()
	{
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLayout(new GridLayout(4,2));
		l1=new JLabel("No.1");
		l2=new JLabel("No.2");
		l3=new JLabel("Result");
		tf1=new JTextField();
		tf2=new JTextField();
		tf3=new JTextField();
		b1=new JButton("Sum");
		b2=new JButton("Avg");
		b1.addActionListener(this);
		b2.addActionListener(this);
		add(l1);add(tf1);
		add(l2);add(tf2);
		add(l3);add(tf3);
		add(b1);add(b2);
		setSize(400,400);
		setVisible(true);
	}
	public static void main(String args[])
	{
		new MyFrame();
	}
	public void actionPerformed(ActionEvent ae)
	{
		int x=Integer.parseInt(tf1.getText());
		int y=Integer.parseInt(tf2.getText());
		JButton b=(JButton)ae.getSource();
		if(b==b1)
		  tf3.setText(x+y+"");
		else
		  tf3.setText((x+y)/2.0f+"");
	}
}	



