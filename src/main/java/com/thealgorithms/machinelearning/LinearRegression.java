
package com.thealgorithms.machinelearning;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : Gowtham Kamalasekar
 * LinkedIn : https://www.linkedin.com/in/gowtham-kamalasekar/
 *
 * Wiki : https://en.wikipedia.org/wiki/Linear_regression
 * Linear Regression Machine Learning Algorithm is a regression algorithm.
 * This programs used for computing y = mx + c
 * Where m is slope and c is intercept
 * We can use this too predict for a given x.
 */

class LinearRegression {
    private ArrayList<Double> dependentX = new ArrayList<Double>();
    private ArrayList<Double> independentY = new ArrayList<Double>();
    private double m;
    private double c;

    /**
     * @param : X (dependent variable), Y (independent variable) as ArrayList
     */
    LinearRegression(ArrayList<Double> dependentX, ArrayList<Double> independentY) {
        this.dependentX = dependentX;
        this.independentY = independentY;
        this.equate();
    }

    private double sumation(List<Double> arr) {
        double sum = 0.0;

        for (int i = 0; i < arr.size(); i++) {
            sum += arr.get(i);
        }

        return sum;
    }

    private List<Double> multiplyNumber(List<Double> arr1, List<Double> arr2) {
        List<Double> temp = new ArrayList<Double>();
        for (int i = 0; i < arr1.size(); i++) {
            temp.add((arr1.get(i) * arr2.get(i)));
        }
        return temp;
    }

    private void equate() {
        int n = dependentX.size();
        this.m = (n * sumation(multiplyNumber(independentY, dependentX)) - (sumation(dependentX) * sumation(independentY)));
        this.m = this.m / (n * (sumation(multiplyNumber(dependentX, dependentX))) - (sumation(dependentX) * sumation(dependentX)));

        this.c = (sumation(independentY) * sumation(multiplyNumber(dependentX, dependentX)) - (sumation(dependentX) * sumation(multiplyNumber(independentY, dependentX))));
        this.c = this.c / (n * (sumation(multiplyNumber(dependentX, dependentX))) - (sumation(dependentX) * sumation(dependentX)));
    }

    public double getM() {
        return this.m;
    }

    public double getC() {
        return this.c;
    }

    public double predictForX(double x) {
        return (this.m * x) + this.c;
    }
}

