package com.thealgorithms.others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author dimgrichr
 */
public class CRCAlgorithm {

    private int correctMess;

    private int wrongMess;

    private int wrongMessCaught;

    private int wrongMessNotCaught;

    private int messSize;

    private double ber;

    private boolean messageChanged;

    private ArrayList<Integer> message;

    private ArrayList<Integer> dividedMessage;

    private ArrayList<Integer> p;

    private Random randomGenerator;

    /**
     * The algorithm's main constructor. The most significant variables, used in
     * the algorithm, are set in their initial values.
     *
     * @param str The binary number P, in a string form, which is used by the
     * CRC algorithm
     * @param size The size of every transmitted message
     * @param ber The Bit Error Rate
     */
    public CRCAlgorithm(String str, int size, double ber) {
        messageChanged = false;
        message = new ArrayList<>();
        messSize = size;
        dividedMessage = new ArrayList<>();
        p = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            p.add(Character.getNumericValue(str.charAt(i)));
        }
        randomGenerator = new Random();
        correctMess = 0;
        wrongMess = 0;
        wrongMessCaught = 0;
        wrongMessNotCaught = 0;
        this.ber = ber;

        boolean[] testArray;
        testArray = new boolean[22];

        for (i = 0; i < 22; i++) {
            testArray[i] = false;
        }
    }

    public void getCoverage(){
        System.out.println(Arrays.toString(testArray));

        int counter = 0;
        for(int i = 0; i < testArray.length; i++){
            if(testArray[i]){
                counter++;
            }
        }

        double percentage = (double) counter / this.testArray.length;
        percentage = percentage * 100;

        System.out.println("PERCENTAGE COVERAGE: " + percentage);
    }

    /**
     * Returns the counter wrongMess
     *
     * @return wrongMess, the number of Wrong Messages
     */
    public int getWrongMess() {
        return wrongMess;
    }

    /**
     * Returns the counter wrongMessCaught
     *
     * @return wrongMessCaught, the number of wrong messages, which are caught
     * by the CRC algoriithm
     */
    public int getWrongMessCaught() {
        return wrongMessCaught;
    }

    /**
     * Returns the counter wrongMessNotCaught
     *
     * @return wrongMessNotCaught, the number of wrong messages, which are not
     * caught by the CRC algorithm
     */
    public int getWrongMessNotCaught() {
        return wrongMessNotCaught;
    }

    /**
     * Returns the counter correctMess
     *
     * @return correctMess, the number of the Correct Messages
     */
    public int getCorrectMess() {
        return correctMess;
    }

    /**
     * Resets some of the object's values, used on the main function, so that it
     * can be re-used, in order not to waste too much memory and time, by
     * creating new objects.
     */
    public void refactor() {
        messageChanged = false;
        message = new ArrayList<>();
        dividedMessage = new ArrayList<>();
    }

    /**
     * Random messages, consisted of 0's and 1's, are generated, so that they
     * can later be transmitted
     */
    public void generateRandomMess() {
        for (int i = 0; i < messSize; i++) {
            int x = ThreadLocalRandom.current().nextInt(0, 2);
            message.add(x);
            this.testArray[0] = true;
        }
    }

    /**
     * The most significant part of the CRC algorithm. The message is divided by
     * P, so the dividedMessage ArrayList<Integer> is created. If check == true,
     * the dividedMessaage is examined, in order to see if it contains any 1's.
     * If it does, the message is considered to be wrong by the receiver,so the
     * variable wrongMessCaught changes. If it does not, it is accepted, so one
     * of the variables correctMess, wrongMessNotCaught, changes. If check ==
     * false, the diviided Message is added at the end of the ArrayList<integer>
     * message.
     *
     * @param check the variable used to determine, if the message is going to
     * be checked from the receiver if true, it is checked otherwise, it is not
     */
    public void divideMessageWithP(boolean check) {
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> k = (ArrayList<Integer>) message.clone();
        if (!check) {
            testArray[1] = true;
            for (int i = 0; i < p.size() - 1; i++) {
                k.add(0);
                testArray[2] = true;
            }
        }
        while (!k.isEmpty()) {
            testArray[3] = true;
            while (x.size() < p.size() && !k.isEmpty()) {
                x.add(k.get(0));
                k.remove(0);
                testArray[4] = true;
            }
            if (x.size() == p.size()) {
                testArray[5] = true;
                for (int i = 0; i < p.size(); i++) {
                    testArray[6] = true;
                    if (x.get(i) == p.get(i)) {
                        x.set(i, 0);
                        testArray[7] = true;
                    } else {
                        x.set(i, 1);
                        testArray[8] = true;
                    }
                }
                for (int i = 0; i < x.size() && x.get(i) != 1; i++) {
                    x.remove(0);
                    testArray[9] = true;
                }
            }
        }
        dividedMessage = (ArrayList<Integer>) x.clone();
        if (!check) {
            testArray[10] = true;
            for (int z : dividedMessage) {
                message.add(z);
                testArray[11] = true;
            }
        } else {
            testArray[12] = true;
            if (dividedMessage.contains(1) && messageChanged) {
                wrongMessCaught++;
                testArray[13] = true;
            } else if (!dividedMessage.contains(1) && messageChanged) {
                wrongMessNotCaught++;
                testArray[14] = true;
            } else if (!messageChanged) {
                correctMess++;
                testArray[15] = true;
            }
        }
    }

    /**
     * Once the message is transmitted, some of it's elements, is possible to
     * change from 1 to 0, or from 0 to 1, because of the Bit Error Rate (ber).
     * For every element of the message, a random double number is created. If
     * that number is smaller than ber, then the spesific element changes. On
     * the other hand, if it's bigger than ber, it does not. Based on these
     * changes. the boolean variable messageChanged, gets the value: true, or
     * false.
     */
    public void changeMess() {
        for (int y : message) {
            testArray[16] = true;
            double x = randomGenerator.nextDouble();
            while (x < 0.0000 || x > 1.00000) {
                x = randomGenerator.nextDouble();
                testArray[17] = true;
            }
            if (x < ber) {
                messageChanged = true;
                testArray[18] = true;
                if (y == 1) {
                    message.set(message.indexOf(y), 0);
                    testArray[19] = true;
                } else {
                    message.set(message.indexOf(y), 1);
                    testArray[20] = true;
                }
            }
        }
        if (messageChanged) {
            wrongMess++;
            this.testArray[21] = true;
        }
    }
}

