package com.designpatterns.creational.prototype;

class BlueColor extends Color {

    BlueColor() {
        this.colorName = "blue";
    }

    @Override
    public String addColor() {
        return "Blue color added";
    }

}

