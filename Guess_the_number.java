import java.util.scanner;
import java.util.Random;
class game{
  int no_of_guess =0 ;
  int userinput ;
  int compinput;
  
  public int getno_of_guess(){
    return this.no_of_guess;
  }
  public void setno_of_guess(int no_of_guess){
    this.no_of_guess = no_of_guess ;
  }
  game (){
Random random = new Random();
    this.compinput = random.nextInt(100);
  }
  void user(){
Scanner sc = new Scanner(System.in);
    System.out.println("Enter your guessed number ");
    userinput = sc.nextInt();
  }
  boolean isnocorrect(){
    no_of_guess++;
if (userinput == compinput){
  System.out.println("Woohoo! you guessed it right ");
}
    else if (userinput >compinput){
      System.out.println("No. os big,go for a smaller one ");
    }
    else(userinput<compinput){
      System.out.println("No. is small go for a big one ");
      }
			return false ;
			
		}
		}
		public class Java {

			public static void main(String[] args) {
		
		
		
		game g = new game();
		boolean b = false;
		while(!b) {
			g.user();
		
			 b =g.isnocorrect();
		System.out.println(b);
			}
