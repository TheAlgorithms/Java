package com.designpatterns.behavioral.strategy;

public class Mute implements QuackBehaviour{
	public void quack() {
		System.out.println("I cannot quack!");
	}
}
