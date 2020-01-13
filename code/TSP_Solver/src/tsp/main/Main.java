/*
 * Main.java
 *
 * Created on June, 10 2007, 01:02 PM
 * Created by Andres Segura
 *
 */

package tsp.main;

import tsp.test.TestEngine;

// Program Main Class of TSP Solver solution GUI
public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
    	
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
    	// Class variables
    	String mode = "GUI";
    	if (args.length > 0)
    		mode = args[0];
    	
    	// Run program...
    	if (mode.toUpperCase().startsWith("GUI")) {
    		
    		// Launch the GUI
    		new tsp.gui.Environment().setVisible(true);
    	}
    	else if (mode.toUpperCase().startsWith("TEST")) {
			
    		// Batch engine variables
    		if (args.length > 1) {
    			String directory = args[1];
    			String algorithm = "SOM";
    			int nTest = 5;
    			
    			if (args.length > 2) {
    				algorithm = args[2];
    				
    				if (args.length > 3) {
    					nTest = Integer.parseInt(args[3]);
    				}
    			}
    			
    			// Run accuracy tests
    			TestEngine.runAccuracyTests(directory, algorithm, nTest);
    		}
			
    	}
    	
    }
    
}
