/*
 * Main.java
 *
 * Created on June, 10 2007, 01:02 PM
 * Created by Andres Segura
 *
 */

package tsp.main;

import tsp.gui.Environment;
import tsp.test.TestEngine;

// Program Main class of TSP Solver solution
public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
    	
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
    	// Program variables
    	String mode = "GUI";
    	if (args.length > 0)
    		mode = args[0].toUpperCase();
    	
    	// Run program...
    	if (mode.startsWith("GUI")) {
    		
    		// Launch the GUI
    		new Environment().setVisible(true);
    	}
    	else if (mode.startsWith("TESTS")) {
			
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
