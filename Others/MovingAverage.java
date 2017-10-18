package testing;

import java.util.ArrayList;


/**
 * Simple class that adds values and returns the average over a set interval.
 * AKA Interpolation.
 */
public class MovingAverage {

	double intervalSize = 5;
	ArrayList<Double> data = new ArrayList<Double>();
	
	public MovingAverage(double size) {
		this.intervalSize = size;
	}
	
	public MovingAverage() {
		this(5);
	}
	
	//Adds a new data point and returns average.
	public double register(double d) {
		data.add(d);
		
		//If theres more data than the set interval, cleave the data.
		if(data.size() > intervalSize) {
			cleave();
		}
		
		//Returns average of data set.
		return sum()/data.size();
	}
	
	//Removes most outdated data point.
	//Private to protect from null pointers.
	private void cleave() {
		data.remove(0);
	}
	
	//Adds all the data points. Riveting.
	//Private to protect from null pointers.
	private double sum() {
		double s = 0;
		
		for(int i = 0; i < data.size(); i++) {
			s+=data.get(i);
		}
		
		return s;
	}
	
}
