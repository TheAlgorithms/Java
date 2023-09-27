package com.thealgorithms.bitmanipulation;

public class SingleBitOperators {

    public static int flipBit(int num, int bit){
        return num ^ (1 << bit);
    }

    public static int setBit(int num, int bit) {
        return num | (1<<bit);
    }

    public static int clearBit(int num, int clear) {
        return num & ~(1 << clear);
    }

    public static int getBit(int num, int get){
        return ((num>>get) & 1);
    }

    public static int getFirstBit(int num){
        return num & 1;
    }


}
