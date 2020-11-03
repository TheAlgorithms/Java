package ProjectEuler;

/*
The prime factors of 13195 are 5, 7, 13 and 29.

What is the largest prime factor of the number 600851475143 ?

Largest prime factor

link: https://projecteuler.net/problem=3
*/

public class Problem03 {
    public static void main(String[] args) {
        assert solution(10000l) == 5l;
        assert solution(2345l) == 67l;
        assert solution(600851475143l) == 6857l;
    }

    public static long solution(final long n) {
        long newNum = n;
        long largestFact = 0;
        int count = 2;

        while (count * count <= newNum) {
            if (newNum % count == 0) {
                newNum = newNum / count;
                largestFact = count;
            } else {
                count++;
            }
        }
        if (newNum > largestFact) {
            largestFact = newNum;
        }
        return largestFact;
    }
}
