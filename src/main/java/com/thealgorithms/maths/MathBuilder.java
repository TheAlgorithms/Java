package com.thealgorithms.maths;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Author: Sadiul Hakim : https://github.com/sadiul-hakim
 * Profession: Backend Engineer
 * Date: Oct 20, 2024
 */
public class MathBuilder {
    private final double number;

    private MathBuilder(Builder builder) {
        this.number = builder.NUMBER;
    }

    // Returns final result
    public double get() {
        return number;
    }

    // Return result in long
    public long toLong() {
        try {
            if (Double.isNaN(number)) {
                throw new IllegalArgumentException("Cannot convert NaN to long");
            }
            if (number == Double.POSITIVE_INFINITY) {
                return Long.MAX_VALUE;
            }
            if (number == Double.NEGATIVE_INFINITY) {
                return Long.MIN_VALUE;
            }
            if (number > Long.MAX_VALUE) {
                return Long.MAX_VALUE;
            }
            if (number < Long.MIN_VALUE) {
                return Long.MIN_VALUE;
            }
            return Math.round(number);
        } catch (Exception ex) {
            return 0;
        }
    }

    public static class Builder {
        private double NUMBER;
        private double memory = 0;

        public Builder() {
            NUMBER = 0;
        }

        public Builder(double num) {
            NUMBER = num;
        }

        public Builder add(double num) {
            NUMBER += num;
            return this;
        }

        // Takes a number and a condition, only does the operation if condition is true.
        public Builder addIf(double num, BiFunction<Double, Double, Boolean> condition) {

            if (condition.apply(NUMBER, num)) {
                NUMBER += num;
            }

            return this;
        }

        public Builder minus(double num) {
            NUMBER -= num;
            return this;
        }

        // Takes a number and a condition, only does the operation if condition is true.
        public Builder minusIf(double num, BiFunction<Double, Double, Boolean> condition) {
            if (condition.apply(NUMBER, num)) {
                NUMBER -= num;
            }

            return this;
        }

        // Generates a random number and sets to NUMBER
        public Builder rand(long seed) {
            if (NUMBER != 0) {
                throw new RuntimeException("Number must be zero for random assignment!");
            }

            Random random = new Random();
            NUMBER = random.nextDouble(seed);
            return this;
        }

        // Takes PI value and sets to NUMBER
        public Builder PI() {
            if (NUMBER != 0) {
                throw new RuntimeException("Number must be zero for PI assignment!");
            }

            NUMBER = Math.PI;
            return this;
        }

        // Takes E value and sets to NUMBER
        public Builder E() {
            if (NUMBER != 0) {
                throw new RuntimeException("Number must be zero for E assignment!");
            }

            NUMBER = Math.E;
            return this;
        }

        public Builder randomInRange(double min, double max) {

            if (NUMBER != 0) {
                throw new RuntimeException("Number must be zero for random assignment!");
            }

            Random random = new Random();
            NUMBER = min + (max - min) * random.nextDouble();
            return this;
        }

        public Builder toDegrees() {
            NUMBER = Math.toDegrees(NUMBER);
            return this;
        }

        public Builder max(double num) {
            NUMBER = Math.max(NUMBER, num);
            return this;
        }

        public Builder min(double num) {
            NUMBER = Math.min(NUMBER, num);
            return this;
        }

        public Builder multiply(double num) {

            NUMBER *= num;
            return this;
        }

        // Takes a number and a condition, only does the operation if condition is true.
        public Builder multiplyIf(double num, BiFunction<Double, Double, Boolean> condition) {


            if (condition.apply(NUMBER, num)) {
                NUMBER *= num;
            }

            return this;
        }

        public Builder divide(double num) {

            if (num == 0) {
                return this;
            }

            NUMBER /= num;
            return this;
        }

        // Takes a number and a condition, only does the operation if condition is true.
        public Builder divideIf(double num, BiFunction<Double, Double, Boolean> condition) {

            if (num == 0) {
                return this;
            }

            if (condition.apply(NUMBER, num)) {
                NUMBER /= num;
            }
            return this;
        }

