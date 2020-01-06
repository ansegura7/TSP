/*
 * SOMAlgorithm.java
 *
 * Created on June 10, 2007, 03:11 PM
 * Created by Andres Segura
 *
 */

package tsp.algorithm;

import tsp.gui.Environment;
import tsp.gui.PaintProcess;
import tsp.util.DoubleLinkedList;
import tsp.util.DoublePoint;
import tsp.util.Node;

// Self organizing map (SOM) class
public class SOMAlgorithm extends TspAlgorithm
{
    // Class constants
    private final static int MAX_ITERATIONS = 1000;
    private final static double LAMBDA = 0.2d;
    
    // Class variables
    private Environment env;
    private DoublePoint[] doublePoint;
    private DoubleLinkedList graph;
    private int[] cache;
    private int msSleep = 50;
    private boolean display;
    private long elapsedTime;
    
    /** Creates a new instance of SOMAlgorithm */
    public SOMAlgorithm()
    {
        this(null, false);
    }
    
    /** Creates a new instance of SOMAlgorithm */
    public SOMAlgorithm(Environment env, boolean b)
    {
        this.env = env;
        this.display = b;
        this.doublePoint = null;
        this.graph = null;
        this.cache = null;
    }
    
    // Function based on the initial cloud of points established by the initial SOM
    public void init(DoublePoint[] points)
    {
        this.doublePoint = points;
        setInitialPoints();
        if (display)
        	paint();
        cache = new int[points.length];
        java.util.Arrays.fill(cache, -1);
    }
    
    // This is the method that solves the TSP problem using SOM algorithm
    public void solve()
    {
        if (doublePoint != null) {
        	elapsedTime = System.nanoTime();
        	
        	while (graph.getNumNodes() < doublePoint.length * 3 / 2)
            {
                for(int i = 0; i < (int)(graph.getNumNodes() * MAX_ITERATIONS / doublePoint.length); i++)
                	updateSOM();
                
                addNodes();
                if (display)
                	paint();
            }
        	finish();
        	elapsedTime = ((System.nanoTime() - elapsedTime) / 1000000);
        	
        	// Show final results
            paint();
            if (env != null)
            	env.displayResults();
        }
    }
    
    // Method that calculates the length of the tour
    public double getTourLength()
    {
    	double tour = 0.0d;
    	Node node = graph.getLastNode();
    	
    	do
    	{
    		tour += Math.sqrt( Math.pow(node.x - node.next.x, 2) + Math.pow(node.y - node.next.y, 2) );
            node = node.next;
    	}
    	while (node != graph.getLastNode());
    	
    	return tour;
    }
    
    // Returns the time it took for the algorithm to resolve the TSP
    public long getElapsedTime()
    {
    	return this.elapsedTime;
    }
    
    // Method that looks for the initial position of the SOM
    private void setInitialPoints()
    {
        if(doublePoint == null)
            return;
        
        double x = 0;
        double y = 0;
        for (int i = 0; i < doublePoint.length; i++)
        {
            x += doublePoint[i].x;
            y += doublePoint[i].y;
        }
        x /= doublePoint.length;
        y /= doublePoint.length;
        
        // Search limit nodes
        DoublePoint norte = new DoublePoint(x, y*0.8);
        DoublePoint noreste = new DoublePoint(x*1.1, y*0.9);
        DoublePoint este = new DoublePoint(x*1.2, y);
        DoublePoint sureste = new DoublePoint(x*1.1, y*1.1);
        DoublePoint sur = new DoublePoint(x, y*1.2);
        DoublePoint suroeste = new DoublePoint(x*0.9, y*1.1);
        DoublePoint oeste = new DoublePoint(x*0.8,y);
        DoublePoint noroeste = new DoublePoint(x*0.9, y*0.9);
        
        // Save graph data
        graph = new DoubleLinkedList();
        graph.rightInsert(new Node(0, norte), graph.getLastNode());
        graph.rightInsert(new Node(1, noreste), graph.search(0));
        graph.rightInsert(new Node(2, este), graph.search(1));
        graph.rightInsert(new Node(3, sureste), graph.search(2));
        graph.rightInsert(new Node(4, sur), graph.search(3));
        graph.rightInsert(new Node(5, suroeste), graph.search(4));
        graph.rightInsert(new Node(6, oeste), graph.search(5));
        graph.rightInsert(new Node(7, noroeste), graph.search(6));
    }
    
    // Method in charge of drawing. Use a separate thread to make the drawings
    private void paint()
    {
    	if (env != null) {
	        new PaintProcess(env.envCanvas, doublePoint, graph);
	        try{ sleep(msSleep); } catch(Exception e) { System.out.println("   Error: " + e.getMessage()); }
    	}
    }
    
