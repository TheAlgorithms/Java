package src.main.java.com.designpatterns.creational.prototype;

import java.util.HashMap;
import java.util.Map;

public class ColorStore {
    private static Map<String, Color> colorMap = new HashMap<>();

    static {
        colorMap.put("blue", new BlueColor());
        colorMap.put("black", new BlackColor());
        colorMap.put("red", new RedColor());
    }

    public static Color getColor(String colorName) {
        return (Color) colorMap.get(colorName).clone();
    }
}
