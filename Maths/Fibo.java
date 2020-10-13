import java.math.BigInteger;
import java.util.ArrayList;

/**
 * A utility to calculate a fibonacci number at the exact position
 * @author KoreeZ https://github.com/HandsomeKoreeZ
 *
 */

public class Fibo {

    //spread the fib number to the list of position of bits
    private static ArrayList<Integer> powList(int p) {
        int bitFlag = 0;
        ArrayList<Integer> list = new ArrayList<>();
        while(p>0){
            if ((p & 1)== 1) list.add(bitFlag);
            p>>=1;
            bitFlag++;
        }
        return list;
    }

    /**
     *
     * @param pow an integer number of the position
     * @return BigInteger fibonacci number
     */

    public static BigInteger at(Integer pow){
        ArrayList<Integer> list = powList(pow);
        ArrayList<FiboMatrix> matrixList = new ArrayList<>();

        //exponent pow matrix list
        for (Integer item: list){ matrixList.add(
                new FiboMatrix().binPowMatrix(item)); }

        //multiplicate matrix each other
        FiboMatrix first = matrixList.get(0);
        for (int i = 1; i<matrixList.size(); i++) {
            first.multiplicateMatrix(matrixList.get(i));
        }
        return first.getMatx()[0][1];
    }


    //matrix helper class
    static class FiboMatrix {
        private BigInteger[][] matrix;

        public FiboMatrix() {
            this.matrix = new BigInteger[][]{{BigInteger.valueOf(0), BigInteger.valueOf(1)}, {BigInteger.valueOf(1), BigInteger.valueOf(1)}};
        }

        public BigInteger[][] getMatx(){
            return matrix;
        }

        public void multiplicateMatrix(FiboMatrix multiplyer){
            BigInteger[][] result;
            BigInteger[][] matx1 = multiplyer.getMatx();
            BigInteger c11 = matx1[0][0].multiply(matrix[0][0]).add(matx1[0][1].multiply(matrix[1][0]));
            BigInteger c12 = matx1[0][0].multiply(matrix[0][1]).add(matx1[0][1].multiply(matrix[1][1]));
            BigInteger c21 = matx1[1][0].multiply(matrix[0][0]).add(matx1[1][1].multiply(matrix[1][0]));
            BigInteger c22 = matx1[1][0].multiply(matrix[0][1]).add(matx1[1][1].multiply(matrix[1][1]));
            result = new BigInteger[][]{{c11,c12},{c21,c22}};
            this.matrix = result;
        }

        //exponential squaring matrix
        public FiboMatrix binPowMatrix(int n){
            for (int i = 0; i<n; i++) this.multiplicateMatrix(this);
            return this;
        }
    }


    //assertions
    public static void main(String[] args) {
        assert Fibo.at(10).equals(new BigInteger(String.valueOf(55)));
        assert Fibo.at(15).equals(new BigInteger(String.valueOf(610)));
    }
}