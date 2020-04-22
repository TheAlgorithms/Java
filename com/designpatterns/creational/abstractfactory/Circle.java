package com.designpatterns.creational.abstractfactory;

public class Circle implements Shape {
    @Override
    public double surfaceArea(float radius) {
        return Math.PI * radius * radius;
    }

    @Override
    public ShapeType getShapeType() {
        return ShapeType.CIRCLE;
    }
}
