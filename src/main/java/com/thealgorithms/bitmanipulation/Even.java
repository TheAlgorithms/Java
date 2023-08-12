import java.util.Scanner;

public class Even {
static int isEven(int number){
  int evenMask = 1 << 0;
    if((number & evenMask) != 0){
    return 0;
    }
    return -1;
}
   
}
