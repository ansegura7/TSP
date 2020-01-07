/*
 * Main.java
 *
 * Created on January 5, 2020, 11:24 AM
 * Created by Andres Segura
 *
 */

package tsp.test;

public class TspCase {
	
	// Class variables
	public String name;
	public String filePath;
	public int nPoints;
	public double bestTour;
	public double currTour;
	public int elapsedTime;
	private double errorTour;
	
	// TspCase constructor
	public TspCase(String name, String filePath, int nPoints, double bestTour) {
		this.name = name;
		this.filePath = filePath;
		this.nPoints = nPoints;
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