        public Builder mod(double num) {

            NUMBER %= num;
            return this;
        }

        // Takes a number and a condition, only does the operation if condition is true.
        public Builder modIf(double num, BiFunction<Double, Double, Boolean> condition) {


            if (condition.apply(NUMBER, num)) {
                NUMBER %= num;
            }
            return this;
        }

        public Builder pow(double num) {

            NUMBER = Math.pow(NUMBER, num);
            return this;
        }

        public Builder sqrt() {

            NUMBER = Math.sqrt(NUMBER);
            return this;
        }

        public Builder round() {

            NUMBER = Math.round(NUMBER);
            return this;
        }

        public Builder floor() {

            NUMBER = Math.floor(NUMBER);
            return this;
        }

        public Builder ceil() {

            NUMBER = Math.ceil(NUMBER);
            return this;
        }

        public Builder abs() {

            NUMBER = Math.abs(NUMBER);
            return this;
        }

        public Builder cbrt() {

            NUMBER = Math.cbrt(NUMBER);
            return this;
        }

        public Builder log() {

            NUMBER = Math.log(NUMBER);
            return this;
        }

        public Builder log10() {

            NUMBER = Math.log10(NUMBER);
            return this;
        }

        public Builder sin() {

            NUMBER = Math.sin(NUMBER);
            return this;
        }

        public Builder cos() {

            NUMBER = Math.cos(NUMBER);
            return this;
        }

        public Builder tan() {

            NUMBER = Math.tan(NUMBER);
            return this;
        }

        public Builder sinh() {

            NUMBER = Math.sinh(NUMBER);
            return this;
        }

        public Builder cosh() {

            NUMBER = Math.cosh(NUMBER);
            return this;
        }

        public Builder tanh() {

            NUMBER = Math.tanh(NUMBER);
            return this;
        }

        public Builder exp() {

            NUMBER = Math.exp(NUMBER);
            return this;
        }

        public Builder toRadians() {

            NUMBER = Math.toRadians(NUMBER);
            return this;
        }

        // Remembers the NUMBER
        public Builder remember() {

            memory = NUMBER;
            return this;
        }

        // Recalls the NUMBER
        public Builder recall(boolean cleanMemory) {

            NUMBER = memory;
            if (cleanMemory) {
                memory = 0;
            }

            return this;
        }

        // Recalls the NUMBER on condition
        public Builder recallIf(Function<Double, Boolean> condition, boolean cleanMemory) {

            if (!condition.apply(NUMBER)) {
                return this;
            }

            NUMBER = memory;
            if (cleanMemory) {
                memory = 0;
            }

            return this;
        }

        // Replaces NUMBER with given number
        public Builder set(double num) {

            if (NUMBER != 0) {
                throw new RuntimeException("Number must be zero to set!");
            }

            NUMBER = num;
            return this;
        }

        // Replaces NUMBER with given number on condition
        public Builder setIf(double num, BiFunction<Double, Double, Boolean> condition) {

            if (NUMBER != 0) {
                throw new RuntimeException("Number must be zero to set!");
            }

            if (condition.apply(NUMBER, num)) {
                NUMBER = num;
            }

            return this;
        }

        // Prints current NUMBER
        public Builder print() {
            System.out.println("MathBuilder Result :: " + NUMBER);
            return this;
        }

        public Builder format(String format) {

            DecimalFormat formater = new DecimalFormat(format);
            String num = formater.format(NUMBER);
            NUMBER = Double.parseDouble(num);
            return this;
        }

        public Builder format(int decimalPlace) {

            String pattern = "." + "#".repeat(decimalPlace);
            DecimalFormat formater = new DecimalFormat(pattern);
            String num = formater.format(NUMBER);
            NUMBER = Double.parseDouble(num);
            return this;
        }

        public MathBuilder build() {
            return new MathBuilder(this);
        }
    }
}
