package com.designpatterns.behavioural.observer;

import com.designpatterns.behavioral.observer.FrontPanel;
import com.designpatterns.behavioral.observer.Weather;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class WeatherAppTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    String Test = "Wind is 13.\nTemperature is 15\n"
            +"Wind is 13.\nTemperature is 17\n"
            +"Wind is 13.\nTemperature is 16\n"
            +"Wind is 3.\nTemperature is 12\n"
            +"Wind is 323.\nTemperature is 15\n"
            +"Wind is 18.\nTemperature is 15\n";
    @Test
    void createWeatherApp(){
        Weather weather = new Weather();
        FrontPanel frontPanel = new FrontPanel();
        weather.register(frontPanel);
        weather.getNewTemperature(15, 13);
        weather.getNewTemperature(17, 13);
        weather.getNewTemperature(16, 13);
        weather.getNewTemperature(12, 3);
        weather.getNewTemperature(15, 323);
        weather.getNewTemperature(15, 18);
        Assertions.assertEquals(outputStreamCaptor.toString(), Test);
    }
}
