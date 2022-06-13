package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class GaussianTest1 {

    @Test
    void passTest1()
    {
    	//Partition:Test for matrix 1*1
        ArrayList<Double> list = new ArrayList<Double>();
        ArrayList<Double> gaussian = new ArrayList<Double>();
        ArrayList<Double> answer = new ArrayList<Double>();
        answer.add(0.0);
        answer.add(1.0);

        int matrixSize = 2;
        list.add(1.0);
        list.add(2.0);
        gaussian=gaussian(matrixSize,list);

        assertEquals(answer,gaussian);
    }
    
    @Test
    void passTest2()
    {
    	//Partition:Test for matrix 2*3
    	ArrayList<Double> list = new ArrayList<Double>();
        ArrayList<Double> gaussian = new ArrayList<Double>();
        ArrayList<Double> answer = new ArrayList<Double>();
        answer.add(0.0);
        answer.add(1.0);

        int matrixSize = 2;
        list.add(1.0);
        list.add(1.0);
        list.add(1.0);
        list.add(2.0);
        list.add(1.0);
        list.add(1.0);
        gaussian=gaussian(matrixSize,list);

        assertEquals(answer,gaussian);
    }

	private ArrayList<Double> gaussian(int matrixSize, ArrayList<Double> list) {
		// TODO Auto-generated method stub
		return null;
	}

	private void assertEquals(ArrayList<Double> answer, ArrayList<Double> gaussian) {
		// TODO Auto-generated method stub
		
	}
}

