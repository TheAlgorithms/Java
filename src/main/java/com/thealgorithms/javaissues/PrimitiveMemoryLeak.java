package com.thealgorithms.javaissues;

import java.math.BigDecimal;
import java.util.Deque;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PrimitiveMemoryLeak {

    public static void primitiveMemoryLeak() {

        final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        final Deque<BigDecimal> numbers = new LinkedBlockingDeque<>();
        final BigDecimal divisor = new BigDecimal(51);

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            BigDecimal number = numbers.peekLast();
            if (number != null && number.remainder(divisor).byteValue() == 0) {
                System.out.println("Number: " + number);
                System.out.println("Deque size: " + numbers.size());
            }
        }, 10, 10, TimeUnit.MILLISECONDS);

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            numbers.add(new BigDecimal(System.currentTimeMillis()));
        }, 10, 10, TimeUnit.MILLISECONDS);

        try {
            scheduledExecutorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
