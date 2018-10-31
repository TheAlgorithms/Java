import java.awt.*;
import java.awt.event.*;
class MyFrame extends Frame implements ActionListener
{
	TextField tf1,tf2;
	Button b1;
	MyFrame()
	{
		setLayout(new GridLayout(3,1));
		tf1=new TextField();
		tf2=new TextField();
		b1=new Button("Swap");
		b1.addActionListener(this);
		add(tf1);add(tf2);
		add(b1);
		setSize(400,400);
		setVisible(true);
	}
	public static void main(String args[])
	{
		new MyFrame();
	}
	public void actionPerformed(ActionEvent ae)
	{
		String t=tf1.getText();
		tf1.setText(tf2.getText());
		tf2.setText(t);
	}	
}	



