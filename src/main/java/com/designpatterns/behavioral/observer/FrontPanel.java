package com.designpatterns.behavioral.observer;

public class FrontPanel implements Display, Observer {
	private int temperature;
	private int wind;

	public void update(int temperature, int wind) {
		this.temperature = temperature;
		this.wind = wind;
		display();
	}


	public void display() {
		System.out.println("Wind is " + this.wind + ".\n" + "Temperature is " + this.temperature);
	}

}
