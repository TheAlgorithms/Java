package src.main.java.com.designpatterns.creational.prototype;

class RedColor extends Color {

    RedColor() {
        this.colorName = "red";
    }

    @Override
    public String addColor() {
        return "Red color added";
    }
}
