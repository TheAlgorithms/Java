package src.test.java.com.designpatterns.structural;

import org.junit.Test;
import src.main.java.com.designpatterns.structural.adapter.BugattiVeyron;
import src.main.java.com.designpatterns.structural.adapter.Movable;
import src.main.java.com.designpatterns.structural.adapter.MovableAdapter;
import src.main.java.com.designpatterns.structural.adapter.MovableAdapterImpl;

import static org.junit.Assert.assertEquals;

public class MovableAdapterTest {
    @Test
    public void testMovableAdapter() {
        Movable bugattiVeyron = new BugattiVeyron();
        MovableAdapter bugattiVeyronAdapter = new MovableAdapterImpl(bugattiVeyron);
        assertEquals(bugattiVeyronAdapter.getSpeed(), 431.30312, 0.00001);
    }
}
