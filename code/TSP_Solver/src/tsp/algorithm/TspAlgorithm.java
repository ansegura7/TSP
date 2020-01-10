/*
 * TspAlgorithm.java
 *
 * Created on January 05, 2020, 03:11 PM
 * Created by Andres Segura
 *
 */

package tsp.algorithm;

import tsp.util.DoublePoint;

// Abstract class for TSP algorithm classes
public abstract class TspAlgorithm extends Thread {
	
    // Super run method that solves the algorithm
    public void run()
    {
    	solve();
    }
    
    // Abstract methods declaration
    public abstract void init(DoublePoint[] points);
    public abstract void solve();
    public abstract double getTourLength();
    public abstract int getElapsedTime();
    
}
