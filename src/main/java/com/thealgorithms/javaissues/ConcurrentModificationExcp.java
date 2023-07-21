package com.thealgorithms.javaissues;

import com.thealgorithms.javaissues.util.Selfie;

import java.util.ArrayList;
import java.util.List;

public class ConcurrentModificationExcp {

    public static void concurrentModificationExc() {

        List<String> hats = new ArrayList<>();
        hats.add("Ushanka"); // that one has ear flaps
        hats.add("Fedora");
        hats.add("Sombrero");
        for (String hat : hats) {
            if (hat == "Ushanka") {
                hats.remove(hat);
            }
        }
    }

}
