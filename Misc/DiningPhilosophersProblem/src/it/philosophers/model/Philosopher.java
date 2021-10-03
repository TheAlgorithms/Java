package it.philosophers.model;

import java.util.List;

public class Philosopher {
	
	private String name;
	private Status status = Status.THINK;
	private int position;
	private boolean rightHand = false;
	private boolean leftHand = false;
	private int hungerProgress = 10;
	
	public Philosopher(int position, String name) {
		this.name = name;
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public boolean isRightHand() {
		return rightHand;
	}

	public void setRightHand(boolean rightHand) {
		this.rightHand = rightHand;
	}

	public boolean isLeftHand() {
		return leftHand;
	}

	public void setLeftHand(boolean leftHand) {
		this.leftHand = leftHand;
	}

	public int getPosition() {
		return position;
	}

	public int getHungerProgress() {
		return hungerProgress;
	}
	
	public void fillHungerProgress() {
		hungerProgress = 2;
	}

	public void decreaseEatingProgress() {
		hungerProgress--;
	}



}
