package com.thealgorithms.others;

public class countSetBits {
    public long countsetBits(long num){
        long cnt=0;
        while(num>0){
            cnt++;
            num&=(num-1);
        }
        return cnt;
    }
}