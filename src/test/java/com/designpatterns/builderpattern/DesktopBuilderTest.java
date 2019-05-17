package src.test.java.com.designpatterns.builderpattern;

import org.junit.Assert;
import org.junit.Test;
import src.main.java.com.designpatterns.builderpattern.Desktop;

public class DesktopBuilderTest {
    private final String configOne = "Desktop{CPU='Intel i7', RAM='Corsair Vengeance 3000', isGraphicCardEnabled=true" +
            ", operatingSystem='Windows 10', diskSizeGB=16, graphicCard='NVIDIA GTX 1080'}";
    private final String configTwo = "Desktop{CPU='Intel i5', RAM='HyperX Fury v5', isGraphicCardEnabled=true, " +
            "operatingSystem='Red Hat Enterprise', diskSizeGB=16, graphicCard='NVIDIA RTX 2080'}";

    @Test
    public void testDesktopBuilder() {
        Desktop d1 = new Desktop.DesktopBuilder("Intel i7", "Corsair Vengeance 3000")
                .setDiskSizeGB(16)
                .setGraphicCard("NVIDIA GTX 1080")
                .setGraphicCardEnabled(true)
                .setOperatingSystem("Windows 10")
                .build();
        Assert.assertEquals(d1.toString(), configOne);

        Desktop d2 = new Desktop.DesktopBuilder("Intel i5", "HyperX Fury v5")
                .setDiskSizeGB(16)
                .setGraphicCard("NVIDIA RTX 2080")
                .setGraphicCardEnabled(true)
                .setOperatingSystem("Red Hat Enterprise")
                .build();
        Assert.assertEquals(d2.toString(), configTwo);
    }

}
