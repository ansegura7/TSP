/*
 * FileManager.java
 *
 * Created on June 10, 2007, 01:02 PM
 * Created by Andres Segura
 *
 */

package tsp.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;

// Class that manages TSP files 
public class FileManager
{
    // Class constants
    private static final int MAX_POINTS = 100000;
    
    // Class variables
    private DoublePoint[] doublePoint;
    private String fileName;
    private double maxValue;
    private JFileChooser jfc;
    private BufferedReader br;
    
    /** Creates a new instance of FileManager */
    public FileManager()
    {
        doublePoint = null;
        fileName = "";
        maxValue = 0.0d;
        jfc = new JFileChooser();
        br = null;
    }

    // Method that transforms double points to positive data on the scale of [0, 1]
    private void scaleData(DoublePoint[] points)
    {
    	if (points == null) {
    		maxValue = 0.0d;
    		return;
    	}
    	
    	double max = 0;
    	double minX = 0;
    	double minY = 0;
    	
    	// En este ciclo busco el minimo valor en X y en Y
    	for(int i=0; i < points.length; i++)
    	{
    		if(points[i].x < minX)
    			minX = points[i].x;
    		if(points[i].y < minY)
    			minY = points[i].y;
    	}
    	
    	// En este ciclo paso todos los doublePoint a positivos
    	if(minX < 0)
    		for(int i=0; i<points.length; i++)
    			points[i].x -= minX;
    	if(minY < 0)
    		for(int i=0; i<points.length; i++)
    			points[i].y -= minY;
    	
    	// Paso todos los valores a un intervalo entre 0 y 1
    	for(int i=0; i<points.length; i++)
    	{
    		if(points[i].x > max)
    			max = points[i].x;
    		if(points[i].y > max)
    			max = points[i].y;
    	}
    	
    	for(int i=0; i < points.length; i++)
    	{
    		points[i].x /= max;
    		points[i].y /= max;
    	}
    	maxValue = max;
    }
    
    // Method that reads the input data from a TSPLIB file
    public DoublePoint[] loadFile()
    {
        doublePoint = null;
        maxValue = 0.0d;
    	
    	if (jfc.showDialog(null, "File selector") == 0)
        {
    		if(!jfc.getSelectedFile().exists())
    			return null;
    		
            String path = jfc.getSelectedFile().toString();
            System.out.println(">> Read file from: " + path);
            
		    try
		    {
		    	br = new BufferedReader( new FileReader(path));
				String line = br.readLine();
				String[] tokens = null;
				int nPoints = 0;
				double x = 0;
		    	double y = 0;
		    	int ix = 0;

		    	System.out.println("   Start cycle");
				for (int i = 0; i < MAX_POINTS && !line.equals("EOF"); i++)
				{
					tokens = line.split(":");
					
					if (tokens[0].toString().startsWith("NAME")) {
						fileName = tokens[1].trim();
						System.out.println("   Name: " + fileName);
					}
					else if (tokens[0].toString().startsWith("DIMENSION")) {
						nPoints = Integer.parseInt(tokens[1].trim());
						System.out.println("   Dimension: " + nPoints);
					}
					else if (tokens[0].toString().startsWith("NODE_COORD_SECTION") && nPoints > 0) {
						doublePoint = new DoublePoint[nPoints];
						System.out.println("   Init Double Point List");
					}
					else if (doublePoint != null) {
						tokens = line.split("\\s+");
						
						x = Double.parseDouble(tokens[1]);
						y = Double.parseDouble(tokens[2]);
					    doublePoint[ix++] = new DoublePoint(x, y);
					}
					
					line = br.readLine().trim();
				}
				br.close();
				System.out.println("   End cycle");
				
				// Scaling data points
				scaleData(doublePoint);
				System.out.println("   Data scaled successfully");
		    }
		    catch(IOException ex)
		    {
		    	System.err.println(">> Load File Error:" + ex.getMessage());
		    }
        }
    	
        return doublePoint;
    }
    
    // Returns the TSP file name
    public String getFileName()
    {
    	return this.fileName;
    }
    
    // Returns the array points
    public DoublePoint[] getPoints()
    {
        return this.doublePoint;
    }
    
    // Returns the max value of data
    public double getMaxValue()
    {
    	return this.maxValue;
    }
}
