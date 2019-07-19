package com.designpatterns.creational.abstractfactory;

public class FactoryProvider {
    public static AbstractShapeFactory getShapeFactory(FactoryType factoryType) {
        if (FactoryType.TWO_D_FACTORY == factoryType) {
            return new TwoDShapeFactory();
        } else if (FactoryType.THREE_D_FACTORY == factoryType) {
            return new ThreeDShapeFactory();
        }
        return null;
    }
}
