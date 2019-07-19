package com.designpatterns.creational.abstractfactory;

public class ThreeDShapeFactory extends AbstractShapeFactory {
    @Override
    public Shape getShape(ShapeType name) {
        if (ShapeType.SPHERE == name) {
            return new Sphere();
        }
        return null;
    }
}
