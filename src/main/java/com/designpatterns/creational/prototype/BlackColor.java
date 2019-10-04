package com.designpatterns.creational.prototype;

class BlackColor extends Color {

    BlackColor() {
        this.colorName = "black";
    }

    @Override
    public String addColor() {
        return "Black color added";
    }
}
