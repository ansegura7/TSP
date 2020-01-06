/*
 * Main.java
 *
 * Created on January 5, 2020, 09:22 AM
 * Created by Andres Segura
 *
 */

package tsp.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import tsp.algorithm.SOMAlgorithm;
import tsp.algorithm.TspAlgorithm;
import tsp.util.DoublePoint;
import tsp.util.FileManager;

public class TestEngine {

	// Class variables
	private static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss.SSSS a");
	private static FileManager fm = new FileManager();
	
	/** Creates a new instance of Main */
    public TestEngine() {
    	
    }
	
    /**
     * @param args the command line arguments
     */
	public static void main(String[] args) {
		
		System.out.println(">> Start Batch Process - " + sdf.format(new Date()));
		
		if (args.length > 1) {
			String filesFolder = args[0];
			String algorithm = args[1];
			runBatchTest(filesFolder, algorithm);
		}
		
		System.out.println(">> End Batch Process - " + sdf.format(new Date()));
	}
	
	// Batch process that calculates the TSP for each case
	private static void runBatchTest(String filesFolder, String algorithm)
	{
		ArrayList<TspTest> tspFiles = readFileList(filesFolder);
		TspTest tspCase = null;
		TspAlgorithm tspAlgo = null;
		
		for (int i = 0; i < tspFiles.size(); i++) {
			tspCase = tspFiles.get(i);
			
			if (new File(tspCase.filePath).exists()) {
				System.out.println(" - The file will be processed: " + tspCase.name + " by " + algorithm + " algorithm - " + sdf.format(new Date()));
				DoublePoint[] dp = fm.loadFile(tspCase.filePath);

	            try
	            {
	            	if (dp != null) {
	            		
	            		if (algorithm.equals("SOM")) {
	                		tspAlgo = new SOMAlgorithm();
	                	}
	            		
		            	// Set data and start algorithm
		            	tspAlgo.init(dp);
		        		tspAlgo.start();
		        		
		            	// Waiting for the results
						tspAlgo.join();
						
						// Show and save the results
						tspCase.currTour = tspAlgo.getTourLength() * fm.getFactorValue();
						tspCase.elapsedTime = tspAlgo.getElapsedTime();
						System.out.println("   Algorithm results: Tour length: " + tspCase.currTour + " units, Tour MAE: " + tspCase.getTourMAE() + ", Elapsed time: " + tspCase.elapsedTime + " ms");
	            	}
	            	else
	            	{
	            		System.out.println("   The data was not loaded - " + sdf.format(new Date()));
	            	}
				}
	            catch (InterruptedException ex)
	            {
					// TODO Auto-generated catch block
					ex.printStackTrace();
					System.err.println(">> Batch Process Error: " + ex.getMessage());
				}
			}
			else {
				System.out.println("   The file: " + tspCase.filePath + " does not exists");
			}
		}
		
	}
	
	// Read the TSP file list
	private static ArrayList<TspTest> readFileList(String filesFolder)
	{
		ArrayList<TspTest> tspFiles = new ArrayList<TspTest>();
		
		String testPath = "test/tests.dat";
		File f = new File(testPath);

	    try
	    {
			if (f.exists()) {
				BufferedReader br;
				String line;
				String[] tokens;
				String fileName;
				String filePath;
				double bestTour;
				
		    	br = new BufferedReader( new FileReader(testPath));
		    	line = br.readLine();
		    	
		    	while (!line.startsWith("EOF")) {
		    		tokens = line.split(",");
		    		
		    		if (tokens.length >= 2) {
		    			fileName = tokens[0].trim();
		    			filePath = filesFolder + fileName.toLowerCase() + ".tsp.txt";  
		    			bestTour = Double.parseDouble(tokens[1].trim());
		    			
		    			tspFiles.add( new TspTest(fileName, filePath, bestTour));
		    		}
		    		line = br.readLine();
		    	}
		    	br.close();
		    }
			else {
				System.out.println("   The test folder: " + testPath + " does not exists");
			}
	    }
	    catch(IOException ex)
	    {
	    	System.err.println(">> Load File Error:" + ex.getMessage());
		}
		
		return tspFiles;
	}
	
}