    // Method that update the SOM
    private void updateSOM()
    {
        int upGanadora = 0;
        int pos = (int)(Math.random()*doublePoint.length);
        int upCache = searchCache(pos);
        
        if (upCache != -1) {
            upGanadora = upCache;
        }
        else
        {
            double distUPGanadora = Double.MAX_VALUE;
            double distUP = 0.0d;
            Node node = graph.getLastNode();
            
            // Search for the node closest to that impulse
            do
            {
                distUP = Math.sqrt( Math.pow( doublePoint[pos].x-node.x ,2) + Math.pow( doublePoint[pos].y-node.y ,2) );
                if (distUP < distUPGanadora)
                {
                    distUPGanadora = distUP;  
                    upGanadora = node.keyName;
                }
                node = node.next;
            }
            while (node != graph.getLastNode());
            cache[pos] = upGanadora;
        }
        
        // Move the winning node and give it its prize
        graph.search(upGanadora).victories++;
        closeupPhase(upGanadora, pos);
    }
    
    // Method of the network nodes insertion
    private void addNodes()
    {   
        Node ini = graph.getLastNode();
        Node node = null;
        int vicGanadora = 0;
        do
        {
            if(ini.victories > vicGanadora)
            {
                vicGanadora = ini.victories;
                node = ini;
            }
            ini = ini.next;
        }
        while (ini != graph.getLastNode());
        
        node.victories = 0;
        
        // A new node is inserted to the right
        double x = (node.x + node.next.x) / 2;
        double y = (node.y + node.next.y) / 2;
        graph.rightInsert(new Node(graph.getNumNodes()+1, 0, x, y), node);
        
        // A new node is inserted to the left
        x = (node.x + node.previous.x) / 2;
        y = (node.y + node.previous.y) / 2;
        graph.leftInsert(new Node(graph.getNumNodes()+1, 0, x, y), node);
    }
    
    // This method ends the TSP. Check which stimuli need a new node
    private void finish()
    {
    	// Local variables
        double distUP = 0.0d;
        double dist = 0.0d;
        int key = 0;
        Node node = null;
        
        for (int i = 0; i < doublePoint.length; i++)
        {
            if(cache[i] != -1  && !graph.search(cache[i]).assigned) {
            	key = cache[i];
            }
            else
            {
                node = graph.getLastNode();
                distUP = Double.MAX_VALUE;
                do
                {
                    if (!node.assigned)
                    {
                        dist = Math.sqrt( Math.pow( Math.abs(doublePoint[i].x-node.x) ,2) + Math.pow( Math.abs(doublePoint[i].y-node.y) ,2) );
                        if (dist < distUP)
                        {
                            distUP = dist;
                            key = node.keyName;
                        }
                    }
                    node = node.next;
                }
                while (node != graph.getLastNode());
            }
            
            // Update
            node = graph.search(key);
            node.assigned = true;
            node.x = doublePoint[i].x;
            node.y = doublePoint[i].y;
        }
        removeIsolatedNodes();
    }
    
    // Method that eliminate isolated nodes
    private void removeIsolatedNodes()
    {
        Node node = graph.getLastNode();
        Node aux = null;
        int g = graph.getNumNodes();
        
        for (int i = 0; i <= g; i++)
        {
            aux = node.next;
            if(!node.assigned)
                graph.deleteNode(node);
            node = aux;
        }
        
    }
    
    // This method searches the cache for a stimulus
    private int searchCache(int stimulus)
    {
        if (cache[stimulus] != -1)
        {
            int up = cache[stimulus];
            cache[stimulus] = -1; 
            return up;
        }
        return -1;
    }

    // This method brings the winning node and its neighbors closer to the stimulus.
    private void closeupPhase(int n, int pos)
    {
        Node node = graph.search(n);
        
        // Update target node position
        node.x += LAMBDA * (doublePoint[pos].x - node.x);
        node.y += LAMBDA * (doublePoint[pos].y - node.y);
        
        // Update right neighbor node position
        node.previous.x += 0.4 * LAMBDA * (doublePoint[pos].x - node.previous.x);
        node.previous.y += 0.4 * LAMBDA * (doublePoint[pos].y - node.previous.y);
        
        // Update left neighbor node position
        node.next.x += 0.4 * LAMBDA * (doublePoint[pos].x - node.next.x);
        node.next.y += 0.4 * LAMBDA * (doublePoint[pos].y - node.next.y);
    }
    
}
