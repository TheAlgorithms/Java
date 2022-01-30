package com.thealgorithms.ciphers;

/**
 * A Java implementation of Playfair Cipher.
 * NOTE: This implementation does not incorporate the use of keywords,
 * which is a slightly more complex version of the Playfair Cipher.
 * Rather, this cipher uses the default alphabet, skipping the
 * letter 'J' as is typical in Playfair Ciphers.
 * 
 * 
 * Walkthrough of Playfair Cipher:
 * https://youtu.be/-KjFbTK1IIw
 *
 * @author jacobmass
 */

public class PlayfairCipher {

    static String encode(String text)
    {

        //First step, remove any spaces in the text.
        text = text.replaceAll(" ","");

        //Also, turn every letter into uppercase
        for(int i = 0; i < text.length(); i++)
        {
            
            if(text.charAt(i) >= 97 && text.charAt(i) <= 122)
            { //if it's lowercase
                text = replaceLetter(text,i,(char)(text.charAt(i)-32));
            }
        }

        //Turn the J's to I's too!
        text = text.replaceAll("J","I");

        //If there are any double letters, turn the second one into an X
        //Technically, we only need to do this is the first of the double letters
        //is at an even index
        for(int i = 0; i < text.length()-1; i+=2)
        {
            if(text.charAt(i) == text.charAt(i+1))
            {
                text = replaceLetter(text,i+1,'X');
            }
        }

        //Next, ensure string has an even number of characters.
        //If not, add an X at the end.
        if(text.length() % 2 != 0)
        {
            text += "X";
        }

        //Now, encode each pair of letters.
        String ciphertext = "";

        for(int i = 0; i < text.length()/2; i++)
        { //For each pair,
            char letter1 = text.charAt(i*2);
            char letter2 = text.charAt(i*2 + 1);

            //Find the column and row of both letters
            int column1 = columnNumber(letter1);
            int row1 = rowNumber(letter1);
            int column2 = columnNumber(letter2);
            int row2 = rowNumber(letter2);

            //System.out.print("" + letter1 + letterToValue(letter1) + letter2 + letterToValue(letter2) + " ");
            //System.out.print("" + letter1 + column1 + row1 + letter2 + column2 + row2 + " ");

            //If same column, move down
            if(column1 == column2)
            {
                row1++;
                row2++;
            }
            //If same row, move right
            else if(row1 == row2)
            {
                column1++;
                column2++;
            }
            //Else, rectangle rule:
            //Swap column numbers.
            else
            {
                int temp = column1;
                column1 = column2;
                column2 = temp;
            }
            
            //Now, recalculate letters 1 and 2 and add them to ciphertext!
            letter1 = findInKey(column1,row1);
            letter2 = findInKey(column2,row2);
            ciphertext = ciphertext + letter1 + letter2;
        }

        return ciphertext;
    }

    static String replaceLetter(String str, int index, char letter)
    {
        return str.substring(0,index) + letter + str.substring(index + 1);
    }

    static int letterToValue(char letter)
    {
        //account for jumping over J:
        if(letter > 'J') letter--;
        //set A to 0:
        letter -= 65;
        return letter;
    }

    static int columnNumber(char letter)
    {
        int value = letterToValue(letter);
        return (value % 5);
    }

    static int rowNumber(char letter)
    {
        int value = letterToValue(letter);
        return value / 5;
    }

    static char findInKey(int column, int row)
    {
        column %= 5;
        row %= 5;

        int position = column + row*5;
        //Account for skipping 'J':
        if(position > 8) position++;
        char letter = (char)(position + 65);

        return letter;
    }

    static String decode(String text)
    {
        String decoded = "";

        //Similar to encoding each pair, but reverse direction
        for(int i = 0; i < text.length()/2; i++)
        { //For each pair,
            char letter1 = text.charAt(i*2);
            char letter2 = text.charAt(i*2 + 1);

            //Find the column and row of both letters
            int column1 = columnNumber(letter1);
            int row1 = rowNumber(letter1);
            int column2 = columnNumber(letter2);
            int row2 = rowNumber(letter2);


            //If same column, move up
            if(column1 == column2)
            {
                //add 6 instead of subtract 1 to avoid negative numbers
                row1+=4;
                row2+=4;
            }
            //If same row, move left
            else if(row1 == row2)
            {
                column1+=4;
                column2+=4;
            }
            //Else, rectangle rule:
            //Swap column numbers.
            else
            {
                int temp = column1;
                column1 = column2;
                column2 = temp;
            }
            
            //Now, recalculate letters 1 and 2 and add them to ciphertext!
            letter1 = findInKey(column1,row1);
            letter2 = findInKey(column2,row2);
            decoded = decoded + letter1 + letter2;
        }

        return decoded;
    }

    public static void main(String[] args)
    {
        //message to be encoded should only contain letters, NO PUNCTUATION.
        //spaces are okay, but they will be removed by the encoding anyway.
        String text = "Test message";
        System.out.println("Message: " + text);
        String ciphertext = encode(text);
        System.out.println("Encoded message: " + ciphertext);
        String decoded = decode(ciphertext);
        System.out.println("Decoded message: " + decoded);

    }
}
