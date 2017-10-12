//this is an implementation of the perceptron learning algorithim
//It is one neuron in a neural network
//@author: rinaldaj

import java.util.Random;

public class Perceptron {
	double[] w;

	
	public Perceptron(int size){
	//This initializes w to random values
	//size is the size of a challenge vector
		w = new double[size];
		Random rand = new Random();
		for( int i = 0; i< size; i++){
			w[i] = rand.nextDouble();
		}
	}
	public void train(double[][] challenge, boolean[] good){
	//this trains the system
		boolean right = false;
		int counting = 0;
		while(!right){
			//System.out.println("This is the " + counting +" of the loop");
			counting++;
			right = true;
			for(int j = 0; j < challenge.length;j++){
				double[] x = challenge[j];
				double dotProduct = 0;
				for(int i = 0; i < w.length;i++){
					dotProduct+= w[i] * x[i];
				}
				int yFound;
				if(dotProduct < 0){
					yFound = -1;
				}else{
					yFound = 1;
				}
				int yReal;
				if(good[j]){
					yReal = 1;
				}else{
					yReal = -1;
				}
				if(yReal != yFound){
					right = false;
					//System.out.println("Fail occured in " + counting+ " loop");
					for(int k = 0; k< w.length; k++){
						w[k] = w[k] + yReal * x[k];
					}
				}

			}
		}
	}
	public boolean test(double[] inputVector){
	//Takes the vector and outputs whether it is accepted or not
		double dotProduct = 0;
		for(int i = 0; i < w.length;i++){
			dotProduct+= w[i] * inputVector[i];
		}
		return dotProduct >= 0;
	}
	
	public void printW(){
		for(double nes:w)
			System.out.print(nes + " ");
		System.out.println("");
	}

	public static double[] genTrainSet(int input,int size){
	//This is a superflourous function that is used to generate training vectors
	//size should be the length of w (can't use w.length because it may not have been initialized)
		double[] ret = new double[size];
		int step = input;
		for(int i = 0;i<size;i++){
			if(step % 2 == 0){
				step = step /2;
			} else {
				step = step * 3 + 1;
			}
			ret[i] = step;
		}
		return ret;
	}
	public static boolean goodVector(double[] input){
	//this is a superflourous function that is used to check if the input vector is good
		double dotProduct = 0;
		for(int i = 0; i < input.length;i++){
			dotProduct+= (i*1/2) * Math.pow(-1,i) * input[i];
		}
		return dotProduct >= 0;
	
	}
	public static void main(String [] args){
		//This generates a training set based on the goodVector function
		int size = 4;
		int sampSize = 1000;
		double[][] trainSet = new double[sampSize][];
		boolean[] good = new boolean[sampSize];
		for(int i = 0; i<sampSize;i++){
			trainSet[i] = Perceptron.genTrainSet(i,size);
			good[i] = Perceptron.goodVector(trainSet[i]);
		}
		Perceptron neuron = new Perceptron(size);
		neuron.train(trainSet,good);
		//neuron.printW();

	}
}
