package com.designpatterns.behavioral.strategy;

public class RubberDuck extends Duck{
	public RubberDuck() {
		this.fly = new FlyWithWings();
		this.quack = new Squeak();
	}
	
	public void display() {
		System.out.println("Rubber Duck on service!");
	}
}
