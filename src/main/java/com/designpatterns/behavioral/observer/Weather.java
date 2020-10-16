package com.designpatterns.behavioral.observer;

import java.util.ArrayList;

public class Weather implements Subject{
	ArrayList<Observer> list = new ArrayList<>();
	private int temperature;
	private int wind;
	public void register(Observer observer) {
		list.add(observer);
	}
	
	public void unRegister(Observer observer) {
		list.remove(observer);
	}
	
	public void notifyObservers() {
		for(Observer observer : list) {
			observer.update(this.temperature, this.wind);
		}
	}
	
	public void getNewTemperature(int temperature, int wind) {
		setTemperature(temperature);
		setWind(wind);
		notifyObservers();
	}
	
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	
	public void setWind(int wind) {
		this.wind = wind;
	}
}
