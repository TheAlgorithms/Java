package com.thealgorithms.oopconcepts;

abstract class Vehicle {
    abstract void start();
    abstract void stop();
}

class Car extends Vehicle {
    @Override
    void start() {
        System.out.println("Car engine started");
    }

    @Override
    void stop() {
        System.out.println("Car stopped");
    }
}

public class AbstractionDemo {
    public static void main(String[] args) {
        Vehicle v = new Car();
        v.start();
        v.stop();
    }
}
