package Pyramids;
class TopPyramid {
    public static void main(String[] args) {
        //prints
        /*
                  *
                 ***
                *****
               *******
              *********
             ***********
            *************
        */
        for(int i=0;i<7;i++) {
            for(int j=1;j<7-i;j++) {
                System.out.print(" ");
            }
            for(int k=0;k<=2*i;k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}