/*
 * Main.java
 *
 * Created on June, 10 2007, 01:02 PM
 * Created by Andres Segura
 *
 */

package tsp.main;

public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Launch the GUI
        new tsp.gui.Environment().setVisible(true);
    }
    
}
