/*
 * PaintProcess.java
 *
 * Created on June 13, 2007, 04:29 PM
 * Created by Andres Segura
 *
 */

package tsp.gui;

import tsp.util.DoubleLinkedList;
import tsp.util.DoublePoint;

// Threading class to painting/repainting the Environment Canvas
public class PaintProcess extends Thread
{
    private EnvCanvas envCanvas;
    private DoublePoint[] doublePoint;
    private DoubleLinkedList graph;
    
    /** Creates a new instance of PaintProcess */
    public PaintProcess(EnvCanvas envCanvas, DoublePoint[] dblPoints, DoubleLinkedList graph)
    {
        this.envCanvas = envCanvas;
        this.doublePoint = dblPoints;
        this.graph = graph;
        start();
    }
    
    // Definition of abstract method to executing the thread
    public void run()
    {
        envCanvas.paintGraph(doublePoint, graph);
    }
}
