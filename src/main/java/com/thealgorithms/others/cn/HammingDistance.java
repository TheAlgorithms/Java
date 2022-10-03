package com.thealgorithms.others.cn;

import java.util.ArrayList;
import java.util.List;

public class HammingDistance {

    public int getHammingDistanceBetweenBits(
        String senderBits,
        String receiverBits
    ) {
        if (senderBits.length() != receiverBits.length()) {
            throw new IllegalArgumentException(
                "Sender and Receiver bits should be same"
            );
        }

        List<byte[]> byteArray = new ArrayList<>();

        byteArray.add(senderBits.getBytes());
        byteArray.add(receiverBits.getBytes());

        byte[] senderData = byteArray.get(0);
        byte[] receiverData = byteArray.get(1);

        int totalErrorBitCount = 0;

        for (int i = 0; i < senderData.length; i++) {
            totalErrorBitCount += senderData[i] ^ receiverData[i];
        }

        if (totalErrorBitCount == 0) {
            System.out.println("No Error bit in data segments");
        } else {
            System.out.println("Total Error bit count " + totalErrorBitCount);
        }
        return totalErrorBitCount;
    }
}
