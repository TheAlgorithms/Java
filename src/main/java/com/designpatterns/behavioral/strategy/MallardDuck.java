package com.designpatterns.behavioral.strategy;

public class MallardDuck extends Duck{
	public MallardDuck() {
		this.fly = new FlyWithWings();
		this.quack = new Quack();
	}
	
	public void display() {
		System.out.println("Mallard Duck on service!");
	}

	
}
