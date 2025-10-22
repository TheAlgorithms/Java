package com.thealgorithms.oopconcepts;
class Vehicle {
    int tyres;
    String colour;
    String companyName;

    void setTyres(int tyres) {
        this.tyres = tyres;
        System.out.println("Number of tyres: " + tyres);
    }

    void setColour(String colour) {
        this.colour = colour;
        System.out.println("Colour of vehicle: " + colour);
    }

    void setCompanyName(String companyName) {
        this.companyName = companyName;
        System.out.println("Company name: " + companyName);
    }
}

class Truck extends Vehicle {
    int wheels = 8, headlights = 2;
    String model;

    Truck(String model) {
        this.model = model;
        System.out.println("This is a truck: " + model);
    }
}

class Motorcycle extends Vehicle {
    int wheels = 2, headlights = 1;
    String model;

    Motorcycle(String model) {
        this.model = model;
        System.out.println("This is a motorcycle: " + model);
    }
}

public class InheritanceDemo {
    public static void main(String[] args) {
        Motorcycle mc = new Motorcycle("Dream");
        mc.setColour("Black");
        mc.setTyres(2);
        mc.setCompanyName("Honda");

        Truck tk = new Truck("Tata Ultra");
        tk.setTyres(8);
        tk.setColour("White");
        tk.setCompanyName("Tata");
    }
}