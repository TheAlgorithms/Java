The peaks

int decimalValue = 0, charB2; (1)

String output = ""; (2)

(int i = 0; i < n.length(); i++) (3)

charB1 = n.charAt(i);(4)
 
(charB1 >= 'A' && charB1 <= 'Z') (5)

charB2 = 10 + (charB1 - 'A') (6)

charB2 = charB1 - '0' (7)

decimalValue = decimalValue * b1 + charB2 (8)

decimalValue != 0 (9)

decimalValue % b2 < 10 (10)

output = Integer.toString(decimalValue % b2) + output (11)

output = (char) ((decimalValue % b2) + 55) + output (12)

decimalValue /= b2 (13)

return output (14)

-> all du paths

D1: (1) (2) (3) (9) (14)

D2: (1) (2) (3) (4) (5) (6) (8) (3) (9) (10) (11) (13) (9) (14)

D3: (1) (2) (3) (4) (5) (6) (8) (3) (9) (10) (12) (13) (9) (14)

D4: (1) (2) (3) (4) (5) (7) (8) (3) (9) (10) (11) (13) (9) (14)

D5: (1) (2) (3) (4) (5) (7) (8) (3) (9) (10) (12) (13) (9) (14)

others paths find not input

 
 

