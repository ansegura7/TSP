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
	
	// TspTest constructor
	public TspTest(String name, String filePath, double bestTour) {
		this.name = name;
		this.filePath = filePath;
		this.bestTour = bestTour;
		this.currTour = 0.0d;
		elapsedTime = 0;
	}
	
}
