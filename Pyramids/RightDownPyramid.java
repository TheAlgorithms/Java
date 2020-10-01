package Pyramids;
class RightDownPyramid {
    public static void main(String[] args) {
        //print
        /*
            *******
             ******
              *****
               ****
                ***
                 **
                  *
        */
        for(int i=7;i>0;i--) {
            for(int j=0;j<7-i;j++) {
                System.out.print(" ");
            }
            for(int k=0;k<i;k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}