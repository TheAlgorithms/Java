import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class Hanoi extends JFrame {

  public static int ONE_SECOND = 1000;

  int number_of_disks = 0;
  int game_counter = 0;
  int i = 0;

  /*  GUI COMPONENTS */
  public JButton move_button = new JButton();
  public JButton exit_button = new JButton();
  public JButton replay_button = new JButton();
  public JButton auto_button = new JButton();

  /* BACKEND COMPONENTS */
  public ArrayList<String> movements = new ArrayList<String>();
  public StringBuilder stringBuilder = new StringBuilder();

  public ArrayList<Integer> Stack1 = new ArrayList<Integer>();
  public ArrayList<Integer> Stack2 = new ArrayList<Integer>();
  public ArrayList<Integer> Stack3 = new ArrayList<Integer>();

  public void updateStacks() {
    if (game_counter != movements.size()) {
      String temp = movements.get(game_counter);
      System.out.println(temp);
      if (temp.charAt(1) == 'A') {
        if (temp.charAt(2) == 'B') {
          int x = Stack1.get(Stack1.size() - 1);
          Stack1.remove(Stack1.size() - 1);
          Stack2.add(x);
        }
      }
      if (temp.charAt(1) == 'C') {
        if (temp.charAt(2) == 'B') {
          int x = Stack3.get(Stack3.size() - 1);
          Stack3.remove(Stack3.size() - 1);
          Stack2.add(x);
        }
      }

      if (temp.charAt(1) == 'B') {
        if (temp.charAt(2) == 'C') {
          int x = Stack2.get(Stack2.size() - 1);
          Stack2.remove(Stack2.size() - 1);
          Stack3.add(x);
        } else if (temp.charAt(2) == 'A') {
          int x = Stack2.get(Stack2.size() - 1);
          Stack2.remove(Stack2.size() - 1);
          Stack1.add(x);
        }
      }
      revalidate();
      repaint();
      game_counter++;
    }
  }

  public void paint(Graphics canvas) {
    super.paint(canvas);

    // Drawing pedestels
    for (int i = 0; i < 3; i++) {
      canvas.drawRect(30 + i * 230, 670, 200, 20);
      canvas.setColor(new Color(76, 174, 227)); // Blue Accent
      canvas.fillRect(30 + i * 230, 670, 200, 20);

      canvas.fillRect(130 + i * 230 - 2, 670 - 170, 4, 170);
      canvas.setColor(new Color(150, 0, 0)); // Arseny
      canvas.fillRect(130 + i * 230 - 2, 670 - 170, 4, 170);
    }

    // Disks in stack1
    for (int i = 1; i <= Stack1.size(); i++) {
      canvas.drawRect(130 - Stack1.get(i - 1) * 10, 670 - i * 12, Stack1.get(i - 1) * 20, 10);
      canvas.setColor(new Color(64, 26, 0)); // Brown Wolfers
      canvas.fillRect(130 - Stack1.get(i - 1) * 10, 670 - i * 12, Stack1.get(i - 1) * 20, 10);
    }

    // Disks in stack2
    for (int i = 1; i <= Stack2.size(); i++) {
      canvas.drawRect(360 - Stack2.get(i - 1) * 10, 670 - i * 12, Stack2.get(i - 1) * 20, 10);
      canvas.setColor(new Color(64, 26, 0)); // Brown Wolfers
      canvas.fillRect(360 - Stack2.get(i - 1) * 10, 670 - i * 12, Stack2.get(i - 1) * 20, 10);
    }

    // Disks in stack3
    for (int i = 1; i <= Stack3.size(); i++) {
      canvas.drawRect(590 - Stack3.get(i - 1) * 10, 670 - i * 12, Stack3.get(i - 1) * 20, 10);
      canvas.setColor(new Color(64, 26, 0)); // Brown Wolfers
      canvas.fillRect(590 - Stack3.get(i - 1) * 10, 670 - i * 12, Stack3.get(i - 1) * 20, 10);
    }
  }

  // Function to initialize the widget properties and the frame.
  public void initialize() {

    move_button.setIcon(new ImageIcon("../Resources/rsz_move.png"));
    move_button.setBounds(130, 0, 50, 50);

    auto_button.setIcon(new ImageIcon("../Resources/rsz_loop.png"));
    auto_button.setBounds(260, 0, 50, 50);

    replay_button.setIcon(new ImageIcon("../Resources/rsz_replay.jpg"));
    replay_button.setBounds(390, 0, 50, 50);

    exit_button.setIcon(new ImageIcon("../Resources/rsz_exit.png"));
    exit_button.setBounds(520, 0, 50, 50);

    add(move_button);
    add(exit_button);
    add(replay_button);
    add(auto_button);

    setLayout(null);
    setSize(720, 720);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  // Main cnstructor.
  Hanoi() {
    super("restricted tower of hanoi");
    initialize();

    // MOVE BUTTON ACTION LISTENER
    move_button.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            updateStacks();
          }
        });

    // EXIT BUTTON ACTION LISTENER
    exit_button.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            System.exit(0);
          }
        });

    // REPLAY BUTTON ACTION LISTENER
    replay_button.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            startGame();
            repaint();
          }
        });

    // AUTOMATIC PLAY BUTTON ACTION LISTENER
    auto_button.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            timer.start();
            if (game_counter == movements.size()) {
              timer.stop();
            }
          }
        });
  }

  Timer timer =
      new Timer(
          ONE_SECOND,
          new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              updateStacks();
            }
          });

  public void startGame() {

    System.out.println("New Game Started");
    timer.stop();

    Stack1 = new ArrayList<Integer>();
    Stack2 = new ArrayList<Integer>();
    Stack3 = new ArrayList<Integer>();

    movements = new ArrayList<String>();
    game_counter = 0;

    for (int i = 0; i < number_of_disks; i++) {
      Stack1.add(number_of_disks - i);
    }

    towerOfHanoi(number_of_disks, 'A', 'C', 'B');
  }

  public static void main(String args[]) {
    Hanoi tower = new Hanoi();
    int number = Integer.parseInt(args[0]);
    tower.number_of_disks = number;
    tower.startGame();
    /*for(int i=0;i<tower.movements.size();i++) {
        System.out.println(tower.movements.get(i));
        //System.out.println(tower.Stack1.get(i));
    }*/
  }

  // Recursive function to formulate restricted tower of hanoi.
  public void towerOfHanoi(int n, char from_rod, char to_rod, char aux_rod) {
    if (n == 1) {
      stringBuilder.setLength(0);
      stringBuilder.append(Integer.toString(n));
      stringBuilder.append(from_rod);
      stringBuilder.append(aux_rod);
      movements.add(stringBuilder.toString());
      // System.out.println("Move disk 1 from rod " +  from_rod + " to rod " + aux_rod);
      stringBuilder.setLength(0);
      stringBuilder.append(Integer.toString(n));
      stringBuilder.append(aux_rod);
      stringBuilder.append(to_rod);
      movements.add(stringBuilder.toString());
      // System.out.println("Move disk 1 from rod " +  aux_rod + " to rod " + to_rod);
      return;
    }
    towerOfHanoi(n - 1, from_rod, to_rod, aux_rod);
    stringBuilder.setLength(0);
    stringBuilder.append(Integer.toString(n));
    stringBuilder.append(from_rod);
    stringBuilder.append(aux_rod);
    movements.add(stringBuilder.toString());
    // System.out.println("Move disk " + n + " from rod " +  from_rod + " to rod " + aux_rod);
    towerOfHanoi(n - 1, to_rod, from_rod, aux_rod);
    stringBuilder.setLength(0);
    stringBuilder.append(Integer.toString(n));
    stringBuilder.append(aux_rod);
    stringBuilder.append(to_rod);
    movements.add(stringBuilder.toString());
    // System.out.println("Move disk " + n + " from rod " +  aux_rod + " to rod " + to_rod);
    towerOfHanoi(n - 1, from_rod, to_rod, aux_rod);
  }
}
