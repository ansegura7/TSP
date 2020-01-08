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
    private JFileChooser jfc;
    private BufferedReader br;
    private double factorValue;
    
    /** Creates a new instance of FileManager */
    public FileManager()
    {
        doublePoint = null;
        fileName = "";
        factorValue = 1.0d;
        jfc = new JFileChooser();
        br = null;
    }

    // Method that transforms double points to positive data on the scale of [0, 1]
    private void scaleData(DoublePoint[] points)
    {
    	if (points == null) {
    		factorValue = 1.0d;
    		return;
    	}
    	
    	double vMin = Double.MAX_VALUE;
    	double vMax = Double.MIN_VALUE;
    	double vRange = 1;
    	
    	// Find the minimum values of X and Y
    	for (int i = 0; i < points.length; i++)
    	{
    		if (points[i].x < vMin)
    			vMin = points[i].x;
    		if (points[i].y < vMin)
    			vMin = points[i].y;
    		
    		if (points[i].x > vMax)
    			vMax = points[i].x;
    		if (points[i].y > vMax)
    			vMax = points[i].y;
    	}
    	
    	vRange = vMax - vMin;
    	factorValue = vRange;
    	System.out.println("   Limits - vMin:" + vMin + ", vMax: " + vMax + ", yMin: " + ", factorValue: " + factorValue);
    	
    	// Scaling X and Y values [0, 1]
    	for (int i = 0; i < points.length; i++)
    	{
    		points[i].x = (points[i].x - vMin) / vRange;
    		points[i].y = (points[i].y - vMin) / vRange;
    	}
    	
    }
    
    // Method that reads the input data from a TSPLIB file
    public DoublePoint[] loadFile()
    {
    	return this.loadFile("");
    }
    
    // Method that reads the input data from a TSPLIB file
    public DoublePoint[] loadFile(String filepath)
    {
        doublePoint = null;
        factorValue = 1.0d;
        
        // If there is no file, then select it
        if (filepath.equals("")) {
        	if (jfc.showDialog(null, "File Selector") == 0) {
        		if(jfc.getSelectedFile().exists()) {
        			filepath = jfc.getSelectedFile().toString();
                    System.out.println("   Read file from: " + filepath);	
        		}
            }
        }
    	
	    try
	    {
	    	// If there are a selected file...
	    	if (!filepath.equals(""))
	    	{
		    	br = new BufferedReader( new FileReader(filepath));
				String line = br.readLine();
				String[] tokens = null;
				int nPoints = 0;
				double x = 0;
		    	double y = 0;
		    	int ix = 0;
	
		    	System.out.println("   Start reading file");
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
				
				// Scaling data points
				scaleData(doublePoint);
				System.out.println("   Data scaled successfully");
	    	}
	    }
	    catch(IOException ex)
	    {
	    	System.err.println(">> Load File Error: " + ex.getMessage());
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
    public double getFactorValue()
    {
    	return this.factorValue;
    }
    
}
