/*
 * Main.java
 *
 * Created on January 5, 2020, 11:24 AM
 * Created by Andres Segura
 *
 */

package tsp.test;

public class TspTest {
	
	// Class variables
	public String name;
	public String filePath;
	public double bestTour;
	public double currTour;
	public int elapsedTime;
	private double errorTour;
	
	// TspTest constructor
	public TspTest(String name, String filePath, double bestTour) {
		this.name = name;
		this.filePath = filePath;
		this.bestTour = bestTour;
		this.currTour = 0.0d;
		this.elapsedTime = 0;
		this.errorTour = 0.0d;
	}
	
	// Return tour MAE (Mean Absolute Error)
	public double getTourMAE(boolean inPerc)
	{
		if (this.bestTour > 0) {
			this.errorTour = Math.abs(this.bestTour - this.currTour) / this.bestTour;
			if (inPerc)
				this.errorTour *= 100;
		}
		else
			this.errorTour = 0.0d;
		return this.errorTour;
	}
	
}
