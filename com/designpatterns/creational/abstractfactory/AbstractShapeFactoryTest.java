package com.designpatterns.creational.abstractfactory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AbstractShapeFactoryTest {
    @Test
    void testAbstractShapeFactory() {
        String failReason = "";
        // Tests for 2-D shape factory
        // Test for Line
        AbstractShapeFactory shapeFactory = FactoryProvider.getShapeFactory(FactoryType.TWO_D_FACTORY);
        Shape shape = shapeFactory.getShape(ShapeType.LINE);
        if (shape.getShapeType() != ShapeType.LINE) {
            failReason += "Could not create an object for LINE.\n";
        }
        if (shape.surfaceArea(5) != 0) {
            failReason += "Surface area of Line is incorrect!.\n";
        }

        // Test for circle
        shape = shapeFactory.getShape(ShapeType.CIRCLE);
        if (shape.getShapeType() != ShapeType.CIRCLE) {
            failReason += "Could not create an object for CIRCLE.\n";
        }
        if (shape.surfaceArea(9) != 254.46900494077323) {
            failReason += "Surface area of Circle is incorrect!.\n";
        }

        // Test for 3-D shape factory
        // Test for Sphere
        shapeFactory = FactoryProvider.getShapeFactory(FactoryType.THREE_D_FACTORY);
        shape = shapeFactory.getShape(ShapeType.SPHERE);

        if (shape.getShapeType() != ShapeType.SPHERE) {
            failReason += "Could not create and object for SPHERE.\n";
        }
        if (shape.surfaceArea(6) != 452.3893421169302) {
            failReason += "Surface area of Sphere is incorrect!.\n";
        }

        Assertions.assertEquals(failReason, "", failReason);

    }
}
