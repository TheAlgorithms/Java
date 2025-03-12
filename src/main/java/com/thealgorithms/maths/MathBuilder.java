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
public final class MathBuilder {
    private final double result;

    private MathBuilder(Builder builder) {
        this.result = builder.number;
    }

    // Returns final result
    public double get() {
        return result;
    }

    // Return result in long
    public long toLong() {
        try {
            if (Double.isNaN(result)) {
                throw new IllegalArgumentException("Cannot convert NaN to long");
            }
            if (result == Double.POSITIVE_INFINITY) {
                return Long.MAX_VALUE;
            }
            if (result == Double.NEGATIVE_INFINITY) {
                return Long.MIN_VALUE;
            }
            if (result > Long.MAX_VALUE) {
                return Long.MAX_VALUE;
            }
            if (result < Long.MIN_VALUE) {
                return Long.MIN_VALUE;
            }
            return Math.round(result);
        } catch (Exception ex) {
            return 0;
        }
    }

    public static class Builder {
        private double number;
        private double memory = 0;

        public Builder() {
            number = 0;
        }

        public Builder(double num) {
            number = num;
        }

        public Builder add(double num) {
            number += num;
            return this;
        }

        // Takes a number and a condition, only does the operation if condition is true.
        public Builder addIf(double num, BiFunction<Double, Double, Boolean> condition) {
            if (condition.apply(number, num)) {
                number += num;
            }
            return this;
        }

        public Builder minus(double num) {
            number -= num;
            return this;
        }

        // Takes a number and a condition, only does the operation if condition is true.
        public Builder minusIf(double num, BiFunction<Double, Double, Boolean> condition) {
            if (condition.apply(number, num)) {
                number -= num;
            }
            return this;
        }

        // Generates a random number and sets to NUMBER
        public Builder rand(long seed) {
            if (number != 0) {
                throw new RuntimeException("Number must be zero for random assignment!");
            }
            Random random = new Random();
            number = random.nextDouble(seed);
            return this;
        }

        // Takes PI value and sets to NUMBER
        public Builder pi() {
            if (number != 0) {
                throw new RuntimeException("Number must be zero for PI assignment!");
            }
            number = Math.PI;
            return this;
        }

        // Takes E value and sets to NUMBER
        public Builder e() {
            if (number != 0) {
                throw new RuntimeException("Number must be zero for E assignment!");
            }
            number = Math.E;
            return this;
        }

        public Builder randomInRange(double min, double max) {

            if (number != 0) {
                throw new RuntimeException("Number must be zero for random assignment!");
            }
            Random random = new Random();
            number = min + (max - min) * random.nextDouble();
            return this;
        }

        public Builder toDegrees() {
            number = Math.toDegrees(number);
            return this;
        }

        public Builder max(double num) {
            number = Math.max(number, num);
            return this;
        }

        public Builder min(double num) {
            number = Math.min(number, num);
            return this;
        }

        public Builder multiply(double num) {
            number *= num;
            return this;
        }

        // Takes a number and a condition, only does the operation if condition is true.
        public Builder multiplyIf(double num, BiFunction<Double, Double, Boolean> condition) {
            if (condition.apply(number, num)) {
                number *= num;
            }
            return this;
        }

        public Builder divide(double num) {
            if (num == 0) {
                return this;
            }
            number /= num;
            return this;
        }

        // Takes a number and a condition, only does the operation if condition is true.
        public Builder divideIf(double num, BiFunction<Double, Double, Boolean> condition) {
            if (num == 0) {
                return this;
            }
            if (condition.apply(number, num)) {
                number /= num;
            }
            return this;
        }

        public Builder mod(double num) {
            number %= num;
            return this;
        }

        // Takes a number and a condition, only does the operation if condition is true.
        public Builder modIf(double num, BiFunction<Double, Double, Boolean> condition) {
            if (condition.apply(number, num)) {
                number %= num;
            }
            return this;
        }

        public Builder pow(double num) {
            number = Math.pow(number, num);
            return this;
        }

        public Builder sqrt() {
            number = Math.sqrt(number);
            return this;
        }

        public Builder round() {
            number = Math.round(number);
            return this;
        }

        public Builder floor() {
            number = Math.floor(number);
            return this;
        }

        public Builder ceil() {
            number = Math.ceil(number);
            return this;
        }

        public Builder abs() {
            number = Math.abs(number);
            return this;
        }

        public Builder cbrt() {
            number = Math.cbrt(number);
            return this;
        }

        public Builder log() {
            number = Math.log(number);
            return this;
        }

        public Builder log10() {
            number = Math.log10(number);
            return this;
        }

        public Builder sin() {
            number = Math.sin(number);
            return this;
        }

        public Builder cos() {
            number = Math.cos(number);
            return this;
        }

        public Builder tan() {
            number = Math.tan(number);
            return this;
        }

        public Builder sinh() {
            number = Math.sinh(number);
            return this;
        }

        public Builder cosh() {
            number = Math.cosh(number);
            return this;
        }

        public Builder tanh() {
            number = Math.tanh(number);
            return this;
        }

        public Builder exp() {
            number = Math.exp(number);
            return this;
        }

        public Builder toRadians() {
            number = Math.toRadians(number);
            return this;
        }

        // Remembers the NUMBER
        public Builder remember() {
            memory = number;
            return this;
        }

        // Recalls the NUMBER
        public Builder recall(boolean cleanMemory) {
            number = memory;
            if (cleanMemory) {
                memory = 0;
            }

            return this;
        }

        // Recalls the NUMBER on condition
        public Builder recallIf(Function<Double, Boolean> condition, boolean cleanMemory) {
            if (!condition.apply(number)) {
                return this;
            }
            number = memory;
            if (cleanMemory) {
                memory = 0;
            }

            return this;
        }

        // Replaces NUMBER with given number
        public Builder set(double num) {
            if (number != 0) {
                throw new RuntimeException("Number must be zero to set!");
            }
            number = num;
            return this;
        }

        // Replaces NUMBER with given number on condition
        public Builder setIf(double num, BiFunction<Double, Double, Boolean> condition) {
            if (number != 0) {
                throw new RuntimeException("Number must be zero to set!");
            }
            if (condition.apply(number, num)) {
                number = num;
            }
            return this;
        }

        // Prints current NUMBER
        public Builder print() {
            System.out.println("MathBuilder Result :: " + number);
            return this;
        }

        public Builder format(String format) {
            DecimalFormat formater = new DecimalFormat(format);
            String num = formater.format(number);
            number = Double.parseDouble(num);
            return this;
        }

        public Builder format(int decimalPlace) {
            String pattern = "."
                 + "#".repeat(decimalPlace);
            DecimalFormat formater = new DecimalFormat(pattern);
            String num = formater.format(number);
            number = Double.parseDouble(num);
            return this;
        }

        public MathBuilder build() {
            return new MathBuilder(this);
        }
    }
}
