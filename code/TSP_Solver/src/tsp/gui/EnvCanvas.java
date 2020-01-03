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
    // Class variables
    private Graphics g;
    public int V;
    private int x;
    private int y;
    private int w;
    private int h;
    private DoublePoint[] doublePoint;
    private DoubleLinkedList graph;
    private Color color1;
    private Color color2;
    public int marginV;
    public int marginH;
    
    // Creates a new instance of EnvCanvas
    public EnvCanvas(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        setBounds(x, y, w, h);
        color1 = Color.black;
        color2 = Color.red;
        doublePoint = null;
        graph = null;
        V = h-50;
        marginV = 25;
        marginH = (w - h) / 2;
    }
    
    public void paint(Graphics g)
    {
        this.g = g;
        paintCanvas();
    }
    
    public synchronized void paintCanvas()
    {
        g.setColor(Color.white);
        g.fillRect(x, y, w, h);
        
        if (doublePoint != null)
        {
            g.setColor(color1);
            for(int i=0; i<doublePoint.length; i++)
                g.fillOval(marginH + (int)(doublePoint[i].x * V)-1, marginV + (int)(doublePoint[i].y * V)-1, 3, 3);
        }
        if (graph != null)
        {
            g.setColor(color2);
            Node node = graph.getLastNode();
            do
            {
                g.fillOval(marginH+(int)(node.x * V)-1, marginV + (int)(node.y * V)-1, 3, 3);
                g.drawLine(marginH+(int)(node.x * V), marginV + (int)(node.y * V), marginH + (int)(node.next.x * V), marginV + (int)(node.next.y * V));
                node = node.next;
            } while (node != graph.getLastNode());
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
