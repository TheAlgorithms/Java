package com.designpatterns.behavioral.strategy;

public abstract class Duck {
		FlyBehaviour fly;
		QuackBehaviour quack;
		public abstract void display();
		public void performQuack() {
			quack.quack();
		}
		public void performFly() {
			fly.fly();
		}
		public void setFlyBehaviour(FlyBehaviour fb) {
			this.fly = fb;
		}
		public void setQuackBehaviour(QuackBehaviour qb) {
			this.quack = quack;
		}
}
