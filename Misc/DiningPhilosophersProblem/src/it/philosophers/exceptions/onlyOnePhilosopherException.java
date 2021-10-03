package it.philosophers.exceptions;

public class onlyOnePhilosopherException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1371696602871586055L;
	
	public onlyOnePhilosopherException() {
		super("Inserisci almeno 2 filosofi");
	}
	

}
