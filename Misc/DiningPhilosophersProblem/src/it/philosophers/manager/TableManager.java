package it.philosophers.manager;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import it.philosophers.exceptions.onlyOnePhilosopherException;
import it.philosophers.model.Fork;
import it.philosophers.model.Philosopher;
import it.philosophers.model.Status;

public class TableManager {
	
	List<Philosopher> philosophers = new LinkedList<>();
	List<Fork> forks = new LinkedList<>();
	
	public void layTable(String... names) throws onlyOnePhilosopherException {
		
		if(names.length == 1)
			throw new onlyOnePhilosopherException();
		for(int i = 0; i < names.length; i++) {
			philosophers.add(new Philosopher(i, names[i]));
			forks.add(new Fork(i, true));
		}

	}
	
	public void makeThemEat(boolean deadlockProtected) {
		
		Random rand = new Random();
		int upperBound = 2;
		int position, rightForkPos, leftForkPos;
		boolean deadlock = false;
		while(!deadlock) {
			
			for(Philosopher phil: philosophers) {
				position = phil.getPosition();
				
				rightForkPos = (position+1 > philosophers.size() - 1 ? 0 : position+1);
				leftForkPos =  position;
				
				if(phil.getStatus() == Status.THINK) {
					switch(rand.nextInt(upperBound)) {
					case 0:
						think(phil);
						break;
					case 1:
						tryToEat(phil, position, rightForkPos, leftForkPos, deadlockProtected);
						break;
					}
					
				}
				else if(phil.getStatus() == Status.EAT) {
					phil.decreaseEatingProgress();
					if(phil.getHungerProgress() == 0)
					putDownForks(phil, rightForkPos, leftForkPos);
				}
				
				else if(phil.getStatus() == Status.ONE_HAND_BUSY || phil.getStatus() == Status.FREE_HANDS) {
					tryToEat(phil, position, rightForkPos, leftForkPos, deadlockProtected);
				}
			}
			
			deadlock = isThisADeadlock();	
		}
		
		System.out.println("DEADLOCK");
	}

	private boolean isThisADeadlock() {
		
		int philsWaiting = 0;
		
		for(Philosopher phil: philosophers) {
			if(phil.getStatus() == Status.ONE_HAND_BUSY)
				philsWaiting++;
		}
		
		return (philsWaiting == philosophers.size() ? true : false);
	}

	private void tryToEat(Philosopher phil, int position, int rightForkPos, int leftForkPos, boolean deadlockProtected) {
		
		if(!philosophers.get(position).isRightHand() || !philosophers.get(position).isLeftHand())
			lookAtForks(phil, position, rightForkPos, leftForkPos, deadlockProtected);
		if(philosophers.get(position).isRightHand() && philosophers.get(position).isLeftHand()) {
			System.out.println("Philosopher " + phil.getName() + " is eating");
			phil.setStatus(Status.EAT);
			phil.fillHungerProgress();
		}
		
		else {
			if(!philosophers.get(position).isRightHand() && !philosophers.get(position).isLeftHand())
				phil.setStatus(Status.FREE_HANDS);
			else
				phil.setStatus(Status.ONE_HAND_BUSY);
		}
			
		
	}

	private void putDownForks(Philosopher phil, int rightForkPos, int leftForkPos) {
		phil.setRightHand(false);
		phil.setLeftHand(false);
		
		forks.get(rightForkPos).setStatus(true);
		forks.get(leftForkPos).setStatus(true);
		
		phil.setStatus(Status.THINK);
		
	}

	private void lookAtForks(Philosopher phil, int position, int rightForkPos, int leftForkPos, boolean deadlockProtected) {
		
		if(deadlockProtected) {
			if(phil.getPosition() == philosophers.size()-1) {
				if(!phil.isRightHand())
					lookAtRightFork(position, rightForkPos, leftForkPos);
				else
					lookAtLeftFork(position, rightForkPos, leftForkPos);				
			}
			else {
				if(!phil.isLeftHand())
					lookAtLeftFork(position, rightForkPos, leftForkPos);
				else
					lookAtRightFork(position, rightForkPos, leftForkPos);
			}
		}
		else {
			if(!lookAtRightFork(position, rightForkPos, leftForkPos))
				lookAtLeftFork(position, rightForkPos, leftForkPos);
		}
		
	}

	private boolean lookAtLeftFork(int position, int rightForkPos, int leftForkPos) {
		if(forks.get(leftForkPos).getStatus()) {
			forks.get(leftForkPos).setStatus(false);
			philosophers.get(position).setLeftHand(true);
			
			return true;
		}
		
		return false;
		
	}

	private boolean lookAtRightFork(int position, int rightForkPos, int leftForkPos) {
		if(forks.get(rightForkPos).getStatus()) {
			forks.get(rightForkPos).setStatus(false);
			philosophers.get(position).setRightHand(true);
			
			return true;
		}
		
		return false;
		
		
	}

	private void think(Philosopher phil) {
		phil.setStatus(Status.THINK);
		System.out.println("Philosopher " + phil.getName() + " is thinking");
	}
}
