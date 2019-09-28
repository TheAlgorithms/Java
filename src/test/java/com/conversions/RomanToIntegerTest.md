```sh
private static Map<Character, Integer> map = new HashMap<>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

1    public static int romanToInt(String A) {
2        char prev = ' ';
3        int sum = 0;
4        int newPrev = 0;
5        for (int i = A.length() - 1; i >= 0; i--) {
6            char c = A.charAt(i);
7            if (prev != ' ') {
8                if(map.get(prev) > newPrev){
9                    newPrev = map.get(prev)
10                } else{
11                    newPrev = newPrev
12                }
13            }
14            int currentNum = map.get(c);
15            if (currentNum >= newPrev) {
16                sum += currentNum;
17            } else {
18                sum -= currentNum;
19            }
20            prev = c;
21        }
22        return sum;
23    }
```

## **1. Flowchart**


## **2. Paths**
### ** Variable A: **
#### **Path 1:** 1 &rarr; 2 &rarr; 3 &rarr; 4 &rarr; 5 &rarr; 22
#### **Path 2:** 1 &rarr; 2 &rarr; 3 &rarr; 4 &rarr; 5 &rarr; 6
### ** Variable prev: **
#### **Path 1:** 2 &rarr; 3 &rarr; 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr; 14 &rarr; 15 &rarr; 16 &rarr; &rarr; 20
#### **Path 2:** 2 &rarr; 3 &rarr; 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr; 14 &rarr; 17 &rarr; 18 &rarr; 20
#### **Path 3:** 2 &rarr; 3 &rarr; 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr; 8 &rarr; 9 &rarr; 14 &rarr; 15 &rarr; 16 &rarr; &rarr; 20
#### **Path 4:** 2 &rarr; 3 &rarr; 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr; 8 &rarr; 9 &rarr; 14 &rarr; 17 &rarr; 18 &rarr; 20
#### **Path 5:** 2 &rarr; 3 &rarr; 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr; 10 &rarr; 11 &rarr; 14 &rarr; 15 &rarr; 16 &rarr; &rarr; 20
#### **Path 6:** 2 &rarr; 3 &rarr; 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr; 10 &rarr; 11 &rarr; 14 &rarr; 17 &rarr; 18 &rarr; 20
### ** Variable newPrev: **
#### **Path 1:** 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr; 14 &rarr; 15 &rarr; 16
#### **Path 2:** 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr; 14 &rarr; 15 &rarr; 18
#### **Path 3:** 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr;  8 &rarr; 9 &rarr; 14 &rarr; 15 &rarr; 16
#### **Path 4:** 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr;  8 &rarr; 9 &rarr; 14 &rarr; 15 &rarr; 18
#### **Path 5:** 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr;  10 &rarr; 11 &rarr; 14 &rarr; 15 &rarr; 16
#### **Path 6:** 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr;  10 &rarr; 11 &rarr; 14 &rarr; 15 &rarr; 18
### ** Variable sum: **
#### **Path 1:** 3 &rarr; 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr; 14 &rarr; 15 &rarr; 16 &rarr; 20 &rarr; 22
#### **Path 2:** 3 &rarr; 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr; 14 &rarr; 17 &rarr; 18 &rarr; 20 &rarr; 22
#### **Path 3:** 3 &rarr; 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr; 8 &rarr; 9 &rarr; 14 &rarr; 15 &rarr; 16 &rarr; 20 &rarr; 22
#### **Path 4:** 3 &rarr; 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr; 8 &rarr; 9 &rarr; 14 &rarr; 17 &rarr; 18 &rarr; 20 &rarr; 22
#### **Path 5:** 3 &rarr; 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr; 10 &rarr; 11 &rarr; 14 &rarr; 15 &rarr; 16 &rarr; 20 &rarr; 22
#### **Path 6:** 3 &rarr; 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr; 10 &rarr; 11 &rarr; 14 &rarr; 17 &rarr; 18 &rarr; 20 &rarr; 22
### ** Variable c: **
#### **Path 1:** 6 &rarr; 7 &rarr; 8 &rarr; 9 &rarr; 14
#### **Path 2:** 6 &rarr; 7 &rarr; 10 &rarr; 11 &rarr; 14
### ** Variable currentNum: **
#### **Path 1:**  14 &rarr; 15 &rarr; 16
#### **Path 2:**  14 &rarr; 17 &rarr; 18

## **3. Generate Test Case**
## **Test 1:**
#### **Input**: A = ""
#### **Expected output**: 0
## **Test 2:**
#### **Input**: A = " "
#### **Expected output**: NullPointerException 
## **Test 3:**
#### **Input**: A = "V"
#### **Expected output**: 5 
## **Test 4:**
#### **Input**: A = "VI"
#### **Expected output**: 6
## **Test 5:**
#### **Input**: A = "IV"
#### **Expected output**: 4
## **Test 6:**
#### **Input**: A = "VII"
#### **Expected output**: 7
## **Test 7:**
#### **Input**: A = "MMMCMXCII"
#### **Expected output**: 3992
## **Test 8:**
#### **Input**: A = "CCCXCIX"
#### **Expected output**: 399


