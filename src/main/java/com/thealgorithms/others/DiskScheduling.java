package com.thealgorithms.others;

/**
 * @author Alexandros Lemonaris
 */

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.abs;

/**
 * This class simulates the procedure of disk responding to disk Input/Output requests.
 * The algorithms are used in order to calculate the total distance of the head of the disk needed to
 * respond to all the requests
 */
public class DiskScheduling {
    public static void main( String [] args){
        ArrayList <Integer> array = new ArrayList<>(Arrays.asList(98, 183, 37, 122, 14, 124, 65, 67));
        int headStart = 53;
        Scan algorithm = new Scan();

        System.out.println(algorithm.calculateDistance(array, headStart));
    }

}

/**
 * First Come First Serve (FCFS) is an operating system scheduling algorithm
 * that automatically executes queued requests and processes in order of their arrival.
 */
class FCFS {
    /**
     * @param array has the requests queue
     * @param headStart has the first position of the head
     * @return the distance travelled by the head to respond to all requests
     */
    public int calculateDistance ( ArrayList<Integer> array, int headStart){
        int sum = 0, headLastPosition = headStart;
        for (int headCurrentPosition : array){
            sum += abs(headCurrentPosition - headLastPosition);
            headLastPosition = headCurrentPosition;
        }
        return sum;
    }
}

/**
 * Shortest seek time first (SSTF) algorithm selects the disk I/O request which requires
 * the least disk arm movement from its current position regardless of the direction.
 * It reduces the total seek time as compared to FCFS.
 * It allows the head to move to the closest track in the service queue.
 */
class SSTF {
    /**
     * @param array has the requests queue
     * @param headStart has the first position of the head
     * @return the distance travelled by the head to respond to all requests
     */
    public int calculateDistance(ArrayList<Integer> array, int headStart){
        ArrayList<Integer> updatedArray = array;
        int min , headLastPosition = headStart, sum = 0, elementToBeRemoved ;
        while (updatedArray.size() > 0)
        {
            min = abs(updatedArray.get(0) - headLastPosition);
            elementToBeRemoved = updatedArray.get(0);
            for (int headPosition : updatedArray){
                if (abs(headLastPosition - headPosition) < min){
                    min = abs(headLastPosition - headPosition);
                    elementToBeRemoved = headPosition;
                }
            }
            sum += min;
            updatedArray.remove( (Object) headLastPosition);
            headLastPosition = elementToBeRemoved;
        }
        return sum;
    }
}

/**
 * In SCAN disk scheduling algorithm, head starts from one end of the disk
 * and moves towards the other end, servicing requests in between one by one and reach the other end.
 * Then the direction of the head is reversed and the process continues as head continuously
 * scan back and forth to access the disk
 */
class Scan{
    /**
     * @param array has the requests queue
     * @param headStart has the first position of the head
     * @return the distance travelled by the head to respond to all requests
     */
    // I consider that the head start scanning moving downwards (to the minimum value)
    public int calculateDistance(ArrayList<Integer> array, int headStart){
        int minValue = array.get(0), maxValue = array.get(0);
        for (int currentPosition : array){
            if (currentPosition < minValue)
                minValue = currentPosition;
            if (currentPosition > maxValue)
                maxValue = currentPosition;
        }
        return maxValue - minValue + headStart - minValue;
    }
}
