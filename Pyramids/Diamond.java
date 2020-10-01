package Pyramids;
class Diamond {
    public static void main(String[] args) {
        //First print
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
        //Then print
        /*
             ***********
              *********
               *******
                *****
                 ***
                  *
        */
        for(int i=6;i>0;i--) {
            for(int j=0;j<=6-i;j++) {
                System.out.print(" ");
            }
            for(int k=1;k<2*i;k++) {
                System.out.print("*");
            }
            System.out.println();
        }
        //Finally
        /*
                  *
                 ***
                *****
               *******
              *********
             ***********
            *************
             ***********
              *********
               *******
                *****
                 ***
                  *
        */
    }
}