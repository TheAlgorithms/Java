package it.philosophers.model;

public class Fork {
	
	private int number;
	private boolean status;
	
	public Fork(int number, boolean status) {
		this.number = number;
		this.status = status;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

}
