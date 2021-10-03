package it.philosophers;

import it.philosophers.manager.TableManager;

public class Main {
	
	public static void main(String[] args) {
		
		/*You can put a variable number of philosophers greater than 1*/
		try {
			
			System.out.println("This is what happen when philosophers eat without a single rule...");
			Thread.sleep(2000);
			TableManager manager = new TableManager();
			manager.layTable("Luca", "Elisa", "Giacomo", "Arianna", "Federico");
			//let them starve
			manager.makeThemEat(false);
			Thread.sleep(1000);
			
			System.out.println("...and this is what happen when you put some rules");
			Thread.sleep(2000);
			TableManager manager2 = new TableManager();
			manager2.layTable("Luca", "Elisa", "Giacomo", "Arianna", "Federico");
			//let them eat endlessly
			manager2.makeThemEat(true);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}
	
}
