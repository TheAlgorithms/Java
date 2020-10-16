package com.designpatterns.behavioral.strategy;

public class RocketDuck extends Duck{
	RocketDuck(){
		this.fly = new FlyRocketPowered();
		this.quack = new Mute();
	}
	
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("Rubber duck on service!");
	}
	
}
