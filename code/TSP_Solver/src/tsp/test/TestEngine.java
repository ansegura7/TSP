/*
 * Main.java
 *
 * Created on January 5, 2020, 09:22 AM
 * Created by Andres Segura
 *
 */

package tsp.test;

import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import tsp.algorithm.SOMAlgorithm;
import tsp.algorithm.TspAlgorithm;
import tsp.util.DoublePoint;
import tsp.util.FileManager;

public class TestEngine {

	// Class variables
	private static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss.SSSS a");
	private static FileManager fm = new FileManager();
	private static DecimalFormat df = new DecimalFormat("##.0000");
	
	/** Creates a new instance of Main */
    public TestEngine() {
    	
    }
	
    /**
     * @param args the command line arguments
     */
	public static void main(String[] args) {
		System.out.println(">> Start Batch Process - " + sdf.format(new Date()));
		
		if (args.length > 0) {
			// Batch engine variables
			String filesFolder = args[0];
			String algorithm = "SOM";
			int nTest = 5;
			
			if (args.length > 1) {
				algorithm = args[1];
				
				if (args.length > 2) {
					nTest = Integer.parseInt(args[2]);
				}
			}
			
			// Run Test Engine in Batch mode
			System.out.println("   Directory: " + filesFolder + ", Algorithm: " + algorithm + ", N Tests: " + nTest);
			runBatchTest(filesFolder, algorithm, nTest);
		}
		
		System.out.println(">> End Batch Process - " + sdf.format(new Date()));
	}
	
	// Batch process that calculates the TSP for each case
	private static void runBatchTest(String filesFolder, String algorithm, int nTest)
	{
		ArrayList<TspCase> tspFiles = readFileList(filesFolder);
		TspCase tspCase = null;
		TspAlgorithm tspAlgo = null;
		
		for (int i = 0; i < tspFiles.size(); i++) {
			tspCase = tspFiles.get(i);
			
			if (new File(tspCase.filePath).exists()) {
				System.out.println(" - The file will be processed: " + tspCase.name + " by " + algorithm + " algorithm - " + sdf.format(new Date()));
				DoublePoint[] dp = fm.loadFile(tspCase.filePath);
				double currTour = 0;
				int elapsedTime = 0;
				
            	if (dp != null && nTest > 0) {
            		
            		// Run N times the TSP algorithm and then average the results 
        			for (int j = 0; j < nTest; j++) {
        				
        				// Select the TSP algorithm
                		if (algorithm.equals("SOM"))
                    		tspAlgo = new SOMAlgorithm();
        				
    	            	// Set data and start algorithm
    	            	tspAlgo.init(dp);
    	        		tspAlgo.start();
    	        		
    					try {
    		            	// Waiting for the results
    						tspAlgo.join();
    						
    						// Accumulate results
    						currTour += tspAlgo.getTourLength() * fm.getFactorValue();
    						elapsedTime += tspAlgo.getElapsedTime();
    					}
    					catch (InterruptedException e) {
    						e.printStackTrace();
    						System.err.println(">> Batch Process Error: " + e.getMessage());
    					}
        			}
        			
        			// Show and save the results
					tspCase.currTour = currTour / nTest;
					tspCase.elapsedTime = elapsedTime / nTest;
					System.out.println("   Algorithm results: Tour length: " + tspCase.currTour + " units, Tour MAE: " + tspCase.getTourMAPE(false) + ", Elapsed time: " + tspCase.elapsedTime + " ms");
            	}
            	else {
            		System.out.println("   The data was not loaded - " + sdf.format(new Date()));
            	}
			}
			else {
				System.out.println("   The file: " + tspCase.filePath + " does not exists");
			}
		}
		
		// Save results
		saveTestResultsToCSV(tspFiles);
		
	}

	// Read the TSP file list
	private static ArrayList<TspCase> readFileList(String filesFolder)
	{
		ArrayList<TspCase> tspFiles = new ArrayList<TspCase>();
		
		String testPath = "test/tests.dat";
		File f = new File(testPath);

	    try {
			if (f.exists()) {
				BufferedReader br;
				String line;
				String[] tokens;
				String fileName;
				String filePath;
				int nPoints;
				double bestTour;
				
		    	br = new BufferedReader( new FileReader(testPath));
		    	line = br.readLine();
		    	
		    	while (!line.startsWith("EOF")) {
		    		tokens = line.split(",");
		    		
		    		if (tokens.length >= 2) {
		    			fileName = tokens[0].trim();
		    			filePath = filesFolder + fileName.toLowerCase() + ".tsp.txt";
		    			nPoints = Integer.parseInt(fileName.replaceAll("([A-Za-z])", ""));
		    			bestTour = Double.parseDouble(tokens[1].trim());
		    			
		    			tspFiles.add( new TspCase(fileName, filePath, nPoints, bestTour));
		    		}
		    		line = br.readLine();
		    	}
		    	br.close();
		    }
			else {
				System.out.println("   The test folder: " + testPath + " does not exists");
			}
	    }
	    catch(IOException e) {
	    	System.err.println(">> Load File Error:" + e.getMessage());
		}
		
		return tspFiles;
	}
	
	// Save the TSP cases results to CSV file
	private static void saveTestResultsToCSV(ArrayList<TspCase> tspFiles) {
		String resultPath = "test/results.csv";
		FileWriter writer = null;
		StringBuilder sb = null;
		TspCase tspCase = null;
		double mape = 0.0;
		int totalPoints = 0;
		double avgMAPE = 0.0d;
		
	    try {
	    	writer = new FileWriter(new File(resultPath), false);
	    	
	    	// Add header to CSV file
	    	sb = new StringBuilder();
	    	sb.append("file_name,n_points,best_tout,curr_tour,mape(%),elapsed_time(ms)\n");
	    	
	    	// Add rows to CSV file
	    	for (int i = 0; i < tspFiles.size(); i++) {
	    		tspCase = tspFiles.get(i);
	    		mape = tspCase.getTourMAPE(true);
	    		
	    		// Add case results to file
	    		sb.append(tspCase.name + ",");
	    		sb.append(tspCase.nPoints + ",");
	    		sb.append(df.format(tspCase.bestTour) + ",");
	    		sb.append(df.format(tspCase.currTour) + ",");
	    		sb.append(df.format(mape) + ",");
	    		sb.append(tspCase.elapsedTime + "\n");
	    		
	    		// The results of each case are weighted
				totalPoints += tspCase.nPoints;
				avgMAPE += mape * tspCase.nPoints;
	    	}
	    	
	    	// Save data and close the CSV file
	        writer.write(sb.toString());
	        writer.close();
	        
	        // Showing the weighted average MAPE
	        avgMAPE /= totalPoints;  
	        System.out.println(" - The weighted average MAPE: " + avgMAPE);
	    }
	    catch (IOException e) {
	        System.out.println(">> Save File Error:" + e.getMessage());
	    }
	}
	
}
