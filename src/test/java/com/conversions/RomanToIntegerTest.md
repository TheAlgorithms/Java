## **1. Flowchart**


## **2. Paths**
### ** Variable A: **
#### **Path 1:** 1 &rarr; 2 &rarr; 3 &rarr; 4 &rarr; 5 &rarr; 23
#### **Path 2:** 1 &rarr; 2 &rarr; 3 &rarr; 4 &rarr; 5 &rarr; 6
### ** Variable prev: **
#### **Path 1:** 2 &rarr; 3 &rarr; 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr; 15 &rarr; 16 &rarr; 17 &rarr; &rarr; 21
#### **Path 2:** 2 &rarr; 3 &rarr; 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr; 15 &rarr; 18 &rarr; 19 &rarr; 21
#### **Path 3:** 2 &rarr; 3 &rarr; 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr; 8 &rarr; 9 &rarr; 15 &rarr; 16 &rarr; 17 &rarr; &rarr; 21
#### **Path 4:** 2 &rarr; 3 &rarr; 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr; 8 &rarr; 9 &rarr; 15 &rarr; 18 &rarr; 19 &rarr; 21
#### **Path 5:** 2 &rarr; 3 &rarr; 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr; 11 &rarr; 12 &rarr; 15 &rarr; 16 &rarr; 17 &rarr; &rarr; 21
#### **Path 6:** 2 &rarr; 3 &rarr; 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr; 11 &rarr; 12 &rarr; 15 &rarr; 18 &rarr; 19 &rarr; 21
### ** Variable newPrev: **
#### **Path 1:** 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr; 15 &rarr; 16 &rarr; 17
#### **Path 2:** 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr; 15 &rarr; 16 &rarr; 19
#### **Path 3:** 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr;  8 &rarr; 9 &rarr; 15 &rarr; 16 &rarr; 17
#### **Path 4:** 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr;  8 &rarr; 9 &rarr; 15 &rarr; 16 &rarr; 19
#### **Path 5:** 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr;  11 &rarr; 12 &rarr; 15 &rarr; 16 &rarr; 17
#### **Path 6:** 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr;  11 &rarr; 12 &rarr; 15 &rarr; 16 &rarr; 19
### ** Variable sum: **
#### **Path 1:** 3 &rarr; 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr; 15 &rarr; 16 &rarr; 17 &rarr; 21 &rarr; 23
#### **Path 2:** 3 &rarr; 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr; 15 &rarr; 18 &rarr; 19 &rarr; 21 &rarr; 23
#### **Path 3:** 3 &rarr; 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr; 8 &rarr; 9 &rarr; 15 &rarr; 16 &rarr; 17 &rarr; 21 &rarr; 23
#### **Path 4:** 3 &rarr; 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr; 8 &rarr; 9 &rarr; 15 &rarr; 18 &rarr; 19 &rarr; 21 &rarr; 23
#### **Path 5:** 3 &rarr; 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr; 11 &rarr; 12 &rarr; 15 &rarr; 18 &rarr; 19 &rarr; 21 &rarr; 23
#### **Path 6:** 3 &rarr; 4 &rarr; 5 &rarr; 6 &rarr; 7 &rarr; 11 &rarr; 12 &rarr; 15 &rarr; 18 &rarr; 19 &rarr; 21 &rarr; 23
### ** Variable c: **
#### **Path 1:** 6 &rarr; 7 &rarr; 8 &rarr; 9 &rarr; 15
#### **Path 2:** 6 &rarr; 7 &rarr; 11 &rarr; 12 &rarr; 15
### ** Variable currentNum: **
#### **Path 1:**  15 &rarr; 16 &rarr; 17
#### **Path 2:**  15 &rarr; 18 &rarr; 19

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


