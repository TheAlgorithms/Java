package com.thealgorithms.datastructures.bloomfilter;


public class BloomFilter<T> {

    private int numberOfHashFunctions;
    private int [] bitArray;
    private Hash<T>[] hashFunctions;

    public BloomFilter(int numberOfHashFunctions, int n) {
        this.numberOfHashFunctions = numberOfHashFunctions;
        hashFunctions = new Hash[numberOfHashFunctions];
        bitArray = new int[n];
        insertHash();
    }

    private void insertHash() {
        for (int i = 0; i < numberOfHashFunctions; i++) {
            hashFunctions[i] = new Hash(i);
        }
    }

    public void insert(T key) {
        for (Hash<T> hash : hashFunctions){
            bitArray[hash.compute(key) % bitArray.length] = 1;
        }
    }

    public boolean contains(T key) {
        for (Hash<T> hash : hashFunctions){
            if (bitArray[hash.compute(key) % bitArray.length] == 0){
                return false;
            }
        }
        return true;
    }

    private class Hash<T> {

        int index;

        public Hash(int index){
            this.index = index;
        }

        public int compute(T key){
            return index * asciiString(String.valueOf(key));
        }

        private int asciiString(String word){
            int number = 0;
            for (int i=0;i<word.length();i++){
                number += word.charAt(i);
            }
            return number;
        }
    }

}
