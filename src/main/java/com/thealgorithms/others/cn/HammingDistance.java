package com.thealgorithms.others.cn;

import java.util.ArrayList;
import java.util.List;

public class HammingDistance {


    private List<byte[]> extractBinaryData(String senderBits, String receiverBits){
        List<byte[]> byteArray = new ArrayList<>();

        byteArray.add(senderBits.getBytes());
        byteArray.add(receiverBits.getBytes());

        return byteArray;
    }

    public int getHammingDistanceBetweenBits(String senderBits, String receiverBits) {

        if(senderBits.length() != receiverBits.length()) {
           return -1;
        }

        List<byte[]> bytes = extractBinaryData(senderBits, receiverBits);
        byte[] senderData = bytes.get(0);
        byte[] receiverData = bytes.get(1);

        int totalErrorBitCount = 0;

        for(int i = 0; i < senderData.length; i++){
           totalErrorBitCount += senderData[i] ^ receiverData[i];
        }

        if(totalErrorBitCount == 0){
            System.out.println("No Error bit in data segments");
        } else{
            System.out.println("Total Error bit count "+totalErrorBitCount);
        }
        return totalErrorBitCount;
    }
}
