package DynamicProgramming;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;



/** @author Darshan Pawar [Applying Graphical User Interface to choose one of any Algorithm.]
  * There are three algorithm provided - fibMemo() , fibBotUp() and fibOptimized().
  * Of which 'fibOptimized' is Fastest- Tested along System time...followed by 'fibMemo' and then 'fibBotUp'.
  * After running ,one has to start and Enter one element to be searched.
  *  Click appropriate Algorithm further.
  *  Thank you.
  */
public class Fibonacci implements ActionListener
{
	int n;
	JLabel jl ;
	JFrame jf;
	JRadioButton rb1 , rb2,rb3;
  Fibonacci()
  {
	  jf = new JFrame("Fibonacci Algorithms");
	  jf.setSize(500,400);
	  jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  jf.setLayout(new FlowLayout());
	  jl = new JLabel("SELECT ANY ONE:");
	  jf.setVisible(true);
	  jf.add(jl);
	  rb1 = new JRadioButton("Algorithm1 (fibMemo).");
	  rb1.addActionListener(this);
	  jf.add(rb1);
	  rb2 = new JRadioButton("Algorithm2 (fibBotUp).");
	  rb2.addActionListener(this);
	  jf.add(rb2);
	  rb3 = new JRadioButton("Algorithm3 (fibOptimized).");
	  rb3.addActionListener(this);
	  jf.add(rb3);
	  ButtonGroup bg = new ButtonGroup();
	  bg.add(rb1);
	  bg.add(rb2);
	  bg.add(rb3);
	  JOptionPane.showMessageDialog(jf, "Enter The Element Of Fibonacci Series!");
	  String s = JOptionPane.showInputDialog(jf,"Enter the N'th term").trim();
	  n = Integer.parseInt(s);
  }
  
  @Override
  public void actionPerformed(ActionEvent e)
  {
	
  	int m;
  	if(rb1.isSelected())
  	{
  		  m=fibMemo(n);
  	
  	}
  	else if(rb2.isSelected())
  	{
  		  m=fibBotUp(n);
  	
  	}
  	 else
  	{
  		  m=fibOptimized(n);
    	 
  	}
  		JOptionPane.showMessageDialog(jf, "Element at " + n + " is : " + m);
  }
  
  

    public static void main(String[] args)
  {
     SwingUtilities.invokeLater(new Runnable()
                                  { public void run() 
                                     { 
                                	    new Fibonacci(); 
                                	  } 
                                  }
                               );
   
  }
  /** @author Varun Upadhyay (https://github.com/varunu28) */
  /**
   * This method finds the nth fibonacci number using memoization technique
   *
   * @param n The input n for which we have to determine the fibonacci number Outputs the nth
   *     fibonacci number
   */
  private static Map<Integer, Integer> map = new HashMap<>();
   public static int fibMemo(int n) {
    if (map.containsKey(n)) {
      return map.get(n);
    }

    int f;

    if (n <= 1) {
      f = n;
    } else {
      f = fibMemo(n - 1) + fibMemo(n - 2);
      map.put(n, f);
    }
    return f;
  }

  /**
   * This method finds the nth fibonacci number using bottom up
   *
   * @param n The input n for which we have to determine the fibonacci number Outputs the nth
   *     fibonacci number
   */
  public static int fibBotUp(int n) {

    Map<Integer, Integer> fib = new HashMap<>();

    for (int i = 0; i <= n; i++) {
      int f;
      if (i <= 1) {
        f = i;
      } else {
        f = fib.get(i - 1) + fib.get(i - 2);
      }
      fib.put(i, f);
    }

    return fib.get(n);
  }

  /**
   * This method finds the nth fibonacci number using bottom up
   *
   * @param n The input n for which we have to determine the fibonacci number Outputs the nth
   *     fibonacci number
   *     <p>This is optimized version of Fibonacci Program. Without using Hashmap and recursion. It
   *     saves both memory and time. Space Complexity will be O(1) Time Complexity will be O(n)
   *     <p>Whereas , the above functions will take O(n) Space.
   * @author Shoaib Rayeen (https://github.com/shoaibrayeen)
   */
  public static int fibOptimized(int n) {
    if (n == 0) {
      return 0;
    }
    int prev = 0, res = 1, next;
    for (int i = 2; i <= n; i++) {
      next = prev + res;
      prev = res;
      res = next;
    }
    return res;
  }
}
