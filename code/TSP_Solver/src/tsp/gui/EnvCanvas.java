/*
 * EnvCanvas.java
 *
 * Created on June 10, 2007, 01:57 PM
 * Created by Andres Segura
 *
 */

package tsp.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import tsp.util.DoubleLinkedList;
import tsp.util.DoublePoint;
import tsp.util.Node;

@SuppressWarnings("serial")
public class EnvCanvas extends Canvas
{    
	// Class constants
	private final static Color PNT_COLOR = Color.black;
    private final static Color NTW_COLOR = new Color(70, 130, 180);
	private final static int SCR_MARGIN = 25;
	private final static int PNT_DIAMETER = 4;
    
    // Class variables
    private DoublePoint[] doublePoint;
    private DoubleLinkedList graph;
    
    // Plot variables
    private Graphics g;
    private int x;
    private int y;
    private int w;
    private int h;
    public int pFactor;
    public int vMargin;
    public int hMargin;
    
    // Creates a new instance of EnvCanvas
    public EnvCanvas(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        setBounds(x, y, w, h);
        doublePoint = null;
        graph = null;
        pFactor = h - 2 * SCR_MARGIN;
        vMargin = SCR_MARGIN;
        hMargin = (w - pFactor) / 2;
    }
    
    public void paint(Graphics g)
    {
        this.g = g;
        paintCanvas();
    }
    
    public synchronized void paintCanvas()
    {
    	int xv = 0, yv = 0;
    	int xn = 0, yn = 0;
    	
        g.setColor(Color.white);
        g.fillRect(x, y, w, h);
        
        // Plot problem points
        if (doublePoint != null)
        {
            g.setColor(PNT_COLOR);
            for (int i = 0; i < doublePoint.length; i++) {
            	xv = (int)(doublePoint[i].x * pFactor);
            	yv = (int)((1 - doublePoint[i].y) * pFactor);
                g.fillOval((hMargin + xv - 1), (vMargin + yv - 1), 2, 2);
            }
        }

        // Plot solution
        if (graph != null)
        {
            g.setColor(NTW_COLOR);
            Node node = graph.getLastNode();
            do
            {
            	xv = (int)(node.x * pFactor);
            	yv = (int)((1 - node.y) * pFactor);
            	xn = (int)(node.next.x * pFactor);
            	yn = (int)((1 - node.next.y) * pFactor);
                g.fillOval((hMargin + xv - PNT_DIAMETER / 2), (vMargin + yv - PNT_DIAMETER / 2), PNT_DIAMETER, PNT_DIAMETER);
                g.drawLine((hMargin + xv), (vMargin + yv), hMargin + xn, vMargin + yn);
                node = node.next;
            }
            while (node != graph.getLastNode());
        }
        
    }
    
    // re-paint graph
    public void paintGraph(DoublePoint[] dblPoints, DoubleLinkedList graph)
    {
        this.doublePoint = dblPoints;
        this.graph = graph;
        repaint();
    }
    
}
