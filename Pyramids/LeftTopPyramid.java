package Pyramids;
class LeftTopPyramid {
    public static void main(String[] args) {
        //print
        /*
            *
            **
            ***
            ****
            *****
            ******
            *******
        */
        for(int i=0;i<7;i++) {
            for(int j=0;j<=i;j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}